package seg.team9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.logic.Calculator;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {


    private static final Calculator calculator = new Calculator();

    private static final Logger logger = LogManager.getLogger(App.class);
    private Stage primaryStage;
    private static String PRIMARY = "primarywindow";

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + PRIMARY + ".fxml"));


        Parent root = fxmlLoader.load();
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static Calculator getCalculator() {
        return calculator;
    }

    public static void main(String[] args) {
        logger.info("Launching application...");
        launch();
    }


    public static void showPopup(String message , Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}