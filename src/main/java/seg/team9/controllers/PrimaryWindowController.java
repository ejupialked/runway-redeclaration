package seg.team9.controllers;

import com.itextpdf.text.DocumentException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.logic.PDFGenerator;
import seg.team9.business.logic.XML.XMLExporter;
import seg.team9.business.logic.XML.XMLImporter;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.airport.AirportViewController;
import seg.team9.controllers.runways.*;
import seg.team9.utils.MockData;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Runway;
import seg.team9.controllers.calculation.CalculationsViewController;
import seg.team9.controllers.obstacle.ObstacleViewController;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import seg.team9.utils.UtilsUI.DialogDirectoryChooser;
import seg.team9.utils.UtilsUI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");

    private static PrimaryWindowController instance;

    MapLegend sideLegend = new MapLegend();
    MapLegend topLegend = new MapLegend();

    // Injecting ui components.
    @FXML private TabPane tabPaneRunways;
    @FXML private MenuBar menuBar; //menu bar

    // Injecting colour pickers
    @FXML private ColorPicker colourPickerTORA;
    @FXML private ColorPicker colourPickerTODA;
    @FXML private ColorPicker colourPickerLDA;
    @FXML private ColorPicker colourPickerASDA;
    @FXML private SplitPane splitPaneView;

    private ArrayList<AnchorPane> lightPanes;
    private ArrayList<AnchorPane> darkPanes;

    @FXML private AnchorPane paneView;


    private App app;

    public PrimaryWindowController() {
        instance = this;
    }
    public static PrimaryWindowController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuBar();
        initArrays();
        initSplitPane();
        initTabPane();
    }


    void initSplitPane() {
        logger.info(splitPaneView.getDividers().get(0).getPosition());
    }

    private void initArrays() {
        lightPanes = new ArrayList<>();
        darkPanes = new ArrayList<>();
    }

    private void initMenuBar() {
    }

    private void initTabPane() {
        tabPaneRunways.getSelectionModel().selectedItemProperty().addListener((observableValue, oldv, newv) -> {
            if (newv.getId().equals("topDownViewTab")) {
                TopDownViewController.getInstance().isSelected = true;
                SideViewController.getInstance().isSelected = false;
            } else {
                TopDownViewController.getInstance().isSelected = false;
                SideViewController.getInstance().isSelected = true;
            }
            TopDownViewController.getInstance().updateUI();
            SideViewController.getInstance().updateUI();
        });
    }


    public void onPrintBreakdownClick(ActionEvent actionEvent) {
        int choice;
        try {
            Airport a = AirportViewController.getInstance().getChoiceBoxAirport().getValue();
            Obstacle o = ObstacleViewController.getInstance().getBoxObstacles().getValue();
            Runway r = AirportViewController.getInstance().getComboBoxRunways().getValue();

            new PDFGenerator(a, o, r);
            choice = UtilsUI.showPopupWithButtons("You can either open and print the " +
                    "report or simply save it.", Alert.AlertType.INFORMATION);
        } catch (IOException | DocumentException e) {
            UtilsUI.showErrorMessage(e.getMessage());
            return;
        }

        if (choice == 3) {
            App.getPrimaryWindow().getScene().setCursor(Cursor.WAIT);
            app.showFile();
            App.getPrimaryWindow().getScene().setCursor(Cursor.DEFAULT);
        } else if (choice == 2) {
            DialogDirectoryChooser ddc = new DialogDirectoryChooser(App.getPrimaryWindow());
            File fileSaved = null; //returns location of the fil
            try {
                fileSaved = ddc.saveFile();
                if(fileSaved != null){
                    UtilsUI.showInfoMessage("The report can be found here: " + fileSaved.getAbsolutePath());
                }
            } catch (Exception e) {
                UtilsUI.showErrorMessage(e.getMessage());
            }
        }
    }

    public void onObstacleExportClick(ActionEvent actionEvent) {
        XMLExporter xmlExporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("XML format(*.xml)","*.xml"));
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

        boolean check = xmlExporter.exportAirport(AirportViewController.getInstance().getChoiceBoxAirport().getValue(),file);

        if (check) {
            logger.info("Exported successfully");
            UtilsUI.showInfoMessage("Airport: " + AirportViewController.getInstance().getChoiceBoxAirport().getValue().getName()
                    + " has been exported to an XML file.");
        }
        else {
            logger.info("Exporting went wrong");
            UtilsUI.showErrorMessage("Something went wrong while trying to export the airport: "
                    + AirportViewController.getInstance().getChoiceBoxAirport().getValue().getName() + " to an XML file.");
        }

    }

    public void onObstacleImportClick(ActionEvent actionEvent) {
        XMLImporter xmlImporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        File file = fileChooser.showOpenDialog(new Stage());
        List<Obstacle> list = xmlImporter.importObstacles(file);
        App.obstacleObservableList.addAll(list);

        UtilsUI.showInfoMessage("Obstacles from file " + file.getName() +" has been imported to the application.");

    }

    public void onAirportImportClick(ActionEvent actionEvent) {
        XMLImporter xmlImporter = App.xml;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose file to import");
        File file = fileChooser.showOpenDialog(new Stage());
        Airport airport = xmlImporter.importAirport(file);
        App.airportObservableList.add(airport);
        logger.info("Imported Airport : " + airport.getName());

        airport.getRunwayList()
                .forEach(r -> logger.info("With runway : " + r.toString()));


        UtilsUI.showInfoMessage("Airport from file " + file.getName()
                +" named " + airport.getName() + " has been imported to the application.");

    }


        public MapLegend getSideLegend () {
            return sideLegend;
        }

        public MapLegend getTopLegend () {
            return topLegend;
        }

        public void setApp (App app){
            this.app = app;
        }

    public void onDefaultColourClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().isColorDefault = true;
        TopDownViewController.getInstance().initArrowsColoursDefault();
    }

    public void onColourBlindColourClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().isColorDefault = false;
        TopDownViewController.getInstance().initArrowsColors();
    }
}

