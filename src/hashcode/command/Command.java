package hashcode.command;

/**
 * @author Sergei Tsimbalist
 */
public abstract class Command {
  public int droneId;
  public Command(int droneId) {
    this.droneId = droneId;
  }
}
