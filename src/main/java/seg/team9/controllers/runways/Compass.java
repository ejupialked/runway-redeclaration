package seg.team9.controllers.runways;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.utils.UtilsUI;

import java.io.IOException;

public class Compass extends AnchorPane {
    private static final Logger logger = LogManager.getLogger("Compass");

    private String FILE_NAME = "compassView";

    public Compass() {
        logger.info("init compass..");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + FILE_NAME + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
            initButtons();
            needle.setRotate(270);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    private void initButtons() {
        align.setOnAction(this::onAlignClick);
        horizontal.setOnAction(this::onHorizontalClick);
    }


    public void rotateNeedle(Double val){
        if(val < 0)
            labelCompass.setText(val+360 + "°");
        else
            labelCompass.setText(val + "°");
        UtilsUI.rotateView(needle, val, 3000);
    }


    public void onAlignClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().rotateRunway();
        TopDownViewController.getInstance().isHorizontal = false;

    }

    public void onHorizontalClick(ActionEvent actionEvent) {
        TopDownViewController.getInstance().rotateRunwayHorizontal();
    }

    @FXML private Button horizontal;
    @FXML private Button align;
    @FXML private ImageView needle;
    @FXML private ImageView compass;
    @FXML private Label labelCompass;

}