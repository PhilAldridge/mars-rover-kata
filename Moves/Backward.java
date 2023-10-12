package src.Moves;

import src.PositionVector;

public class Backward implements Movement {
    @Override
    public PositionVector go(String direction) {
        return switch(direction){
            case "N" -> new PositionVector(0,1);
            case "E" -> new PositionVector(-1,0);
            case "S" -> new PositionVector(0,-1);
            case "W" -> new PositionVector(1,0);
            default -> new PositionVector(0,0);
        };
    }
}
