package seg.team9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
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


    public static void main(String[] args) {
        logger.info("Launching application...");
        launch();
    }

}