package com.blesscard.tradepush.services.messages;

import com.aliyun.openservices.ons.api.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SendMqMsgRequest {
    private Message message;
}
