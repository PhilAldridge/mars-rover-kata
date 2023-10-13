package src.Worlds;

public interface World {
    int[] implementWrapping(int newX, int newY, int newDirection);
    int oppositeDirection(int direction);
}
