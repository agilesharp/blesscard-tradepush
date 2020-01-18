package com.blesscard.tradepush.controller;

import com.alibaba.fastjson.JSONObject;
import com.blesscard.tradepush.services.BridgeCardService;
import com.blesscard.tradepush.services.messages.BuyBridgeCardRequest;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class BridgeCardController {
    @Autowired
    private BridgeCardService bridgeCardService;

    @RequestMapping(value = "/tradePayNotify", method = RequestMethod.POST)
    public Object tradePayNotify(@RequestBody String content,
                                 @RequestHeader("Event-Sign") String eventSign,
                                 @RequestHeader("Event-Type") String eventType,
                                 @RequestHeader("Client-Id") String clientId) {
        Object result = null;
        log.info("1. trade pay callback coming");
        try {
            if (StringUtils.isNotEmpty(content) &&
                    StringUtils.equalsIgnoreCase("trade_TradeBuyerPay", eventType)) {
                log.info(content);
                log.info("2. content has value");
                var request = new BuyBridgeCardRequest();
                request.setData(content);
                bridgeCardService.buyBridgeCard(request);
                result = buildSuccessResponse();
            }
        } catch (Exception e) {
            log.error("BridgeCardController", "tradePayNotify", e);
            result = buildFailedResponse();
        }
        return result;
    }

    private JSONObject buildSuccessResponse() {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        return res;
    }

    private JSONObject buildFailedResponse() {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "failed");
        return res;
    }
}
