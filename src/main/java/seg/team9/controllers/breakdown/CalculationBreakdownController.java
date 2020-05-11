package seg.team9.controllers.breakdown;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
        logger.info("init CalculationBreakdownViewController...");
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

    public void showBreakdown(Map<String, Map<String, String>> calculationBreakdown) {
        StringBuilder toraOver = new StringBuilder();
        StringBuilder toraTowards = new StringBuilder();

        toraOver.append(calculationBreakdown.get("over").get("title")).append("\n\n");
        toraOver.append("TORA = ").append(calculationBreakdown.get("over").get("TORA")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("TORA1")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("TORA2")).append("\n");

        toraTowards.append(calculationBreakdown.get("towards").get("title")).append("\n\n");
        toraTowards.append("TORA = ").append(calculationBreakdown.get("towards").get("TORA")).append("\n")
                 .append("           = ").append(calculationBreakdown.get("towards").get("TORA1")).append("\n")
                 .append("           = ").append(calculationBreakdown.get("towards").get("TORA2")).append("\n");


        StringBuilder todaOver = new StringBuilder();
        StringBuilder todaTowards = new StringBuilder();

        todaOver.append(calculationBreakdown.get("over").get("title")).append("\n\n");
        todaOver.append("TODA = ").append(calculationBreakdown.get("over").get("TODA")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("TODA1")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("TORA2")).append("\n");

        todaTowards.append(calculationBreakdown.get("towards").get("title")).append("\n\n");
        todaTowards.append("TODA = ").append(calculationBreakdown.get("towards").get("TODA")).append("\n")
                   .append("           = ").append(calculationBreakdown.get("towards").get("TODA1")).append("\n");


        StringBuilder ldaOver = new StringBuilder();
        StringBuilder ldaTowards = new StringBuilder();

        ldaOver.append(calculationBreakdown.get("over").get("title")).append("\n\n");
        ldaOver.append("LDA = ").append(calculationBreakdown.get("over").get("LDA")).append("\n")
                .append("        = ").append(calculationBreakdown.get("over").get("LDA1")).append("\n")
                .append("        = ").append(calculationBreakdown.get("over").get("LDA2")).append("\n");

        ldaTowards.append(calculationBreakdown.get("towards").get("title")).append("\n\n");
        ldaTowards.append("LDA = ").append(calculationBreakdown.get("towards").get("LDA")).append("\n")
                  .append("        = ").append(calculationBreakdown.get("towards").get("LDA1")).append("\n")
                  .append("        = ").append(calculationBreakdown.get("towards").get("LDA2")).append("\n");



        StringBuilder asdaOver = new StringBuilder();
        StringBuilder asdaTowards = new StringBuilder();


        asdaOver.append(calculationBreakdown.get("over").get("title")).append("\n\n");
        asdaOver.append("ASDA = ").append(calculationBreakdown.get("over").get("ASDA")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("ASDA1")).append("\n")
                .append("           = ").append(calculationBreakdown.get("over").get("ASDA2")).append("\n");

        asdaTowards.append(calculationBreakdown.get("towards").get("title")).append("\n\n");
        asdaTowards.append("ASDA = ").append(calculationBreakdown.get("towards").get("ASDA")).append("\n")
                   .append("           = ").append(calculationBreakdown.get("towards").get("ASDA1")).append("\n");



        TORAText.setText(toraOver.append("\n\n").append(toraTowards).toString());
        TODAText.setText(todaOver.append("\n\n").append(todaTowards).toString());
        LDAText.setText(ldaOver.append("\n\n").append(ldaTowards).toString());
        ASDAText.setText(asdaOver.append("\n\n").append(asdaTowards).toString());
    }
}
