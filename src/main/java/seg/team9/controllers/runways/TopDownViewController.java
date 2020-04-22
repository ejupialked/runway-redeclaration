package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
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
import seg.team9.controllers.PrimaryWindowController;
import seg.team9.utils.UtilsUI;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");


    String FONT_FAMILY = "Helvetica";

    @FXML private AnchorPane topDownView;
    @FXML private PrimaryWindowController primaryWindowController;
    private static TopDownViewController instance;

    public boolean isSelected = true;

    private Obstacle currentObstacle = new Obstacle("Nothing", 0D, 0D, 900000D, 0D,0D);
    private Runway currentRunway = new Runway(new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D),new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D));

    //UI STUFF
    private Pane graphics = new Pane();
    private Pane text = new Pane();
    public Pane arrows = new Pane();


    private Double screenWidth;
    private Double screenHeight;
    private Double xScaler;
    private Double middleY;
    private Rectangle runwayBase = new Rectangle();
    private Polygon clearedAndGradedArea = new Polygon();
    private Line centreLine = new Line();

    private Rectangle obstacle = new Rectangle();

    //Right Arrows
    private Double TORAStartXR = 0D;
    private Double TORAEndXR = 0D;

    private Double TODAStartXR = 0D;
    private Double TODAEndXR = 0D;

    private Double ASDAStartXR = 0D;
    private Double ASDAEndXR = 0D;

    private Double LDAStartXR = 0D;
    private Double LDAEndXR = 0D;


    private Line TODAStartLineR = new Line();
    private Line ASDAStartLineR = new Line();
    private Line TORAStartLineR = new Line();
    private Line LDAStartLineR = new Line();
    private Line TODAEndLineR = new Line();
    private Line ASDAEndLineR = new Line();
    private Line TORAEndLineR = new Line();
    private Line LDAEndLineR = new Line();
    private Arrow arrowTODAR = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAR = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAR = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAR = new Arrow(0,0,0,0,0);
    private Text textTODAR = new Text("TODA");
    private Text textASDAR = new Text("ASDA");
    private Text textTORAR = new Text("TORA");
    private Text textLDAR = new Text("LDA");

    //Left Arrows
    private Double TORAStartXL = 0D;
    private Double TORAEndXL = 0D;

    private Double TODAStartXL = 0D;
    private Double TODAEndXL = 0D;

    private Double ASDAStartXL = 0D;
    private Double ASDAEndXL = 0D;

    private Double LDAStartXL = 0D;
    private Double LDAEndXL = 0D;


    private Line TODAStartLineL = new Line();
    private Line ASDAStartLineL = new Line();
    private Line TORAStartLineL = new Line();
    private Line LDAStartLineL = new Line();
    private Line TODAEndLineL = new Line();
    private Line ASDAEndLineL = new Line();
    private Line TORAEndLineL = new Line();
    private Line LDAEndLineL = new Line();
    private Arrow arrowTODAL = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAL = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAL = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAL = new Arrow(0,0,0,0,0);
    private Text textTODAL = new Text("TODA");
    private Text textASDAL = new Text("ASDA");
    private Text textTORAL = new Text("TORA");
    private Text textLDAL = new Text("LDA");




    //Right Runway
    private Text runwayDesignatorR = new Text("SELECT A RUNWAY");
    private Line thresholdR = new Line();
    private Rectangle stopwayR = new Rectangle();
    private Rectangle clearwayR = new Rectangle();

    private Double TODAR = 0D;
    private Double ASDAR = 0D;
    private Double TORAR = 0D;
    private Double LDAR = 0D;

    //Left Runway
    private Text runwayDesignatorL = new Text("SELECT A RUNWAY");
    private Line thresholdL = new Line();
    private Rectangle stopwayL = new Rectangle();
    private Rectangle clearwayL = new Rectangle();

    private Double TODAL = 0D;
    private Double ASDAL = 0D;
    private Double TORAL = 0D;
    private Double LDAL = 0D;


    //Positional Info
    private Double runwayBeginX;
    private Double runwayEndX;
    private Double runwayLength;
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
        middleY = graphics.getHeight()*0.5;
        runwayBeginX = screenWidth * 0.15;
        runwayLength = screenWidth * 0.7;
        runwayEndX = runwayBeginX + runwayLength;
        xScaler = runwayLength/currentRunway.getRRunway().getTora();
        if(currentObstacle.getDistanceRThreshold()>=(currentRunway.getRRunway().getTora()/2)) {
            runwayOffsetR = 0D;
        }
        else{
            runwayOffsetR = xScaler*(currentObstacle.getDistanceRThreshold())+obstacle.getWidth();
        }
        if(currentObstacle.getDistanceLThreshold()>=currentRunway.getLRunway().getTora()/2) {
            runwayOffsetL = 0D;
        }
        else{
            runwayOffsetL = xScaler*(currentObstacle.getDistanceLThreshold())+obstacle.getWidth();
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
        centreLine.setStrokeWidth(12d);
        centreLine.getStrokeDashArray().addAll(50d, 30d);

        thresholdR.setStroke(Color.WHITE);
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
        screenWidth = topDownView.getWidth();
        screenHeight = topDownView.getHeight();
        updateScaler();
        updateClearedAndGradedArea();
        updateRunwayBase();
        updateCentreLine();
        updateRunwayDesignator();
        updateThreshold();
        updateStopway();
        updateClearway();
        updateArrows();
        updateObstacle();
        addChildren();
        try {

            double graphicsRot = Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90;

            PrimaryWindowController.getInstance().rotateNeedle(graphicsRot);

            UtilsUI.rotateView(graphics, graphicsRot, 3000);

            double rotText = Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90;

            if(rotText <= 180){
                UtilsUI.rotateView(text, rotText, 3000);
            } else {
                UtilsUI.rotateView(text, Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 270, 3000);
            }

        }catch (NumberFormatException e){
            logger.info("invalidrunwayrotation");
        }

        PrimaryWindowController.getInstance().changeColourArrows();

    }




    public void updateClearedAndGradedArea(){
        double[] points = {
                0,0.3* screenHeight,
                0.2* screenWidth, 0.3* screenHeight,
                0.3* screenWidth,0.2* screenHeight,
                0.7* screenWidth,0.2* screenHeight,
                0.8* screenWidth,0.3* screenHeight,
                screenWidth,0.3* screenHeight,
                screenWidth,0.7* screenHeight,
                0.8* screenWidth,0.7* screenHeight,
                0.7* screenWidth,0.8* screenHeight,
                0.3* screenWidth,0.8* screenHeight,
                0.2* screenWidth,0.7* screenHeight,
                0,0.7* screenHeight};
        clearedAndGradedArea = new Polygon(points);
        clearedAndGradedArea.setFill(Color.BLUE);
        topDownView.getChildren().add(clearedAndGradedArea);
    }

    public void updateRunwayBase(){
        runwayBase.setX(runwayBeginX);
        runwayBase.setY(screenHeight *0.4);
        runwayBase.setWidth(runwayLength);
        runwayBase.setHeight(screenHeight *0.2);
    }

    public void updateCentreLine(){
        centreLine.setStartX(runwayBeginX+0.1*runwayLength);
        centreLine.setStartY(screenHeight *0.5);
        centreLine.setEndX(runwayEndX-0.1*runwayLength);
        centreLine.setEndY(screenHeight *0.5);
    }

    public void updateRunwayDesignator(){
        runwayDesignatorR.setX(runwayBeginX);
        runwayDesignatorR.setY(screenHeight *0.5);
        runwayDesignatorL.setX(runwayEndX-60);
        runwayDesignatorL.setY(screenHeight *0.5);
    }

    public void updateThreshold(){
        thresholdR.setStartX(runwayBeginX+displacedDesignatorR*xScaler);
        thresholdR.setStartY(screenHeight *0.45);
        thresholdR.setEndX(runwayBeginX+displacedDesignatorR*xScaler);
        thresholdR.setEndY(screenHeight *0.55);
        thresholdL.setStartX(runwayBeginX+runwayLength-displacedDesignatorL*xScaler);
        thresholdL.setStartY(screenHeight *0.45);
        thresholdL.setEndX(runwayBeginX+runwayLength-displacedDesignatorL*xScaler);
        thresholdL.setEndY(screenHeight *0.55);
    }

    public void updateStopway(){
        stopwayR.setX(runwayBeginX+runwayLength);
        stopwayR.setY(screenHeight *0.4);
        stopwayR.setWidth(currentRunway.getRRunway().getStopway()*xScaler);
        stopwayR.setHeight(screenHeight *0.2);
        stopwayL.setX(runwayBeginX-currentRunway.getLRunway().getStopway()*xScaler);
        stopwayL.setY(screenHeight *0.4);
        stopwayL.setWidth(currentRunway.getLRunway().getStopway()*xScaler);
        stopwayL.setHeight(screenHeight *0.2);
    }

    public void updateClearway(){
        clearwayR.setX(runwayBeginX+runwayLength);
        clearwayR.setY(screenHeight *0.35);
        clearwayR.setWidth(currentRunway.getRRunway().getClearway()*xScaler);
        clearwayR.setHeight(screenHeight *0.3);
        clearwayL.setX(runwayBeginX-currentRunway.getLRunway().getClearway()*xScaler);
        clearwayL.setY(screenHeight *0.35);
        clearwayL.setWidth(currentRunway.getLRunway().getClearway()*xScaler);
        clearwayL.setHeight(screenHeight *0.3);
    }

    public void setLinePos(Line line, double startX, double startY, double endX, double endY){
        line.setStartX(startX);
        line.setStartY(startY);

        line.setEndX(endX);
        line.setEndY(endY);
    }

    public void updateArrows(){
        arrows.getChildren().clear();
        text.getChildren().clear();

        updateText();

        if(currentObstacle.getDistanceRThreshold()>currentObstacle.getDistanceLThreshold()){
            TORAStartXR = runwayBeginX;
            TORAEndXR = runwayBeginX + currentRunway.getRRunway().getWorkingTORA() * xScaler;
            TODAStartXR = runwayBeginX;
            TODAEndXR = runwayBeginX + currentRunway.getRRunway().getWorkingTODA() * xScaler;
            ASDAStartXR = runwayBeginX;
            ASDAEndXR = runwayBeginX + currentRunway.getRRunway().getWorkingASDA() * xScaler;
            LDAStartXR = runwayBeginX;
            LDAEndXR = runwayBeginX + currentRunway.getRRunway().getWorkingLDA() * xScaler;

            TORAEndXL = runwayBeginX;
            TORAStartXL = runwayBeginX + currentRunway.getLRunway().getWorkingTORA() * xScaler;
            TODAEndXL = clearwayL.getX();
            TODAStartXL = TODAEndXL + currentRunway.getLRunway().getWorkingTODA() * xScaler;
            ASDAEndXL = stopwayL.getX();
            ASDAStartXL = ASDAEndXL + currentRunway.getLRunway().getWorkingASDA() * xScaler;
            LDAEndXL = runwayBeginX + currentRunway.getLRunway().getThreshold() * xScaler;
            LDAStartXL = LDAEndXL + currentRunway.getLRunway().getWorkingLDA() * xScaler;
        }


        setLinePos(TORAStartLineR, TORAStartXR,middleY, TORAStartXR,0);
        setLinePos(TORAEndLineR, TORAEndXR,middleY, TORAEndXR,0);
        setLinePos(TORAStartLineL,TORAStartXL, middleY, TORAStartXL, topDownView.getHeight());
        setLinePos(TORAEndLineL, TORAEndXL, middleY, TORAEndXL, topDownView.getHeight());

        setLinePos(TODAStartLineR, TODAStartXR,middleY, TODAStartXR,0);
        setLinePos(TODAEndLineR, TODAEndXR,middleY, TODAEndXR,0);
        setLinePos(TODAStartLineL,TODAStartXL, middleY, TODAStartXL, topDownView.getHeight());
        setLinePos(TODAEndLineL, TODAEndXL, middleY, TODAEndXL, topDownView.getHeight());

        setLinePos(ASDAStartLineR, ASDAStartXR,middleY, ASDAStartXR,0);
        setLinePos(ASDAEndLineR, ASDAEndXR,middleY, ASDAEndXR,0);
        setLinePos(ASDAStartLineL,ASDAStartXL, middleY, ASDAStartXL, topDownView.getHeight());
        setLinePos(ASDAEndLineL, ASDAEndXL, middleY, ASDAEndXL, topDownView.getHeight());

        setLinePos(LDAStartLineR, LDAStartXR,middleY, LDAStartXR,0);
        setLinePos(LDAEndLineR, LDAEndXR,middleY, LDAEndXR,0);
        setLinePos(LDAStartLineL,LDAStartXL, middleY, LDAStartXL, topDownView.getHeight());
        setLinePos(LDAEndLineL, LDAEndXL, middleY, LDAEndXL, topDownView.getHeight());

        arrowTORAR = new Arrow(TORAStartXR, 0.05*screenHeight, TORAEndXR, 0.05*screenHeight);
        arrowTORAL = new Arrow(TORAStartXL, 0.95*screenHeight, TORAEndXL, 0.95*screenHeight);

        arrowTODAR = new Arrow(TODAStartXR,0.15*screenHeight, TORAEndXR, 0.15*screenHeight);
        arrowTODAL = new Arrow(TORAStartXL, 0.85*screenHeight, TORAEndXL, 0.85*screenHeight);

        arrowASDAR = new Arrow(ASDAStartXR, 0.25*screenHeight, TORAEndXR, 0.25*screenHeight);
        arrowASDAL = new Arrow(ASDAStartXL, 0.75*screenHeight, TORAEndXL, 0.75*screenHeight);

        arrowLDAR = new Arrow(LDAStartXR, 0.35*screenHeight, LDAEndXR, 0.35*screenHeight);
        arrowLDAL = new Arrow(LDAStartXL, 0.65*screenHeight, LDAEndXL, 0.65*screenHeight);


        text.getChildren().add(textTORAR);
        text.getChildren().add(textTORAL);
        text.getChildren().add(textTODAR);
        text.getChildren().add(textTODAL);
        text.getChildren().add(textASDAR);
        text.getChildren().add(textASDAL);
        text.getChildren().add(textLDAR);
        text.getChildren().add(textLDAL);


        arrows.getChildren().add(text);


        arrows.getChildren().add(TORAStartLineR);
        arrows.getChildren().add(TORAStartLineL);
        arrows.getChildren().add(TORAEndLineR);
        arrows.getChildren().add(TORAEndLineL);
        arrows.getChildren().add(TODAStartLineR);
        arrows.getChildren().add(TODAStartLineL);
        arrows.getChildren().add(TODAEndLineR);
        arrows.getChildren().add(TODAEndLineL);
        arrows.getChildren().add(ASDAStartLineR);
        arrows.getChildren().add(ASDAStartLineL);
        arrows.getChildren().add(ASDAEndLineR);
        arrows.getChildren().add(ASDAEndLineL);
        arrows.getChildren().add(LDAStartLineR);
        arrows.getChildren().add(LDAStartLineL);
        arrows.getChildren().add(LDAEndLineR);
        arrows.getChildren().add(LDAEndLineL);

        arrows.getChildren().add(arrowTORAR);
        arrows.getChildren().add(arrowTORAL);
        arrows.getChildren().add(arrowTODAR);
        arrows.getChildren().add(arrowTODAL);
        arrows.getChildren().add(arrowASDAR);
        arrows.getChildren().add(arrowASDAL);
        arrows.getChildren().add(arrowLDAR);
        arrows.getChildren().add(arrowLDAL);


    }

    public void updateObstacle(){
        obstacle.setX(runwayBeginX+currentObstacle.getDistanceRThreshold()*xScaler);
        obstacle.setY(0.5* screenHeight +currentObstacle.getDistanceCenter()*xScaler-(currentObstacle.getWidth()*xScaler)/2);
        obstacle.setWidth((currentRunway.getRRunway().getTora()-(currentObstacle.getDistanceRThreshold()+currentObstacle.getDistanceLThreshold()))*xScaler);
        obstacle.setHeight(currentObstacle.getWidth()*xScaler);
    }

    public void addChildren(){
        topDownView.getChildren().clear();
        graphics.getChildren().clear();

        graphics.getChildren().add(clearedAndGradedArea);
        graphics.getChildren().add(runwayBase);
        graphics.getChildren().add(centreLine);
        graphics.getChildren().add(obstacle);

        graphics.getChildren().add(runwayDesignatorR);
        graphics.getChildren().add(thresholdR);
        graphics.getChildren().add(stopwayR);
        graphics.getChildren().add(clearwayR);


        graphics.getChildren().add(runwayDesignatorL);
        graphics.getChildren().add(thresholdL);
        graphics.getChildren().add(stopwayL);
        graphics.getChildren().add(clearwayL);

        topDownView.getChildren().add(graphics);

        if(isSelected){
            topDownView.getChildren().add(arrows);
        }

    }



    public void displayDirectedRunwaySelected(Runway runway) {
        currentRunway = runway;
        updateValues();
        updateUI();
    }

    public void displayObstacleSelected(Obstacle obstacle){
        currentObstacle = obstacle;
        updateValues();
        updateUI();
    }

    public Obstacle getCurrentObstacle(){
        return currentObstacle;
    }

    public void updateValues(){
        logger.info("currentRunway"+currentRunway);
        try {
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
        catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void updateText(){
        textTODAR.setText("TODA: "+TODAR.toString());
        textTODAR.setX(graphics.getWidth()*0.5);
        textTODAR.setY(screenHeight*0.14);
        textASDAR.setText("ASDA: "+ASDAR.toString());
        textASDAR.setX(graphics.getWidth()*0.5);
        textASDAR.setY(screenHeight*0.24);
        textTORAR.setText("TORA: "+TORAR.toString());
        textTORAR.setX(graphics.getWidth()*0.5);
        textTORAR.setY(screenHeight*0.04);
        textLDAR.setText("LDA: "+LDAR.toString());
        textLDAR.setX(graphics.getWidth()*0.5);
        textLDAR.setY(screenHeight*0.34);

        textTODAL.setText("TODA: "+TODAL.toString());
        textTODAL.setX(graphics.getWidth()*0.5);
        textTODAL.setY(screenHeight*0.83);
        textASDAL.setText("ASDA: "+ASDAL.toString());
        textASDAL.setX(graphics.getWidth()*0.5);
        textASDAL.setY(screenHeight*0.73);
        textTORAL.setText("TORA: "+TORAL.toString());
        textTORAL.setX(graphics.getWidth()*0.5);
        textTORAL.setY(screenHeight*0.93);
        textLDAL.setText("LDA: "+LDAL.toString());
        textLDAR.setX(graphics.getWidth()*0.5);
        textLDAR.setY(screenHeight*0.63);
    }


    public Runway getCurrentRunway(){
        return currentRunway;
    }

    public void changeColourTORA(Color color){
        arrowTORAL.changeColour(color);
        arrowTORAR.changeColour(color);
    }

    public void changeColourTODA(Color color){
        arrowTODAL.changeColour(color);
        arrowTODAR.changeColour(color);
    }

    public void changeColourLDA(Color color){
        arrowLDAL.changeColour(color);
        arrowLDAR.changeColour(color);
    }

    public void changeColourASDA(Color color){
        arrowASDAR.changeColour(color);
        arrowASDAL.changeColour(color);
    }
}