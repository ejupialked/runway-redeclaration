module seg.team9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;

    opens seg.team9.controllers to javafx.fxml;
    exports seg.team9;
}