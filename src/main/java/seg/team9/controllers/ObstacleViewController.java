package seg.team9.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.Utils.MockData;
import seg.team9.business.models.Obstacle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("ObstacleViewController");

    @FXML
    private ChoiceBox<Obstacle> boxObstacles;



    @FXML
    private Label txtObstacleName;

    @FXML
    private Label txtObstacleHeight;

    @FXML
    private Label txtObstacleWidth;

    @FXML
    private Label txtDistanceCenter;

    @FXML
    private Label txtDistanceThresholdLeft;

    @FXML
    private Label txtDistanceThresholdRight;

    private Obstacle selectedObstacle;



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
        MockData.obstacleList();
        boxObstacles.setItems(MockData.obstacles);
        boxObstacles.getSelectionModel().selectFirst();

        boxObstacles.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null)
                TopDownViewController.getInstance().displayObstacleSelected(newValue);
        }));


        boxObstacles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Obstacle>() {
            @Override
            public void changed(ObservableValue<? extends Obstacle> observableValue, Obstacle obstacle, Obstacle t1) {
                setSelectedObstacle(t1);
                updateLabelsObstacle(t1);
            }
        });

        TopDownViewController.getInstance().displayObstacleSelected(boxObstacles.getSelectionModel().getSelectedItem());
    }


    public void setSelectedObstacle(Obstacle selectedObstacle) {
        this.selectedObstacle = selectedObstacle;
    }

    @FXML
    void onClick(MouseEvent event) {
        final String formName= "obstacleaddform";
        Parent root;
        try
        {
            root = FXMLLoader.load(ObstacleViewController.class.getResource("/view/"+formName+".fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Obstacle");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }



    @FXML
    void onEditObstacle(MouseEvent event) {
        final String formName= "obstacleeditform";
        Parent root;
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(ObstacleEditFormController.class.getResource("/view/"+formName+".fxml"));

            root = fxmlLoader.load();

            ObstacleEditFormController obstacleEditFormController = fxmlLoader.getController();
            obstacleEditFormController.setEditItem(selectedObstacle);
            obstacleEditFormController.initForm();

            //root = FXMLLoader.load(ObstacleViewController.class.getResource("/view/"+formName+".fxml"));
            Stage stage = new Stage();
            stage.setTitle("Edit Obstacle");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

    }

    public void updateLabelsObstacle(Obstacle o) {

        System.out.println(o.toString());
          txtObstacleName.setText(o.getName());
          txtObstacleHeight.setText(addUnitMeasurement(o.getHeight().toString()));
          txtObstacleWidth.setText(addUnitMeasurement(o.getWidth().toString()));
          txtDistanceCenter.setText(addUnitMeasurement(o.getDistanceCenter().toString()));
          txtDistanceThresholdLeft.setText(addUnitMeasurement(o.getDistanceLThreshold().toString()));
          txtDistanceThresholdRight.setText(o.getDistanceRThreshold().toString());
    }


    private String addUnitMeasurement(String value) {
        return value + "m";
    }

}
