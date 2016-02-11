package hashcode.model;

public class Point {

    int row, col;

    public Point(){}

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point(Point position) {
        this.row = position.row;
        this.col = position.col;
    }

    public static int distance(Point a, Point b) {
        double diffRow = a.row - b.row;
        double diffCol = a.col - b.col;
        return (int) StrictMath.round(StrictMath.sqrt(diffRow * diffRow + diffCol * diffCol));
    }

    public int distanceTo(Point x) {
        return distance(this, x);
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return col == point.col;

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}
