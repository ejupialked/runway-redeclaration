package seg.team9.controllers.breakdown;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CalculationBreakdownController implements Initializable {

    private static final Logger logger = LogManager.getLogger("CalculationBreakdownViewController");
    private static CalculationBreakdownController instance;

    @FXML private TextArea textBreakdown;

    public CalculationBreakdownController() {
        instance = this;
    }

    public static CalculationBreakdownController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textBreakdown.setPrefHeight(700d);
        textBreakdown.setPrefWidth(700d);
    }


    public TextArea getTextBreakdown() {
        return textBreakdown;
    }

    public void showBreakdown(HashMap<String, String> calculationBreakdown) {
        textBreakdown.clear();
        String print = textBreakdown.getText();
        for(String s: calculationBreakdown.values()){

            print += s + "\n \n";
            textBreakdown.setText(print);
        }
    }
}
