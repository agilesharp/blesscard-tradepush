/**
 * Copyright 2020 bejson.com
 */
package com.blesscard.tradepush.models.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class YZ_ItemImgs {
    private String thumbnail;
    private Date created;
    private String medium;
    private long id;
    private String url;
    private String combine;

}