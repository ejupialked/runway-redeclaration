package seg.team9.controllers.breakdown;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.logic.Calculator;
import seg.team9.controllers.calculation.CalculationsViewController;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class CalculationBreakdownController implements Initializable {

    private static final Logger logger = LogManager.getLogger("CalculationBreakdownViewController");


    @FXML private TextArea textBreakdown;

    private static CalculationBreakdownController instance;


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
