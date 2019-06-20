package com.crayu.discount

import com.crayu.model.SimpleProduct
import spock.lang.Specification

import java.util.stream.Collectors

class DiscountDivisionTest extends Specification {

    def "discountDivision should divide discount according to implementation"() {
        setup:
        def products = productsPrices.stream()
                .map{p -> SimpleProduct.newProductWithNameAndPrice("P", BigDecimal.valueOf(p))}
                .collect(Collectors.toList())

        when:
        def result = discountDivisionStrategy.discountPerProduct(products, discount)

        then:
        result == expectedDiscountPerProduct

        where:
        discountDivisionStrategy           | productsPrices       | discount | expectedDiscountPerProduct
        new ProportionalDiscountDivision() | [1500.0, 500.0]      | 100.0    | [75.0, 25.0]
        new ProportionalDiscountDivision() | [500.0, 500.0]       | 100.0    | [50.0, 50.0]
        new ProportionalDiscountDivision() | [10.0, 90.0]         | 100.0    | [10.0, 90.0]
        new ProportionalDiscountDivision() | [1500.0, 500.0]      | 0.0      | [0.0, 0.0]
        new ProportionalDiscountDivision() | [1000.0, 500.0, 500] | 100.0    | [50.0, 25.0, 25.0]
        new ProportionalDiscountDivision() | []                   | 100.0    | []
        new ProportionalDiscountDivision() | [1500.0, 500.0]      | 2000.0   | [1500.0, 500.0]
        new ProportionalDiscountDivision() | [1500.0, 500.0]      | 5000.0   | [1500.0, 500.0]
        new ProportionalDiscountDivision() | [10.0, 10.0]         | 5.0      | [2.5, 2.5]

    }
}
