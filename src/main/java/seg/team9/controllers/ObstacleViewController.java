package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.Utils.MockData;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("ObstacleViewController");

    @FXML private ChoiceBox<Obstacle> boxObstacles;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init ObstacleViewController");
        initObstacleBox();
    }

    public void initObstacleBox() {
        logger.info("init ObstacleBox");
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
        boxObstacles.getItems().addAll(MockData.obstacleList());
        boxObstacles.getSelectionModel().selectFirst();

        boxObstacles.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null)
                logger.info("Selected object: " + newValue.getName());
        }));

        boxObstacles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Obstacle>() {
            @Override
            public void changed(ObservableValue<? extends Obstacle> observable, Obstacle oldValue, Obstacle newValue) {
                TopDownViewController.getInstance().displayObstacleSelected(newValue);
            }
        });
    }


}
