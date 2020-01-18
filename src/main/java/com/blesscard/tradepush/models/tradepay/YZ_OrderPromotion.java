/**
  * Copyright 2019 bejson.com 
  */
package com.blesscard.tradepush.models.tradepay;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Auto-generated: 2019-12-29 15:25:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Getter
@Setter
public class YZ_OrderPromotion {

    private List<YZ_Item> item;
    private List<YZ_Order> order;
    private String adjust_fee;
    private String item_discount_fee;
    private String order_discount_fee;

}