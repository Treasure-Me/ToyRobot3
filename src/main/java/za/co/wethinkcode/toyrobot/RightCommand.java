package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        boolean turn = true;
        if (target.setCurrentDirection(turn)){
            target.addInstruction(getName());
            target.setStatus("Turned right.");
        };
        return true;
    }

    public RightCommand() {
        super("right");
    }
}
