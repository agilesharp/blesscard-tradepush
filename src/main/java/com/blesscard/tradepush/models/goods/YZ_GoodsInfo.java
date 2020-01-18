/**
  * Copyright 2020 bejson.com 
  */
package com.blesscard.tradepush.models.goods;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Date;


@Getter
@Setter
public class YZ_GoodsInfo {
    private YZ_GoodsTemplate template;
    private String auto_listing_time;
    private String detail_url;
    private List<YZ_SKU> skus;
    private int post_fee;
    private YZ_VirtualExt virtual_extend;
    private int buy_quota;
    private int item_type;
    private int num;
    private String title;
    private boolean join_level_discount;
    private String item_no;
    private long kdt_id;
    private boolean purchase_right;
    private long price;
    private List<String> sku_images;
    private YZ_PurchaseRightList purchase_right_list;
    private YZ_PresaleExt presale_extend;
    private String alias;
    private int post_type;
    private String one_item_multi_code;
    private String summary;
    private List<Long> tag_ids;
    private long quantity;
    private List<YZ_ItemTags> item_tags;
    private long item_id;
    private Date created;
    private List<YZ_ItemImgs> item_imgs;
    private YZ_FenXiao_Ext fenxiao_extend;
    private boolean is_listing;
    private int sold_num;
    private YZ_HotelExt hotel_extend;
    private YZ_DeliveryTemplate delivery_template_info;
    private String share_url;
    private String pic_thumb_url;
    private boolean is_lock;
    private String messages;
    private String sell_point;
    private String origin_price;
    private String pic_url;
    private String desc;
    private int cid;
}