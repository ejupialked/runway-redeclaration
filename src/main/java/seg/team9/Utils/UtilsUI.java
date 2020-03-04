package seg.team9.Utils;

import javafx.scene.control.Alert;
import seg.team9.App;

public class UtilsUI {

    public static void showErrorMessage(String error){
        showPopup(error, Alert.AlertType.ERROR);
    }

    private static void showPopup(String message , Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(type.name());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
