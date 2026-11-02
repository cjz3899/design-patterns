package com.junzhecai.strategy.business.simple;

public class CouponDiscountService {
    public double discountAmount(int type, double typeContent, double skuPrice, double typeExt) {
        //直减券
        if (1 == type) {
            return skuPrice - typeContent;
        }
        //满减券
        if (2 == type) {
            if (skuPrice < typeExt) {
                return skuPrice;
            }
            return skuPrice - typeContent;
        }
        //折扣券
        if (3 == type) {
            return skuPrice * typeContent;
        }
        //n元购券
        if (4 == type) {
            return typeContent;
        }
        return 0D;
    }
}
