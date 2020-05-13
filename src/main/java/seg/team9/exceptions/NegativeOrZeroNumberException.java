package seg.team9.exceptions;

public class NegativeOrZeroNumberException extends Exception {
    public NegativeOrZeroNumberException(String name){
        super("'" + name + "' has a negative or non-zero value. Please insert a positive value.");
    }
}
