package seg.team9.controllers.obstacle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.PrimaryWindowController;
import seg.team9.controllers.runways.SideViewController;
import seg.team9.controllers.runways.TopDownViewController;
import seg.team9.utils.UtilsUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
    @FXML private AnchorPane paneObstacles;
    private Obstacle selectedObstacle;
    private HashMap<CheckBox,Obstacle> checkToObst = new HashMap<CheckBox, Obstacle>();


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


        boxObstacles.setItems(App.obstacleObservableList);
        boxObstacles.getSelectionModel().selectFirst();
        updateLabelsObstacle(boxObstacles.getValue());

        setSelectedObstacle(boxObstacles.getValue());
        TopDownViewController.getInstance().displayObstacleSelected(boxObstacles.getValue());



        //When an obstacle is selected
        boxObstacles.getSelectionModel().selectedItemProperty().addListener((observableValue, obstacle, t1) -> {
            setSelectedObstacle(t1);
            updateLabelsObstacle(t1);
            TopDownViewController.getInstance().displayObstacleSelected(t1);
            SideViewController.getInstance().updateUI();
        });



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
            stage.setResizable(false);

            //prevent user from interacting with main view
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(App.getPrimaryWindow());

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
            stage.setResizable(false);
            stage.setScene(new Scene(root));

            //prevent user from interacting with main view
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(App.getPrimaryWindow());

            stage.show();
        }
        catch (IOException e) {
            logger.error(e);
        }

    }


    public void updateCheckbox(){
        boxObstacles.getItems().clear();
        boxObstacles.setItems(App.obstacleObservableList);
        TopDownViewController.getInstance().updateUI();
    }
    public void updateLabelsObstacle(Obstacle o) {
         txtObstacleName.setText(o.getName());
         txtObstacleHeight.setText(UtilsUI.addUnitMeasurement(o.getHeight().toString()));
         txtObstacleWidth.setText(UtilsUI.addUnitMeasurement(o.getWidth().toString()));
         txtDistanceCenter.setText(UtilsUI.addUnitMeasurement(o.getDistanceCenter().toString()));
         txtDistanceThresholdLeft.setText(UtilsUI.addUnitMeasurement(o.getDistanceLThreshold().toString()));
         txtDistanceThresholdRight.setText(o.getDistanceRThreshold().toString());

    }


    public ChoiceBox<Obstacle> getBoxObstacles() {
        return boxObstacles;
    }

    public AnchorPane getPaneObstacles() {
        return paneObstacles;
    }
}
