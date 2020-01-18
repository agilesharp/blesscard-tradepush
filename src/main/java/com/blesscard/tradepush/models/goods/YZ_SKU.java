/**
 * Copyright 2020 bejson.com
 */
package com.blesscard.tradepush.models.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class YZ_SKU {
    private String sku_unique_code;
    private int quantity;
    private long item_id;
    private Date created;
    private String properties_name_json;
    private long sku_id;
    private String outer_id;
    private int sold_num;
    private String item_no;
    private int with_hold_quantity;
    private int price;
    private Date modified;
    private String one_item_multi_code;
    private int cost_price;
}