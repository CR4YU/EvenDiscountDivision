package com.crayu.model;

import java.math.BigDecimal;

public class SimpleProduct extends Product {

    private SimpleProduct() {}

    public static Product newProductWithNameAndPrice(String name, BigDecimal price) {
        Product product = new SimpleProduct();
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
