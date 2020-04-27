package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.utils.UtilsUI;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.calculation.CalculationsViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    private static PrimaryWindowController instance;
    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar
    @FXML private MenuItem menuItemClose;
    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ChoiceBox<Runway> choiceBoxRunway;
    // Injecting colour pickers
    @FXML private ColorPicker colourPickerTORA;
    @FXML private ColorPicker colourPickerTODA;
    @FXML private ColorPicker colourPickerLDA;
    @FXML private ColorPicker colourPickerASDA;
    @FXML private SplitPane splitPaneView;

    private ArrayList<AnchorPane> lightPanes;
    private ArrayList<AnchorPane> darkPanes;
    @FXML private AnchorPane paneView;
    @FXML private AnchorPane paneCalculations;
    @FXML private AnchorPane paneQMarks;

    // Injecting controllers
    @FXML private SideViewController sideViewController; // side runway
    @FXML private TopDownViewController topDownViewController; // top down runway
    @FXML private ObstacleViewController obstacleViewController;
    @FXML private CalculationsViewController calculationsViewController;

    @FXML
    ImageView compass;
    @FXML
    ImageView needle;

    @FXML Label labelCompass;

    //Declaring colours
    private String white = " #FFFFFF";
    private String grey = "#E0E0E0";
    private String darkerWhite = "#cccccc";
    private String darkerGray = "#B3B3B3";


    public PrimaryWindowController(){instance = this;}
    public static PrimaryWindowController getInstance(){return instance;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sideViewController = SideViewController.getInstance();
        initCompass();
        initMenuBar();
        initChoiceBoxes();
        initArrays();
        initColorPickers();
        initSplitPane();
        initTabPane();

    }

    private void initCompass() {
        needle.setRotate(270);
    }

    void initSplitPane(){
        logger.info(splitPaneView.getDividers().get(0));
    }

    private void initColorPickers(){
        colourPickerTORA.setValue(Color.BLACK);
        colourPickerTODA.setValue(Color.BLACK);
        colourPickerASDA.setValue(Color.BLACK);
        colourPickerLDA.setValue(Color.BLACK);
    }
    private void initArrays() {
        lightPanes = new ArrayList<>();
        darkPanes = new ArrayList<>();
        lightPanes.add(paneCalculations);
        lightPanes.add(calculationsViewController.getPaneCalculations());
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
        sideViewController.updateUI();

        //when an airport is selected the runway list will update
        choiceBoxAirport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Airport>() {
            @Override
            public void changed(ObservableValue<? extends Airport> observableValue, Airport airport, Airport t1) {
                choiceBoxRunway.getItems().clear();
                choiceBoxRunway.getItems().addAll(t1.getRunwayList());
                choiceBoxRunway.getSelectionModel().selectFirst();
            }
        });


        choiceBoxRunway.getSelectionModel().selectedItemProperty().addListener((observableValue, directedRunway, t1) -> {
            if(t1 != null) {
                topDownViewController.displayDirectedRunwaySelected(observableValue.getValue());
                sideViewController.updateUI();
            }
            //topDownViewController.updateUI(); you're calling this method already in line 67

            topDownViewController.initArrowsColors();
            //changeColourArrows();
            logger.info("Changed colours");
        });

    }

    public void changeColourArrows() {
        //Recolour arrows
        try {
            topDownViewController.changeColourTORA(colourPickerTORA.getValue());
            topDownViewController.changeColourTODA(colourPickerTODA.getValue());
            topDownViewController.changeColourASDA(colourPickerASDA.getValue());
            topDownViewController.changeColourLDA(colourPickerLDA.getValue());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void initMenuBar(){
        menuItemClose.setOnAction(actionEvent -> UtilsUI.showErrorMessage("This feature has not been implemented yet"));
    }

    private void initTabPane(){
        tabPaneRunways.getSelectionModel().selectedItemProperty().addListener((observableValue, oldv, newv) -> {
            if(newv.getId().equals("topDownViewTab")){
                topDownViewController.isSelected = true;
                sideViewController.isSelected = false;
            }
            else{
                topDownViewController.isSelected = false;
                sideViewController.isSelected = true;
            }
            topDownViewController.updateUI();
            sideViewController.updateUI();
        });
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

    public void onSelectedTORAColour(ActionEvent actionEvent) {
       topDownViewController.changeColourTORA(colourPickerTORA.getValue());
    }

    public void onSelectedTODAColour(ActionEvent actionEvent) {
        topDownViewController.changeColourTODA(colourPickerTODA.getValue());
    }

    public void onSelectedASDAColour(ActionEvent actionEvent) {
        topDownViewController.changeColourASDA(colourPickerASDA.getValue());
    }

    public void o0nSelectedLDAColour(ActionEvent actionEvent) {
        topDownViewController.changeColourLDA(colourPickerLDA.getValue());
    }


    public void rotateNeedle(Double val){
        labelCompass.setText(val + "°");
       UtilsUI.rotateView(needle, val, 3000);
    }
}
