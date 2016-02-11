package model;

import java.util.HashMap;
import java.util.Map;

public class Order {
  public int id;
  public Point point;
  public Map<Integer, Integer> products = new HashMap<>();
}
