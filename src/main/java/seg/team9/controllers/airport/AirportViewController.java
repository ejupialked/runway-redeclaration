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
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Runway;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;
import seg.team9.utils.UtilsUI;

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
    @FXML private TextField txtRunwayLength;

    @FXML private Label txtLeftDesignator;
    @FXML private Label txtRightDesignator;
    @FXML private TextField txtThresholdRight;
    @FXML private TextField txtClearwayRight;
    @FXML private TextField txtStopwayRight;
    @FXML private TextField txtThresholdLeft;
    @FXML private TextField txtClearwayLeft;
    @FXML private TextField txtStopwayLeft;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChoiceBoxes();
        updateTextFields(comboBoxRunways.getValue());
    }

    private void updateTextFields(Runway t1) {
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

        Airport a = choiceBoxAirport.getValue();

        initComboBox(a);


        TopDownViewController.getInstance().displayDirectedRunwaySelected(comboBoxRunways.getSelectionModel().getSelectedItem());
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

                updateTextFields(t1);
                TopDownViewController.getInstance().displayDirectedRunwaySelected(observableValue.getValue());
                SideViewController.getInstance().updateUI();
            }

            TopDownViewController.getInstance().initArrowsColors();
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

    public void onAlignClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().rotateRunway();
        TopDownViewController.getInstance().isHorizontal = false;

    }

    public void onHorizontalClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().rotateRunwayHorizontal();
    }
    public void addAirport(ActionEvent actionEvent) {
    }
}
