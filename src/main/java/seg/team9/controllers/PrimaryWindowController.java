package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.logic.XML.XMLExporter;
import seg.team9.business.logic.XML.XMLImporter;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.runways.Compass;
import seg.team9.controllers.runways.MapLegend;
import seg.team9.utils.MockData;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.calculation.CalculationsViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;
import seg.team9.utils.UtilsUI;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    private static PrimaryWindowController instance;

    MapLegend sideLegend = new MapLegend();
    MapLegend topLegend = new MapLegend();

    Compass sideCompass = new Compass();
    Compass topCompass = new Compass();

    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar
    @FXML private MenuItem menuItemClose;
    @FXML private ChoiceBox<Airport> choiceBoxAirport;
    @FXML private ComboBox<Runway> comboBoxRunways;
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


    // -fx-background-color #E0E0E0
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
        initMenuBar();
        initChoiceBoxes();
        initArrays();
        initSplitPane();
        initTabPane();
    }


    void initSplitPane(){
        logger.info(splitPaneView.getDividers().get(0).getPosition());
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
        choiceBoxAirport.setItems(App.airportObservableList);
        choiceBoxAirport.getSelectionModel().selectFirst();

        Airport a = choiceBoxAirport.getValue();

        initComboBox(a);


        topDownViewController.displayDirectedRunwaySelected(comboBoxRunways.getSelectionModel().getSelectedItem());
        sideViewController.updateUI();

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
            if(t1 != null) {
                topDownViewController.displayDirectedRunwaySelected(observableValue.getValue());
                sideViewController.updateUI();
            }

            topDownViewController.initArrowsColors();
            logger.info("Changed colours");
        });

    }


    public void initComboBox(Airport a){
        comboBoxRunways.setPromptText("Select runway");
        comboBoxRunways.setItems(FXCollections.observableArrayList(a.getRunwayList()));
        comboBoxRunways.getSelectionModel().selectFirst();
    }

    private void initMenuBar(){
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

    public ChoiceBox<Airport> getChoiceBoxAirport() {
        return choiceBoxAirport;
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





    public void onPrintBreakdownClick(ActionEvent actionEvent) {
    }

    public void onObstacleExportClick(ActionEvent actionEvent) {
        XMLExporter xmlExporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML format(*.xml)","*. xml"));
        File file = fileChooser.showSaveDialog(new Stage());
        if(file == null)
            return;

        if(!file.getName().contains(".xml"))
            file = new File(file.getAbsolutePath()+".xml");

        boolean check = xmlExporter.exportObstacles(ObstacleViewController.getInstance().getBoxObstacles().getValue(),file);
        if (check) {
            logger.info("Exported successfully");
            UtilsUI.showInfoMessage("The obstacles have been successfully exported to an XML file.");
        }
        else {
            logger.info("Exporting went wrong");
            UtilsUI.showErrorMessage("Something went wrong while trying to export the obstacles to an XML file.");
        }
    }

    public void onAirportExportClick(ActionEvent actionEvent) {
        XMLExporter xmlExporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML format(*.xml)","*.xml"));
        File file = fileChooser.showSaveDialog(new Stage());
        if(file == null)
            return;

        if(!file.getName().contains(".xml"))
            file = new File(file.getAbsolutePath()+".xml");

        boolean check = xmlExporter.exportAirport(getChoiceBoxAirport().getValue(),file);
        if (check) {
            logger.info("Exported successfully");
            UtilsUI.showInfoMessage("Airport: " + getChoiceBoxAirport().getValue().getName() + " has been exported to an XML file.");
        }
        else {
            logger.info("Exporting went wrong");
            UtilsUI.showErrorMessage("Something went wrong while trying to export the airport: " + getChoiceBoxAirport().getValue().getName() + " to an XML file.");
        }

    }

    public void onObstacleImportClick(ActionEvent actionEvent) {
        XMLImporter xmlImporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        File file = fileChooser.showOpenDialog(new Stage());
//        MockData.obstacles.addAll(xmlImporter.importObstacles(file));
        List<Obstacle> list = xmlImporter.importObstacles(file);
        for(Obstacle o : list)
            App.obstacleObservableList.add(o);
    }

    public void onAirportImportClick(ActionEvent actionEvent) {
        XMLImporter xmlImporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        File file = fileChooser.showOpenDialog(new Stage());
        Airport airport = xmlImporter.importAirport(file);
        App.airportObservableList.add(airport);
        logger.info("Imported Airport : " + airport.getName());
        for(Runway runway : airport.getRunwayList())
            logger.info("With runway : " + runway.toString());
    }


    public Compass getSideCompass() {
        return sideCompass;
    }

    public Compass getTopCompass() {
        return topCompass;
    }

    public MapLegend getSideLegend() {
        return sideLegend;
    }

    public MapLegend getTopLegend() {
        return topLegend;
    }
}
