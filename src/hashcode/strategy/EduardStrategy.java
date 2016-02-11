package hashcode.strategy;

import hashcode.Main;
import hashcode.model.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class EduardStrategy extends Strategy {

    Point start = Warehouse.warehouses[0].position;

    int numberOfCommands;

    private Comparator<Order> orderComparator = new Comparator<Order>() {

        @Override
        public int compare(Order a, Order b) {
            return Long.compare(a.points, b.points);
        }
    };

    @Override
    public void run() {

        Order[] orders = Order.orders;
        for (Order o : orders) {
            o.points = start.distanceTo(o.point) * (int) (Math.ceil(o.weight * 1.0 / Drone.MAX_WEIGHT));
        }
        Arrays.sort(orders, orderComparator);
        Arrays.sort(Warehouse.warehouses, (a,b) -> Integer.compare(start.distanceTo(a.position), start.distanceTo(b.position)));

        int[] timeSpent = new int[Drone.drones.length];
        int[] toWarehouse = new int[Drone.drones.length];
        Main.TURNS /= 2;
        boolean delivery = true;
        main:
        while (delivery) {
            delivery = false;

            for (Drone drone : Drone.drones) {

                Order order = getFirstUnfilledOrder(orders);
                if (order == null)
                    break main;

                int travelTime = order.point.distanceTo(Warehouse.warehouses[drone.currentWarehouse].position);
                timeSpent[drone.id] += travelTime + toWarehouse[drone.id];
                toWarehouse[drone.id] = travelTime;

                if (timeSpent[drone.id] + toWarehouse[drone.id] > Main.TURNS) {
                    continue;
                }

                for (Map.Entry<Integer, Integer> entry : order.products.entrySet()) {
                    ProductType productType = ProductType.productTypes[entry.getKey()];
                    int count = entry.getValue();
                    int canTake = Math.min(drone.freeSpace / productType.weight, count);
                    canTake = Math.min(canTake, Warehouse.warehouses[drone.currentWarehouse].products[productType.id]);
                    if (canTake > 0 && timeSpent[drone.id] + 2 <= Main.TURNS) {
                        order.removeProduct(productType.id, canTake);
                        drone.addProduct(productType.id, canTake);
                        Warehouse.warehouses[drone.currentWarehouse].products[productType.id] -= canTake;
                        timeSpent[drone.id] += 2;
                        load(drone.id, 0, productType.id, canTake);
                    }
                }

                if (drone.products.size() > 0)
                    delivery = true;
                else {
                    drone.currentWarehouse++;
                    drone.currentWarehouse %= Warehouse.warehouses.length;
                    delivery = true;
                }


                for (Map.Entry<Integer, Integer> entry : drone.products.entrySet()) {
                    deliver(drone.id, order.id, entry.getKey(), entry.getValue());
                }

                drone.products.clear();
                drone.freeSpace = Drone.MAX_WEIGHT;
            }
        }

        out.println(numberOfCommands);
        out.print(sb.toString());
    }

    private Order getFirstUnfilledOrder(Order[] orders) {
        for (Order order : orders)
            if (order.weight > 0)
                return order;

        return null;
    }

    StringBuilder sb = new StringBuilder();

    private void load(int droneId, int warehouseId, int productTypeId, int numberOfItems) {
        numberOfCommands++;
        sb.append(String.format("%d L %d %d %d\n", droneId, warehouseId, productTypeId, numberOfItems));
    }

    private void deliver(int droneId, int orderId, int productTypeId, int numberOfItems) {
        numberOfCommands++;
        sb.append(String.format("%d D %d %d %d\n", droneId, orderId, productTypeId, numberOfItems));
    }

}
