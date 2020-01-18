package com.blesscard.tradepush.services.events.refundtrade;

import org.springframework.context.ApplicationEvent;

public class RefundTradeEvent extends ApplicationEvent {
    public RefundTradeEvent(Object source, String tid,
                            RefundTradeStatus status) {
        super(source);
        this.tid = tid;
        this.status = status;
    }

    private RefundTradeStatus status;
    private String tid;
}

