package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.*;

import java.util.List;

public abstract class AbstractWorld implements IWorld{
    public Robot robot;
    public String name;
    public IWorld.Direction worldDirection;
    public Position worldPosition = IWorld.CENTRE;


    public AbstractWorld(Robot robot, String name, int width, int height){
        this.robot = robot;
        this.name = name;
        worldDirection = Direction.UP;
    }

    public UpdateResponse updatePosition(int nrSteps) {
        int xPos = worldPosition.getX();
        int yPos = worldPosition.getY();
        switch (worldDirection) {
            case Direction.UP:
                yPos += nrSteps;
            case Direction.RIGHT:
                xPos += nrSteps;
            case Direction.DOWN:
                yPos -= nrSteps;
            case Direction.LEFT:
                xPos -= nrSteps;
        }

        return UpdateResponse.SUCCESS;
    }

    public Position getPosition() {
        return null;
    }


    public Direction getCurrentDirection() {
        return null;
    }

    public void updateDirection(boolean turnRight) {
        za.co.wethinkcode.toyrobot.Direction robotCurrentDirection = robot.getCurrentDirection();
        switch (robotCurrentDirection) {
            case za.co.wethinkcode.toyrobot.Direction.NORTH:
                worldDirection = Direction.UP;
            case za.co.wethinkcode.toyrobot.Direction.EAST:
                worldDirection = Direction.RIGHT;
            case za.co.wethinkcode.toyrobot.Direction.SOUTH:
                worldDirection = Direction.DOWN;
            case za.co.wethinkcode.toyrobot.Direction.WEST:
                worldDirection = Direction.LEFT;
            default:
                worldDirection = Direction.UP;
        }
    }

    public boolean isAtEdge() {
        return false;
    }


    public boolean isNewPositionAllowed(Position position) {
        return false;
    }

    public void reset() {

    }


    public List<Obstacle> getObstacles() {
        return List.of();
    }

    public void showObstacles() {

    }
}
