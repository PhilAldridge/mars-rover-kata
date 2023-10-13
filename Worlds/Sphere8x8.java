package src.Worlds;

public class Sphere8x8 implements World {
    public int[] implementWrapping(int newX, int newY, int newDirection){
        //EW wrapping
        if(newX ==0){ newX = 8; }
        if(newX ==9){ newX = 1; }

        //NS wrapping
        if(newY ==0){
            newY = 1;
            newX = xToOppositeHemisphere(newX);
            newDirection = oppositeDirection(newDirection);
        }
        if(newY == 9){
            newY=8;
            newX = xToOppositeHemisphere(newX);
            newDirection = oppositeDirection(newDirection);
        }

        return new int[] {newX, newY, newDirection};
    }
    private int xToOppositeHemisphere(int xPos){
        int newX = (xPos+4)%8;
        if(newX==0) return 8;
        return newX;
    }

    public int oppositeDirection(int direction){ return (direction+2)%4; }
}
