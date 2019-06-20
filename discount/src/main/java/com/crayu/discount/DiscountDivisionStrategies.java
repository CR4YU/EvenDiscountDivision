package com.crayu.discount;

public class DiscountDivisionStrategies {

    public static DiscountDivision proportionalDiscountDivision() {
        return new ProportionalDiscountDivision();
    }
}
