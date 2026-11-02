package com.junzhecai.strategy.business.design.event;

import com.junzhecai.strategy.business.design.ICouponDiscount;

import java.math.BigDecimal;

/**
 * n元优惠券
 */
public class NYGCouponDiscount implements ICouponDiscount<Double> {
    /**
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }
}
