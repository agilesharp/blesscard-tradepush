package com.blesscard.tradepush.services.events.bridgecard;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.blesscard.tradepush.infrastructures.rocketmq.RocketMqConfiguration;
import com.blesscard.tradepush.models.bridgecard.FL_BridgeCards;
import com.blesscard.tradepush.models.bridgecard.FL_CardItem;
import com.blesscard.tradepush.models.tradepay.YZ_BuyerInfo;
import com.blesscard.tradepush.models.tradepay.YZ_FullOrderInfo;
import com.blesscard.tradepush.models.tradepay.YZ_Orders;
import com.blesscard.tradepush.services.BridgeCardService;
import com.blesscard.tradepush.services.NotifyService;
import com.blesscard.tradepush.services.YouZanService;
import com.blesscard.tradepush.services.messages.CheckBridgeCardRequest;
import com.blesscard.tradepush.services.messages.RefundTradeRequest;
import com.blesscard.tradepush.services.messages.SendMqMsgRequest;
import com.blesscard.tradepush.services.messages.SendSmsMsgRequest;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BridgeCardListener implements MessageListener {
    @Autowired
    private YouZanService youZanService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private RocketMqConfiguration rocketMqConfig;
    @Autowired
    private BridgeCardService bridgeCardService;

    @EventListener
    public void run(BridgeCardEvent event) {
        var fullOrderInfo = event.getFullOrderInfo();
        if (event.getStatus() == BridgeCardStatus.Failed) {
            var products = getBridgeCardOrders(fullOrderInfo);
            if (!CollectionUtils.isEmpty(products)) {
                var request = new RefundTradeRequest(fullOrderInfo.getOrder_info()
                        .getTid(), products);
                youZanService.refundTrade(request);
            }
        } else if (event.getStatus() == BridgeCardStatus.Success) {
            //下单成功 立刻放入延时队列 然后不断的查询结果
            sendDelayMsg(fullOrderInfo);
        }
    }

    public Action consume(Message message, ConsumeContext consumeContext) {
        YZ_FullOrderInfo orderInfo =
                JSON.parseObject(message.getBody(), YZ_FullOrderInfo.class);
        if (orderInfo != null) {
            var response = bridgeCardService.checkBridgeCard(new CheckBridgeCardRequest(orderInfo));
            if (response.isSuccess()) {
                var cards = JSON.parseObject(response.getContent(), FL_BridgeCards.class);
                if (cards != null &&
                        !CollectionUtils.isEmpty(cards.getCards())) {
                    sendCardMsg(orderInfo.getBuyer_info(), cards.getCards());
                }
                return Action.CommitMessage;
            } else {
                return Action.ReconsumeLater;
            }
        }
        return Action.ReconsumeLater;
    }

    private void sendDelayMsg(YZ_FullOrderInfo orderInfo) {
        Message msg = new Message( //
                // 您在控制台创建的 Topic
                rocketMqConfig.getBridgeCardTopic(),
                // Message Tag, 可理解为 Gmail 中的标签，对消息进行再归类，方便 Consumer 指定过滤条件在消息队列 RocketMQ 版服务器过滤
                rocketMqConfig.getBridgeCardTag(),
                // Message Body 可以是任何二进制形式的数据，消息队列 RocketMQ 版不做任何干预，需要 Producer 与 Consumer 协商好一致的序列化和反序列化方式
                JSON.toJSONBytes(orderInfo));
        // 设置代表消息的业务关键属性，请尽可能全局唯一。
        // 以方便您在无法正常收到消息情况下，可通过控制台查询消息并补发。
        // 注意：不设置也不会影响消息正常收发
        msg.setKey("ORDERID_100");
        // 延时消息，单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 3 秒后投递
        long delayTime = System.currentTimeMillis() + rocketMqConfig.getBridgeCardDelay();
        // 设置消息需要被投递的时间
        msg.setStartDeliverTime(delayTime);
        var request = new SendMqMsgRequest(msg);
        notifyService.sendMqMsg(request);
    }

    private List<YZ_Orders> getBridgeCardOrders(YZ_FullOrderInfo fullOrderInfo) {
        List<YZ_Orders> result = null;
        if (fullOrderInfo != null &&
                !CollectionUtils.isEmpty(fullOrderInfo.getOrders())) {
            result = fullOrderInfo.getOrders()
                    .stream().filter(p -> p.getItem_type() == 182)//182为虚拟商品
                    .collect(Collectors.toList());
        }
        return result;
    }

    private void sendCardMsg(YZ_BuyerInfo buyer, List<FL_CardItem> cards) {
        Map<String, String> message = new HashMap<>();
        for (var item : cards) {
            StringBuilder builder = new StringBuilder();
            var dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            builder.append("卡号：");
            builder.append(item.getCard_number());
            builder.append("卡密：");
            builder.append(item.getCard_pwd());
            builder.append("有效期：");
            builder.append(dateFormat.format(item.getCard_deadline()));
            message.put(buyer.getBuyer_phone(), builder.toString());
        }
        notifyService.sendSmsMsg(new SendSmsMsgRequest(message));
    }
}