/**
  * Copyright 2020 bejson.com 
  */
package com.blesscard.tradepush.models.goods;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class YZ_VirtualExt {
    private String instructions;
    private int validity_type;
    private boolean update_we_chat_bag;
    private Date item_validity_start;
    private int operate_version;
    private String we_chat_tpl_id;
    private String card_title;
    private int effective_type;
    private String card_service_tel_code;
    private boolean holidays_available;
    private String use_address;
    private String card_color;
    private int effective_delay_hours;
    private Date item_validity_end;
    private String card_service_tel;
    private String item_validity_day;
}