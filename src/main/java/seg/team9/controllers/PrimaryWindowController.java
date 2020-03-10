package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.Utils.UtilsUI;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.calculation.CalculationBreakdownViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar
    @FXML private MenuItem menuItemClose;
    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ChoiceBox<Runway> choiceBoxRunway;

    private ArrayList<AnchorPane> lightPanes;
    private ArrayList<AnchorPane> darkPanes;
    @FXML private AnchorPane paneView;
    @FXML private AnchorPane paneCalculations;
    @FXML private AnchorPane paneQMarks;
    // Injecting controllers
    @FXML private SideViewController sideViewController; // side runway
    @FXML private TopDownViewController topDownViewController; // top down runway
    @FXML private ObstacleViewController obstacleViewController;
    @FXML private CalculationBreakdownViewController calculationsBreakdownViewController;

    private String white = " #FFFFFF";
    private String grey = "#E0E0E0";
    private String darkerWhite = "#cccccc";
    private String darkerGray = "#B3B3B3";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuBar();
        initChoiceBoxes();
        initArrays();
    }

    private void initArrays() {
        lightPanes = new ArrayList<>();
        darkPanes = new ArrayList<>();
        lightPanes.add(paneCalculations);
        lightPanes.add(calculationsBreakdownViewController.getPaneCalculationsBreakdown());
        darkPanes.add(paneView);
        darkPanes.add(paneQMarks);
        darkPanes.add(obstacleViewController.getPaneObstacles());
    }

    private void initChoiceBoxes(){
        choiceBoxAirport.getItems().addAll(App.airportObservableList());
        choiceBoxAirport.getSelectionModel().selectFirst();

        Airport a = choiceBoxAirport.getValue();

        choiceBoxRunway.getItems().addAll(a.getRunwayList());
        choiceBoxRunway.getSelectionModel().selectFirst();

        topDownViewController.displayDirectedRunwaySelected(choiceBoxRunway.getSelectionModel().getSelectedItem());

        //when an airport is selected the runway list will update
        choiceBoxAirport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observableValue, Airport airport, Airport t1) {
                choiceBoxRunway.getItems().clear();
                choiceBoxRunway.getItems().addAll(airport.getRunwayList());
                choiceBoxRunway.getSelectionModel().selectFirst();
            }
        });


        choiceBoxRunway.getSelectionModel().selectedItemProperty().addListener((observableValue, directedRunway, t1) -> {
            topDownViewController.displayDirectedRunwaySelected(observableValue.getValue());
            //topDownViewController.updateUI(); you're calling this method already in line 67
        });

    }

    private void initMenuBar(){
        menuItemClose.setOnAction(actionEvent -> UtilsUI.showErrorMessage("This feature has not been implemented yet"));
    }

    public void onColourChangeDefault(ActionEvent actionEvent) {
        for (AnchorPane pane : lightPanes)
            pane.setStyle("-fx-background-color: " + white + ";");

        for(AnchorPane pane : darkPanes)
            pane.setStyle("-fx-background-color: " + grey + ";");
    }

    public void onColourChangeDark(ActionEvent actionEvent) {
        for (AnchorPane pane : lightPanes)
            pane.setStyle("-fx-background-color: " + darkerWhite + ";");

        for(AnchorPane pane : darkPanes)
            pane.setStyle("-fx-background-color: " + darkerGray + ";");
    }
}
