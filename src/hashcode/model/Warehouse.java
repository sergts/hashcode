package hashcode.model;

import java.util.Arrays;

public class Warehouse {

    public static Warehouse[] warehouses;

    public int id;
    public hashcode.model.Point position;
    public int[] products = new int[hashcode.model.ProductType.PRODUCT_TYPE_COUNT];

    public Warehouse(int id, hashcode.model.Point position) {
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
