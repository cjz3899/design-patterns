package com.junzhecai.observer.business.design.event.listener;

import com.junzhecai.observer.business.design.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQEventListener implements EventListener {

    private static final Logger log = LoggerFactory.getLogger(MQEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        log.info("记录用户 {} 的摇号结果 {}", result.getuId(), result.getMsg());
    }
}
