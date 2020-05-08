package seg.team9.controllers.airport;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;

import java.net.URL;
import java.util.ResourceBundle;

public class AirportViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger("AirportViewController");
    private static AirportViewController instance;

    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ComboBox<Runway> comboBoxRunways;
    @FXML private TextField txtRunwayLength;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initChoiceBoxes();

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
                txtRunwayLength.setText(String.valueOf(t1.getLength()));
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
}
