package com.junzhecai.strategy.business.design;

import com.junzhecai.strategy.business.design.event.MJCouponDiscount;
import com.junzhecai.strategy.business.design.event.NYGCouponDiscount;
import com.junzhecai.strategy.business.design.event.ZJCouponDiscount;
import com.junzhecai.strategy.business.design.event.ZKCouponDiscount;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    private static final Logger log = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_zj() {
        // 直减；100-10，商品100元
        Context<Double> context = new Context<Double>(new ZJCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(10D, new BigDecimal(100));
        log.info("测试结果：直减优惠后金额 {}", discountAmount);
    }

    @Test
    public void test_mj() {
        Context<Map<String, String>> context = new Context<>(new MJCouponDiscount());
        Map<String, String> mapReq = new HashMap<>();
        mapReq.put("x", "100");
        mapReq.put("n", "10");
        BigDecimal discountAmount = context.discountAmount(mapReq, new BigDecimal(100));
        log.info("测试结果：直减优惠后金额 {}", discountAmount);
    }

    @Test
    public void test_zk() {
        // 折扣9折，商品100元
        Context<Double> context = new Context<Double>(new ZKCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(0.9D, new BigDecimal(100));
        log.info("测试结果：折扣9折后金额 {}", discountAmount);
    }

    @Test
    public void test_nyg() {
        // n元购；100-10，商品100元
        Context<Double> context = new Context<Double>(new NYGCouponDiscount());
        BigDecimal discountAmount = context.discountAmount(90D, new BigDecimal(100));
        log.info("测试结果：n元购优惠后金额 {}", discountAmount);
    }
}
