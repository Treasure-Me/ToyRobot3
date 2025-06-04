package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;

public class ReplayCommand extends Command {

    @Override
    public boolean execute(Robot target) {
        String argument1 = getArgument();
        String argument2 = getArgument2();
        ArrayList<String> instructionList = target.getInstructionList();
        int firstNum = 0;
        int secondNum = 0;
        int lastCommand = 0;
        int instructionSize = instructionList.size();

        if ("".equals(argument1)) {
            // Original replay all logic
            for (int i = 0; i < instructionSize; i++) {
                Command command = Command.create(instructionList.get(i));
                boolean shouldContinue = target.handleCommand(command);
                if (!shouldContinue) break;
                System.out.println(target.toString());
            }
            target.setStatus("replayed " + instructionSize + " commands.");
        }
        else if (argument1.equals("reversed")) {
            if ("".equals(argument2)) {
                // Original reversed all logic
                for (int i = instructionSize - 1; i >= 0; i--) {
                    Command command = Command.create(instructionList.get(i));
                    boolean shouldContinue = target.handleCommand(command);
                    if (!shouldContinue) break;
                    System.out.println(target.toString());
                }
                target.setStatus("replayed " + instructionSize + " commands.");
            }
            else {
                try {
                    // Handle reversed with number (replay reversed N)
                    lastCommand = Integer.parseInt(argument2);
                    int fromCommand = Math.max(0, instructionSize - lastCommand);
                    for (int i = instructionSize - 1; i >= fromCommand; i--) {
                        Command command = Command.create(instructionList.get(i));
                        boolean shouldContinue = target.handleCommand(command);
                        if (!shouldContinue) break;
                        System.out.println(target.toString());
                    }
                    target.setStatus("replayed " + lastCommand + " commands.");
                }
                catch (NumberFormatException e1) {
                    try {
                        // Handle reversed with range (replay reversed M-N)
                        String[] rangeParts = argument2.split("-");
                        firstNum = Integer.parseInt(rangeParts[0]);
                        secondNum = Integer.parseInt(rangeParts[1]);
                        int startIdx = Math.max(0, instructionSize - firstNum);
                        int endIdx = Math.max(0, instructionSize - secondNum);

                        for (int i = endIdx-1; i >= startIdx; i--) {
                            Command command = Command.create(instructionList.get(i));
                            boolean shouldContinue = target.handleCommand(command);
                            if (!shouldContinue) break;
                            System.out.println(target.toString());
                        }
                        target.setStatus("replayed " + (firstNum - secondNum) + " commands.");
                    }
                    catch (Exception e2) {
                        throw new IllegalArgumentException("Invalid replay arguments: " + argument2);
                    }
                }
            }
        }
        else {
            // Your original non-reversed number/range handling
            try {
                lastCommand = Integer.parseInt(argument1);
                int fromCommand = instructionSize - lastCommand;
                for (int i = fromCommand; i < instructionSize; i++) {
                    Command command = Command.create(instructionList.get(i));
                    boolean shouldContinue = target.handleCommand(command);
                    if (!shouldContinue) break;
                    System.out.println(target.toString());
                }
                target.setStatus("replayed " + lastCommand + " commands.");
            }
            catch (IllegalArgumentException e) {
                String[] listSteps = argument1.split("-");
                firstNum = Integer.parseInt(listSteps[0]);
                secondNum = Integer.parseInt(listSteps[1]);
                int firstCommand = instructionSize - firstNum;
                int endCommand = instructionSize - secondNum;
                for (int i = firstCommand; i < endCommand; i++) {
                    Command command = Command.create(instructionList.get(i));
                    boolean shouldContinue = target.handleCommand(command);
                    if (!shouldContinue) break;
                    System.out.println(target.toString());
                }
                target.setStatus("replayed " + (firstNum - secondNum) + " commands.");
            }
        }
        return true;
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
    }

    public ReplayCommand(String arg1, String arg2) {
        super("replay", arg1, arg2);
    }
}