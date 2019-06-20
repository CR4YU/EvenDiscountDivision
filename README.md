# DiscountDivision


Multi module Java application for counting discount for each product with given strategy.

## Modules description
Project contains 3 maven modules:
* **client** -  client which uses chosen implementation of `DiscountDivision`
* **discount** - contains strategies for counting discount per product
* **model** - simple Product class

## Current discount division strategies
* `ProportionalDiscountDivision` - distributes given discount proportionally among products eg.  
**Input**  
Products = [ {"P1", 1500.0}, {"P2", 500.0} ]  
Discount = 100.0  
**Output**  
Discount per product = [ 75.0, 25.0 ]  
**Note**! In case discount cannot be split proportionally remaining discount is added to last product.  
**Note!** In case discount is bigger than actual sum of products all products are discounted with their exact prices.


## Prerequisites
In order to run application you need:
* Installed **JDK** (at least 8)
* `JAVA_HOME` setup properly


## Running client
1. Put desired products inside *client/in/products.txt*
2. Inside root folder execute:
```shell
./mvnw clean install
cd client
../mvnw -q exec:java
```

## Running tests
From root folder:
```shell
./mvnw clean install test
```