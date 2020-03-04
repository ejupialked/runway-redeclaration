package seg.team9.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;


public class SideViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");


    @FXML Button zoomInButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Init Side View");
    }


    @FXML
    public void zoomMap(ActionEvent actionEvent) {
        logger.info("Zooming in map..");
    }
}
