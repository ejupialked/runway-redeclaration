package seg.team9.controllers.obstacle;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.utils.MockData;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.runways.TopDownViewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("ObstacleViewController");

    private static ObstacleViewController instance;


    @FXML private ChoiceBox<Obstacle> boxObstacles;
    @FXML private Label txtObstacleName;
    @FXML private Label txtObstacleHeight;
    @FXML private Label txtObstacleWidth;
    @FXML private Label txtDistanceCenter;
    @FXML private Label txtDistanceThresholdLeft;
    @FXML private Label txtDistanceThresholdRight;

    private Obstacle selectedObstacle;


    public ObstacleViewController() {
        instance = this;
    }

    public static ObstacleViewController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init ObstacleViewController");
        initObstacleBox();
    }

    public void initObstacleBox() {
        logger.info("init ObstacleBox");

        //Adding mock objects (U13 - Predefined obstacles)
        MockData.obstacleList().addListener(new ListChangeListener<Obstacle>() {
            @Override
            public void onChanged(Change<? extends Obstacle> change) {
                // update everything
            }
        });


        boxObstacles.setItems(MockData.obstacles);
        boxObstacles.getSelectionModel().selectFirst();

        setSelectedObstacle(boxObstacles.getValue());

        //When an obstacle is selected
        boxObstacles.valueProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue!=null)
                TopDownViewController.getInstance().displayObstacleSelected(newValue);
        }));

        //When an obstacle is selected
        boxObstacles.getSelectionModel().selectedItemProperty().addListener((observableValue, obstacle, t1) -> {
            setSelectedObstacle(t1);
            updateLabelsObstacle(t1);
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
        try {
            root = FXMLLoader.load(ObstacleViewController.class.getResource("/view/"+formName+".fxml"));
            Stage stage = new Stage();
            stage.setTitle("Add Obstacle");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            logger.error(e);
        }
    }

    @FXML
    void onEditObstacle(MouseEvent event) {
        final String formName= "obstacleeditform";
        Parent root;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ObstacleEditFormController.class.getResource("/view/"+formName+".fxml"));
            root = fxmlLoader.load();

            ObstacleEditFormController obstacleEditFormController = fxmlLoader.getController();
            obstacleEditFormController.initForm(selectedObstacle);

            Stage stage = new Stage();
            stage.setTitle("Edit Obstacle");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            logger.error(e);
        }

    }

    public void updateLabelsObstacle(Obstacle o) {
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