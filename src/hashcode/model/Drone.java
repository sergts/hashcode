package hashcode.model;

import java.util.HashMap;
import java.util.Map;

public class Drone {

    public static Drone[] drones;

    static public int MAX_WEIGHT;

    public int id;
    public Point position;
    public int currentWarehouse;
    public Map<Integer, Integer> products = new HashMap<>();
    public int freeSpace = MAX_WEIGHT;

    public Drone(int id, Point point) {
        this.id = id;
        this.position = point;
    }


    public boolean addProduct(int productId) {
        if (ProductType.productTypes[productId].weight > freeSpace)
            return false;

        products.put(productId, products.getOrDefault(productId, 0) + 1);
        freeSpace -= ProductType.productTypes[productId].weight;
        return true;
    }

    public void removeProduct(int productId) {
        products.put(productId, products.get(productId) - 1);
        freeSpace += ProductType.productTypes[productId].weight;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", position=" + position +
                ", products=" + products +
                ", freeSpace=" + freeSpace +
                '}';
    }

    public void addProduct(int id, int canTake) {
        products.put(id, products.getOrDefault(id, 0) + canTake);
        freeSpace -= ProductType.productTypes[id].weight * canTake;
    }
}
