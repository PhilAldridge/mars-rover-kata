package src;

public class PositionVector {
    private int x,y;

    public PositionVector(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add(PositionVector vector){
        x += vector.getX();
        y += vector.getY();
    }

    public void add(int xDelta, int yDelta){
        x += xDelta;
        y += yDelta;
    }

    public Boolean isEqualTo(PositionVector positionVector){
        return positionVector.getX() == x && positionVector.getY() == y;
    }
}
