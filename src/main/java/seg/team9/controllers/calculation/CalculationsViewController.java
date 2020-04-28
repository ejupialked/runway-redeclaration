package seg.team9.controllers.calculation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Runway;
import seg.team9.utils.UtilsUI;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationsViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("CalculationViewController");

    private static CalculationsViewController instance;

    public CalculationsViewController() {
        instance = this;
    }

    public static CalculationsViewController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init CalculationsViewController");
        initLabels();
        initColors();
    }

    private void initColors() {
        colorTora.setFill(UtilsUI.Colors.TORA);
        colorToda.setFill(UtilsUI.Colors.TODA);
        colorAsda.setFill(UtilsUI.Colors.ASDA);
        colorLda.setFill(UtilsUI.Colors.LDA);
    }


    public void initLabels(){

    }

    public void updateCalculationValues(Runway runway){

        rightDesignator.setText(runway.getRRunway().getDesignator());
        labelTODAr.setText(runway.getRRunway().getWorkingTODA().toString());
        labelTODAr.setText(runway.getRRunway().getWorkingASDA().toString());
        labelTODAr.setText(runway.getRRunway().getWorkingTORA().toString());
        labelTODAr.setText(runway.getRRunway().getWorkingLDA().toString());
        labelTODAr.setText(runway.getRRunway().getResa().toString());

        leftDesignator.setText(runway.getLRunway().getDesignator());
        labelTODAl.setText(runway.getLRunway().getWorkingTODA().toString());
        labelASDAl.setText(runway.getLRunway().getWorkingASDA().toString());
        labelTORAl.setText(runway.getLRunway().getWorkingTORA().toString());
        labelLDAl.setText(runway.getLRunway().getWorkingLDA().toString());
        labelRESAl.setText(runway.getLRunway().getResa().toString());
    }

    public AnchorPane getPaneCalculations() {
        return paneCalculations;
    }

    @FXML private AnchorPane paneCalculations;
    @FXML private Label labelRESAl;
    @FXML private Label labelTORAl;
    @FXML private Label labelTODAl;
    @FXML private Label labelLDAl;
    @FXML private Label labelASDAl;
    @FXML private Label labelALSl;
    @FXML private Label labelSlopel;
    @FXML private Label labelTOCSl;
    @FXML private Rectangle colorResa;
    @FXML private Rectangle colorTora;
    @FXML private Rectangle colorToda;
    @FXML private Rectangle colorLda;
    @FXML private Rectangle colorAsda;
    @FXML private Rectangle colorTocs;
    @FXML private Rectangle colorAls;
    @FXML private Rectangle colorSlope;
    @FXML private Label leftDesignator;
    @FXML private Label rightDesignator;
    @FXML private Label labelRESAr;
    @FXML private Label labelTORAr;
    @FXML private Label labelTODAr;
    @FXML private Label labelLDAr;
    @FXML private Label labelASDAr;
    @FXML private Label labelTOCSr;
    @FXML private Label labelALSr;
    @FXML private Label labelSlopeR;
}