package seg.team9.utils;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class UtilsUI {

    public static void showErrorMessage(String error){
        showPopup(error, Alert.AlertType.ERROR);
    }

    public static void showInfoMessage(String info){
        showPopup(info, Alert.AlertType.INFORMATION);
    }
    public static String addUnitMeasurement(String value) {
        return value + "m";
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

    public static class Colors {
        public static Color TORA = Color.YELLOW;
        public static Color TODA = Color.CORAL;
        public static Color ASDA = Color.MAGENTA;
        public static Color LDA = Color.CYAN;
        public static Color RESA = Color.LAWNGREEN;
        public static Color BLAST = Color.BROWN;
        public static Color OBSTACLE = Color.RED;




    }
}
