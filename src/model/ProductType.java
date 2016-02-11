package model;

public class ProductType {

    int id;

    int weight;

    public ProductType() {}

    public ProductType(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductType that = (ProductType) o;

        if (id != that.id) return false;
        return weight == that.weight;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + weight;
        return result;
    }
}
