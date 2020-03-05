package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.utils.MockData;
import seg.team9.utils.UtilsUI;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.calculation.CalculationBreakdownViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar
    @FXML private MenuItem menuItemClose;
    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ChoiceBox<Runway> choiceBoxRunway;

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
        choiceBoxRunway.getItems().addAll(MockData.runwayList());
        choiceBoxRunway.getSelectionModel().selectFirst();
        topDownViewController.displayDirectedRunwaySelected(choiceBoxRunway.getSelectionModel().getSelectedItem());


        choiceBoxRunway.getSelectionModel().selectedItemProperty().addListener((observableValue, directedRunway, t1) -> {
            topDownViewController.displayDirectedRunwaySelected(observableValue.getValue());
            topDownViewController.updateUI();
        });

    }

    private void initMenuBar(){
        menuItemClose.setOnAction(actionEvent -> UtilsUI.showErrorMessage("This feature has not been implemented yet"));
    }

}
