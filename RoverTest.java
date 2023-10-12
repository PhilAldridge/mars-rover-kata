package src;

import org.junit.Test;
import src.Obstacles.MockDetector;
import src.Obstacles.ObstacleDetector;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;

public class RoverTest {
    PositionVector[] obstacles = new PositionVector[] {new PositionVector(5,4)};
    ObstacleDetector mockDetector = new MockDetector(obstacles);

    @Test
    public void roverInitialiseConnection() {
        Rover rover = new Rover(1,1,"E");
    }
@Test
    public void acceptCommands(){
        Rover rover = new Rover(1,1,"E");
        rover.acceptCommands(new String[] {"L","F"});
    }
@Test
    public void processForwardFacingEast(){
        Rover rover = new Rover(1,1,"E");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,1 facing E");
    }

    @Test
    public void processForwardFacingNorth(){
        Rover rover = new Rover(1,2,"N");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 1,1 facing N");
    }

    @Test
    public void processForwardFacingSouth(){
        Rover rover = new Rover(1,1,"S");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 1,2 facing S");

    }

    @Test
    public void processForwardFacingWest(){
        Rover rover = new Rover(1,1,"W");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 8,1 facing W");
    }

    @Test
    public void processBackwardFacingWest(){
        Rover rover = new Rover(1,1,"W");
        String actual = rover.acceptCommands(new String[] {"B"});
        PositionVector expected = new PositionVector(2,1);
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,1 facing W");

    }

    @Test
    public void processMultipleForwardsBackwardsFacingWest(){
        Rover rover = new Rover(1,1,"W");
        String actual = rover.acceptCommands(new String[] {"B","F","F","F"});
        PositionVector expected = new PositionVector(7,1);
        assertEquals(actual, "Commands completed successfully. Rover is now at 7,1 facing W");

    }

    @Test
    public void processLeftThenForwards(){
        Rover rover = new Rover(1,1,"W");
        String actual = rover.acceptCommands(new String[] {"L","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 1,2 facing S");

        actual = rover.acceptCommands(new String[] {"L","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,2 facing E");

    }

    @Test
    public void processRightThenForwards(){
        Rover rover = new Rover(1,3,"W");
        String actual = rover.acceptCommands(new String[] {"R","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 1,2 facing N");

        actual = rover.acceptCommands(new String[] {"R","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,2 facing E");

    }

    @Test
    public void processXNegativeWrapping(){
        Rover rover = new Rover(1,1,"W");
        String actual = rover.acceptCommands(new String[] {"F","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 7,1 facing W");

    }

    @Test
    public void processXPositiveWrapping(){
        Rover rover = new Rover(7,1,"E");
        String actual = rover.acceptCommands(new String[] {"F","F","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,1 facing E");

    }

    @Test
    public void processCrossNorthPole(){
        Rover rover = new Rover(1,1,"N");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 5,1 facing S");

    }

    @Test
    public void processCrossNorthPoleThenMove(){
        Rover rover = new Rover(1,1,"N");
        String actual = rover.acceptCommands(new String[] {"F","F","R","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 4,2 facing W");

    }

    @Test
    public void processCrossNorthPoleFromOtherSide(){
        Rover rover = new Rover(6,1,"N");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 2,1 facing S");
    }

    @Test
    public void processCrossSouthPole(){
        Rover rover = new Rover(1,8,"S");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 5,8 facing N");
    }

    @Test
    public void processCrossSouthPoleOtherSide(){
        Rover rover = new Rover(7,8,"S");
        String actual = rover.acceptCommands(new String[] {"F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 3,8 facing N");
    }

    @Test
    public void processCrossSouthPoleThenMove(){
        Rover rover = new Rover(1,8,"S");
        String actual = rover.acceptCommands(new String[] {"F","F","L","F"});
        assertEquals(actual, "Commands completed successfully. Rover is now at 4,7 facing W");
    }

    @Test
    public void positionEqualsFunction(){
        assertTrue(new PositionVector(1,1).isEqualTo(new PositionVector(1,1)));
    }

    @Test
    public void collisionDetection(){
        assertTrue(mockDetector.obstacleAtPosition(new PositionVector(5,4)));
    }

    @Test
    public void collisionDetectionFromRover(){
        Rover rover = new Rover(4,4,"E", mockDetector);
        String actual = rover.acceptCommands(new String[] {"F"});
        assertThat(actual,containsString("Collision Detected"));
    }

    @Test
    public void collisionDetectedStopsRover(){
        Rover rover = new Rover(4,4,"E", mockDetector);
        String actual = rover.acceptCommands(new String[] {"F","F"});
        assertEquals(actual, "Collision Detected, further operations aborted. Rover is now at 4,4 facing E");
    }
}

