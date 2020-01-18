package com.blesscard.tradepush.services;

import com.blesscard.tradepush.infrastructures.youzan.YouZanConfiguration;
import com.blesscard.tradepush.services.events.refundtrade.RefundTradeEvent;
import com.blesscard.tradepush.services.events.refundtrade.RefundTradeStatus;
import com.blesscard.tradepush.services.messages.RefundTradeRequest;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.core.oauth.model.OAuthToken;
import com.youzan.cloud.open.sdk.core.oauth.token.TokenParameter;
import com.youzan.cloud.open.sdk.gen.v3_0_0.api.YouzanTradeRefundSellerActive;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanTradeRefundSellerActiveParams;
import com.youzan.cloud.open.sdk.gen.v3_0_0.model.YouzanTradeRefundSellerActiveResult;
import lombok.extern.log4j.Log4j2;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class YouZanService {
    @Autowired
    private YouZanConfiguration youzanConfig;
    @Autowired
    private ApplicationContext applicationContext;

    public void refundTrade(RefundTradeRequest request) {
        try {
            YouZanClient yzClient = new DefaultYZClient();
            TokenParameter tokenParameter = TokenParameter.self()
                    .clientId(youzanConfig.getClientId())
                    .clientSecret(youzanConfig.getClientSecret())
                    .grantId(youzanConfig.getGrantId())
                    .build();
            OAuthToken oAuthToken = yzClient.getOAuthToken(tokenParameter);
            Token token = new Token(oAuthToken.getAccessToken());
            YouzanTradeRefundSellerActive youzanTradeRefundSellerActive
                    = new YouzanTradeRefundSellerActive();
            //创建参数对象,并设置参数
            var params = new YouzanTradeRefundSellerActiveParams();
            params.setTid(request.getTid());
            youzanTradeRefundSellerActive.setAPIParams(params);

            var result = yzClient.invoke(youzanTradeRefundSellerActive, token,
                    YouzanTradeRefundSellerActiveResult.class);
            if (!result.getSuccess()) {
                applicationContext.publishEvent(new RefundTradeEvent(this,
                        request.getTid(),
                        RefundTradeStatus.Failed));
            } else {
                applicationContext.publishEvent(new RefundTradeEvent(this,
                        request.getTid(),
                        RefundTradeStatus.Success));
            }
        } catch (SDKException e) {
            log.error(this, e);
        }
    }
}
