package model.command;

/**
 * @author Sergei Tsimbalist
 */
public class Deliver extends Command {

  public int orderId;
  public int productType;
  public int productAmount;

  public Deliver(int droneId, int orderId, int productType, int productAmount) {
    super(droneId);
    this.orderId = orderId;
    this.productType = productType;
    this.productAmount = productAmount;
  }
}
