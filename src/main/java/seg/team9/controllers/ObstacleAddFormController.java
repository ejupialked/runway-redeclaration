package seg.team9.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seg.team9.App;
import seg.team9.Utils.MockData;
import seg.team9.business.models.Obstacle;

import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleAddFormController implements Initializable {

    @FXML
    private AnchorPane obstacleAddForm;

    @FXML
    private TextField textHeight;

    @FXML
    private TextField textWidth;

    @FXML
    private TextField textCenterDist;

    @FXML
    private TextField textDistLThrreshold;

    @FXML
    private TextField textDistRThrreshold;

    @FXML
    private Button buttonFinish;

    @FXML
    private TextField textName;


    public void test(){
        System.out.println("testing");
    }


    @FXML
    void onClick(MouseEvent event) {
        if(textName.getText().equals(""))
        {
            App.showPopup("Please complete the Name text box.", Alert.AlertType.ERROR);
        }
        else if(textWidth.getText().equals("")) {
            App.showPopup("Please complete the Width text box.", Alert.AlertType.ERROR);
        }
        else if(textHeight.getText().equals(""))
        {
            App.showPopup("Please complete the Height text box.", Alert.AlertType.ERROR);
        }
        else if(textCenterDist.getText().equals(""))
        {
            App.showPopup("Please complete the Center Distance text box.", Alert.AlertType.ERROR);
        }
        else if(textDistLThrreshold.getText().equals(""))
        {
            App.showPopup("Please complete the Distance Left Threshold text box.", Alert.AlertType.ERROR);
        }
        else if(textDistRThrreshold.getText().equals(""))
        {
            App.showPopup("Please complete the Distance Right Threshold text box.", Alert.AlertType.ERROR);
        }
        else
        {
            MockData.addObstacle(new Obstacle(textName.getText(),Double.parseDouble(textHeight.getText()),
                    Double.parseDouble(textWidth.getText()),Double.parseDouble(textCenterDist.getText()),
                    Double.parseDouble(textDistRThrreshold.getText()),Double.parseDouble(textDistLThrreshold.getText())));
            Stage scene = (Stage) buttonFinish.getScene().getWindow();
            scene.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}