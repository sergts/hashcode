package hashcode;

import hashcode.io.InputReader;
import hashcode.model.*;
import hashcode.strategy.*;

import java.io.*;
import java.util.*;


// hashcode2016@google.com
public class Main implements Runnable {
    public InputReader in;
    public PrintWriter out;

    public static int TURNS;
    public static int ROWS;
    public static int COLS;

    public void solve() throws Exception {
        // solution goes here
        ROWS = in.nextInt();
        COLS = in.nextInt();

        int dronesCount = in.nextInt();

        TURNS = in.nextInt();
        Drone.MAX_WEIGHT = in.nextInt();

        int productTypeCount = in.nextInt();
        ProductType.PRODUCT_TYPE_COUNT = productTypeCount;
        ProductType[] productTypes = new ProductType[productTypeCount];
        for (int i = 0; i < productTypeCount; i++) {
            productTypes[i] = new ProductType(i, in.nextInt());
        }
        ProductType.productTypes = productTypes;

        int warehouseCount = in.nextInt();
        Warehouse[] warehouses = new Warehouse[warehouseCount];
        for (int i = 0; i < warehouseCount; i++) {
            warehouses[i] = new Warehouse(i, new Point(in.nextInt(), in.nextInt()));
            for (int j = 0; j < ProductType.PRODUCT_TYPE_COUNT; j++) {
                warehouses[i].products[j] = in.nextInt();
            }
        }
        Warehouse.warehouses = warehouses;

        Drone[] drones = new Drone[dronesCount];
        for (int i = 0; i < dronesCount; i++) {
            drones[i] = new Drone(i, new Point(warehouses[0].position));
        }
        Drone.drones = drones;


        int orderCount = in.nextInt();
        Order[] orders = new Order[orderCount];
        for (int i = 0; i < orderCount; i++) {
            orders[i] = new Order(i, new Point(in.nextInt(), in.nextInt()));
            int items = in.nextInt();
            for (int j = 0; j < items; j++) {
                orders[i].addProduct(in.nextInt());
            }
        }
        Order.orders = orders;

        new EduardStrategy().run();
    }



    public void run() {
        try {
            String filename = "mother_of_all_warehouses";
            in = new InputReader(new FileInputStream(filename + ".in"));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename + ".out"))));
            Strategy.out = out;
            Locale.setDefault(Locale.US);
                solve();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(7);
        }
    }

    static int abs(int x) {
        return x < 0 ? -x : x;
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

    static int min(int a, int b) {
        return a < b ? a : b;
    }

    static long abs(long x) {
        return x < 0 ? -x : x;
    }

    static long max(long a, long b) {
        return a > b ? a : b;
    }

    static long min(long a, long b) {
        return a < b ? a : b;
    }

    public static void main(String args[]) {
        new Thread(null, new Main(), "Main", 1 << 28).start();
    }

    static boolean OJ = System.getProperty("ONLINE_JUDGE") != null;

    public void console(Object... objects) {
        if (!OJ) {
            out.println(Arrays.deepToString(objects));
            out.flush();
        }
    }
}