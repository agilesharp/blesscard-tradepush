package com.blesscard.tradepush.infrastructures.rocketmq;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class RocketMqConfiguration {
    @Value("${spring.rocketmq.accessKey}")
    private String accessKey;
    @Value("${spring.rocketmq.secretKey}")
    private String secretKey;
    @Value("${spring.rocketmq.nameSrvAddr}")
    private String nameSrvAddr;
    @Value("${spring.rocketmq.topics.bridgeCard}")
    private String bridgeCardTopic;
    @Value("${spring.rocketmq.tags.bridgeCard}")
    private String bridgeCardTag;
    @Value("${spring.rocketmq.delays.bridgeCard}")
    private int bridgeCardDelay;
    @Value("${spring.rocketmq.groupId}")
    private String groupId;
}