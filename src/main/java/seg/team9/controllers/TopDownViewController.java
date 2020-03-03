package seg.team9.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import seg.team9.business.logic.Calculator;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    @FXML private AnchorPane topDownView;
    @FXML private Label title;

    private Calculator calculator = new Calculator();

    //UI STUFF
    private Double xScaler;
    private Double yScaler;
    private Rectangle runwayBase = new Rectangle();
    private Polygon clearedAndGradedArea = new Polygon();
    private Line centreLine = new Line();

    //Right Runway
    private Text runwayDesignatorR = new Text("09/R");
    private Line thresholdR = new Line();
    private Rectangle stopwayR = new Rectangle();
    private Rectangle clearwayR = new Rectangle();
    private Line EORunwayR = new Line();
    private Line EOStopwayR = new Line();
    private Line EOClearwayR = new Line();
    private Line runwayStartR = new Line();
    private Arrow arrowTODAR = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAR = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAR = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAR = new Arrow(0,0,0,0,0);
    private Text textTODAR = new Text("TODA");
    private Text textASDAR = new Text("ASDA");
    private Text textTORAR = new Text("TORA");
    private Text textLDAR = new Text("LDA");

    //Left Runway
    private Text runwayDesignatorL = new Text("27/L");
    private Line thresholdL = new Line();
    private Rectangle stopwayL = new Rectangle();
    private Rectangle clearwayL = new Rectangle();
    private Line EORunwayL = new Line();
    private Line EOStopwayL = new Line();
    private Line EOClearwayL = new Line();
    private Line runwayStartL = new Line();
    private Arrow arrowTODAL = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAL = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAL = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAL = new Arrow(0,0,0,0,0);
    private Text textTODAL = new Text("TODA");
    private Text textASDAL = new Text("ASDA");
    private Text textTORAL = new Text("TORA");
    private Text textLDAL = new Text("LDA");

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
        runwayDesignatorR.setRotate(90);
        runwayDesignatorR.setFont(Font.font(15));
        runwayDesignatorR.setStroke(Color.WHITE);
        textTODAR.setFont(Font.font(10));
        textTODAR.setStroke(Color.BLACK);
        textASDAR.setFont(Font.font(10));
        textASDAR.setStroke(Color.BLACK);
        textTORAR.setFont(Font.font(10));
        textTORAR.setStroke(Color.BLACK);
        textLDAR.setFont(Font.font(10));
        textLDAR.setStroke(Color.BLACK);
        runwayDesignatorL.setRotate(270);
        runwayDesignatorL.setFont(Font.font(15));
        runwayDesignatorL.setStroke(Color.WHITE);
        textTODAL.setFont(Font.font(10));
        textTODAL.setStroke(Color.BLACK);
        textASDAL.setFont(Font.font(10));
        textASDAL.setStroke(Color.BLACK);
        textTORAL.setFont(Font.font(10));
        textTORAL.setStroke(Color.BLACK);
        textLDAL.setFont(Font.font(10));
        textLDAL.setStroke(Color.BLACK);
    }

    public void initLines(){
        centreLine.setStroke(Color.WHITE);
        centreLine.getStrokeDashArray().addAll(25d, 10d);
        thresholdR.setStroke(Color.WHITE);
        EOClearwayR.setStroke(Color.BLACK);
        EORunwayR.setStroke(Color.BLACK);
        EOStopwayR.setStroke(Color.BLACK);
        runwayStartR.setStroke(Color.BLACK);
        thresholdL.setStroke(Color.WHITE);
        EOClearwayL.setStroke(Color.BLACK);
        EORunwayL.setStroke(Color.BLACK);
        EOStopwayL.setStroke(Color.BLACK);
        runwayStartL.setStroke(Color.BLACK);
    }

    public void initRectangles(){
        runwayBase.setFill(Color.GRAY);
        stopwayR.setFill(Color.TRANSPARENT);
        stopwayR.setStroke(Color.WHITE);
        clearwayR.setFill(Color.TRANSPARENT);
        clearwayR.setStroke(Color.WHITE);
        clearwayR.getStrokeDashArray().addAll(10d, 10d);
        stopwayL.setFill(Color.TRANSPARENT);
        stopwayL.setStroke(Color.WHITE);
        clearwayL.setFill(Color.TRANSPARENT);
        clearwayL.setStroke(Color.WHITE);
        clearwayL.getStrokeDashArray().addAll(10d, 10d);
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
        runwayDesignatorR.setX(xScaler*0.1);
        runwayDesignatorR.setY(yScaler*0.5);
        runwayDesignatorL.setX(xScaler*0.865);
        runwayDesignatorL.setY(yScaler*0.5);
    }

    public void updateThreshold(){
        thresholdR.setStartX(xScaler*0.14);
        thresholdR.setStartY(yScaler*0.45);
        thresholdR.setEndX(xScaler*0.14);
        thresholdR.setEndY(yScaler*0.55);
        thresholdL.setStartX(xScaler*0.86);
        thresholdL.setStartY(yScaler*0.45);
        thresholdL.setEndX(xScaler*0.86);
        thresholdL.setEndY(yScaler*0.55);
    }

    public void updateStopway(){
        stopwayR.setX(xScaler*0.9);
        stopwayR.setY(yScaler*0.4);
        stopwayR.setWidth(xScaler*0.05);
        stopwayR.setHeight(yScaler*0.2);
        stopwayL.setX(xScaler*0.05);
        stopwayL.setY(yScaler*0.4);
        stopwayL.setWidth(xScaler*0.05);
        stopwayL.setHeight(yScaler*0.2);
    }

    public void updateClearway(){
        clearwayR.setX(xScaler*0.9);
        clearwayR.setY(yScaler*0.35);
        clearwayR.setWidth(xScaler*0.075);
        clearwayR.setHeight(yScaler*0.3);
        clearwayL.setX(xScaler*0.025);
        clearwayL.setY(yScaler*0.35);
        clearwayL.setWidth(xScaler*0.075);
        clearwayL.setHeight(yScaler*0.3);
    }

    public void updateEORunway(){
        EORunwayR.setStartX(0.9*xScaler);
        EORunwayR.setStartY(0.5*yScaler);
        EORunwayR.setEndX(0.9*xScaler);
        EORunwayR.setEndY(0.15*yScaler);
        EORunwayL.setStartX(0.1*xScaler);
        EORunwayL.setStartY(0.5*yScaler);
        EORunwayL.setEndX(0.1*xScaler);
        EORunwayL.setEndY(0.85*yScaler);
    }

    public void updateEOStopway(){
        EOStopwayR.setStartX(0.95*xScaler);
        EOStopwayR.setStartY(0.5*yScaler);
        EOStopwayR.setEndX(0.95*xScaler);
        EOStopwayR.setEndY(0.1*yScaler);
        EOStopwayL.setStartX(0.05*xScaler);
        EOStopwayL.setStartY(0.5*yScaler);
        EOStopwayL.setEndX(0.05*xScaler);
        EOStopwayL.setEndY(0.9*yScaler);
    }

    public void updateEOClearway(){
        EOClearwayR.setStartX(0.975*xScaler);
        EOClearwayR.setStartY(0.5*yScaler);
        EOClearwayR.setEndX(0.975*xScaler);
        EOClearwayR.setEndY(0.05*yScaler);
        EOClearwayL.setStartX(0.025*xScaler);
        EOClearwayL.setStartY(0.5*yScaler);
        EOClearwayL.setEndX(0.025*xScaler);
        EOClearwayL.setEndY(0.95*yScaler);
    }

    private void updateRunwayStart(){
        runwayStartR.setStartX(0.1*xScaler);
        runwayStartR.setStartY(0.5*yScaler);
        runwayStartR.setEndX(0.1*xScaler);
        runwayStartR.setEndY(0.05*yScaler);
        runwayStartL.setStartX(0.9*xScaler);
        runwayStartL.setStartY(0.5*yScaler);
        runwayStartL.setEndX(0.9*xScaler);
        runwayStartL.setEndY(0.95*yScaler);
    }

    private void updateArrowTODA(){
        arrowTODAR = new Arrow(0.1*xScaler,0.05*yScaler,0.975*xScaler,0.05*yScaler,10);
        arrowTODAL = new Arrow(0.9*xScaler,0.95*yScaler,0.025*xScaler,0.95*yScaler,10);
    }

    private void updateArrowASDA(){
        arrowASDAR = new Arrow(0.1*xScaler,0.1*yScaler,0.95*xScaler,0.1*yScaler,10);
        arrowASDAL = new Arrow(0.9*xScaler,0.9*yScaler,0.05*xScaler,0.9*yScaler,10);
    }

    private void updateArrowTORA(){
        arrowTORAR = new Arrow(0.1*xScaler,0.15*yScaler,0.9*xScaler,0.15*yScaler,10);
        arrowTORAL = new Arrow(0.9*xScaler,0.85*yScaler,0.1*xScaler,0.85*yScaler,10);
    }

    private void updateArrowLDA(){
        arrowLDAR = new Arrow(0.1*xScaler,0.19*yScaler,0.9*xScaler,0.19*yScaler,10);
        arrowLDAL = new Arrow(0.9*xScaler,0.81*yScaler,0.1*xScaler,0.81*yScaler,10);
    }

    private void updateTextTODA(){
        textTODAR.setX(0.11*xScaler);
        textTODAR.setY(0.05*yScaler);
        textTODAL.setX(0.8*xScaler);
        textTODAL.setY(0.95*yScaler);
    }

    private void updateTextASDA(){
        textASDAR.setX(0.11*xScaler);
        textASDAR.setY(0.1*yScaler);
        textASDAL.setX(0.8*xScaler);
        textASDAL.setY(0.9*yScaler);
    }

    private void updateTextTORA(){
        textTORAR.setX(0.11*xScaler);
        textTORAR.setY(0.15*yScaler);
        textTORAL.setX(0.8*xScaler);
        textTORAL.setY(0.85*yScaler);
    }

    private void updateTextLDA(){
        textLDAR.setX(0.11*xScaler);
        textLDAR.setY(0.19*yScaler);
        textLDAL.setX(0.8*xScaler);
        textLDAL.setY(0.81*yScaler);
    }

    public void addChildren(){
        topDownView.getChildren().clear();
        topDownView.getChildren().add(clearedAndGradedArea);
        topDownView.getChildren().add(runwayBase);
        topDownView.getChildren().add(centreLine);

        topDownView.getChildren().add(runwayDesignatorR);
        topDownView.getChildren().add(thresholdR);
        topDownView.getChildren().add(stopwayR);
        topDownView.getChildren().add(clearwayR);
        topDownView.getChildren().add(EORunwayR);
        topDownView.getChildren().add(EOStopwayR);
        topDownView.getChildren().add(EOClearwayR);
        topDownView.getChildren().add(runwayStartR);
        topDownView.getChildren().add(arrowTODAR);
        topDownView.getChildren().add(arrowASDAR);
        topDownView.getChildren().add(arrowTORAR);
        topDownView.getChildren().add(arrowLDAR);
        topDownView.getChildren().add(textTODAR);
        topDownView.getChildren().add(textASDAR);
        topDownView.getChildren().add(textTORAR);
        topDownView.getChildren().add(textLDAR);

        topDownView.getChildren().add(runwayDesignatorL);
        topDownView.getChildren().add(thresholdL);
        topDownView.getChildren().add(stopwayL);
        topDownView.getChildren().add(clearwayL);
        topDownView.getChildren().add(EORunwayL);
        topDownView.getChildren().add(EOStopwayL);
        topDownView.getChildren().add(EOClearwayL);
        topDownView.getChildren().add(runwayStartL);
        topDownView.getChildren().add(arrowTODAL);
        topDownView.getChildren().add(arrowASDAL);
        topDownView.getChildren().add(arrowTORAL);
        topDownView.getChildren().add(arrowLDAL);
        topDownView.getChildren().add(textTODAL);
        topDownView.getChildren().add(textASDAL);
        topDownView.getChildren().add(textTORAL);
        topDownView.getChildren().add(textLDAL);

        topDownView.getChildren().add(title);
    }



    public void displayDirectedRunwaySelected(Runway runway) {
        calculator.redesignate(runway, new Obstacle("Nothing", 1D, 1D, 0D, 3660D,0D));
        runwayDesignatorR.setText(runway.getRRunway().getDesignator().toString());
        textTODAR.setText("TODA: "+runway.getRRunway().getWorkingTODA().toString());
        textASDAR.setText("ASDA: "+runway.getRRunway().getWorkingASDA().toString());
        textTORAR.setText("TORA: "+runway.getRRunway().getWorkingTORA().toString());
        textLDAR.setText("LDA: "+runway.getRRunway().getWorkingLDA().toString());

        runwayDesignatorL.setText(runway.getLRunway().getDesignator().toString());
        textTODAL.setText("TODA: "+runway.getLRunway().getWorkingTODA().toString());
        textASDAL.setText("ASDA: "+runway.getLRunway().getWorkingASDA().toString());
        textTORAL.setText("TORA: "+runway.getLRunway().getWorkingTORA().toString());
        textLDAL.setText("LDA: "+runway.getLRunway().getWorkingLDA().toString());
    }
}
