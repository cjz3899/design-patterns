package com.junzhecai.observer.business.design.event.listener;

import com.junzhecai.observer.business.design.LotteryResult;

public interface EventListener {

    void doEvent(LotteryResult result);
}
