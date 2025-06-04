package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {
    private final Position TOP_LEFT = new Position(-200, 100);
    private final Position BOTTOM_RIGHT = new Position(100, -200);

    public static final Position CENTRE = new Position(0, 0);
    public ArrayList<String> instructionList = new ArrayList<>();

    private Position position;
    private static Direction currentDirection;
    private String status;
    private final String name;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        currentDirection = Direction.NORTH;
    }

    public String getStatus() {
        return this.status;
    }

    public ArrayList<String> getInstructionList(){
        return instructionList;
    }

    public void addInstruction(String instruction){
        instructionList.add(instruction);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public boolean setCurrentDirection(boolean turn){
        ArrayList<Direction> directions = new ArrayList<>();
        directions.add(Direction.NORTH);
        directions.add(Direction.EAST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.WEST);
        if (turn){
            int turner = directions.indexOf(getCurrentDirection());
            if (getCurrentDirection() == Direction.WEST){
                currentDirection = Direction.NORTH;
            }
            else{
                currentDirection = directions.get(turner+1);
            }
        }
        else {
            int turner = directions.indexOf(getCurrentDirection());
            if (getCurrentDirection() == Direction.NORTH) {
                currentDirection = Direction.WEST;
            } else {
                currentDirection = directions.get(turner - 1);
            }
        }
        return true;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public boolean sprint( int nrSteps){
        while (nrSteps>0){
            updatePosition(nrSteps);
            System.out.println("Moved forward by "+nrSteps+ " steps.");
        }
        return true;
     }
    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        }
        if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        }
        if (Direction.WEST.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }
        if (Direction.EAST.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.position.getX() + "," + this.position.getY() + "] "
                + this.name + "> " + this.status;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }
}