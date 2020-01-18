package com.blesscard.tradepush.services.messages;

import com.blesscard.tradepush.models.tradepay.YZ_Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RefundTradeRequest {
    private String tid;
    private List<YZ_Orders> orders;
}
