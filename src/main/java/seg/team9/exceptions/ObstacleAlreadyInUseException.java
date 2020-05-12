package seg.team9.exceptions;

public class ObstacleAlreadyInUseException extends Exception {

    public ObstacleAlreadyInUseException(String name){
        super("'" + name + "' name is already in use. Please choose another name for the obstacle.");
    }
}
