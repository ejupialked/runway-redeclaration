package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init TopDownViewController");
    }
}
