package seg.team9.controllers.airport;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.logic.Calculator;
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;

import java.net.URL;
import java.util.ResourceBundle;

import static seg.team9.utils.UtilsUI.addUnitMeasurement;

public class AirportViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger("AirportViewController");
    private static AirportViewController instance;

    public AirportViewController() {
        instance = this;
    }

    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ComboBox<Runway> comboBoxRunways;
    @FXML private Label txtRunwayLength;

    @FXML private Label txtLeftDesignator;
    @FXML private Label txtRightDesignator;
    @FXML private Label txtThresholdRight;
    @FXML private Label txtClearwayRight;
    @FXML private Label txtStopwayRight;
    @FXML private Label txtThresholdLeft;
    @FXML private Label txtClearwayLeft;
    @FXML private Label txtStopwayLeft;
    @FXML private Label txtStripEnd;
    @FXML private Label txtBlastProtection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChoiceBoxes();
        updateLabels(comboBoxRunways.getValue());
        logger.info("init AirportController...");
    }


    private void updateLabels(Runway t1) {
        txtRunwayLength.setText(addUnitMeasurement(String.valueOf(t1.getLength())));

        DirectedRunway left = t1.getLRunway();
        DirectedRunway right=  t1.getRRunway();

        //left
        txtLeftDesignator.setText(left.getDesignator());
        txtClearwayLeft.setText(addUnitMeasurement(left.getClearway().toString()));
        txtStopwayLeft.setText(addUnitMeasurement(left.getStopway().toString()));
        txtThresholdLeft.setText(addUnitMeasurement(left.getThreshold().toString()));

        //right
        txtRightDesignator.setText(right.getDesignator());
        txtClearwayRight.setText(addUnitMeasurement(right.getClearway().toString()));
        txtStopwayRight.setText(addUnitMeasurement(right.getStopway().toString()));
        txtThresholdRight.setText(addUnitMeasurement(right.getThreshold().toString()));
    }


    private void initChoiceBoxes(){
        choiceBoxAirport.setItems(App.airportObservableList);
        choiceBoxAirport.getSelectionModel().selectFirst();

        txtStripEnd.setText(Calculator.getBlastProtection() + "m");
        txtStripEnd.setText(Calculator.getSTRIPEND() + "m");

        Airport a = choiceBoxAirport.getValue();

        initComboBox(a);

        SideViewController.getInstance().updateUI();

        //when an airport is selected the runway list will update
        choiceBoxAirport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observableValue, Airport airport, Airport t1) {
                comboBoxRunways.getItems().clear();
                comboBoxRunways.setItems(FXCollections.observableArrayList(t1.getRunwayList()));
                comboBoxRunways.getSelectionModel().selectFirst();
            }
        });

        comboBoxRunways.getSelectionModel().selectedItemProperty().addListener((observableValue, directedRunway, t1) -> {
            if (t1 != null) {

                updateLabels(t1);
                Airport air = AirportViewController.getInstance().getChoiceBoxAirport().getValue();
                Obstacle o = ObstacleViewController.getInstance().getBoxObstacles().getValue();
                TopDownViewController.getInstance().displayDirectedRunwaySelected(air, observableValue.getValue(), o, 2);
                SideViewController.getInstance().updateUI();
            }
            if(!TopDownViewController.getInstance().isColorDefault)
                TopDownViewController.getInstance().initArrowsColors();
            else
                TopDownViewController.getInstance().initArrowsColoursDefault();
            logger.info("Changed colours");
        });

    }

    public void initComboBox(Airport a) {
        comboBoxRunways.setPromptText("Select runway");
        comboBoxRunways.setItems(FXCollections.observableArrayList(a.getRunwayList()));
        comboBoxRunways.getSelectionModel().selectFirst();
    }

    public ChoiceBox<Airport> getChoiceBoxAirport() {
        return choiceBoxAirport;
    }

    public ComboBox<Runway> getComboBoxRunways() {
        return comboBoxRunways;
    }

    public static AirportViewController getInstance() {
        return instance;
    }



    public void addAirport(ActionEvent actionEvent) {
    }
}
