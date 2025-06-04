package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandHistoryTest {

    @Test
    void testHistoryRecording() {
        Robot robot = new Robot("Prototype");
        Command command = Command.create("forward 1");
        command.execute(robot);
        Command command2 = Command.create("right");
        command2.execute(robot);
        Command command3 = Command.create("forward 1");
        command3.execute(robot);

        ArrayList<String> history = robot.getInstructionList();
        assertEquals(new ArrayList<>(Arrays.asList("forward 1", "right", "forward 1")), history);
    }
}

