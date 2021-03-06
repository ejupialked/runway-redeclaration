package seg.team9.controllers.runways.legends;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import seg.team9.App;
import seg.team9.utils.UtilsUI;

import java.io.IOException;

public class SideMapLegend extends AnchorPane {

    private String FILE_NAME = "sideMapLegend";

    public SideMapLegend() {
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
        colorTora.setFill(UtilsUI.Colors.TORA);
        colorToda.setFill(UtilsUI.Colors.TODA);
        colorAsda.setFill(UtilsUI.Colors.ASDA);
        colorLda.setFill(UtilsUI.Colors.LDA);
        colorResa.setFill(UtilsUI.Colors.RESA);
        colorObstacle.setFill(UtilsUI.Colors.OBSTACLE);
        colorBlast.setFill(UtilsUI.Colors.BLAST);
        colorClearway.setFill(UtilsUI.Colors.CLEARWAY);
        colorStopway.setFill(UtilsUI.Colors.STOPWAY);

    }

    @FXML
    private Rectangle colorResa;
    @FXML private Rectangle colorTora;
    @FXML private Rectangle colorToda;
    @FXML private Rectangle colorLda;
    @FXML private Rectangle colorObstacle;
    @FXML private Rectangle colorAsda;
    @FXML private Rectangle colorBlast;
    @FXML private Rectangle colorClearway;
    @FXML private Rectangle colorStopway;

}
