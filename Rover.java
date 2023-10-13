package src;

import src.Obstacles.ObstacleDetector;
import src.Worlds.*;

public class Rover {
    private int x,y,direction;
    private final ObstacleDetector detector;
    private final World world = new Sphere8x8();

    public Rover(int x, int y, String direction, ObstacleDetector detector){
        this.x=x;
        this.y=y;
        this.direction = directionStringToInt(direction);
        this.detector = detector;
    }
    public String processCommands(String[] commands){
        String obstacleString = "Obstacle detected. Rover stopped at %d,%d facing %s";
        String invalidCommandString = "Invalid command given. Rover stopped at %d,%d facing %s";
        String commandsSuccessful = "Commands completed successfully. Rover is now at %d,%d facing %s";
        for(String command:commands){
            switch(command){
                case "F":
                    if(!move(direction)) return String.format(obstacleString,x,y,directionIntToString(direction));
                    break;
                case "B":
                    if(!move(world.oppositeDirection(direction))) return String.format(obstacleString,x,y,directionIntToString(direction));
                    break;
                case "L":
                    turnLeft();
                    break;
                case "R":
                    turnRight();
                    break;
                default:
                    return String.format(invalidCommandString,x,y,directionIntToString(direction));
            }
        }
        return String.format(commandsSuccessful,x,y,directionIntToString(direction));
    }

    private boolean move(int direction){
        return switch (direction){
            case 0 -> moveEast();
            case 1 -> moveNorth();
            case 2 -> moveWest();
            case 3 -> moveSouth();
            default -> false;
        };
    }

    private boolean moveNorth(){ return checkNoObstacleAndMove(world.implementWrapping(x, y - 1, direction)); }
    private boolean moveSouth() { return checkNoObstacleAndMove(world.implementWrapping(x, y + 1, direction)); }
    private boolean moveEast() { return checkNoObstacleAndMove(world.implementWrapping(x+1, y, direction)); }
    private boolean moveWest() { return checkNoObstacleAndMove(world.implementWrapping(x-1, y, direction)); }

    private boolean checkNoObstacleAndMove(int[] newXYDir) {
        if(detector.obstacleAtPosition(newXYDir[0], newXYDir[1])) return false;
        updatePosition(newXYDir[0],newXYDir[1],newXYDir[2]);
        return true;
    }

    private void updatePosition(int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    private void turnLeft(){ direction = (direction+1)%4;  }
    private void turnRight(){ direction = (direction+3)%4; }

    private int directionStringToInt(String direction){
        return switch (direction){
            case "E" -> 0;
            case "N" -> 1;
            case "W" -> 2;
            default -> 3;
        };
    }

    private String directionIntToString(int direction){
        return switch (direction){
            case 0 ->"East";
            case 1 ->"North";
            case 2 ->"West";
            default -> "South";
        };
    }
}
