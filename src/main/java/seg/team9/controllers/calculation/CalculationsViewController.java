package seg.team9.controllers.calculation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.logic.Calculator;
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
        /*colorTora.setFill(UtilsUI.Colors.TORA);
        colorToda.setFill(UtilsUI.Colors.TODA);
        colorAsda.setFill(UtilsUI.Colors.ASDA);
        colorLda.setFill(UtilsUI.Colors.LDA);
        colorResa.setFill(UtilsUI.Colors.RESA);
*/
    }


    public void initLabels(){

    }

    public void updateCalculationValues(Runway runway){

        rightDesignator.setText(runway.getRRunway().getDesignator());
        labelTODAr.setText(UtilsUI.addUnitMeasurement(runway.getRRunway().getWorkingTODA().toString()));
        labelASDAr.setText(UtilsUI.addUnitMeasurement(runway.getRRunway().getWorkingASDA().toString()));
        labelTORAr.setText(UtilsUI.addUnitMeasurement(runway.getRRunway().getWorkingTORA().toString()));
        labelLDAr.setText(UtilsUI.addUnitMeasurement(runway.getRRunway().getWorkingLDA().toString()));
        labelRESAr.setText(UtilsUI.addUnitMeasurement(runway.getRRunway().getResa().toString()));

        leftDesignator.setText(runway.getLRunway().getDesignator());
        labelTODAl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getWorkingTODA().toString()));
        labelASDAl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getWorkingASDA().toString()));
        labelTORAl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getWorkingTORA().toString()));
        labelLDAl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getWorkingLDA().toString()));
        labelRESAl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getResa().toString()));


        labelTOCSl.setText("-");
        labelTOCSr.setText("-");

        labelALSl.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getAls().toString()));
        labelALSr.setText(UtilsUI.addUnitMeasurement(runway.getLRunway().getAls().toString()));

        labelSlopel.setText(Calculator.getSLOPE().toString()+"°");
        labelSlopeR.setText(Calculator.getSLOPE().toString()+"°");


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