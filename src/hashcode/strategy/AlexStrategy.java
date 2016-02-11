package hashcode.strategy;/*
    User: ShvedA
    Date: 11.02.2016
    Time: 21:08
    repo
*/

import hashcode.Strategy;
import hashcode.model.Order;
import hashcode.model.ProductType;
import hashcode.model.Warehouse;

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
    }

    @Override
    public void run() {
        out.print("AlexStrategy started");
    }
}
