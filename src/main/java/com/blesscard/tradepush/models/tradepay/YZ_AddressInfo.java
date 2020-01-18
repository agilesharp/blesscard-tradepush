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
public class YZ_AddressInfo {
    private String receiver_name;
    private String delivery_address;
    private String address_extra;
    private String delivery_district;
    private Date delivery_end_time;
    private String delivery_postal_code;
    private String self_fetch_info;
    private String delivery_province;
    private Date delivery_start_time;
    private String receiver_tel;
    private String delivery_city;
}