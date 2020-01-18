package com.blesscard.tradepush.services.events.refundtrade;

import com.blesscard.tradepush.services.YouZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class RefundTradeListener {
    @Autowired
    private YouZanService youZanService;

    @EventListener
    public void run(RefundTradeEvent event) {

    }
}