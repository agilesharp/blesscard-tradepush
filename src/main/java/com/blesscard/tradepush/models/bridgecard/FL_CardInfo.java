package com.blesscard.tradepush.models.bridgecard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FL_CardInfo {
    private long product_id;
    private String product_name;
    private int face_value;
    private String product_type;
    private int purchase_price;
    private String template_id;
    private String stock_status;
    private String sales_status;
}