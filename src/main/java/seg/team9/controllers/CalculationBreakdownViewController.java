package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationBreakdownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("CalculationBreakdownViewController");

    @FXML private Label labelTOCS;
    @FXML private Label labelALS;
    @FXML private Label labelSlope;
    @FXML private Label labelRESA;
    @FXML private Label labelLDA;
    @FXML private Label labelASDA;
    @FXML private Label labelTODA;
    @FXML private Label labelTORA;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init CalculationBreakdownViewController");
        initLabels();
    }

    public void initLabels(){
     labelTOCS.setText("3932");
     labelALS.setText("732");
     labelSlope.setText("52");
     labelRESA.setText("33");
     labelLDA.setText("154");
     labelASDA.setText("530");
     labelTODA.setText("932");
     labelTORA.setText("731");
    }
}