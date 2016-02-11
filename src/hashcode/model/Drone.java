package hashcode.model;

import hashcode.Point;

import java.util.HashMap;
import java.util.Map;

public class Drone {

    public static Drone[] drones;

    static public int MAX_WEIGHT;

    public int id;
    public Point position;
    public Map<Integer, Integer> currentProducts = new HashMap<>();
    public int freeSpace = MAX_WEIGHT;

    public Drone(int id, Point point) {
        this.id = id;
        this.position = point;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", position=" + position +
                ", currentProducts=" + currentProducts +
                ", freeSpace=" + freeSpace +
                '}';
    }
}
