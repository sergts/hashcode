package model;

import java.util.Arrays;

public class Warehouse {

    public static Warehouse[] warehouses;

    public int id;
    public Point position;
    public int[] products = new int[ProductType.PRODUCT_TYPE_COUNT];

    public Warehouse(int id, Point position) {
        this.id = id;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", position=" + position +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
