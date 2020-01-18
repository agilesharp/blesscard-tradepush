package com.blesscard.tradepush.services.events.bridgecard;

import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.blesscard.tradepush.infrastructures.rocketmq.RocketMqConfiguration;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class BridgeCardConsume implements
        FactoryBean<ConsumerBean>, InitializingBean, DisposableBean {
    @Autowired
    private RocketMqConfiguration rocketMqConfig;
    @Autowired
    private BridgeCardListener bridgeCardListener;

    private ConsumerBean consumerBean;

    public void buildConsumer() {
        consumerBean = new ConsumerBean();
        //配置文件
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, rocketMqConfig.getGroupId());
        //将消费者线程数固定为20个 20为默认值
        properties.setProperty(PropertyKeyConst.ConsumeThreadNums, "20");
        // AccessKeyId 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.AccessKey, rocketMqConfig.getAccessKey());
        // AccessKeySecret 阿里云身份验证，在阿里云服务器管理控制台创建
        properties.put(PropertyKeyConst.SecretKey, rocketMqConfig.getSecretKey());
        // 设置 TCP 接入域名，进入控制台的实例管理页面的“获取接入点信息”区域查看
        properties.put(PropertyKeyConst.NAMESRV_ADDR, rocketMqConfig.getNameSrvAddr());
        consumerBean.setProperties(properties);
        //订阅关系
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<>();
        Subscription subscription = new Subscription();
        subscription.setTopic(rocketMqConfig.getBridgeCardTopic());
        subscription.setExpression(rocketMqConfig.getBridgeCardTag());
        subscriptionTable.put(subscription, bridgeCardListener);
        //订阅多个topic如上面设置

        consumerBean.setSubscriptionTable(subscriptionTable);
        consumerBean.start();
    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public ConsumerBean getObject() throws Exception {
        return consumerBean;
    }

    @Override
    public Class<?> getObjectType() {
        return ConsumerBean.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildConsumer();
    }
}
