package com.junzhecai.strategy.business.design;

import java.math.BigDecimal;

public interface ICouponDiscount<T> {
    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);
}
