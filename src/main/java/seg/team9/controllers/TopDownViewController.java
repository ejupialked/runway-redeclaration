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
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.CheckedOutputStream;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    @FXML private AnchorPane topDownView;
    @FXML private Label title;

    private static TopDownViewController instance;

    private Calculator calculator = new Calculator();

    private Obstacle currentObstacle = new Obstacle("Nothing", 1D, 1D, 0D, 3660D,0D);
    private Runway currentRunway = new Runway(new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D),new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D));

    //UI STUFF
    private Double xScaler;
    private Double yScaler;
    private Rectangle runwayBase = new Rectangle();
    private Polygon clearedAndGradedArea = new Polygon();
    private Line centreLine = new Line();

    private Rectangle obstacle = new Rectangle();

    //Right Runway
    private Text runwayDesignatorR = new Text("SELECT A RUNWAY");
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
    private Double TODAR = 0D;
    private Double ASDAR = 0D;
    private Double TORAR = 0D;
    private Double LDAR = 0D;

    //Left Runway
    private Text runwayDesignatorL = new Text("SELECT A RUNWAY");
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
    private Double TODAL = 0D;
    private Double ASDAL = 0D;
    private Double TORAL = 0D;
    private Double LDAL = 0D;

    //Positional Info
    private Double runwayBeginX;
    private Double runwayEndX;
    private Double runwayLength;
    private Double runwayScaleX;

    private Double displacedDesignatorL = 0D;
    private Double displacedDesignatorR = 0D;

    public TopDownViewController() {
        instance = this;
    }

    public static TopDownViewController getInstance() {
        return instance;
    }

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

    public void updateScaler(){
        runwayBeginX = xScaler*0.1;
        runwayEndX = xScaler*0.1+xScaler*0.8;
        runwayLength = xScaler*0.8;
        runwayScaleX = currentRunway.getLRunway().getTora()/runwayLength;
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
        obstacle.setFill(Color.RED);
    }

    public void updateUI(){
        xScaler = topDownView.getWidth();
        yScaler = topDownView.getHeight();
        updateScaler();
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
        updateObstacle();
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
        runwayBase.setX(runwayBeginX);
        runwayBase.setY(yScaler*0.4);
        runwayBase.setWidth(runwayLength);
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
        thresholdR.setStartX(runwayBeginX+displacedDesignatorR*runwayScaleX);
        thresholdR.setStartY(yScaler*0.45);
        thresholdR.setEndX(runwayBeginX+displacedDesignatorR*runwayScaleX);
        thresholdR.setEndY(yScaler*0.55);
        thresholdL.setStartX(runwayBeginX+runwayLength-displacedDesignatorL*runwayScaleX);
        thresholdL.setStartY(yScaler*0.45);
        thresholdL.setEndX(runwayBeginX+runwayLength-displacedDesignatorL*runwayScaleX);
        thresholdL.setEndY(yScaler*0.55);
    }

    public void updateStopway(){
        stopwayR.setX(runwayBeginX+runwayLength);
        stopwayR.setY(yScaler*0.4);
        stopwayR.setWidth(currentRunway.getRRunway().getStopway()*runwayScaleX);
        stopwayR.setHeight(yScaler*0.2);
        stopwayL.setX(runwayBeginX-currentRunway.getLRunway().getStopway()*runwayScaleX);
        stopwayL.setY(yScaler*0.4);
        stopwayL.setWidth(currentRunway.getLRunway().getStopway()*runwayScaleX);
        stopwayL.setHeight(yScaler*0.2);
    }

    public void updateClearway(){
        clearwayR.setX(runwayBeginX+runwayLength);
        clearwayR.setY(yScaler*0.35);
        clearwayR.setWidth(currentRunway.getRRunway().getClearway()*runwayScaleX);
        clearwayR.setHeight(yScaler*0.3);
        clearwayL.setX(runwayBeginX-currentRunway.getLRunway().getClearway()*runwayScaleX);
        clearwayL.setY(yScaler*0.35);
        clearwayL.setWidth(currentRunway.getLRunway().getClearway()*runwayScaleX);
        clearwayL.setHeight(yScaler*0.3);
    }

    public void updateEORunway(){
        EORunwayR.setStartX(runwayBeginX+runwayLength);
        EORunwayR.setStartY(0.5*yScaler);
        EORunwayR.setEndX(runwayBeginX+runwayLength);
        EORunwayR.setEndY(0.15*yScaler);
        EORunwayL.setStartX(runwayBeginX);
        EORunwayL.setStartY(0.5*yScaler);
        EORunwayL.setEndX(runwayBeginX);
        EORunwayL.setEndY(0.85*yScaler);
    }

    public void updateEOStopway(){
        EOStopwayR.setStartX(runwayBeginX+runwayLength+currentRunway.getRRunway().getStopway()*runwayScaleX);
        EOStopwayR.setStartY(0.5*yScaler);
        EOStopwayR.setEndX(runwayBeginX+runwayLength+currentRunway.getRRunway().getStopway()*runwayScaleX);
        EOStopwayR.setEndY(0.1*yScaler);
        EOStopwayL.setStartX(runwayBeginX-currentRunway.getLRunway().getStopway()*runwayScaleX);
        EOStopwayL.setStartY(0.5*yScaler);
        EOStopwayL.setEndX(runwayBeginX-currentRunway.getLRunway().getStopway()*runwayScaleX);
        EOStopwayL.setEndY(0.9*yScaler);
    }

    public void updateEOClearway(){
        EOClearwayR.setStartX(runwayBeginX+runwayLength+currentRunway.getRRunway().getClearway()*runwayScaleX);
        EOClearwayR.setStartY(0.5*yScaler);
        EOClearwayR.setEndX(runwayBeginX+runwayLength+currentRunway.getRRunway().getClearway()*runwayScaleX);
        EOClearwayR.setEndY(0.05*yScaler);
        EOClearwayL.setStartX(runwayBeginX-currentRunway.getLRunway().getClearway()*runwayScaleX);
        EOClearwayL.setStartY(0.5*yScaler);
        EOClearwayL.setEndX(runwayBeginX-currentRunway.getLRunway().getClearway()*runwayScaleX);
        EOClearwayL.setEndY(0.95*yScaler);
    }

    private void updateRunwayStart(){
        runwayStartR.setStartX(runwayBeginX);
        runwayStartR.setStartY(0.5*yScaler);
        runwayStartR.setEndX(runwayBeginX);
        runwayStartR.setEndY(0.05*yScaler);
        runwayStartL.setStartX(runwayBeginX+runwayLength);
        runwayStartL.setStartY(0.5*yScaler);
        runwayStartL.setEndX(runwayBeginX+runwayLength);
        runwayStartL.setEndY(0.95*yScaler);
    }

    private void updateArrowTODA(){
        arrowTODAR = new Arrow(runwayBeginX,0.05*yScaler,runwayBeginX+runwayLength+currentRunway.getRRunway().getClearway()*runwayScaleX,0.05*yScaler,10);
        arrowTODAL = new Arrow(runwayEndX,0.95*yScaler,runwayBeginX-currentRunway.getLRunway().getClearway()*runwayScaleX,0.95*yScaler,10);
    }

    private void updateArrowASDA(){
        arrowASDAR = new Arrow(runwayBeginX,0.1*yScaler,runwayEndX+currentRunway.getRRunway().getStopway()*runwayScaleX,0.1*yScaler,10);
        arrowASDAL = new Arrow(runwayEndX,0.9*yScaler,runwayBeginX-currentRunway.getLRunway().getStopway()*runwayScaleX,0.9*yScaler,10);
    }

    private void updateArrowTORA(){
        arrowTORAR = new Arrow(runwayBeginX,0.15*yScaler,runwayEndX,0.15*yScaler,10);
        arrowTORAL = new Arrow(runwayEndX,0.85*yScaler,runwayBeginX,0.85*yScaler,10);
    }

    private void updateArrowLDA(){
        arrowLDAR = new Arrow(runwayBeginX+displacedDesignatorR,0.19*yScaler,runwayEndX,0.19*yScaler,10);
        arrowLDAL = new Arrow(runwayEndX-displacedDesignatorL,0.81*yScaler,runwayBeginX,0.81*yScaler,10);
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

    public void updateObstacle(){
        obstacle.setX(runwayBeginX+currentObstacle.getDistanceRThreshold());
        obstacle.setY(0.5*yScaler);
        obstacle.setWidth(currentObstacle.getWidth()*xScaler);
        obstacle.setHeight(currentObstacle.getHeight()*yScaler);
    }

    public void addChildren(){
        topDownView.getChildren().clear();
        topDownView.getChildren().add(clearedAndGradedArea);
        topDownView.getChildren().add(runwayBase);
        topDownView.getChildren().add(centreLine);
        topDownView.getChildren().add(obstacle);

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
        currentRunway = runway;
        updateValues();
        updateText();
        updateUI();
    }

    public void displayObstacleSelected(Obstacle obstacle){
        currentObstacle = obstacle;
        updateValues();
        updateText();
        updateUI();
    }

    public void updateValues(){
        calculator.redesignate(currentRunway, currentObstacle);
        runwayDesignatorR.setText(currentRunway.getRRunway().getDesignator().toString());
        TODAR = currentRunway.getRRunway().getWorkingTODA();
        ASDAR = currentRunway.getRRunway().getWorkingASDA();
        TORAR = currentRunway.getRRunway().getWorkingTORA();
        LDAR = currentRunway.getRRunway().getWorkingLDA();

        runwayDesignatorL.setText(currentRunway.getLRunway().getDesignator().toString());
        TODAL = currentRunway.getLRunway().getWorkingTODA();
        ASDAL = currentRunway.getLRunway().getWorkingASDA();
        TORAL = currentRunway.getLRunway().getWorkingTORA();
        LDAL = currentRunway.getLRunway().getWorkingLDA();
    }

    public void updateText(){
        textTODAR.setText("TODA: "+TODAR.toString());
        textASDAR.setText("ASDA: "+ASDAR.toString());
        textTORAR.setText("TORA: "+TORAR.toString());
        textLDAR.setText("LDA: "+LDAR.toString());

        textTODAL.setText("TODA: "+TODAL.toString());
        textASDAL.setText("ASDA: "+ASDAL.toString());
        textTORAL.setText("TORA: "+TORAL.toString());
        textLDAL.setText("LDA: "+LDAL.toString());
    }
}
