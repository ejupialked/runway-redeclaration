package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seg.team9.Exceptions.TextFieldEmptyException;
import seg.team9.Utils.UtilsUI;
import seg.team9.business.models.Obstacle;

public class ObstacleEditFormController  {

    @FXML private TextField textHeight;
    @FXML private TextField textWidth;
    @FXML private TextField textCenterDist;
    @FXML private TextField textDistLThreshold;
    @FXML private TextField textDistRThreshold;
    @FXML private Button buttonEdit;
    @FXML private TextField textName;
    private Obstacle obstacleSelected;

    public void initForm(Obstacle obstacleSelected){
        this.obstacleSelected = obstacleSelected;
        textHeight.setText(obstacleSelected.getHeight().toString());
        textWidth.setText(obstacleSelected.getWidth().toString());
        textCenterDist.setText(obstacleSelected.getDistanceCenter().toString());
        textDistLThreshold.setText(obstacleSelected.getDistanceLThreshold().toString());
        textDistRThreshold.setText(obstacleSelected.getDistanceRThreshold().toString());
        textName.setText(obstacleSelected.getName());
    }

    @FXML
    void onClick(MouseEvent event) {

        try {
            validateUserInput();
        } catch (TextFieldEmptyException e) {
            UtilsUI.showErrorMessage(e.getMessage());
        }

        //get scene
        Stage scene = (Stage) buttonEdit.getScene().getWindow();

        obstacleSelected.setName(textName.getText());
        obstacleSelected.setDistanceCenter(Double.parseDouble(textCenterDist.getText()));
        obstacleSelected.setDistanceLThreshold(Double.parseDouble(textDistLThreshold.getText()));
        obstacleSelected.setDistanceRThreshold(Double.parseDouble(textDistRThreshold.getText()));
        obstacleSelected.setWidth(Double.parseDouble(textWidth.getText()));
        obstacleSelected.setHeight(Double.parseDouble(textHeight.getText()));

        //updates labels in the obstacle view
        ObstacleViewController.getInstance().updateLabelsObstacle(obstacleSelected);

        scene.close();
    }

    /**
     * Validate all user inputs
     * @throws TextFieldEmptyException if a text field is empty
     */
    public void validateUserInput() throws TextFieldEmptyException {
        if(textName.getText().equals("")) {
            throw new TextFieldEmptyException("Name");
        }
        else if(textWidth.getText().equals("")) {
            throw new TextFieldEmptyException("Width");
        }
        else if(textHeight.getText().equals("")) {
            throw new TextFieldEmptyException("Height");
        }
        else if(textCenterDist.getText().equals("")) {
            throw new TextFieldEmptyException("Center Distance");
        }
        else if(textDistLThreshold.getText().equals("")) {
            throw new TextFieldEmptyException("Distance threshold (left)");
        }
        else if(textDistRThreshold.getText().equals("")) {
            throw new TextFieldEmptyException("Distance threshold (right)");
        }
    }

}
