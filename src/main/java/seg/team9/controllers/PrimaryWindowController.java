package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.Utils.MockData;
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar
    @FXML private MenuItem menuItemClose;
    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ChoiceBox<DirectedRunway> choiceBoxRunway;


    // Injecting controllers
    @FXML private SideViewController sideViewController; // side runway
    @FXML private TopDownViewController topDownViewController; // top down runway
    @FXML private ObstacleViewController obstacleViewController;
    @FXML private CalculationBreakdownViewController calculationsBreakdownViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuBar();
        initChoiceBoxes();
    }

    private void initChoiceBoxes(){
        choiceBoxAirport.getItems().addAll(MockData.airportList());
        choiceBoxAirport.getSelectionModel().selectFirst();
        choiceBoxRunway.getItems().addAll(MockData.directedRunways());
        choiceBoxRunway.getSelectionModel().selectFirst();


        choiceBoxRunway.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DirectedRunway>() {
            @Override
            public void changed(ObservableValue<? extends DirectedRunway> observableValue, DirectedRunway directedRunway, DirectedRunway t1) {
                topDownViewController.displayDirectedRunwaySelected(observableValue.getValue().getDesignator());
                topDownViewController.updateUI();
            }
        });

    }

    private void initMenuBar(){
        menuItemClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showPopup("This feature has not been implemented yet");
            }
        });
    }

    private void showPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
