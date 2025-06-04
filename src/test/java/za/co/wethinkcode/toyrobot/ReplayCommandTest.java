package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReplayCommandTest {

    @Test
    void testReplayAll() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("forward 1");
        command.execute(robot);
        Command command2 = Command.create("right");
        command2.execute(robot);
        Command command3 = Command.create("forward 1");
        command3.execute(robot);
        Command command4 = Command.create("replay 3");
        command4.execute(robot); // Replays last 3 commands

        // Final position after forward, right, forward, forward, right, forward
        assertEquals(2, robot.getPosition().getX());
        assertEquals(0, robot.getPosition().getY());
    }

    @Test
    void testReplayRange() {
        Robot robot = new Robot("Testdummy");
        Command command = Command.create("forward 1");
        command.execute(robot); // 0
        Command command2 = Command.create("right");
        command2.execute(robot);  // 1
        Command command3 = Command.create("forward 1");
        command3.execute(robot); // 2
        Command command4 = Command.create("replay 3-1");
        command4.execute(robot); // Replays index 1 & 2: right, forward

        assertEquals(2, robot.getPosition().getX());
        assertEquals(1, robot.getPosition().getY());
    }

    @Test
    void testReplayReversedRange() {
        Robot robot = new Robot("Draft");
        Command command = Command.create("forward 1");
        command.execute(robot); // 0
        Command command2 = Command.create("right");
        command2.execute(robot);   // 1
        Command command3 = Command.create("forward 1");
        command3.execute(robot); // 2
        Command command4 = Command.create("back 10");
        command4.execute(robot);
        Command command5 = Command.create("replay reversed 4-1");
        command5.execute(robot); // Replays: forward, right, forward in reverse: forward, right, forward

        assertEquals(-8, robot.getPosition().getX());
        assertEquals(0, robot.getPosition().getY());
    }
}

