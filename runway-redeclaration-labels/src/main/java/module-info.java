module seg.team9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;

    opens seg.team9.controllers.calculation to javafx.fxml;
    opens seg.team9.controllers.obstacle to javafx.fxml;
    opens seg.team9.controllers.runways to javafx.fxml;
    opens seg.team9.controllers to javafx.fxml;


    exports seg.team9;
}