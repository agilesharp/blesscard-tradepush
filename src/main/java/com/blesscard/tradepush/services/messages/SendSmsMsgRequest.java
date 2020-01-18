package com.blesscard.tradepush.services.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class SendSmsMsgRequest {
    private Map<String,String> message;
}
