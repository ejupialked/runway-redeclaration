package seg.team9;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.logic.Calculator;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;
import seg.team9.utils.MockData;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static final Calculator calculator = new Calculator();

    private static final Logger logger = LogManager.getLogger(App.class);
    private Stage primaryWindow;
    private static String PRIMARY = "primarywindow";

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryWindow = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + PRIMARY + ".fxml"));

        Parent root = fxmlLoader.load();
        primaryWindow.setTitle("Runway-Redeclaration");
        primaryWindow.setScene(new Scene(root));
        primaryWindow.setMaximized(true);
        primaryWindow.setResizable(false);
        primaryWindow.show();
    }

    public static Calculator getCalculator() {
        return calculator;
    }

    public static void main(String[] args) {
        logger.info("Launching application...");
        launch();
    }

    public static ObservableList<Obstacle> obstacleObservableList(){
        return FXCollections.observableArrayList(MockData.obstacles);
    }

    public static ObservableList<Airport> airportObservableList(){
        return FXCollections.observableArrayList(MockData.aiports);
    }


}