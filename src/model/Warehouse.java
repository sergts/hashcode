package model;

public class Warehouse {

    public static Warehouse[] warehouses;

    public int id;
    public Point position;
    public int[] products = new int[ProductType.PRODUCT_TYPE_COUNT];

    public Warehouse(int id, Point position) {
        this.id = id;
        this.position = position;
    }
}
