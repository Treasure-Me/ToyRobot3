package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SprintCommandTest {

    @Test
    void testSprint3MovesCorrectly() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("sprint 3");
        command.execute(robot);
        // Sprint 3 = move 3 + 2 + 1 = total 6 steps forward
        assertEquals(0, robot.getPosition().getX());
        assertEquals(6, robot.getPosition().getY());
    }
}

