package com.junzhecai.observer.business.design;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ApiTest {

    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test() {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.draw("1234567890");
        log.info("测试结果：{}", JSON.toJSONString(result));
    }
}
