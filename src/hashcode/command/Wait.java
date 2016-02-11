package hashcode.command;

/**
 * @author Sergei Tsimbalist
 */
public class Wait extends Command {

  public int turns;

  public Wait(int droneId, int turns) {
    super(droneId);
    this.turns = turns;
  }
}
