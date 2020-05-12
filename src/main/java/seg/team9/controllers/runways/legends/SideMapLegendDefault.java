package seg.team9.controllers.runways.legends;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import seg.team9.App;
import seg.team9.utils.UtilsUI;

import java.io.IOException;

public class SideMapLegendDefault extends AnchorPane {

    private String FILE_NAME = "sideMapDefault";

    public SideMapLegendDefault() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + FILE_NAME + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initColors();
    }


    private void initColors() {
        colorObstacle.setFill(UtilsUI.Colors.OBSTACLE);
        colorClearway.setFill(UtilsUI.Colors.CLEARWAY);
        colorStopway.setFill(UtilsUI.Colors.STOPWAY);

    }


    @FXML private Rectangle colorObstacle;
    @FXML private Rectangle colorClearway;
    @FXML private Rectangle colorStopway;

}
