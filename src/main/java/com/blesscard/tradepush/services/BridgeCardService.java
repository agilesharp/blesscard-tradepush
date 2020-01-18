package com.blesscard.tradepush.services;

import com.alibaba.fastjson.JSON;
import com.blesscard.tradepush.infrastructures.bridgecard.BridgeCardConfiguration;
import com.blesscard.tradepush.models.tradepay.YZ_FullOrderInfo;
import com.blesscard.tradepush.models.tradepay.YZ_OrderInfo;
import com.blesscard.tradepush.models.tradepay.YZ_Orders;
import com.blesscard.tradepush.models.tradepay.YZ_TradePayInfo;
import com.blesscard.tradepush.services.events.bridgecard.BridgeCardEvent;
import com.blesscard.tradepush.services.events.bridgecard.BridgeCardStatus;
import com.blesscard.tradepush.services.messages.BuyBridgeCardRequest;
import com.blesscard.tradepush.services.messages.CheckBridgeCardRequest;
import com.blesscard.tradepush.services.messages.CheckBridgeCardResponse;
import fulu.sup.open.api.core.MethodConst;
import fulu.sup.open.api.model.InputCardOrderDto;
import fulu.sup.open.api.model.InputOrderGetDto;
import fulu.sup.open.api.model.response.DefaultClientResponse;
import fulu.sup.open.api.sdk.DefaultOpenApiClient;
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BridgeCardService {
    @Autowired
    private BridgeCardConfiguration bridgeCardConfig;
    @Autowired
    private ApplicationContext applicationContext;

    public void buyBridgeCard(BuyBridgeCardRequest request) {
        if (StringUtils.isNotEmpty(request.getData())) {
            YZ_TradePayInfo payInfo = JSON.parseObject(request.getData(), YZ_TradePayInfo.class);

            if (payInfo != null) {
                var products = getBridgeCardOrders(payInfo.getFull_order_info());
                if (!CollectionUtils.isEmpty(products)) {
                    YZ_OrderInfo orderInfo = payInfo.getFull_order_info().getOrder_info();
                    InputCardOrderDto dto = new InputCardOrderDto();
                    dto.setCustomerOrderNo(orderInfo.getTid());
                    for (var item : products) {
                        dto.setBizContent(item.getOuter_sku_id(), item.getNum());
                    }

                    var openApiClient = new DefaultOpenApiClient(bridgeCardConfig.getApiUrl(),
                            bridgeCardConfig.getAppKey(), bridgeCardConfig.getAppSecret(),
                            MethodConst.OPEN_API_CARD_ORDER_ADD);
                    openApiClient.setBizObject(dto);
                    String result = openApiClient.excute();

                    if (StringUtils.isNotEmpty(result)) {
                        var response = JSON.parseObject(result, DefaultClientResponse.class);
                        if (response != null &&
                                response.getCode().equalsIgnoreCase("0")) {
                            applicationContext.publishEvent(new BridgeCardEvent(this,
                                    payInfo.getFull_order_info(),
                                    BridgeCardStatus.Success));
                        } else {
                            applicationContext.publishEvent(new BridgeCardEvent(this,
                                    payInfo.getFull_order_info(),
                                    BridgeCardStatus.Failed));
                        }
                    }
                }
            }
        }
    }

    public CheckBridgeCardResponse checkBridgeCard(CheckBridgeCardRequest request) {
        CheckBridgeCardResponse response = new CheckBridgeCardResponse();
        if (request.getOrderInfo() != null) {
            var openApiClient = new DefaultOpenApiClient(bridgeCardConfig.getApiUrl(),
                    bridgeCardConfig.getAppKey(), bridgeCardConfig.getAppSecret(),
                    MethodConst.OPEN_API_ORDER_GET);

            InputOrderGetDto dto = new InputOrderGetDto();
            dto.setCustomerOrderNo(request.getOrderInfo().getOrder_info().getTid());

            openApiClient.setBizObject(dto);
            String result = openApiClient.excute();
            if (StringUtils.isNotEmpty(result)) {
                var clientResponse = JSON.parseObject(result, DefaultClientResponse.class);
                if (clientResponse != null &&
                        clientResponse.getCode().equalsIgnoreCase("0")) {
                    response.setSuccess(true);
                    response.setContent(clientResponse.getResult());
                }
            }
        }
        return response;
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
}
