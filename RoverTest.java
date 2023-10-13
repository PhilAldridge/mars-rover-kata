package src;

import org.junit.Test;
import src.Obstacles.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RoverTest {
    int[][] obstacles = {{5, 4},{8,8}};
    ObstacleDetector detector = new MockDetector(obstacles);

    @Test
    public void acceptCommands() {
        Rover rover = new Rover(1, 1, "E", detector);
        rover.processCommands(new String[]{"L", "F"});
    }

    @Test
    public void rejectInvalidCommands() {
        Rover rover = new Rover(1, 1, "E", detector);
        String actual = rover.processCommands(new String[]{"S", "F"});
        assertThat(actual, containsString("Invalid"));
    }

    @Test
    public void forwardFacingEast() {
        Rover rover = new Rover(1, 1, "E", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 2,1 facing East", actual);
    }

    @Test
    public void forwardFacingWest() {
        Rover rover = new Rover(3, 1, "W", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 2,1 facing West", actual);
    }

    @Test
    public void forwardFacingNorth() {
        Rover rover = new Rover(1, 3, "N", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 1,2 facing North", actual);
    }

    @Test
    public void forwardFacingSouth() {
        Rover rover = new Rover(1, 1, "S", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 1,2 facing South", actual);
    }


    @Test
    public void backFacingEast() {
        Rover rover = new Rover(3, 1, "E", detector);
        String actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 2,1 facing East", actual);
    }

    @Test
    public void backFacingWest() {
        Rover rover = new Rover(1, 1, "W", detector);
        String actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 2,1 facing West", actual);
    }

    @Test
    public void backFacingNorth() {
        Rover rover = new Rover(1, 1, "N", detector);
        String actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 1,2 facing North", actual);
    }

    @Test
    public void backFacingSouth() {
        Rover rover = new Rover(1, 3, "S", detector);
        String actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 1,2 facing South", actual);
    }

    @Test
    public void turnLeft() {
        Rover rover = new Rover(1, 3, "S", detector);
        String actual = rover.processCommands(new String[]{"L"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing East", actual);

        actual = rover.processCommands(new String[]{"L"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing North", actual);

        actual = rover.processCommands(new String[]{"L"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing West", actual);

        actual = rover.processCommands(new String[]{"L"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing South", actual);
    }

    @Test
    public void turnRight() {
        Rover rover = new Rover(1, 3, "S", detector);
        String actual = rover.processCommands(new String[]{"R"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing West", actual);

        actual = rover.processCommands(new String[]{"R"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing North", actual);

        actual = rover.processCommands(new String[]{"R"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing East", actual);

        actual = rover.processCommands(new String[]{"R"});
        assertEquals("Commands completed successfully. Rover is now at 1,3 facing South", actual);
    }

    @Test
    public void multipleCommands() {
        Rover rover = new Rover(1, 3, "S", detector);
        String actual = rover.processCommands(new String[]{"B", "L", "F", "R", "F", "F"});
        assertEquals("Commands completed successfully. Rover is now at 2,4 facing South", actual);
    }

    @Test
    public void northPoleForwardsandBackwards() {
        Rover rover = new Rover(1, 1, "N", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 5,1 facing South", actual);
        actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 1,1 facing North", actual);
    }

    @Test
    public void southPoleForwardsandBackwards() {
        Rover rover = new Rover(1, 8, "S", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 5,8 facing North", actual);
        actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 1,8 facing South", actual);
    }

    @Test
    public void eastWrappingForwardsandBackwards() {
        Rover rover = new Rover(8, 1, "E", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 1,1 facing East", actual);
        actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 8,1 facing East", actual);
    }

    @Test
    public void westWrappingForwardsandBackwards() {
        Rover rover = new Rover(8, 1, "W", detector);
        String actual = rover.processCommands(new String[]{"B"});
        assertEquals("Commands completed successfully. Rover is now at 1,1 facing West", actual);
        actual = rover.processCommands(new String[]{"F"});
        assertEquals("Commands completed successfully. Rover is now at 8,1 facing West", actual);
    }

    @Test
    public void detectsCollision() {
        Rover rover = new Rover(4, 4, "E", detector);
        String actual = rover.processCommands(new String[]{"F"});
        assertThat(actual, containsString("Obstacle detected"));
    }

    @Test
    public void stopsAtCollision() {
        Rover rover = new Rover(4, 4, "E", detector);
        String actual = rover.processCommands(new String[]{"F", "L", "F"});
        assertEquals("Obstacle detected. Rover stopped at 4,4 facing East", actual);
    }

    @Test
    public void stopsAtCollisionOverPole() {
        Rover rover = new Rover(4, 8, "S", detector);
        String actual = rover.processCommands(new String[]{"F", "L", "F"});
        assertEquals("Obstacle detected. Rover stopped at 4,8 facing South", actual);
    }
}
