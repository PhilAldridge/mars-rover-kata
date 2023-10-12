package src;

import src.Moves.*;
import src.Obstacles.MockDetector;
import src.Obstacles.ObstacleDetector;

public class Rover {
    private PositionVector position;
    private String direction;
    private final Movement forward = new Forward();
    private final Movement backward = new Backward();
    private final Rotation left = new Left();
    private final Rotation right = new Right();

    private final ObstacleDetector obstacleDetector;

    public Rover(int x, int y, String direction){
        this.position = new PositionVector(x,y);
        this.direction=direction;
        this.obstacleDetector = new MockDetector(new PositionVector[] {});
    }

    public Rover(int x, int y, String direction, ObstacleDetector obstacleDetector){
        this.position = new PositionVector(x,y);
        this.direction=direction;
        this.obstacleDetector = obstacleDetector;
    }

    public String acceptCommands(String[] commands){
        for (String command : commands) {
            int oldX = position.getX();
            int oldY = position.getY();
            move(command);
            if (obstacleDetector.obstacleAtPosition(position)) {
                position = new PositionVector(oldX,oldY);
                return "Collision Detected, further operations aborted. Rover is now at "+position.getX()+","+position.getY()+" facing "+direction;
            }
        }
        return "Commands completed successfully. Rover is now at "+position.getX()+","+position.getY()+" facing "+direction;
    }

    private void implementWrapping() {
        int x = position.getX();
        if(x<1){
            position.add(new PositionVector(8,0));
        }
        if(x>8){
            position.add(new PositionVector(-8,0));
        }
        int y = position.getY();
        if(y<1){
            position.add(new PositionVector(4,1-(2*y)));
            direction = "S";
            implementWrapping();
        }
        if(y>8){
            position.add(new PositionVector(4,1-2*(y-8)));
            direction = "N";
            implementWrapping();
        }
    }

    private void move(String command){
        switch (command) {
            case "F":
                position.add(forward.go(direction));
                implementWrapping();
                return;
            case "B":
                position.add(backward.go(direction));
                implementWrapping();
                return;
            case "L":
                direction = left.go(direction);
                return;
            case "R":
                direction = right.go(direction);
        }

    }
}
