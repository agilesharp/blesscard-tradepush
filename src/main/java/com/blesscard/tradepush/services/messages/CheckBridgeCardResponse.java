package com.blesscard.tradepush.services.messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckBridgeCardResponse {
    private boolean isSuccess;
    private String content;
}
