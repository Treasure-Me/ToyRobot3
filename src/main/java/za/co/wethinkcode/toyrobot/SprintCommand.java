package za.co.wethinkcode.toyrobot;

public class SprintCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());
        while (nrSteps>0) {
            if (target.updatePosition(nrSteps)) {

                target.setStatus("Moved forward by " + nrSteps + " steps.");
                if (nrSteps == 1){
                    break;
                }
                System.out.println(target.toString());
            } else {
                target.setStatus("Sorry, I cannot go outside my safe zone.");
            }
            nrSteps -=1;
        }
        target.addInstruction(getName()+" "+getArgument());
        return true;
    }

    public SprintCommand(String argument) {
        super("sprint", argument);
    }
}

