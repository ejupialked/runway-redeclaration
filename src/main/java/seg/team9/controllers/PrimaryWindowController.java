package seg.team9.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.StringConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Obstacle;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryWindowController implements Initializable {
    private static final Logger logger = LogManager.getLogger("PrimaryWindowController");


    // Inject ui components.
    @FXML private TabPane tabPaneRunways;

    // Inject tab content
    @FXML private Tab sideViewTab;
    @FXML private Tab topDownViewTab;
    @FXML private ChoiceBox<Obstacle> boxObstacles;
    // Inject tab controller
    @FXML private SideViewController sideViewController; // id tab + "Controller"
    @FXML private TopDownViewController topDownViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTabPane();
        initObstacleBox();
    }

    private void initObstacleBox() {
        boxObstacles.setConverter(new StringConverter<Obstacle>() {

            //Obstacle.toString() returns the name of the obstacle
            @Override
            public String toString(Obstacle object) {
                return  object.getName();
            }

            //Gets the first object that matches the given name
            @Override
            public Obstacle fromString(String string) {
                return boxObstacles.getItems().stream().filter(obstacle -> obstacle.getName().equals(string)).findFirst().orElse(null);
            }
        });

        //Adding mock objects (U13 - Predefined obstacles)
        boxObstacles.getItems().add(new Obstacle("Unselect",0.0,0.0,0.0,0.0));
        boxObstacles.getItems().add(new Obstacle("Object1",10.0,10.0,10.0,20.0));
        boxObstacles.getItems().add(new Obstacle("Object2",11.0,9.0,100.0,20.0));
        boxObstacles.getItems().add(new Obstacle("Object3",12.0,11.0,20.0,20.0));
        boxObstacles.getItems().add(new Obstacle("Object4",13.0,12.0,30.0,20.0));

        boxObstacles.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null)
                logger.info("Selected object: " + newValue.getName());
        }));
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
