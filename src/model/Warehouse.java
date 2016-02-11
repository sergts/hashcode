package model;

public class Warehouse {

    int id;
    Point position;
    ProductType[] productTypes = new ProductType[ProductType.PRODUCT_TYPE_COUNT];

    public Warehouse(int id, Point position) {
        this.id = id;
        this.position = position;
    }
}
