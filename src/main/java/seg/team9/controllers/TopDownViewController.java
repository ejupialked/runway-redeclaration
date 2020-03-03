package seg.team9.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    @FXML private AnchorPane topDownView;
    @FXML private Label title;

    private Double xScaler;
    private Double yScaler;

    private Rectangle runwayBase = new Rectangle();
    private Polygon clearedAndGradedArea = new Polygon();
    private Text runwayDesignatorOne = new Text("09/R");
    private Line centreLine = new Line();
    private Line threshold = new Line();
    private Rectangle stopway = new Rectangle();
    private Rectangle clearway = new Rectangle();
    private Line EORunway = new Line();
    private Line EOStopway = new Line();
    private Line EOClearway = new Line();
    private Line runwayStart = new Line();
    private Arrow arrowTODA = new Arrow(0,0,0,0,0);
    private Arrow arrowASDA = new Arrow(0,0,0,0,0);
    private Arrow arrowTORA = new Arrow(0,0,0,0,0);
    private Arrow arrowLDA = new Arrow(0,0,0,0,0);
    private Text textTODA = new Text("TODA");
    private Text textASDA = new Text("ASDA");
    private Text textTORA = new Text("TORA");
    private Text textLDA= new Text("LDA");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("init TopDownViewController");
        topDownView.setMinHeight(0);
        topDownView.setMinWidth(0);
        addChildren();
        initText();
        initLines();
        initRectangles();
        topDownView.widthProperty().addListener((obs,oldVal,newVal) -> {
            updateUI();
        });
        topDownView.heightProperty().addListener((obs,oldVal,newVal) -> {
            updateUI();
        });
    }

    public void initText(){
        runwayDesignatorOne.setRotate(90);
        runwayDesignatorOne.setFont(Font.font(15));
        runwayDesignatorOne.setStroke(Color.WHITE);
        textTODA.setFont(Font.font(10));
        textTODA.setStroke(Color.BLACK);
        textASDA.setFont(Font.font(10));
        textASDA.setStroke(Color.BLACK);
        textTORA.setFont(Font.font(10));
        textTORA.setStroke(Color.BLACK);
        textLDA.setFont(Font.font(10));
        textLDA.setStroke(Color.BLACK);
    }

    public void initLines(){
        centreLine.setStroke(Color.WHITE);
        centreLine.getStrokeDashArray().addAll(25d, 10d);
        threshold.setStroke(Color.WHITE);
        EOClearway.setStroke(Color.BLACK);
        EORunway.setStroke(Color.BLACK);
        EOStopway.setStroke(Color.BLACK);
        runwayStart.setStroke(Color.BLACK);
    }

    public void initRectangles(){
        runwayBase.setFill(Color.GRAY);
        stopway.setFill(Color.TRANSPARENT);
        stopway.setStroke(Color.WHITE);
        clearway.setFill(Color.TRANSPARENT);
        clearway.setStroke(Color.WHITE);
        clearway.getStrokeDashArray().addAll(10d, 10d);
    }

    public void updateUI(){
        xScaler = topDownView.getWidth();
        yScaler = topDownView.getHeight();
        updateClearedAndGradedArea();
        updateRunwayBase();
        updateCentreLine();
        updateRunwayDesignator();
        updateThreshold();
        updateStopway();
        updateClearway();
        updateEORunway();
        updateEOStopway();
        updateEOClearway();
        updateRunwayStart();
        updateArrowTODA();
        updateArrowASDA();
        updateArrowTORA();
        updateArrowLDA();
        updateTextTODA();
        updateTextASDA();
        updateTextTORA();
        updateTextLDA();
        addChildren();
    }

    public void updateClearedAndGradedArea(){
        double points[] = {
                0,0.3*yScaler,
                0.2*xScaler, 0.3*yScaler,
                0.3*xScaler,0.2*yScaler,
                0.7*xScaler,0.2*yScaler,
                0.8*xScaler,0.3*yScaler,
                xScaler,0.3*yScaler,
                xScaler,0.7*yScaler,
                0.8*xScaler,0.7*yScaler,
                0.7*xScaler,0.8*yScaler,
                0.3*xScaler,0.8*yScaler,
                0.2*xScaler,0.7*yScaler,
                0,0.7*yScaler};
        clearedAndGradedArea = new Polygon(points);
        clearedAndGradedArea.setFill(Color.BLUE);
        topDownView.getChildren().add(clearedAndGradedArea);
    }

    public void updateRunwayBase(){
        runwayBase.setX(xScaler*0.1);
        runwayBase.setY(yScaler*0.4);
        runwayBase.setWidth(xScaler*0.8);
        runwayBase.setHeight(yScaler*0.2);
    }

    public void updateCentreLine(){
        centreLine.setStartX(xScaler*0.15);
        centreLine.setStartY(yScaler*0.5);
        centreLine.setEndX(xScaler*0.85);
        centreLine.setEndY(yScaler*0.5);
    }

    public void updateRunwayDesignator(){
        runwayDesignatorOne.setX(xScaler*0.1);
        runwayDesignatorOne.setY(yScaler*0.5);

    }

    public void updateThreshold(){
        threshold.setStartX(xScaler*0.14);
        threshold.setStartY(yScaler*0.45);
        threshold.setEndX(xScaler*0.14);
        threshold.setEndY(yScaler*0.55);
    }

    public void updateStopway(){
        stopway.setX(xScaler*0.9);
        stopway.setY(yScaler*0.4);
        stopway.setWidth(xScaler*0.05);
        stopway.setHeight(yScaler*0.2);
    }

    public void updateClearway(){
        clearway.setX(xScaler*0.9);
        clearway.setY(yScaler*0.35);
        clearway.setWidth(xScaler*0.075);
        clearway.setHeight(yScaler*0.3);
    }

    public void updateEORunway(){
        EORunway.setStartX(0.9*xScaler);
        EORunway.setStartY(0.5*yScaler);
        EORunway.setEndX(0.9*xScaler);
        EORunway.setEndY(0.15*yScaler);
    }

    public void updateEOStopway(){
        EOStopway.setStartX(0.95*xScaler);
        EOStopway.setStartY(0.5*yScaler);
        EOStopway.setEndX(0.95*xScaler);
        EOStopway.setEndY(0.1*yScaler);
    }

    public void updateEOClearway(){
        EOClearway.setStartX(0.975*xScaler);
        EOClearway.setStartY(0.5*yScaler);
        EOClearway.setEndX(0.975*xScaler);
        EOClearway.setEndY(0.05*yScaler);
    }

    private void updateRunwayStart(){
        runwayStart.setStartX(0.1*xScaler);
        runwayStart.setStartY(0.5*yScaler);
        runwayStart.setEndX(0.1*xScaler);
        runwayStart.setEndY(0.05*yScaler);
    }

    private void updateArrowTODA(){
        arrowTODA = new Arrow(0.1*xScaler,0.05*yScaler,0.975*xScaler,0.05*yScaler,10);
    }

    private void updateArrowASDA(){
        arrowASDA = new Arrow(0.1*xScaler,0.1*yScaler,0.95*xScaler,0.1*yScaler,10);
    }

    private void updateArrowTORA(){
        arrowTORA = new Arrow(0.1*xScaler,0.15*yScaler,0.9*xScaler,0.15*yScaler,10);
    }

    private void updateArrowLDA(){
        arrowLDA = new Arrow(0.1*xScaler,0.19*yScaler,0.9*xScaler,0.19*yScaler,10);
    }

    private void updateTextTODA(){
        textTODA.setX(0.11*xScaler);
        textTODA.setY(0.05*yScaler);
    }

    private void updateTextASDA(){
        textASDA.setX(0.11*xScaler);
        textASDA.setY(0.1*yScaler);
    }

    private void updateTextTORA(){
        textTORA.setX(0.11*xScaler);
        textTORA.setY(0.15*yScaler);
    }

    private void updateTextLDA(){
        textLDA.setX(0.11*xScaler);
        textLDA.setY(0.19*yScaler);
    }

    public void addChildren(){
        topDownView.getChildren().clear();
        topDownView.getChildren().add(clearedAndGradedArea);
        topDownView.getChildren().add(runwayBase);

        topDownView.getChildren().add(runwayDesignatorOne);
        topDownView.getChildren().add(centreLine);
        topDownView.getChildren().add(threshold);
        topDownView.getChildren().add(stopway);
        topDownView.getChildren().add(clearway);
        topDownView.getChildren().add(EORunway);
        topDownView.getChildren().add(EOStopway);
        topDownView.getChildren().add(EOClearway);
        topDownView.getChildren().add(runwayStart);
        topDownView.getChildren().add(arrowTODA);
        topDownView.getChildren().add(arrowASDA);
        topDownView.getChildren().add(arrowTORA);
        topDownView.getChildren().add(arrowLDA);
        topDownView.getChildren().add(textTODA);
        topDownView.getChildren().add(textASDA);
        topDownView.getChildren().add(textTORA);
        topDownView.getChildren().add(textLDA);

        topDownView.getChildren().add(title);
    }



    public void displayDirectedRunwaySelected(String designator) {
        runwayDesignatorOne.setText(designator);
    }
}
