package com.junzhecai.observer.business.simple;

import com.junzhecai.observer.business.design.MinibusTargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LotteryServiceImpl implements LotteryService {

    private static final Logger log = LoggerFactory.getLogger(LotteryServiceImpl.class);

    private final MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    public LotteryResult doDraw(String uId) {
        //摇号
        String lottery = minibusTargetService.lottery(uId);
        //发短信
        log.info("给用户 {} 发送短信 {}", uId, lottery);
        //发MQ消息
        log.info("记录用户 {} 的摇号结果 {}", uId, lottery);
        return new LotteryResult(uId, lottery, new Date());
    }
}
