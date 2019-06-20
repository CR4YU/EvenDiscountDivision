package com.crayu.discount;

import com.crayu.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProportionalDiscountDivision implements DiscountDivision {

    private static final int SCALE = 2;

    /**
     * This implementation splits the discount proportionally.
     * In case cannot split the remaining discount is added to last product.
     * In case discount >= products.prices.sum return products.prices
     *
     * @param products products
     * @param discount discount
     * @return discount per product
     */
    @Override
    public BigDecimal[] discountPerProduct(List<Product> products, BigDecimal discount) {
        if (discount.doubleValue() < 0)
            throw new IllegalArgumentException("Discount cannot be lower than 0");

        BigDecimal sum = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (discount.doubleValue() >= sum.doubleValue()) {
            return products.stream()
                    .map(Product::getPrice)
                    .toArray(BigDecimal[]::new);
        }
        BigDecimal[] discountPerProduct = new BigDecimal[products.size()];
        BigDecimal discountSum = BigDecimal.ZERO;

        for (int i = 0; i < products.size(); i++) {
            BigDecimal price = products.get(i).getPrice();
            discountPerProduct[i] =  price
                    .divide(sum, SCALE, RoundingMode.DOWN)
                    .multiply(discount)
                    .setScale(SCALE, RoundingMode.DOWN);
            discountSum = discountSum.add(discountPerProduct[i]);
        }

        discountPerProduct[discountPerProduct.length - 1] =
                discountPerProduct[discountPerProduct.length - 1].add(discount.subtract(discountSum));

        return discountPerProduct;
    }
}
