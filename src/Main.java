
import io.InputReader;
import model.Drone;
import model.Point;
import model.ProductType;
import model.Warehouse;

import java.io.*;
import java.util.*;


// hashcode2016@google.com
public class Main implements Runnable {
    public InputReader in;
    public PrintWriter out;

    public void solve() throws Exception {
        // solution goes here
        int rows = in.nextInt();
        int cols = in.nextInt();

        int dronesCount = in.nextInt();
        int turns = in.nextInt();
        Drone.MAX_WEIGHT = in.nextInt();

        int productTypeCount = in.nextInt();
        ProductType[] productTypes = new ProductType[productTypeCount];
        for (int i = 0; i < productTypeCount; i++) {
            productTypes[i] = new ProductType(i, in.nextInt());
        }

        int warehouseCount = in.nextInt();
        Warehouse[] warehouses = new Warehouse[warehouseCount];
        for (int i = 0; i < warehouseCount; i++) {
            warehouses[i] = new Warehouse(i, new Point(in.nextInt(), in.nextInt()));

        }

    }



    public void run() {
        try {
            in = new InputReader(new FileInputStream("mother_of_all_warehouses.in"));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
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