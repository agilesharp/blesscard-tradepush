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
public class YZ_TradePayInfo {
    private YZ_FullOrderInfo full_order_info;
    private List<YZ_DeliveryOrder> delivery_order;
    private YZ_OrderPromotion order_promotion;
    private List<YZ_RefundOrder> refund_order;
}