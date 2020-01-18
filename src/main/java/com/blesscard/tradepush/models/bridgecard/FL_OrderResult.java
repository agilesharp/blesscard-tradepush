package com.blesscard.tradepush.models.bridgecard;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FL_OrderResult {
    private String order_id;
    private Date charge_finish_time;
    private String customer_order_no;
    private String order_status;
    private String recharge_description;
    private String product_id;
    private String price;
    private String buy_num;
    private String operator_serial_number;
    private String sign;
}
