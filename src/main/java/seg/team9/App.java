package seg.team9;

import com.itextpdf.text.DocumentException;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.logic.PDFGenerator;
import seg.team9.business.logic.Calculator;
import seg.team9.business.logic.XML.XML;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.PrimaryWindowController;
import seg.team9.utils.MockData;
import seg.team9.utils.UtilsUI;

import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Calculator calculator = new Calculator();
    public static final XML xml = new XML();


    private static final Logger logger = LogManager.getLogger(App.class);
    private static Stage primaryWindow;
    private static String PRIMARY = "primarywindow";

    public static ObservableList<Obstacle> obstacleObservableList;
    public static ObservableList<Airport> airportObservableList;

    @Override
    public void init() throws Exception {
        super.init();

        airportObservableList = FXCollections.observableArrayList(MockData.aiports);
        obstacleObservableList =  FXCollections.observableArrayList(MockData.obstacles);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryWindow = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + PRIMARY + ".fxml"));


        Parent root = fxmlLoader.load();
        PrimaryWindowController controller = fxmlLoader.getController();

        controller.setApp(this);
        primaryWindow.setTitle("Runway Re-declaration Software");
        primaryWindow.setScene(new Scene(root));
        primaryWindow.setMaximized(true);
        primaryWindow.setResizable(false);
        primaryWindow.show();
    }


    public static Calculator getCalculator() {
        return calculator;
    }

    public static Stage getPrimaryWindow() {
        return primaryWindow;
    }

    public static void main(String[] args) {
        logger.info("Launching application...");
        launch();
    }

    public void showFile(){
        File file = new File(PDFGenerator.PDF);
        HostServices hostServices = getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

}