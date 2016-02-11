package model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    public static Order[] orders;

    public int id;
    public Point point;
    public Map<Integer, Integer> products = new HashMap<>();

    public Order(int id, Point point) {
        this.id = id;
        this.point = point;
    }

    public void addProduct(int productId) {
        products.put(productId, products.getOrDefault(productId, 0) + 1);
    }
}
