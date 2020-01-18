/**
  * Copyright 2019 bejson.com 
  */
package com.blesscard.tradepush.models.bridgecard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2019-12-29 19:47:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Getter
@Setter
public class FL_BridgeCards {
    private String area;
    private int buy_num;
    private List<FL_CardItem> cards;
    private String charge_account;
    private Date create_time;
    private String customer_order_no;
    private Date finish_time;
    private String operator_serial_number;
    private String order_id;
    private double order_price;
    private String order_state;
    private int order_type;
    private long product_id;
    private String product_name;
    private String server;
    private String type;
}