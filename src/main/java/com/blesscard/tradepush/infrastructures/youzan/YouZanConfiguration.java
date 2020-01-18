package com.blesscard.tradepush.infrastructures.youzan;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class YouZanConfiguration {
    @Value("${spring.oauth.youzan.grantId}")
    private String grantId;
    @Value("${spring.oauth.youzan.clientId}")
    private String clientId;
    @Value("${spring.oauth.youzan.clientSecret}")
    private String clientSecret;
}