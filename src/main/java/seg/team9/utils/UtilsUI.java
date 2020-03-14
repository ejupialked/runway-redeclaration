package seg.team9.utils;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.util.Duration;


public class UtilsUI {

    public static void showErrorMessage(String error){
        showPopup(error, Alert.AlertType.ERROR);
    }

    public static void showInfoMessage(String info){
        showPopup(info, Alert.AlertType.INFORMATION);
    }

    private static void showPopup(String message , Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(type.name());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public static void rotateView(Node graphics, double angle, long duration){
        RotateTransition rt = new RotateTransition(Duration.millis(duration), graphics);
        rt.setToAngle(angle);
        rt.setAutoReverse(true);
        rt.play();
    }

}
