package seg.team9.exceptions;

public class TextFieldEmptyException extends Exception {

    public TextFieldEmptyException(String labelOfTextField){
        super("Please complete the " + labelOfTextField + " text field.");
    }
}
