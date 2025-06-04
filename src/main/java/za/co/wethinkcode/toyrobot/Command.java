package za.co.wethinkcode.toyrobot;

public abstract class Command {
    private final String name;
    private String argument;
    private String argument2;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
        this.argument2= "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
        this.argument2 = "";
    }
    public Command(String name, String argument, String argument1){
        this(name);
        this.argument = argument.trim();
        this.argument2 = argument1.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getArgument2() {
        return this.argument2;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        switch (args[0]){
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackwardCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "sprint":
                return new SprintCommand(args[1]);
            case "replay":
                try {
                    return new ReplayCommand(args [1], args[2]);
                }
                catch(ArrayIndexOutOfBoundsException e){
                    try {
                        return new ReplayCommand(args[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        return new ReplayCommand("");
                    }
                }
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}