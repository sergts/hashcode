package model;

public class Order {
  public int id;
  public Point point;
  public int[] products = new int[ProductType.PRODUCT_TYPE_COUNT];
}
