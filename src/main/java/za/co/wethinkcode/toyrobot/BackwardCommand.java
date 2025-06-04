package za.co.wethinkcode.toyrobot;

public class BackwardCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(-1*nrSteps)){
            target.addInstruction(getName()+" "+getArgument());
            target.setStatus("Moved back by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }

    public BackwardCommand(String argument) {
        super("back", argument);
    }
}
