package seg.team9.exceptions;

public class ObstacleOutsideOfRunwayException extends Exception {

    public ObstacleOutsideOfRunwayException(){
        super("Obstacle outside of valid bounds");
    }
}
