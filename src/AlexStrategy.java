/*
    User: ShvedA
    Date: 11.02.2016
    Time: 21:08
    repo
*/

import hashcode.Main;
import hashcode.model.Order;
import hashcode.model.ProductType;
import hashcode.model.Warehouse;
import hashcode.strategy.Strategy;

import java.util.Arrays;
import java.util.Comparator;

public class AlexStrategy extends Strategy {

    public void countForAllOrders(){
        for (Order order : Order.orders) {
            int distance = order.point.distanceTo(Warehouse.warehouses[0].position);
            int overalQty = 0;
            for (int productId : order.products.keySet()){
                int quantity = Main.TURNS / ProductType.productTypes[productId].weight;
                overalQty += quantity;
            }
            order.time = distance * overalQty;
        }
        Arrays.sort(Order.orders, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.time - o2.time;
            }
        });
    }

    @Override
    public void run() {
        out.print("AlexStrategy started");
    }

}
