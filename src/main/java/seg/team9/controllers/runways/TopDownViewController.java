package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import seg.team9.controllers.calculation.CalculationsViewController;
import seg.team9.utils.UtilsUI;

import java.net.URL;
import java.util.ResourceBundle;

public class TopDownViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("TopDownViewController");
    public AnchorPane gridPane1;


    String FONT_FAMILY = "Helvetica";

    @FXML private AnchorPane topDownView;
    private static TopDownViewController instance;

    private Button button = new Button("Helllo");
    public boolean isSelected = true;

    private Obstacle currentObstacle = new Obstacle("Nothing", 0D, 0D, 900000D, 0D,0D);
    private Runway currentRunway = new Runway(new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D),new DirectedRunway("SELECTARUNWAY",0D,0D,0D,0D,0D,0D,0D), 4000d);

    //UI STUFF
    private Pane graphics = new Pane();
    private Pane text = new Pane();
    public Pane arrows = new Pane();
    private Compass compass;
    private boolean textFlipped = false;


    public Double screenWidth;
    public Double screenHeight;
    public Double xScaler;
    public Double middleY;
    private Rectangle runwayBase = new Rectangle();
    private Polygon clearedAndGradedArea = new Polygon();
    private Line centreLine = new Line();

    private Rectangle obstacle = new Rectangle();

    //Right Arrows
    public Double TORAStartXR = 0D;
    public Double TORAEndXR = 0D;

    public Double TODAStartXR = 0D;
    public Double TODAEndXR = 0D;

    public Double ASDAStartXR = 0D;
    public Double ASDAEndXR = 0D;

    public Double LDAStartXR = 0D;
    public Double LDAEndXR = 0D;

    public Double RESAStartXR = 0D;
    public Double RESAEndXR = 0D;

    public Double BlastStartXR = 0D;
    public Double BlastEndXR = 0D;


    private Line TODAStartLineR = new Line();
    private Line ASDAStartLineR = new Line();
    private Line TORAStartLineR = new Line();
    private Line LDAStartLineR = new Line();
    private Line RESAStartLineR = new Line();
    private Line BlastStartLineR = new Line();

    private Line TODAEndLineR = new Line();
    private Line ASDAEndLineR = new Line();
    private Line TORAEndLineR = new Line();
    private Line LDAEndLineR = new Line();
    private Line RESAEndLineR = new Line();
    private Line BlastEndLineR = new Line();

    private Arrow arrowTODAR = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAR = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAR = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAR = new Arrow(0,0,0,0,0);
    private Arrow arrowRESAR = new Arrow(0,0,0,0,0);
    private Arrow arrowBlastR = new Arrow(0,0,0,0,0);

    private Text textTODAR = new Text("TODA");
    private Text textASDAR = new Text("ASDA");
    private Text textTORAR = new Text("TORA");
    private Text textLDAR = new Text("LDA");
    private Text textRESAR = new Text("RESA");
    private Text textBlastR = new Text("Blast Protection");

    //Left Arrows
    public Double TORAStartXL = 0D;
    public Double TORAEndXL = 0D;

    public Double TODAStartXL = 0D;
    public Double TODAEndXL = 0D;

    public Double ASDAStartXL = 0D;
    public Double ASDAEndXL = 0D;

    public Double LDAStartXL = 0D;
    public Double LDAEndXL = 0D;

    public Double RESAStartXL = 0D;
    public Double RESAEndXL = 0D;

    public Double BlastStartXL = 0D;
    public Double BlastEndXL = 0D;

    private Line TODAStartLineL = new Line();
    private Line ASDAStartLineL = new Line();
    private Line TORAStartLineL = new Line();
    private Line LDAStartLineL = new Line();
    private Line RESAStartLineL = new Line();
    private Line BlastStartLineL = new Line();

    private Line TODAEndLineL = new Line();
    private Line ASDAEndLineL = new Line();
    private Line TORAEndLineL = new Line();
    private Line LDAEndLineL = new Line();
    private Line RESAEndLineL = new Line();
    private Line BlastEndLineL = new Line();

    private Arrow arrowTODAL = new Arrow(0,0,0,0,0);
    private Arrow arrowASDAL = new Arrow(0,0,0,0,0);
    private Arrow arrowTORAL = new Arrow(0,0,0,0,0);
    private Arrow arrowLDAL = new Arrow(0,0,0,0,0);
    private Arrow arrowRESAL = new Arrow(0,0,0,0,0);
    private Arrow arrowBlastL = new Arrow(0,0,0,0,0);

    private Text textTODAL = new Text("TODA");
    private Text textASDAL = new Text("ASDA");
    private Text textTORAL = new Text("TORA");
    private Text textLDAL = new Text("LDA");
    private Text textRESAL = new Text("RESA");
    private Text textBlastL = new Text("Blast Protection");



    //Right Runway
    private Text runwayDesignatorR = new Text("SELECT A RUNWAY");
    private Line thresholdR = new Line();
    private Rectangle stopwayR = new Rectangle();
    private Rectangle clearwayR = new Rectangle();

    public Double TODAR = 0D;
    public Double ASDAR = 0D;
    public Double TORAR = 0D;
    public Double LDAR = 0D;
    public Double RESAR = 0D;

    //Left Runway
    private Text runwayDesignatorL = new Text("SELECT A RUNWAY");
    private Line thresholdL = new Line();
    private Rectangle stopwayL = new Rectangle();
    private Rectangle clearwayL = new Rectangle();

    public Double TODAL = 0D;
    public Double ASDAL = 0D;
    public Double TORAL = 0D;
    public Double LDAL = 0D;
    public Double RESAL = 0D;


    //Positional Info
    public Double runwayBeginX;
    public Double runwayEndX;
    public Double runwayLength;
    public Double runwayOffsetR;
    public Double runwayOffsetL;

    public Double displacedDesignatorL = 0D;
    public Double displacedDesignatorR = 0D;


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
        AnchorPane.setBottomAnchor(topDownView, 0d);
        AnchorPane.setTopAnchor(topDownView, 0d);
        AnchorPane.setLeftAnchor(topDownView, 0d);
        AnchorPane.setRightAnchor(topDownView, 0d);


        topDownView.setStyle("-fx-background-color: green");
        initCompass();
        initText();
        initLines();
        initRectangles();
        initArrowsColors();

        addChildren();


        topDownView.widthProperty().addListener((obs,oldVal,newVal) -> {
            updateUI();

        });
        topDownView.heightProperty().addListener((obs,oldVal,newVal) -> {
            updateUI();
        });

        updateUI();

    }

    private void initCompass() {
        this.compass = PrimaryWindowController.getInstance().getTopCompass();
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
        runwayDesignatorR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 18));
        runwayDesignatorR.setFill(Color.WHITE);
        runwayDesignatorR.setSmooth(true);
        runwayDesignatorR.setScaleX(1.7d);

        runwayDesignatorL.setRotate(270);
        runwayDesignatorL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 18));
        runwayDesignatorL.setFill(Color.WHITE);
        runwayDesignatorL.setSmooth(true);
        runwayDesignatorL.setScaleX(1.7d);


        textTODAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTODAR.setFill(Color.WHITE);
        textASDAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textASDAR.setFill(Color.WHITE);
        textTORAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTORAR.setFill(Color.WHITE);
        textLDAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textLDAR.setFill(Color.WHITE);
        textRESAR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textRESAR.setFill(Color.WHITE);
        textBlastR.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textBlastR.setFill(Color.WHITE);

        textTODAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTODAL.setFill(Color.WHITE);
        textASDAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textASDAL.setFill(Color.WHITE);
        textTORAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTORAL.setFill(Color.WHITE);
        textLDAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textLDAL.setFill(Color.WHITE);
        textRESAL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textRESAL.setFill(Color.WHITE);
        textBlastL.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textBlastL.setFill(Color.WHITE);
    }

    public void initLines(){
        centreLine.setStroke(Color.WHITE);
        centreLine.setStrokeWidth(7d);
        centreLine.getStrokeDashArray().addAll(30d, 30d);
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
        text.setPrefWidth(graphics.getWidth());
        text.setPrefHeight(graphics.getHeight());
        screenWidth = graphics.getWidth();
        screenHeight = graphics.getHeight();
        updateScaler();
        updateClearedAndGradedArea();
        updateRunwayBase();
        updateCentreLine();
        updateRunwayDesignator();
        updateThreshold();
        updateStopway();
        updateClearway();
        updateObstacle();
        updateArrows();
        addChildren();


        try {

            double graphicsRot = Integer.parseInt(currentRunway.getRRunway().getDesignator().replaceAll("\\D", "")) * 10 - 90;


            compass.rotateNeedle(graphicsRot-90);

            UtilsUI.rotateView(graphics, graphicsRot, 3000);

            if((graphicsRot>90) && !textFlipped) {
                UtilsUI.rotateView(text, 180, 3000);
                textFlipped = true;
            }
            else if(textFlipped){
                UtilsUI.rotateView(text, 0, 3000);
                textFlipped = false;

            }

        }catch (NumberFormatException e){
            logger.info("invalidrunwayrotation");
        }

        //PrimaryWindowController.getInstance().changeColourArrows();

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
        clearedAndGradedArea.setFill(Color.DARKBLUE);
        topDownView.getChildren().add(clearedAndGradedArea);
    }

    public void updateRunwayBase(){
        runwayBase.setX(runwayBeginX);
        runwayBase.setY(screenHeight * 0.45);
        runwayBase.setWidth(runwayLength);
        runwayBase.setHeight(screenHeight *0.1);
    }

    public void updateCentreLine(){
        centreLine.setStartX(runwayBeginX+0.1*runwayLength);
        centreLine.setStartY(screenHeight * 0.5-1);
        centreLine.setEndX(runwayEndX-0.1*runwayLength);
        centreLine.setEndY(screenHeight *0.5-1);
    }

    public void updateRunwayDesignator(){
        runwayDesignatorR.setX(runwayBeginX);
        runwayDesignatorR.setY(screenHeight *0.51);
        runwayDesignatorL.setX(runwayEndX-38);
        runwayDesignatorL.setY(screenHeight *0.51);

        ;
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
        line.setStroke(Color.WHITE);
        line.getStrokeDashArray().addAll(5d, 4d);

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
            RESAStartXR = RESAEndXR - currentRunway.getLRunway().getResa() * xScaler;
            RESAEndXR = obstacle.getX();


            if(LDAEndXR!=RESAStartXR){
                BlastStartXR = LDAEndXR;
                BlastEndXR = RESAStartXR;
            }
            else{
                BlastStartXR = 0D;
                BlastEndXR = 0D;
            }
            textBlastR.setText("Blast Protection: " + Math.round(Math.abs(LDAEndXR-RESAStartXR)/xScaler));



            TORAEndXL = runwayBeginX;
            TORAStartXL = runwayBeginX + currentRunway.getLRunway().getWorkingTORA() * xScaler;
            TODAEndXL = clearwayL.getX();
            TODAStartXL = TODAEndXL + currentRunway.getLRunway().getWorkingTODA() * xScaler;
            ASDAEndXL = stopwayL.getX();
            ASDAStartXL = ASDAEndXL + currentRunway.getLRunway().getWorkingASDA() * xScaler;
            LDAEndXL = runwayBeginX + currentRunway.getLRunway().getThreshold() * xScaler;
            LDAStartXL = LDAEndXL + currentRunway.getLRunway().getWorkingLDA() * xScaler;
            RESAStartXL = obstacle.getX();
            RESAEndXL = RESAStartXL - currentRunway.getLRunway().getResa() * xScaler;;

            if(LDAStartXL!=RESAEndXL){
                BlastStartXL = RESAEndXL;
                BlastEndXL = LDAStartXL;
            }
            else{
                BlastStartXL = 0D;
                BlastEndXL = 0D;
            }
            textBlastL.setText("Blast Protection: " + Math.round(Math.abs(LDAStartXL-RESAEndXL)/xScaler));
        }
        else{
            TORAEndXR = runwayBeginX + currentRunway.getRRunway().getTora() * xScaler;
            TORAStartXR = TORAEndXR - currentRunway.getRRunway().getWorkingTORA()*xScaler;
            TODAEndXR = runwayBeginX + currentRunway.getRRunway().getToda() * xScaler;
            TODAStartXR = TODAEndXR - currentRunway.getRRunway().getWorkingTODA()*xScaler;
            ASDAEndXR = runwayBeginX + currentRunway.getRRunway().getAsda() * xScaler;
            ASDAStartXR = ASDAEndXR - currentRunway.getRRunway().getWorkingASDA()*xScaler;
            LDAEndXR = runwayBeginX + currentRunway.getRRunway().getTora() * xScaler;
            LDAStartXR = LDAEndXR - currentRunway.getRRunway().getWorkingLDA()*xScaler;
            RESAStartXR = obstacle.getX()+obstacle.getWidth();
            RESAEndXR = RESAStartXR + currentRunway.getLRunway().getResa() * xScaler;;

            if(LDAStartXR!=RESAEndXR){
                BlastStartXR = LDAStartXR;
                BlastEndXR = RESAEndXR;
            }
            else{
                BlastStartXR = 0D;
                BlastEndXR = 0D;
            }
            textBlastR.setText("Blast Protection: " + Math.round(Math.abs(LDAStartXR-RESAEndXR)/xScaler));


            TORAStartXL = runwayBeginX + currentRunway.getLRunway().getTora() * xScaler;
            TORAEndXL = TORAStartXL - currentRunway.getLRunway().getWorkingTORA() * xScaler;
            TODAStartXL = runwayBeginX + currentRunway.getLRunway().getTora() * xScaler;
            TODAEndXL = TODAStartXL - currentRunway.getLRunway().getWorkingTODA() * xScaler;
            ASDAStartXL = runwayBeginX + currentRunway.getLRunway().getTora() * xScaler;
            ASDAEndXL = ASDAStartXL - currentRunway.getLRunway().getWorkingASDA() * xScaler;
            LDAStartXL = runwayBeginX + currentRunway.getLRunway().getTora() * xScaler;
            LDAEndXL = LDAStartXL - currentRunway.getLRunway().getWorkingLDA() * xScaler;
            RESAEndXL = obstacle.getX()+obstacle.getWidth();
            RESAStartXL = RESAEndXL + currentRunway.getLRunway().getResa() * xScaler;;

            if(LDAEndXL!=RESAStartXL){
                BlastStartXL = LDAEndXL;
                BlastEndXL = RESAStartXL;
            }
            else{
                BlastStartXL = 0D;
                BlastEndXL = 0D;
            }
            textBlastL.setText("Blast Protection: " + Math.round(Math.abs(LDAEndXL-RESAStartXL)/xScaler));
        }

        updateText();


        setLinePos(TORAStartLineR, TORAStartXR,middleY, TORAStartXR,screenHeight*0.05);
        setLinePos(TORAEndLineR, TORAEndXR,middleY, TORAEndXR,screenHeight*0.05);
        setLinePos(TORAStartLineL,TORAStartXL, middleY, TORAStartXL, screenHeight*0.95);
        setLinePos(TORAEndLineL, TORAEndXL, middleY, TORAEndXL, screenHeight*0.95);

        setLinePos(TODAStartLineR, TODAStartXR,middleY, TODAStartXR,screenHeight*0.15);
        setLinePos(TODAEndLineR, TODAEndXR,middleY, TODAEndXR,screenHeight*0.15);
        setLinePos(TODAStartLineL,TODAStartXL, middleY, TODAStartXL, screenHeight*0.85);
        setLinePos(TODAEndLineL, TODAEndXL, middleY, TODAEndXL, screenHeight*0.85);

        setLinePos(ASDAStartLineR, ASDAStartXR,middleY, ASDAStartXR,screenHeight*0.25);
        setLinePos(ASDAEndLineR, ASDAEndXR,middleY, ASDAEndXR,screenHeight*0.25);
        setLinePos(ASDAStartLineL,ASDAStartXL, middleY, ASDAStartXL, screenHeight*0.75);
        setLinePos(ASDAEndLineL, ASDAEndXL, middleY, ASDAEndXL, screenHeight*0.75);

        setLinePos(LDAStartLineR, LDAStartXR,middleY, LDAStartXR,screenHeight*0.35);
        setLinePos(LDAEndLineR, LDAEndXR,middleY, LDAEndXR,screenHeight*0.35);
        setLinePos(LDAStartLineL,LDAStartXL, middleY, LDAStartXL, screenHeight*0.65);
        setLinePos(LDAEndLineL, LDAEndXL, middleY, LDAEndXL, screenHeight*0.65);

        setLinePos(RESAStartLineR, RESAStartXR,middleY, RESAStartXR,screenHeight*0.4);
        setLinePos(RESAEndLineR, RESAEndXR,middleY, RESAEndXR,screenHeight*0.4);
        setLinePos(RESAStartLineL,RESAStartXL, middleY, RESAStartXL, screenHeight*0.6);
        setLinePos(RESAEndLineL, RESAEndXL, middleY, RESAEndXL, screenHeight*0.6);

        setLinePos(BlastStartLineR, BlastStartXR,middleY, BlastStartXR,screenHeight*0.42);
        setLinePos(BlastEndLineR, BlastEndXR,middleY, BlastEndXR,screenHeight*0.42);
        setLinePos(BlastStartLineL,BlastStartXL, middleY, BlastStartXL, screenHeight*0.58);
        setLinePos(BlastEndLineL, BlastEndXL, middleY, BlastEndXL, screenHeight*0.58);



        arrowTORAR = new Arrow(TORAStartXR, 0.05*screenHeight, TORAEndXR, 0.05*screenHeight);
        arrowTORAL = new Arrow(TORAStartXL, 0.95*screenHeight, TORAEndXL, 0.95*screenHeight);

        arrowTODAR = new Arrow(TODAStartXR,0.15*screenHeight, TODAEndXR, 0.15*screenHeight);
        arrowTODAL = new Arrow(TORAStartXL, 0.85*screenHeight, TODAEndXL, 0.85*screenHeight);

        arrowASDAR = new Arrow(ASDAStartXR, 0.25*screenHeight, ASDAEndXR, 0.25*screenHeight);
        arrowASDAL = new Arrow(ASDAStartXL, 0.75*screenHeight, ASDAEndXL, 0.75*screenHeight);

        arrowLDAR = new Arrow(LDAStartXR, 0.35*screenHeight, LDAEndXR, 0.35*screenHeight);
        arrowLDAL = new Arrow(LDAStartXL, 0.65*screenHeight, LDAEndXL, 0.65*screenHeight);

        arrowRESAR = new Arrow(RESAStartXR, 0.4*screenHeight, RESAEndXR, 0.4*screenHeight);
        arrowRESAL = new Arrow(RESAStartXL, 0.6*screenHeight, RESAEndXL, 0.6*screenHeight);

        arrowBlastR= new Arrow(BlastStartXR, 0.42*screenHeight, BlastEndXR, 0.42*screenHeight);
        arrowBlastL = new Arrow(BlastStartXL, 0.58*screenHeight, BlastEndXL, 0.58*screenHeight);

        initArrowsColors();


        text.getChildren().add(textTORAR);
        text.getChildren().add(textTORAL);
        text.getChildren().add(textTODAR);
        text.getChildren().add(textTODAL);
        text.getChildren().add(textASDAR);
        text.getChildren().add(textASDAL);
        text.getChildren().add(textLDAR);
        text.getChildren().add(textLDAL);
        text.getChildren().add(textRESAR);
        text.getChildren().add(textRESAL);
        text.getChildren().add(textBlastR);
        text.getChildren().add(textBlastL);


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
        arrows.getChildren().add(RESAStartLineR);
        arrows.getChildren().add(RESAStartLineL);
        arrows.getChildren().add(RESAEndLineR);
        arrows.getChildren().add(RESAEndLineL);
        arrows.getChildren().add(BlastStartLineR);
        arrows.getChildren().add(BlastStartLineL);
        arrows.getChildren().add(BlastEndLineR);
        arrows.getChildren().add(BlastEndLineL);

        arrows.getChildren().add(arrowTORAR);
        arrows.getChildren().add(arrowTORAL);
        arrows.getChildren().add(arrowTODAR);
        arrows.getChildren().add(arrowTODAL);
        arrows.getChildren().add(arrowASDAR);
        arrows.getChildren().add(arrowASDAL);
        arrows.getChildren().add(arrowLDAR);
        arrows.getChildren().add(arrowLDAL);
        arrows.getChildren().add(arrowRESAR);
        arrows.getChildren().add(arrowRESAL);
        arrows.getChildren().add(arrowBlastR);
        arrows.getChildren().add(arrowBlastL);


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
        clearedAndGradedArea.toBack();
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

        if(isSelected){
            graphics.getChildren().add(arrows);
        }


        topDownView.getChildren().add(graphics);
        topDownView.getChildren().add(PrimaryWindowController.getInstance().getTopLegend());

        AnchorPane.setTopAnchor(PrimaryWindowController.getInstance().getTopLegend(), 20d);
        AnchorPane.setRightAnchor(PrimaryWindowController.getInstance().getTopLegend(), 20d);



        topDownView.getChildren().add(compass);
        AnchorPane.setBottomAnchor(compass, 0d);
        AnchorPane.setRightAnchor(compass, -35d);
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

            CalculationsViewController.getInstance().updateCalculationValues(currentRunway);

            runwayDesignatorR.setText(currentRunway.getRRunway().getDesignator());
            TODAR = currentRunway.getRRunway().getWorkingTODA();
            ASDAR = currentRunway.getRRunway().getWorkingASDA();
            TORAR = currentRunway.getRRunway().getWorkingTORA();
            LDAR = currentRunway.getRRunway().getWorkingLDA();
            RESAR = currentRunway.getRRunway().getResa();


            runwayDesignatorL.setText(currentRunway.getLRunway().getDesignator());
            TODAL = currentRunway.getLRunway().getWorkingTODA();
            ASDAL = currentRunway.getLRunway().getWorkingASDA();
            TORAL = currentRunway.getLRunway().getWorkingTORA();
            LDAL = currentRunway.getLRunway().getWorkingLDA();
            RESAL = currentRunway.getLRunway().getResa();

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
        textRESAR.setText("RESA: "+ RESAR.toString());
        textRESAR.setY(screenHeight*0.40);
        textRESAR.setX(graphics.getWidth()*0.5);
        textBlastR.setY(screenHeight*0.42);
        textBlastR.setX(graphics.getWidth()*0.5);

        textTODAL.setText("TODA: "+TODAL.toString());
        textTODAL.setX(graphics.getWidth()*0.5);
        textTODAL.setY(screenHeight*0.84);
        textASDAL.setText("ASDA: "+ASDAL.toString());
        textASDAL.setX(graphics.getWidth()*0.5);
        textASDAL.setY(screenHeight*0.74);
        textTORAL.setText("TORA: "+TORAL.toString());
        textTORAL.setX(graphics.getWidth()*0.5);
        textTORAL.setY(screenHeight*0.94);
        textLDAL.setText("LDA: "+LDAL.toString());
        textLDAL.setX(graphics.getWidth()*0.5);
        textLDAL.setY(screenHeight*0.64);
        textRESAL.setText("RESA: "+ RESAL.toString());
        textRESAL.setX(graphics.getWidth()*0.5);
        textRESAL.setY(screenHeight*0.6);
        textBlastL.setX(graphics.getWidth()*0.5);
        textBlastL.setY(screenHeight*0.57);


    }


    public Runway getCurrentRunway(){
        return currentRunway;
    }

    public void initArrowsColors(){
        arrowTORAL.changeColour(UtilsUI.Colors.TORA);
        arrowTORAL.setStrokeWidth(4f);

        arrowTORAR.changeColour(UtilsUI.Colors.TORA);
        arrowTORAR.setStrokeWidth(4f);

        arrowTODAL.changeColour(UtilsUI.Colors.TODA);
        arrowTODAL.setStrokeWidth(4f);

        arrowTODAR.changeColour(UtilsUI.Colors.TODA);
        arrowTODAR.setStrokeWidth(4f);

        arrowLDAL.changeColour(UtilsUI.Colors.LDA);
        arrowLDAL.setStrokeWidth(4f);

        arrowLDAR.changeColour(UtilsUI.Colors.LDA);
        arrowLDAR.setStrokeWidth(4f);

        arrowASDAR.changeColour(UtilsUI.Colors.ASDA);
        arrowASDAR.setStrokeWidth(4f);

        arrowASDAL.changeColour(UtilsUI.Colors.ASDA);
        arrowASDAL.setStrokeWidth(4f);

        arrowRESAL.changeColour(UtilsUI.Colors.RESA);
        arrowRESAL.setStrokeWidth(4f);

        arrowRESAR.changeColour(UtilsUI.Colors.RESA);
        arrowRESAR.setStrokeWidth(4f);

        arrowBlastL.changeColour(UtilsUI.Colors.BLAST);
        arrowBlastL.setStrokeWidth(4f);

        arrowBlastR.changeColour(UtilsUI.Colors.BLAST);
        arrowBlastR.setStrokeWidth(4f);
    }


    public AnchorPane getTopDownView() {
        return topDownView;
    }
}