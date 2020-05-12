package seg.team9.utils;

import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;


import java.io.File;

import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import seg.team9.business.logic.PDFGenerator;

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

    public static void showPopup(String message , Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(type.name());
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int  showPopupWithButtons(String message, Alert.AlertType type) {
        ButtonType showPDF = new ButtonType("Open", ButtonBar.ButtonData.OTHER);
        ButtonType save = new ButtonType("Save as...", ButtonBar.ButtonData.OTHER);
        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(type, message, showPDF, save, close);
        alert.setTitle("Information");
        alert.setHeaderText("Your calculation report has been generated!");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == close)
            return 1;
        else if (result.get() == save)
            return 2;
        else if (result.get() == showPDF)
            return 3;

        return 1;
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
        public static Color DEFAULT = Color.WHITE;
        public static Color DIRECTION = Color.WHITE;

        public static Color CLEARWAY = Color.PURPLE;
        public static Color STOPWAY = Color.DARKGRAY;


    }


    public static class DialogDirectoryChooser  {
        Stage primaryStage;
        public DialogDirectoryChooser(Stage primaryStage)  {
            this.primaryStage = primaryStage;
        }


        public File saveFile() throws IOException {
            File file = new File(PDFGenerator.PDF);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Report as");

            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);


            File fileSaved = fileChooser.showSaveDialog(primaryStage);
            if (fileSaved != null) {

                if (fileSaved.getName().contains(".")) {
                    String name = fileSaved.getAbsolutePath();
                    name = name.replace(".", "-");
                    name = name + "_" + PDFGenerator.date;
                    File newName = new File(name);
                    FileUtils.copyFile(file, newName);
                    return newName;
                }

                String name = fileSaved.getAbsolutePath();
                name = name + "_" + PDFGenerator.date;
                File newName = new File(name);
                FileUtils.copyFile(file, newName);
                return newName;

            }else {
                return null;
            }
        }
    }

}
