package com.blesscard.tradepush.services.events.refundtrade;

public enum RefundTradeStatus {
    Failed(-1), Success(1);

    public int getStatus() {
        return status;
    }

    private final int status;

    RefundTradeStatus(int status) {
        this.status = status;
    }
}
