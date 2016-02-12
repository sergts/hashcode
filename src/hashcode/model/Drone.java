package hashcode.model;

import hashcode.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Drone {

    public static Drone[] drones;

    static public int MAX_WEIGHT;

    public int id;
    public Point position;
    public int currentWarehouse;
    public Map<Integer, Integer> products = new HashMap<>();
    public Map<Integer, Integer> currentProducts = new HashMap<Integer, Integer>();
    public int freeSpace = MAX_WEIGHT;
    public int TTL = Main.TURNS;

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
        freeSpace += ProductType.productTypes[productId].weight; }
    public boolean Load(Warehouse warehouse, int productId, int quantity){
        int qty = quantity;
        int totalWeight = ProductType.productTypes[productId].weight * quantity;
        int productQtyInWh = 0;
        while (quantity > 0) {
            productQtyInWh = warehouse.products[productId];
            if (productQtyInWh >= quantity) {
                productQtyInWh = quantity;
                warehouse.products[productId] -= quantity;
                quantity = 0;
            } else {
                if (productQtyInWh > 0) {
                    quantity -= productQtyInWh;
                    int time = position.distanceTo(warehouse.position) + 1;
                    position = warehouse.position;
                    TTL -= time;
                    if (TTL < 0) {
                        return false;
                    }
                    warehouse.products[productId] = 0;
                    Print(id, "L", warehouse.id, productId, productQtyInWh);
                    //return false;
                }
                if (Warehouse.warehouses.length == warehouse.vid + 1) {
                    warehouse = Warehouse.warehouses[0];
                } else {
                    warehouse = Warehouse.warehouses[warehouse.vid + 1];
                }
            }
        }
        if (totalWeight > freeSpace){
            return false;
        }
        int time = position.distanceTo(warehouse.position) + 1;
        position = warehouse.position;
        TTL -= time;
        if (TTL < 0){
            return false;
        }
        Print(id, "L", warehouse.id, productId, productQtyInWh);
        freeSpace -= totalWeight;
        currentProducts.put(productId, currentProducts.getOrDefault(productId, 0) + qty);

        return true;
    }

    public void Print(int a, String b, int c, int d, int e){
        Main.sb.append("\n" + a + " " + b + " " + c + " " + d + " " + e);
        Main.CommandCount++;
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
        freeSpace -= ProductType.productTypes[id].weight * canTake;    }
    public boolean DeliverAll(Warehouse firstWarehouse, Order order) {
        int time = position.distanceTo(order.point);
        position = order.point;
        TTL -= time;
        if (TTL < 0){
            return false;
        }
        Set<Integer> integers = currentProducts.keySet();
        int[] values = new int[currentProducts.keySet().size()];
        int varrr = 0;
        for (Integer productId : integers){
            values[varrr] = productId;
            varrr++;
        }
        for(int product : values){
            Integer productQty = currentProducts.get(product);
            if (!Deliver(order, product, productQty)){
                 return false;
            }
            freeSpace += ProductType.productTypes[product].weight * productQty;
            if (currentProducts.get(product) == productQty){
                currentProducts.remove(product);
            }                                   else {
                currentProducts.put(product, currentProducts.get(product) - productQty);
            }
        }
        return true;
    }

    public boolean Deliver(Order order, int product, Integer integer){
        TTL--;
        if (TTL < 0) {
            return false;
        }
        Print(id, "D", order.id, product, integer);
        return true;
    }

    public void UnLoadAll(Warehouse firstWarehouse) {
        for(int productId : currentProducts.keySet()){
            TTL--;
            Print(id, "U", firstWarehouse.id, productId, currentProducts.get(productId));
        }
        currentProducts = new HashMap<Integer, Integer>();
    }
}
