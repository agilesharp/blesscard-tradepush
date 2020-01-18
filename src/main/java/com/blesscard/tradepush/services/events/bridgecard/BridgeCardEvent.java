package com.blesscard.tradepush.services.events.bridgecard;

import com.blesscard.tradepush.models.tradepay.YZ_FullOrderInfo;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class BridgeCardEvent extends ApplicationEvent {
    public BridgeCardEvent(Object source, YZ_FullOrderInfo fullOrderInfo,
                           BridgeCardStatus status) {
        super(source);
        this.fullOrderInfo = fullOrderInfo;
        this.status = status;
    }

    public BridgeCardEvent(Object source, YZ_FullOrderInfo fullOrderInfo,
                           String content,
                           BridgeCardStatus status) {
        super(source);
        this.fullOrderInfo = fullOrderInfo;
        this.status = status;
        this.content = content;
    }

    private YZ_FullOrderInfo fullOrderInfo;
    private BridgeCardStatus status;
    private String content;
}
