package com.blesscard.tradepush.services;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.blesscard.tradepush.infrastructures.rocketmq.RocketMqConfiguration;
import com.blesscard.tradepush.services.messages.SendMqMsgRequest;
import com.blesscard.tradepush.services.messages.SendSmsMsgRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Log4j2
public class NotifyService {
    @Autowired
    private RocketMqConfiguration rocketMqConfig;

    public void sendMqMsg(SendMqMsgRequest request) {
        if (request.getMessage() != null) {
            Properties properties = new Properties();
            // AccessKeyId 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.AccessKey, rocketMqConfig.getAccessKey());
            // AccessKeySecret 阿里云身份验证，在阿里云服务器管理控制台创建
            properties.put(PropertyKeyConst.SecretKey, rocketMqConfig.getSecretKey());
            // 设置 TCP 接入域名，进入控制台的实例管理页面的“获取接入点信息”区域查看
            properties.put(PropertyKeyConst.NAMESRV_ADDR, rocketMqConfig.getNameSrvAddr());
            properties.setProperty(PropertyKeyConst.GROUP_ID, rocketMqConfig.getGroupId());
            Producer producer = ONSFactory.createProducer(properties);
            // 在发送消息前，必须调用 start 方法来启动 Producer，只需调用一次即可。
            producer.start();
            try {
                SendResult sendResult = producer.send(request.getMessage());
                // 同步发送消息，只要不抛异常就是成功
                if (sendResult != null) {
                }
            } catch (Exception e) {
            }
            // 在应用退出前，销毁 Producer 对象<br>
            // 注意：如果不销毁也没有问题
            producer.shutdown();
        }
    }

    public void sendSmsMsg(SendSmsMsgRequest request) {

    }
}
