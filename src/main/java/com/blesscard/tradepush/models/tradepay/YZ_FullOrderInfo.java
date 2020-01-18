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
public class YZ_FullOrderInfo {
    private List<YZ_Orders> orders;
    private YZ_OrderInfo order_info;
    private YZ_RemarkInfo remark_info;
    private YZ_AddressInfo address_info;
    private YZ_BuyerInfo buyer_info;
    private YZ_SourceInfo source_info;
    private YZ_PayInfo pay_info;
    private YZ_ChildInfo child_info;

}