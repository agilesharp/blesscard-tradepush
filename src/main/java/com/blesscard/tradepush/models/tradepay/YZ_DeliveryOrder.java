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
public class YZ_DeliveryOrder {
    private int pk_id;
    private int express_state;
    private int express_type;
    private List<YZ_Oids> oids;

}