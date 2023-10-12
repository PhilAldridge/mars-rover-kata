package src.Moves;

public class Left implements Rotation {
    @Override
    public String go(String direction) {
        return switch(direction){
            case "N" -> "W";
            case "E" -> "N";
            case "S" -> "E";
            case "W" -> "S";
            default -> direction;
        };
    }
}
