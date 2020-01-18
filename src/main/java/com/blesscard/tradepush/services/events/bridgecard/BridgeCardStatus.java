package com.blesscard.tradepush.services.events.bridgecard;

public enum BridgeCardStatus {
    Failed(-1), Success(1);

    public int getStatus() {
        return status;
    }

    private final int status;

    BridgeCardStatus(int status) {
        this.status = status;
    }
}
