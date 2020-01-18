/**
 * Copyright 2020 bejson.com
 */
package com.blesscard.tradepush.models.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class YZ_ItemTags {
    private Date created;
    private String share_url;
    private String name;
    private String alias;
    private long id;
    private String tag_url;
    private int type;
    private int item_num;
    private String desc;
}