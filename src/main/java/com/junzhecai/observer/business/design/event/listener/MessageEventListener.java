package com.junzhecai.observer.business.design.event.listener;

import com.junzhecai.observer.business.design.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEventListener implements EventListener {

    private static final Logger log = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        log.info("给用户 {} 发送短信 {}", result.getuId(), result.getMsg());
    }
}
