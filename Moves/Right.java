package src.Moves;

public class Right implements Rotation {
    @Override
    public String go(String direction) {
        return switch(direction){
            case "N" -> "E";
            case "E" -> "S";
            case "S" -> "W";
            case "W" -> "N";
            default -> direction;
        };
    }
}
