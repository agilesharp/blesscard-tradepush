package com.blesscard.tradepush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class TradePushApplication  {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(TradePushApplication.class, args);
    }

}
