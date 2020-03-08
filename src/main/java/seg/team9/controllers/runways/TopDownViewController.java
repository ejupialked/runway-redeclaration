package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.App;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");


    String FONT_FAMILY = "Helvetica";

    @FXML private AnchorPane topDownView;

    private static TopDownViewController instance;


    private Obstacle currentObstacle = new Obstacle("Nothing", 1D, 1D, 0D, 3660D,0D);
    private Runway currentRunway = new Runway(new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D),new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D));

    //UI STUFF
    private Pane graphics = new Pane();
    private Pane text = new Pane();

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
    private Line TODARLineR = new Line();
    private Line ADSALineR = new Line();
    private Line TORALineR = new Line();
    private Line LDALineR = new Line();
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
    private Line TODALineL = new Line();
    private Line ASDALineL = new Line();
    private Line TORALineL = new Line();
    private Line LDALineL = new Line();
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
    private Double runwayOffsetR;
    private Double runwayOffsetL;

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
        topDownView.setStyle("-fx-background-color: green");
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
        runwayBeginX = xScaler * 0.15;
        runwayLength = xScaler * 0.7;
        runwayEndX = runwayBeginX + runwayLength;
        runwayScaleX = runwayLength / currentRunway.getLRunway().getTora();
        if(currentObstacle.getDistanceRThreshold()>=(currentRunway.getRRunway().getTora()/2)) {
            runwayOffsetR = 0D;
        }
        else{
            runwayOffsetR = runwayScaleX*(currentObstacle.getDistanceRThreshold())+obstacle.getWidth();
        }
        if(currentObstacle.getDistanceLThreshold()>=currentRunway.getLRunway().getTora()/2) {
            runwayOffsetL = 0D;
        }
        else{
            runwayOffsetL = runwayScaleX*(currentObstacle.getDistanceLThreshold())+obstacle.getWidth();
        }
    }

    public void initText(){
        runwayDesignatorR.setRotate(90);
        runwayDesignatorR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 30));
        runwayDesignatorR.setFill(Color.WHITE);
        runwayDesignatorR.setSmooth(true);


        runwayDesignatorL.setRotate(270);
        runwayDesignatorL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 30));
        runwayDesignatorL.setFill(Color.WHITE);
        runwayDesignatorL.setSmooth(true);

        textTODAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTODAR.setFill(Color.WHITE);
        textASDAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textASDAR.setFill(Color.WHITE);
        textTORAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTORAR.setFill(Color.WHITE);
        textLDAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textLDAR.setFill(Color.WHITE);

        textTODAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTODAL.setFill(Color.WHITE);
        textASDAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textASDAL.setFill(Color.WHITE);
        textTORAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTORAL.setFill(Color.WHITE);
        textLDAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textLDAL.setFill(Color.WHITE);
    }

    public void initLines(){
        centreLine.setStroke(Color.WHITE);
        centreLine.setStrokeWidth(5d);
        centreLine.getStrokeDashArray().addAll(40d, 20d);
        thresholdR.setStroke(Color.WHITE);
        TORALineR.setStroke(Color.BLACK);
        TODARLineR.setStroke(Color.BLACK);
        ADSALineR.setStroke(Color.BLACK);
        LDALineR.setStroke(Color.BLACK);
        runwayStartR.setStroke(Color.BLACK);
        thresholdL.setStroke(Color.WHITE);
        TORALineL.setStroke(Color.BLACK);
        TODALineL.setStroke(Color.BLACK);
        ASDALineL.setStroke(Color.BLACK);
        LDALineL.setStroke(Color.BLACK);
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
        graphics.setPrefWidth(topDownView.getWidth());
        graphics.setPrefHeight(topDownView.getHeight());
        text.setPrefWidth(topDownView.getWidth());
        text.setPrefHeight(topDownView.getHeight());
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
        updateTODALine();
        updateASDALine();
        updateTORALine();
        updateLDALine();
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
        try {
            graphics.setRotate(Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90);
            if(Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90 <= 180){
                text.setRotate(Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90);
            }
            else{
                text.setRotate(Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 270);
            }
        }catch (NumberFormatException e){
            logger.info("invalidrunwayrotation");
        }
    }

    public void updateClearedAndGradedArea(){
        double[] points = {
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
        centreLine.setStartX(runwayBeginX+0.1*runwayLength);
        centreLine.setStartY(yScaler*0.5);
        centreLine.setEndX(runwayEndX-0.1*runwayLength);
        centreLine.setEndY(yScaler*0.5);
    }

    public void updateRunwayDesignator(){
        runwayDesignatorR.setX(runwayBeginX);
        runwayDesignatorR.setY(yScaler*0.5);
        runwayDesignatorL.setX(runwayEndX-60);
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

    public void updateTODALine(){
        TODARLineR.setStartX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTODA()*runwayScaleX);
        TODARLineR.setStartY(0.5*yScaler);
        TODARLineR.setEndX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTODA()*runwayScaleX);
        TODARLineR.setEndY(0.05*yScaler);
        TODALineL.setStartX(runwayEndX-currentRunway.getLRunway().getWorkingTODA()*runwayScaleX-runwayOffsetL);
        TODALineL.setStartY(0.5*yScaler);
        TODALineL.setEndX(runwayEndX-currentRunway.getLRunway().getWorkingTODA()*runwayScaleX-runwayOffsetL);
        TODALineL.setEndY(0.95*yScaler);
    }

    public void updateASDALine(){
        ADSALineR.setStartX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingASDA()*runwayScaleX);
        ADSALineR.setStartY(0.5*yScaler);
        ADSALineR.setEndX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingASDA()*runwayScaleX);
        ADSALineR.setEndY(0.1*yScaler);
        ASDALineL.setStartX(runwayEndX-currentRunway.getLRunway().getWorkingASDA()*runwayScaleX-runwayOffsetL);
        ASDALineL.setStartY(0.5*yScaler);
        ASDALineL.setEndX(runwayEndX-currentRunway.getLRunway().getWorkingASDA()*runwayScaleX-runwayOffsetL);
        ASDALineL.setEndY(0.9*yScaler);
    }

    public void updateTORALine(){
        TORALineR.setStartX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTORA()*runwayScaleX);
        TORALineR.setStartY(0.5*yScaler);
        TORALineR.setEndX(runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTORA()*runwayScaleX);
        TORALineR.setEndY(0.15*yScaler);
        TORALineL.setStartX(runwayEndX-currentRunway.getLRunway().getWorkingTORA()*runwayScaleX-runwayOffsetL);
        TORALineL.setStartY(0.5*yScaler);
        TORALineL.setEndX(runwayEndX-currentRunway.getLRunway().getWorkingTORA()*runwayScaleX-runwayOffsetL);
        TORALineL.setEndY(0.85*yScaler);
    }

    private void updateLDALine(){
        LDALineR.setStartX(runwayOffsetR+runwayBeginX+displacedDesignatorR+currentRunway.getRRunway().getWorkingLDA()*runwayScaleX);
        LDALineR.setStartY(0.5*yScaler);
        LDALineR.setEndX(runwayOffsetR+runwayBeginX+displacedDesignatorR+currentRunway.getRRunway().getWorkingLDA()*runwayScaleX);
        LDALineR.setEndY(0.19*yScaler);
        LDALineL.setStartX(runwayEndX-displacedDesignatorL-currentRunway.getLRunway().getWorkingLDA()*runwayScaleX-runwayOffsetL);
        LDALineL.setStartY(0.5*yScaler);
        LDALineL.setEndX(runwayEndX-displacedDesignatorL-currentRunway.getLRunway().getWorkingLDA()*runwayScaleX-runwayOffsetL);
        LDALineL.setEndY(0.81*yScaler);
    }

    private void updateRunwayStart(){
        runwayStartR.setStartX(runwayOffsetR+runwayBeginX);
        runwayStartR.setStartY(0.5*yScaler);
        runwayStartR.setEndX(runwayOffsetR+runwayBeginX);
        runwayStartR.setEndY(0.05*yScaler);
        runwayStartL.setStartX(runwayEndX-runwayOffsetL);
        runwayStartL.setStartY(0.5*yScaler);
        runwayStartL.setEndX(runwayEndX-runwayOffsetL);
        runwayStartL.setEndY(0.95*yScaler);
    }

    private void updateArrowTODA(){
        arrowTODAR = new Arrow(runwayOffsetR+runwayBeginX,0.05*yScaler,runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTODA()*runwayScaleX,0.05*yScaler,10);
        arrowTODAL = new Arrow(runwayEndX-runwayOffsetL,0.95*yScaler,runwayEndX-currentRunway.getLRunway().getWorkingTODA()*runwayScaleX-runwayOffsetL,0.95*yScaler,10);
    }

    private void updateArrowASDA(){
        arrowASDAR = new Arrow(runwayOffsetR+runwayBeginX,0.1*yScaler,runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingASDA()*runwayScaleX,0.1*yScaler,10);
        arrowASDAL = new Arrow(runwayEndX-runwayOffsetL,0.9*yScaler,runwayEndX-currentRunway.getLRunway().getWorkingASDA()*runwayScaleX-runwayOffsetL,0.9*yScaler,10);
    }

    private void updateArrowTORA(){
        arrowTORAR = new Arrow(runwayOffsetR+runwayBeginX,0.15*yScaler,runwayOffsetR+runwayBeginX+currentRunway.getRRunway().getWorkingTORA()*runwayScaleX,0.15*yScaler,10);
        arrowTORAL = new Arrow(runwayEndX-runwayOffsetL,0.85*yScaler,runwayEndX-currentRunway.getLRunway().getWorkingTORA()*runwayScaleX-runwayOffsetL,0.85*yScaler,10);
    }

    private void updateArrowLDA(){
        arrowLDAR = new Arrow(runwayOffsetR+runwayBeginX+displacedDesignatorR,0.19*yScaler,runwayOffsetR+runwayBeginX+displacedDesignatorR+currentRunway.getRRunway().getWorkingLDA()*runwayScaleX,0.19*yScaler,10);
        arrowLDAL = new Arrow(runwayEndX-displacedDesignatorL-runwayOffsetL,0.81*yScaler,runwayEndX-displacedDesignatorL-currentRunway.getLRunway().getWorkingLDA()*runwayScaleX-runwayOffsetL,0.81*yScaler,10);
    }

    private void updateTextTODA(){
        textTODAR.setX(runwayOffsetR+runwayBeginX);
        textTODAR.setY(0.05*yScaler);
        textTODAL.setX(runwayEndX-200-runwayOffsetL);
        textTODAL.setY(0.95*yScaler);
    }

    private void updateTextASDA(){
        textASDAR.setX(runwayOffsetR+runwayBeginX);
        textASDAR.setY(0.1*yScaler);
        textASDAL.setX(runwayEndX-200-runwayOffsetL);
        textASDAL.setY(0.9*yScaler);
    }

    private void updateTextTORA(){
        textTORAR.setX(runwayOffsetR+runwayBeginX);
        textTORAR.setY(0.15*yScaler);
        textTORAL.setX(runwayEndX-200-runwayOffsetL);
        textTORAL.setY(0.85*yScaler);
    }

    private void updateTextLDA(){
        textLDAR.setX(runwayOffsetR+runwayBeginX);
        textLDAR.setY(0.19*yScaler);
        textLDAL.setX(runwayEndX-200-runwayOffsetL);
        textLDAL.setY(0.81*yScaler);
    }

    public void updateObstacle(){
        obstacle.setX(runwayBeginX+currentObstacle.getDistanceRThreshold()*runwayScaleX);
        obstacle.setY(0.5*yScaler+currentObstacle.getDistanceCenter()*runwayScaleX-(currentObstacle.getWidth()*runwayScaleX)/2);
        obstacle.setWidth((currentRunway.getRRunway().getTora()-(currentObstacle.getDistanceRThreshold()+currentObstacle.getDistanceLThreshold()))*runwayScaleX);
        obstacle.setHeight(currentObstacle.getWidth()*runwayScaleX);
    }

    public void addChildren(){
        topDownView.getChildren().clear();
        graphics.getChildren().clear();
        text.getChildren().clear();
        graphics.getChildren().add(clearedAndGradedArea);
        graphics.getChildren().add(runwayBase);
        graphics.getChildren().add(centreLine);
        graphics.getChildren().add(obstacle);

        graphics.getChildren().add(runwayDesignatorR);
        graphics.getChildren().add(thresholdR);
        graphics.getChildren().add(stopwayR);
        graphics.getChildren().add(clearwayR);
        graphics.getChildren().add(TODARLineR);
        graphics.getChildren().add(ADSALineR);
        graphics.getChildren().add(TORALineR);
        graphics.getChildren().add(LDALineR);
        graphics.getChildren().add(runwayStartR);
        graphics.getChildren().add(arrowTODAR);
        graphics.getChildren().add(arrowASDAR);
        graphics.getChildren().add(arrowTORAR);
        graphics.getChildren().add(arrowLDAR);
        text.getChildren().add(textTODAR);
        text.getChildren().add(textASDAR);
        text.getChildren().add(textTORAR);
        text.getChildren().add(textLDAR);

        graphics.getChildren().add(runwayDesignatorL);
        graphics.getChildren().add(thresholdL);
        graphics.getChildren().add(stopwayL);
        graphics.getChildren().add(clearwayL);
        graphics.getChildren().add(TODALineL);
        graphics.getChildren().add(ASDALineL);
        graphics.getChildren().add(TORALineL);
        graphics.getChildren().add(LDALineL);
        graphics.getChildren().add(runwayStartL);
        graphics.getChildren().add(arrowTODAL);
        graphics.getChildren().add(arrowASDAL);
        graphics.getChildren().add(arrowTORAL);
        graphics.getChildren().add(arrowLDAL);
        text.getChildren().add(textTODAL);
        text.getChildren().add(textASDAL);
        text.getChildren().add(textTORAL);
        text.getChildren().add(textLDAL);

        topDownView.getChildren().add(graphics);
        topDownView.getChildren().add(text);
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
        logger.info("currentRunway"+currentRunway);
        App.getCalculator().redesignate(currentRunway, currentObstacle);
        runwayDesignatorR.setText(currentRunway.getRRunway().getDesignator());
        TODAR = currentRunway.getRRunway().getWorkingTODA();
        ASDAR = currentRunway.getRRunway().getWorkingASDA();
        TORAR = currentRunway.getRRunway().getWorkingTORA();
        LDAR = currentRunway.getRRunway().getWorkingLDA();

        runwayDesignatorL.setText(currentRunway.getLRunway().getDesignator());
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

    public Runway getCurrentRunway(){
        return currentRunway;
    }
}
