package za.co.wethinkcode.toyrobot;

public class LeftCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        boolean turn = false;
        if (target.setCurrentDirection(turn)){
            target.addInstruction(getName());
            target.setStatus("Turned left.");
        };
        return true;
    }

    public LeftCommand() {
        super("left");
    }
}

