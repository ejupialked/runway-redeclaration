package seg.team9.controllers.breakdown;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CalculationBreakdownController implements Initializable {

    private static final Logger logger = LogManager.getLogger("CalculationBreakdownViewController");
    private static CalculationBreakdownController instance;

    @FXML private TabPane tabPane;
    @FXML private AnchorPane breakdownPane;

    @FXML private TextArea TORAText;
    @FXML private TextArea TODAText;
    @FXML private TextArea LDAText;
    @FXML private TextArea ASDAText;

    public CalculationBreakdownController() {
        instance = this;
    }

    public static CalculationBreakdownController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TORAText.setPrefHeight(700d);
        TORAText.setPrefWidth(700d);
        TORAText.setEditable(false);
        TODAText.setPrefHeight(700d);
        TODAText.setPrefWidth(700d);
        TODAText.setEditable(false);
        LDAText.setPrefHeight(700d);
        LDAText.setPrefWidth(700d);
        LDAText.setEditable(false);
        ASDAText.setPrefHeight(700d);
        ASDAText.setPrefWidth(700d);
        ASDAText.setEditable(false);
        tabPane.setPrefHeight(800d);
        tabPane.setPrefWidth(800d);
    }


    public TextArea getTORATextBreakdown() {
        return TORAText;
    }
    public TextArea getTODATextBreakdown() {
        return TODAText;
    }
    public TextArea getLDATextBreakdown() {
        return LDAText;
    }
    public TextArea getASDATextBreakdown() {
        return ASDAText;
    }

    public void showBreakdown(HashMap<String, String> calculationBreakdown) {
        String toraTextText = calculationBreakdown.get("TOTLT")+"\n";
        toraTextText = toraTextText + calculationBreakdown.get("TOTLTTORAdesc")+"\n";
        toraTextText = toraTextText + calculationBreakdown.get("TOTLTTORAval")+"\n \n \n";
        toraTextText = toraTextText + calculationBreakdown.get("TOALO")+"\n";
        toraTextText = toraTextText + calculationBreakdown.get("TOALOTORAdesc")+"\n";
        toraTextText = toraTextText + calculationBreakdown.get("TOALOTORAval")+"\n";


        String todaTextText = calculationBreakdown.get("TOTLT")+"\n";
        todaTextText = todaTextText + calculationBreakdown.get("TOTLTTODAdesc")+"\n";
        todaTextText = todaTextText + calculationBreakdown.get("TOTLTTODAval")+"\n \n \n";
        todaTextText = todaTextText + calculationBreakdown.get("TOALO")+"\n";
        todaTextText = todaTextText + calculationBreakdown.get("TOALOTODAdesc")+"\n";
        todaTextText = todaTextText + calculationBreakdown.get("TOALOTODAval")+"\n";


        String ldaTextText = calculationBreakdown.get("TOTLT")+"\n";
        ldaTextText = ldaTextText + calculationBreakdown.get("TOTLTLDAdesc")+"\n";
        ldaTextText = ldaTextText + calculationBreakdown.get("TOTLTLDAval")+"\n \n \n";
        ldaTextText = ldaTextText + calculationBreakdown.get("TOALO")+"\n";
        ldaTextText = ldaTextText + calculationBreakdown.get("TOALOLDAdesc")+"\n";
        ldaTextText = ldaTextText + calculationBreakdown.get("TOALOLDAval")+"\n";


        String asdaTextText = calculationBreakdown.get("TOTLT")+"\n";
        asdaTextText = asdaTextText + calculationBreakdown.get("TOTLTASDAdesc")+"\n";
        asdaTextText = asdaTextText + calculationBreakdown.get("TOTLTASDAval")+"\n \n \n";
        asdaTextText = asdaTextText + calculationBreakdown.get("TOALO")+"\n";
        asdaTextText = asdaTextText + calculationBreakdown.get("TOALOASDAdesc")+"\n";
        asdaTextText = asdaTextText + calculationBreakdown.get("TOALOASDAval")+"\n";



        TORAText.setText(toraTextText);
        TODAText.setText(todaTextText);
        LDAText.setText(ldaTextText);
        ASDAText.setText(asdaTextText);
    }
}
