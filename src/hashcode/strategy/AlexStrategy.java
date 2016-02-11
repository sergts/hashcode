package hashcode.strategy;/*
    User: ShvedA
    Date: 11.02.2016
    Time: 21:08
    repo
*/

import hashcode.Main;
import hashcode.model.Drone;
import hashcode.model.Order;
import hashcode.model.ProductType;
import hashcode.model.Warehouse;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

public class AlexStrategy extends Strategy {

    public static int dronToUse = 19;

    public Drone getNextDron(){
        dronToUse++;
        if (dronToUse > 19) {
            dronToUse = 0;
        }
        return Drone.drones[dronToUse];
    }

    public void countForAllOrders(){
        Warehouse firstWarehouse = Warehouse.warehouses[0];
        for (Order order : Order.orders) {
            int distance = order.point.distanceTo(firstWarehouse.position);
            int overalQty = 0;
            for (int productId : order.products.keySet()){
                int quantity = Main.TURNS / ProductType.productTypes[productId].weight;
                overalQty += quantity;
            }
            order.time = distance * overalQty;
/*
            int totalQty = 0;
            for (int productId : order.products.keySet()){
                totalQty += ProductType.productTypes[productId].weight * order.products.get(productId);
            }
            order.time = distance * (totalQty / Drone.MAX_WEIGHT) + (totalQty / Drone.MAX_WEIGHT) * 2;*/
        }
        Arrays.sort(Order.orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.time - o2.time;
            }
        });

        Drone drone = getNextDron();
        int weight;
        int nrOfProducts;
        for(Warehouse warehouse: Warehouse.warehouses){
        outer:  for (Order order: Order.orders) {
            Set<Integer> integers = order.products.keySet();

            int[] values = new int[order.products.keySet().size()];
            int varrr = 0;
            for (Integer productId : integers){
                  values[varrr] = productId;
                varrr++;
            }
            for (int productId : values) {
                weight = ProductType.productTypes[productId].weight;
                if (drone.freeSpace < weight) {
                    drone.DeliverAll(warehouse, order);
                    drone = getNextDron();
                }
                Integer productQty = order.products.get(productId);
                nrOfProducts = StrictMath.min(productQty, drone.freeSpace / weight);
                if (nrOfProducts == productQty) {
                    order.products.remove(productId);
                } else {
                    order.products.put(productId, productQty - nrOfProducts);
                }
                if (!drone.Load(warehouse, productId, nrOfProducts)) {
                    drone.UnLoadAll(warehouse);
                    drone.freeSpace = Drone.MAX_WEIGHT;
                    continue outer;
                }
            }
            drone.DeliverAll(warehouse, order);
            drone = getNextDron();
        }    }
        out.print(Main.CommandCount);
            out.print(Main.sb.toString());
    }

    @Override
    public void run() {
        //out.print("AlexStrategy started");
    }
}
