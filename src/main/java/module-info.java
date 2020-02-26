module org.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens seg.team9.controllers to javafx.fxml;
    exports seg.team9;
}