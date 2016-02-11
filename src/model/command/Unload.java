package model.command;

/**
 * @author Sergei Tsimbalist
 */
public class Unload extends Command {

  public int warehouseId;
  public int productType;
  public int productAmount;


  public Unload(int droneId, int warehouseId, int productType, int productAmount){
    super(droneId);
    this.warehouseId = warehouseId;
    this.productType = productType;
    this.productAmount = productAmount;
  }
}
