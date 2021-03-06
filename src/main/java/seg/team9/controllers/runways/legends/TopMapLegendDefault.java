package seg.team9.controllers.runways.legends;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.shape.Rectangle;
        import seg.team9.App;
        import seg.team9.utils.UtilsUI;

        import java.io.IOException;

public class TopMapLegendDefault extends AnchorPane {

    private String FILE_NAME = "topMapLegendDefault";

    public TopMapLegendDefault() {
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
    }


    @FXML
    private Rectangle colorObstacle;
}
