package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seg.team9.App;
import seg.team9.Utils.MockData;
import seg.team9.business.models.Obstacle;

import java.net.URL;
import java.util.ResourceBundle;

public class ObstacleEditFormController  {
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
    private Button buttonEdit;

    @FXML
    private TextField textName;

    @FXML
    private Obstacle obstacleSelected;






    public void initForm(){
        textHeight.setText(obstacleSelected.getHeight().toString());

        textWidth.setText(obstacleSelected.getWidth().toString());

        textCenterDist.setText(obstacleSelected.getDistanceCenter().toString());

        textDistLThrreshold.setText(obstacleSelected.getDistanceLThreshold().toString());

        textDistRThrreshold.setText(obstacleSelected.getDistanceRThreshold().toString());

        textName.setText(obstacleSelected.getName());
    }


    public void setEditItem(Obstacle o){
        this.obstacleSelected = o;
    }

    public Obstacle getObstacleSelected() {
        return obstacleSelected;
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
//            obstacleSelected = new Obstacle(textName.getText(),Double.parseDouble(textHeight.getText()),
//                    Double.parseDouble(textWidth.getText()),Double.parseDouble(textCenterDist.getText()),
//                    Double.parseDouble(textDistRThrreshold.getText()),Double.parseDouble(textDistLThrreshold.getText()));
            Stage scene = (Stage) buttonEdit.getScene().getWindow();

            obstacleSelected.setName(textName.getText());
            obstacleSelected.setDistanceCenter(Double.parseDouble(textCenterDist.getText()));
            obstacleSelected.setDistanceLThreshold(Double.parseDouble(textDistLThrreshold.getText()));
          //  obstacleSelected.getDistanceRThreshold(Double.parseDouble(textDistRThrreshold.getText()));
            obstacleSelected.setWidth(Double.parseDouble(textWidth.getText()));
            obstacleSelected.setHeight(Double.parseDouble(textHeight.getText()));


            System.out.println(obstacleSelected.toString());

            scene.close();
        }
    }
}
