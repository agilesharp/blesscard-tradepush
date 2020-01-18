package com.blesscard.tradepush.infrastructures.bridgecard;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Getter
public class BridgeCardConfiguration {
    @Value("${spring.bridgecard.fulu.appKey}")
    private String appKey;
    @Value("${spring.bridgecard.fulu.appSecret}")
    private String appSecret;
    @Value("${spring.bridgecard.fulu.apiUrl}")
    private String apiUrl;
}