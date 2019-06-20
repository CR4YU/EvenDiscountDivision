package com.crayu.discount;

import com.crayu.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountDivision {

    BigDecimal[] discountPerProduct(List<Product> products, BigDecimal discount);
}
