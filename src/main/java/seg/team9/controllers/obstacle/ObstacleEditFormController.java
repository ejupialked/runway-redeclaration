package seg.team9.controllers.obstacle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import seg.team9.App;
import seg.team9.business.logic.DistanceCheck;
import seg.team9.business.models.Runway;
import seg.team9.controllers.airport.AirportViewController;
import seg.team9.exceptions.NegativeOrZeroNumberException;
import seg.team9.exceptions.ObstacleAlreadyInUseException;
import seg.team9.exceptions.ObstacleOutsideOfRunwayException;
import seg.team9.exceptions.TextFieldEmptyException;
import seg.team9.utils.MockData;
import seg.team9.utils.UtilsUI;
import seg.team9.business.models.Obstacle;
import seg.team9.controllers.runways.TopDownViewController;

public class ObstacleEditFormController  {

    @FXML private TextField textHeight;
    @FXML private TextField textWidth;
    @FXML private TextField textCenterDist;
    @FXML private TextField textDistLThreshold;
    @FXML private TextField textDistRThreshold;
    @FXML private Button buttonEdit;
    @FXML private TextField textName;
    private Obstacle obstacleSelected;

    public void initForm(Obstacle obstacleSelected, boolean resetDistances){
        this.obstacleSelected = obstacleSelected;
        textName.setText(obstacleSelected.getName());

        textHeight.setText(obstacleSelected.getHeight().toString());
        textWidth.setText(obstacleSelected.getWidth().toString());

        if(resetDistances) {
            textCenterDist.setText("");
            textDistLThreshold.setText("");
            textDistRThreshold.setText("");
        }else{
            textCenterDist.setText(obstacleSelected.getDistanceCenter().toString());
            textDistLThreshold.setText(obstacleSelected.getDistanceLThreshold().toString());
            textDistRThreshold.setText(obstacleSelected.getDistanceRThreshold().toString());
        }
    }

    @FXML
    void onClick(MouseEvent event) {
        try {
            Obstacle obstacle = validateUserInput(false, AirportViewController.getInstance().getComboBoxRunways().getValue(),
                    textName.getText(),
                    textWidth.getText(),
                    textHeight.getText(),
                    textCenterDist.getText(),
                    textDistLThreshold.getText(),
                    textDistRThreshold.getText()
            );
            App.obstacleObservableList.remove(obstacleSelected);
            App.obstacleObservableList.add(obstacle);
           ObstacleViewController.getInstance().getBoxObstacles().getSelectionModel().select(obstacle);
            TopDownViewController.getInstance().updateUI();
            Stage scene = (Stage) buttonEdit.getScene().getWindow();
            scene.close();
            UtilsUI.showInfoMessage("Obstacle: " + obstacleSelected.getName() + " has been edited!");

        } catch (TextFieldEmptyException | ObstacleOutsideOfRunwayException e) {
            UtilsUI.showErrorMessage(e.getMessage());
        } catch (NumberFormatException e){
            UtilsUI.showErrorMessage("Please insert a valid number. " + e.getMessage());
        } catch (Exception e) {
           UtilsUI.showErrorMessage(e.getMessage());
        }
    }


    public Obstacle getObstacleSelected() {
        return obstacleSelected;
    }

    /**
     * Validate all user inputs
     * @throws TextFieldEmptyException if a text field is empty
     */
    public Obstacle validateUserInput(boolean testing, Runway runway, String name, String width, String height, String center,
                                             String left, String right)
            throws TextFieldEmptyException,
            ObstacleOutsideOfRunwayException,
            ObstacleAlreadyInUseException, NegativeOrZeroNumberException {
        Obstacle o = null;

        //check if fields are empty
        if(name.isBlank()) {
            throw new TextFieldEmptyException("Name");
        }
        else if(width.isBlank()) {
            throw new TextFieldEmptyException("Width");
        }
        else if(height.isBlank()) {
            throw new TextFieldEmptyException("Height");
        }
        else if(center.isBlank()) {
            throw new TextFieldEmptyException("Center Distance");
        }
        else if(left.isBlank()) {
            throw new TextFieldEmptyException("Distance threshold (left)");
        }
        else if(right.isBlank()) {
            throw new TextFieldEmptyException("Distance threshold (right)");
        }


        //check if the obstacle has the same name as another obstacle
        if(!testing) {
            for (Obstacle ob : App.obstacleObservableList) {
                if (ob.getName().equalsIgnoreCase(name.replaceAll(" ", "")) && !ob.equals(obstacleSelected)) {
                    throw new ObstacleAlreadyInUseException(name);
                }
            }
        }else{
            for (Obstacle ob : MockData.obstacles) {
                if (ob.getName().equalsIgnoreCase(name.replaceAll(" ", "")) && !ob.equals(obstacleSelected)) {
                    throw new ObstacleAlreadyInUseException(name);
                }
            }
        }


        Double heightD = Double.parseDouble(height);
        Double widthD = Double.parseDouble(width);
        Double centerD = Double.parseDouble(center);
        Double rightD = Double.parseDouble(right);
        Double leftD = Double.parseDouble(left);


        //check if fields are empty
        if(widthD <= 0) {
            throw new NegativeOrZeroNumberException("Width");
        } else if (heightD <= 0) {
            throw new NegativeOrZeroNumberException("Height");
        }


        //check if obstacle are valid
        if (!testing)
            runway = AirportViewController.getInstance().getComboBoxRunways().getValue();

        DistanceCheck.checkObstacleDistances(runway, centerD, rightD, leftD);

        o = new Obstacle(name, heightD, widthD, centerD, rightD, leftD);


        return o;
    }


}
