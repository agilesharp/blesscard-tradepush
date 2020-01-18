/**
  * Copyright 2019 bejson.com 
  */
package com.blesscard.tradepush.models.tradepay;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Auto-generated: 2019-12-29 15:25:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Getter
@Setter
public class YZ_OrderInfo {
    private String status;
    private int type;
    private String tid;
    private String status_str;
    private int pay_type;
    private int close_type;
    private Date expired_time;
    private int express_type;
    private int refund_state;
    private int team_type;
    private Date consign_time;
    private Date update_time;
    private int offline_id;
    private Date created;
    private Date pay_time;
    private Date confirm_time;
    private boolean is_retail_order;
    private Date success_time;
    private YZ_OrderExt order_extra;
    private YZ_OrderTags order_tags;

}