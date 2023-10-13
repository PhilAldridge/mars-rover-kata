package src.Obstacles;

public class MockDetector implements ObstacleDetector {
    private final int[][] obstacleList;

    public MockDetector(int[][] obstacleList){
        this.obstacleList = obstacleList;
    }
    @Override
    public Boolean obstacleAtPosition(int xPos, int yPos) {
        for(int[] obstacle: obstacleList){
            if(xPos == obstacle[0] && yPos ==obstacle[1]) return true;
        }
        return false;
    }
}
