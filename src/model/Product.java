package model;

public class Product {

    ProductType productType;

    int amount;

    public Product() {}

    public Product(ProductType productType, int amount) {
        this.productType = productType;
        this.amount = amount;
    }
}
