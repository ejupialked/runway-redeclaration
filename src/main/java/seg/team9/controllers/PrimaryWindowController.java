package seg.team9.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");


    // Inject ui components.
    @FXML private TabPane tabPaneRunways;

    // Inject tab content
    @FXML private Tab sideViewTab;
    @FXML private Tab topDownViewTab;

    // Inject tab controller
    @FXML private SideViewController sideViewController; // id tab + "Controller"
    @FXML private TopDownViewController topDownViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTabPane();
    }


    public void initTabPane(){
        tabPaneRunways.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) -> {
            if (newValue == sideViewTab) {
                System.out.println("Side View Tab");
            } else if (newValue == topDownViewTab) {
                System.out.println("Top Down View Tab");
            } else {
                System.out.println("- another Tab -");
            }
        });
    }
}
