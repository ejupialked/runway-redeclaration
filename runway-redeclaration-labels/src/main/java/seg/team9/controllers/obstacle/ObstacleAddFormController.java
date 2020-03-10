package seg.team9.controllers.obstacle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seg.team9.exceptions.TextFieldEmptyException;
import seg.team9.utils.MockData;
import seg.team9.utils.UtilsUI;
import seg.team9.business.models.Obstacle;

public class ObstacleAddFormController {

    @FXML private TextField textHeight;
    @FXML private TextField textWidth;
    @FXML private TextField textCenterDist;
    @FXML private TextField textDistLThreshold;
    @FXML private TextField textDistRThreshold;
    @FXML private Button buttonFinish;
    @FXML private TextField textName;

    @FXML
    void onClick(MouseEvent event) {

        try {
            validateUserInput();
        } catch (TextFieldEmptyException e) {
            UtilsUI.showErrorMessage(e.getMessage());
        }
        Obstacle o = new Obstacle(
                    textName.getText(),
                    Double.parseDouble(textHeight.getText()),
                    Double.parseDouble(textWidth.getText()),
                    Double.parseDouble(textCenterDist.getText()),
                    Double.parseDouble(textDistRThreshold.getText()),
                    Double.parseDouble(textDistLThreshold.getText()));

            MockData.addObstacle(o);

            Stage scene = (Stage) buttonFinish.getScene().getWindow();
            scene.close();

            UtilsUI.showInfoMessage("Obstacle: " + o.getName() + " has been added!");
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
