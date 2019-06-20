package com.crayu.model;

import java.math.BigDecimal;

public abstract class Product {

    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.doubleValue() < 0)
            throw new IllegalArgumentException("Price cannot be lower than 0");
        this.price = price;
    }


}
