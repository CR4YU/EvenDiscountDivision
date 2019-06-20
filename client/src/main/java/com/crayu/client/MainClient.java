package com.crayu.client;

import com.crayu.discount.DiscountDivision;
import com.crayu.discount.DiscountDivisionStrategies;
import com.crayu.model.Product;
import com.crayu.model.SimpleProduct;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainClient {

    static final String PRODUCTS_FILE = "in/products.txt";

    public static void main(String[] args) {
        List<Product> products = fromFile();
        BigDecimal discount = BigDecimal.valueOf(100);
        DiscountDivision discountDivision = DiscountDivisionStrategies.proportionalDiscountDivision();
        BigDecimal[] discountPerProduct = discountDivision.discountPerProduct(products, discount);

        System.out.println("Products: " + products);
        System.out.println("Discount: " + discount);
        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product " + products.get(i)+ " has discount " + discountPerProduct[i]);
        }
    }

    private static List<Product> fromFile() {
        List<Product> products;
        try (Stream<String> lines = Files.lines(Paths.get(PRODUCTS_FILE))){
            products = lines.map(l -> l.split(" "))
                    .map(l -> SimpleProduct.newProductWithNameAndPrice(l[0], new BigDecimal(l[1])))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Could not read file with products: " + PRODUCTS_FILE);
            return Collections.emptyList();
        }
        return products;
    }
}
