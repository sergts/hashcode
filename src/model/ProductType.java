package model;

public class ProductType {

    public static int PRODUCT_TYPE_COUNT;

    public static ProductType[] productTypes;

    public int id;

    public int weight;

    public ProductType() {}

    public ProductType(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", weight=" + weight +
                '}';
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
