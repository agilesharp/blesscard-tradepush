/**
 * Copyright 2020 bejson.com
 */
package com.blesscard.tradepush.models.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class YZ_PurchaseRightList {
    private List<String> ump_tags;
    private List<String> ump_tags_text;
    private List<String> ump_level_text;
    private List<String> ump_levels;
}