module seg.team9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;
    requires java.xml;
    requires itextpdf;
    requires javafx.swing;
    requires layout;
    requires commons.io;

    opens seg.team9.controllers.calculation to javafx.fxml;
    opens seg.team9.controllers.breakdown to javafx.fxml;
    opens seg.team9.controllers.obstacle to javafx.fxml;
    opens seg.team9.controllers.runways to javafx.fxml;
    opens seg.team9.controllers.airport to javafx.fxml;

    opens seg.team9.controllers to javafx.fxml;

    exports seg.team9;
}