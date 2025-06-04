package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RobotMovementTest {

    @Test
    void testForwardMovement() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("forward 1");
        assertTrue(command.execute(robot));
        assertEquals(0, robot.getPosition().getX());
        assertEquals(1, robot.getPosition().getY());
    }

    @Test
    void testBackMovement() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("back 1");
        assertTrue(command.execute(robot));
        assertEquals(0, robot.getPosition().getX());
        assertEquals(-1, robot.getPosition().getY());
    }

    @Test
    void testTurnRight() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("right");
        assertTrue(command.execute(robot));
        assertEquals(Direction.EAST, robot.getCurrentDirection());
    }

    @Test
    void testTurnLeft() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("left");
        assertTrue(command.execute(robot));
        assertEquals(Direction.WEST, robot.getCurrentDirection());
    }
}

