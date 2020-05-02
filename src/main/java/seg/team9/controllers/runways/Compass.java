package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import seg.team9.App;
import seg.team9.utils.UtilsUI;

import java.io.IOException;

public class Compass extends AnchorPane {

    private String FILE_NAME = "compassView";

    public Compass() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/" + FILE_NAME + ".fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
            needle.setRotate(270);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


    public void rotateNeedle(Double val){
        if(val < 0)
            labelCompass.setText(val+360 + "°");
        else
            labelCompass.setText(val + "°");
        UtilsUI.rotateView(needle, val, 3000);
    }

    @FXML private ImageView needle;
    @FXML private ImageView compass;
    @FXML private Label labelCompass;

}