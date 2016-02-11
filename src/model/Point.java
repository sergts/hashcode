package model;

public class Point {

    int x, y;

    public Point(){}

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Point a, Point b) {
        double diffX = a.x - b.x;
        double diffY = a.y - b.y;
        return StrictMath.sqrt(diffX * diffX + diffY * diffY);
    }

    public double distanceTo(Point x) {
        return distance(this, x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
