package com.blesscard.tradepush.services.messages;

import com.blesscard.tradepush.models.tradepay.YZ_FullOrderInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckBridgeCardRequest {
    private YZ_FullOrderInfo orderInfo;
}
