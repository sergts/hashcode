package model;

public class Order {
  public int id;
  public Point point;
  public ProductType[] products = new ProductType[ProductType.PRODUCT_TYPE_COUNT];
}
