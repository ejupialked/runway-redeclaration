package seg.team9.controllers.calculation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.utils.UtilsUI;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculationsViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("CalculationViewController");

    private static CalculationsViewController instance;

    @FXML private Label labelTOCS;
    @FXML private Label labelALS;
    @FXML private Label labelSlope;
    @FXML private Label labelRESA;
    @FXML private Label labelLDA;
    @FXML private Label labelASDA;
    @FXML private Label labelTODA;
    @FXML private Label labelTORA;
    @FXML private AnchorPane paneCalculations;

    @FXML private Rectangle colorResa;
    @FXML private Rectangle colorTora;
    @FXML private Rectangle colorToda;
    @FXML private Rectangle colorLda;
    @FXML private Rectangle colorAsda;
    @FXML private Rectangle colorTocs;
    @FXML private Rectangle colorAls;
    @FXML private Rectangle colorSlope;

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
     labelTOCS.setText("3932");
     labelALS.setText("732");
     labelSlope.setText("52");
     labelRESA.setText("33");
     labelLDA.setText("154");
     labelASDA.setText("530");
     labelTODA.setText("932");
     labelTORA.setText("731");
    }

    public AnchorPane getPaneCalculations() {
        return paneCalculations;
    }
}