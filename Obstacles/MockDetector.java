package src.Obstacles;

import src.PositionVector;

public class MockDetector implements ObstacleDetector{
    private final PositionVector[] obstacleList;

    public MockDetector(PositionVector[] obstacleList){
        this.obstacleList = obstacleList;
    }
    @Override
    public Boolean obstacleAtPosition(PositionVector position) {
        for(PositionVector obstacle: obstacleList){
            if(obstacle.isEqualTo(position)) return true;
        }
        return false;
    }
}
