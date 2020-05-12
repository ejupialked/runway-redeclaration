package seg.team9.controllers.runways;

import com.itextpdf.xmp.impl.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.PrimaryWindowController;
import seg.team9.utils.UtilsUI;

import java.net.URL;
import java.util.ResourceBundle;


public class SideViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    private TopDownViewController topDownViewController = TopDownViewController.getInstance();
    private Runway currentRunway;

    public boolean isSelected = false;

    public boolean isColorDefault = false;


    @FXML AnchorPane sideView;

    String FONT_FAMILY = "Helvetica";

    private static SideViewController instance;

    private Pane graphics = new Pane();
    private Pane text = new Pane();
    private Pane arrows = new Pane();

    private Double xScale;
    private Double screenHeight;
    private Double screenWidth;
    private Double yScale;
    public Double middleY;

    private Rectangle obstacle = new Rectangle();

    private Rectangle stopwayL = new Rectangle();
    private Rectangle stopwayR = new Rectangle();

    private Rectangle clearwayL = new Rectangle();
    private Rectangle clearwayR = new Rectangle();

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

    public Arrow arrowTODAR = new Arrow(0,0,0,0,0);
    public Arrow arrowASDAR = new Arrow(0,0,0,0,0);
    public Arrow arrowTORAR = new Arrow(0,0,0,0,0);
    public Arrow arrowLDAR = new Arrow(0,0,0,0,0);
    public Arrow arrowRESAR = new Arrow(0,0,0,0,0);
    public Arrow arrowBlastR = new Arrow(0,0,0,0,0);
    public Arrow arrowTakeoff = new Arrow(0,0,0,0,0);

    private Text textTODAR = new Text("TODA");
    private Text textASDAR = new Text("ASDA");
    private Text textTORAR = new Text("TORA");
    private Text textLDAR = new Text("LDA");
    private Text textRESAR = new Text("RESA");
    private Text textBlastR = new Text("Blast Protection");
    private Text textTakeoff= new Text("Take off Direction");

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

    public Arrow arrowTODAL = new Arrow(0,0,0,0,0);
    public Arrow arrowASDAL = new Arrow(0,0,0,0,0);
    public Arrow arrowTORAL = new Arrow(0,0,0,0,0);
    public Arrow arrowLDAL = new Arrow(0,0,0,0,0);
    public Arrow arrowRESAL = new Arrow(0,0,0,0,0);
    public Arrow arrowBlastL = new Arrow(0,0,0,0,0);
    public Arrow arrowLanding = new Arrow(0,0,0,0,0);

    private Text textTODAL = new Text("TODA");
    private Text textASDAL = new Text("ASDA");
    private Text textTORAL = new Text("TORA");
    private Text textLDAL = new Text("LDA");
    private Text textRESAL = new Text("RESA");
    private Text textBlastL = new Text("Blast Protection");
    private Text textLanding = new Text("Landing Direction");

    private Rectangle runway = new Rectangle();
    private Rectangle grass = new Rectangle();



    private Line slope = new Line();

    public SideViewController() {
        instance = this;
    }

    public static SideViewController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Init Side View");
        initRectangles();
        initText();
        sideView.setMinWidth(0);
        sideView.setMinHeight(0);

        obstacle.setFill(Color.RED);
        slope.setStroke(Color.WHITE);
        slope.setSmooth(true);

        slope.getStrokeDashArray().addAll(4d,4d);
        sideView.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        rescale();
        updateUI();
        addChildren();

        sideView.widthProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });
        sideView.heightProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });

    }

    public void initText(){
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
        textTakeoff.setFont(Font.font(FONT_FAMILY, FontWeight.BOLD, 13));
        textTakeoff.setFill(Color.WHITE);

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
        textLanding.setFont(Font.font(FONT_FAMILY,FontWeight.BOLD,13));
        textLanding.setFill(Color.WHITE);
    }

    public void initRectangles(){
        runway.setFill(Color.GRAY);
        grass.setFill(Color.GREEN);
        grass.setX(0);
        grass.setY(0);
        stopwayL.setFill(UtilsUI.Colors.STOPWAY);
        stopwayR.setFill(UtilsUI.Colors.STOPWAY);
        clearwayL.setFill(UtilsUI.Colors.CLEARWAY);
        clearwayR.setFill(UtilsUI.Colors.CLEARWAY);
    }

    public void updateUI(){
        rescale();
        topDownViewController.updateUI();
        updateRunway();
        updateObstacle();
        updateSlopes();
        updateArrows();
        updateText();
        updateStopwayClearWay();
        addChildren();
    }

    public void updateText(){
        textTODAR.setText("TODA: "+topDownViewController.TODAR.toString());
        textTODAR.setX(topDownViewController.TODAStartXR);
        textTODAR.setY(screenHeight*0.14);
        textASDAR.setText("ASDA: "+topDownViewController.ASDAR.toString());
        textASDAR.setX(topDownViewController.ASDAStartXR);
        textASDAR.setY(screenHeight*0.24);
        textTORAR.setText("TORA: "+topDownViewController.TORAR.toString());
        textTORAR.setX(topDownViewController.TORAStartXR);
        textTORAR.setY(screenHeight*0.04);
        textLDAR.setText("LDA: "+topDownViewController.LDAR.toString());
        textLDAR.setX(topDownViewController.LDAStartXR);
        textLDAR.setY(screenHeight*0.34);
        textRESAR.setText("RESA: "+ topDownViewController.RESAR.toString());
        textRESAR.setY(screenHeight*0.39);
        textRESAR.setX(topDownViewController.RESAStartXR);
        textBlastR.setY(screenHeight*0.415);
        textBlastR.setX(topDownViewController.BlastStartXR);
        textTakeoff.setText("Take off Direction");
        textTakeoff.setX(screenWidth*0.02);
        textTakeoff.setY(screenHeight*0.15);

        textTODAL.setText("TODA: "+topDownViewController.TODAL.toString());
        textTODAL.setX(topDownViewController.TODAEndXL);
        textTODAL.setY(screenHeight*0.84);
        textASDAL.setText("ASDA: "+topDownViewController.ASDAL.toString());
        textASDAL.setX(topDownViewController.ASDAEndXL);
        textASDAL.setY(screenHeight*0.74);
        textTORAL.setText("TORA: "+topDownViewController.TORAL.toString());
        textTORAL.setX(topDownViewController.TORAEndXL);
        textTORAL.setY(screenHeight*0.94);
        textLDAL.setText("LDA: "+topDownViewController.LDAL.toString());
        textLDAL.setX(topDownViewController.LDAEndXL);
        textLDAL.setY(screenHeight*0.64);
        textRESAL.setText("RESA: "+ topDownViewController.RESAL.toString());
        textRESAL.setX(topDownViewController.RESAEndXL);
        textRESAL.setY(screenHeight*0.595);
        textBlastL.setX(topDownViewController.BlastEndXL);
        textBlastL.setY(screenHeight*0.57);
        textLanding.setText("Landing Direction");
        textLanding.setX(screenWidth*0.87);
        textLanding.setY(screenHeight*0.90);


    }

    public void updateRunway(){
        runway.setX(screenWidth*0.15);
        runway.setY(screenHeight*0.5);
        runway.setWidth(screenWidth*0.7);
        runway.setHeight(0.01*screenHeight);
    }

    private void updateArrows(){
        text.getChildren().clear();
        arrows.getChildren().clear();

        updateText();

        if(topDownViewController.getCurrentObstacle().getDistanceRThreshold()>topDownViewController.getCurrentObstacle().getDistanceLThreshold()){
            TORAStartXR = topDownViewController.runwayBeginX;
            TORAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getWorkingTORA() * topDownViewController.xScaler;
            TODAStartXR = topDownViewController.runwayBeginX;
            TODAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getWorkingTODA() * topDownViewController.xScaler;
            ASDAStartXR = topDownViewController.runwayBeginX;
            ASDAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getWorkingASDA() * topDownViewController.xScaler;
            LDAStartXR = topDownViewController.runwayBeginX;
            LDAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getWorkingLDA() * topDownViewController.xScaler;
            RESAEndXR = obstacle.getX();
            RESAStartXR = RESAEndXR - currentRunway.getLRunway().getResa() * topDownViewController.xScaler;


            if(LDAEndXR!=RESAStartXR){
                BlastStartXR = LDAEndXR;
                BlastEndXR = RESAStartXR;
            }
            else{
                BlastStartXR = 0D;
                BlastEndXR = 0D;
            }
            textBlastR.setText(Math.round(Math.abs(LDAEndXR-RESAStartXR)/topDownViewController.xScaler) + "m");



            TORAEndXL = topDownViewController.runwayBeginX;
            TORAStartXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getWorkingTORA() * topDownViewController.xScaler;
            TODAEndXL = topDownViewController.clearwayL.getX();
            TODAStartXL = TODAEndXL + currentRunway.getLRunway().getWorkingTODA() * topDownViewController.xScaler;
            ASDAEndXL = topDownViewController.stopwayL.getX();
            ASDAStartXL = ASDAEndXL + currentRunway.getLRunway().getWorkingASDA() * topDownViewController.xScaler;
            LDAEndXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getThreshold() * topDownViewController.xScaler;
            LDAStartXL = LDAEndXL + currentRunway.getLRunway().getWorkingLDA() * topDownViewController.xScaler;
            RESAStartXL = obstacle.getX();
            RESAEndXL = RESAStartXL - currentRunway.getLRunway().getResa() * topDownViewController.xScaler;;

            if(LDAStartXL!=RESAEndXL){
                BlastStartXL = RESAEndXL;
                BlastEndXL = LDAStartXL;
            }
            else{
                BlastStartXL = 0D;
                BlastEndXL = 0D;
            }
            textBlastL.setText(Math.round(Math.abs(LDAStartXL-RESAEndXL)/topDownViewController.xScaler) + "m");
        }
        else{
            TORAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getTora() * topDownViewController.xScaler;
            TORAStartXR = TORAEndXR - currentRunway.getRRunway().getWorkingTORA()*topDownViewController.xScaler;
            TODAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getToda() * topDownViewController.xScaler;
            TODAStartXR = TODAEndXR - currentRunway.getRRunway().getWorkingTODA()*topDownViewController.xScaler;
            ASDAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getAsda() * topDownViewController.xScaler;
            ASDAStartXR = ASDAEndXR - currentRunway.getRRunway().getWorkingASDA()*topDownViewController.xScaler;
            LDAEndXR = topDownViewController.runwayBeginX + currentRunway.getRRunway().getTora() * topDownViewController.xScaler;
            LDAStartXR = LDAEndXR - currentRunway.getRRunway().getWorkingLDA()*topDownViewController.xScaler;
            RESAStartXR = obstacle.getX()+obstacle.getWidth();
            RESAEndXR = RESAStartXR + currentRunway.getLRunway().getResa() * topDownViewController.xScaler;;

            if(LDAStartXR!=RESAEndXR){
                BlastStartXR = LDAStartXR;
                BlastEndXR = RESAEndXR;
            }
            else{
                BlastStartXR = 0D;
                BlastEndXR = 0D;
            }
            textBlastR.setText(Math.round(Math.abs(LDAStartXR-RESAEndXR)/topDownViewController.xScaler) + "m");


            TORAStartXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getTora() * topDownViewController.xScaler;
            TORAEndXL = TORAStartXL - currentRunway.getLRunway().getWorkingTORA() * topDownViewController.xScaler;
            TODAStartXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getTora() * topDownViewController.xScaler;
            TODAEndXL = TODAStartXL - currentRunway.getLRunway().getWorkingTODA() * topDownViewController.xScaler;
            ASDAStartXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getTora() * topDownViewController.xScaler;
            ASDAEndXL = ASDAStartXL - currentRunway.getLRunway().getWorkingASDA() * topDownViewController.xScaler;
            LDAStartXL = topDownViewController.runwayBeginX + currentRunway.getLRunway().getTora() * topDownViewController.xScaler;
            LDAEndXL = LDAStartXL - currentRunway.getLRunway().getWorkingLDA() * topDownViewController.xScaler;
            RESAEndXL = obstacle.getX()+obstacle.getWidth();
            RESAStartXL = RESAEndXL + currentRunway.getLRunway().getResa() * topDownViewController.xScaler;;

            if(LDAEndXL!=RESAStartXL){
                BlastStartXL = LDAEndXL;
                BlastEndXL = RESAStartXL;
            }
            else{
                BlastStartXL = 0D;
                BlastEndXL = 0D;
            }
            textBlastL.setText(Math.round(Math.abs(LDAEndXL-RESAStartXL)/topDownViewController.xScaler) + "m");
        }

        updateText();

        topDownViewController.updateUI();
        topDownViewController.setLinePos(TORAStartLineR, TORAStartXR,middleY, TORAStartXR,screenHeight*0.05);
        topDownViewController.setLinePos(TORAEndLineR, TORAEndXR,middleY, TORAEndXR,screenHeight*0.05);
        topDownViewController.setLinePos(TORAStartLineL,TORAStartXL, middleY, TORAStartXL, screenHeight*0.95);
        topDownViewController.setLinePos(TORAEndLineL, TORAEndXL, middleY, TORAEndXL, screenHeight*0.95);

        topDownViewController.setLinePos(TODAStartLineR, TODAStartXR,middleY, TODAStartXR,screenHeight*0.15);
        topDownViewController.setLinePos(TODAEndLineR, TODAEndXR,middleY, TODAEndXR,screenHeight*0.15);
        topDownViewController.setLinePos(TODAStartLineL,TODAStartXL, middleY, TODAStartXL, screenHeight*0.85);
        topDownViewController.setLinePos(TODAEndLineL, TODAEndXL, middleY, TODAEndXL, screenHeight*0.85);

        topDownViewController.setLinePos(ASDAStartLineR, ASDAStartXR,middleY, ASDAStartXR,screenHeight*0.25);
        topDownViewController.setLinePos(ASDAEndLineR, ASDAEndXR,middleY, ASDAEndXR,screenHeight*0.25);
        topDownViewController.setLinePos(ASDAStartLineL,ASDAStartXL, middleY, ASDAStartXL, screenHeight*0.75);
        topDownViewController.setLinePos(ASDAEndLineL, ASDAEndXL, middleY, ASDAEndXL, screenHeight*0.75);

        topDownViewController.setLinePos(LDAStartLineR, LDAStartXR,middleY, LDAStartXR,screenHeight*0.35);
        topDownViewController.setLinePos(LDAEndLineR, LDAEndXR,middleY, LDAEndXR,screenHeight*0.35);
        topDownViewController.setLinePos(LDAStartLineL,LDAStartXL, middleY, LDAStartXL, screenHeight*0.65);
        topDownViewController.setLinePos(LDAEndLineL, LDAEndXL, middleY, LDAEndXL, screenHeight*0.65);

        topDownViewController.setLinePos(RESAStartLineR, RESAStartXR,middleY, RESAStartXR,screenHeight*0.4);
        topDownViewController.setLinePos(RESAEndLineR, RESAEndXR,middleY, RESAEndXR,screenHeight*0.4);
        topDownViewController.setLinePos(RESAStartLineL,RESAStartXL, middleY, RESAStartXL, screenHeight*0.6);
        topDownViewController.setLinePos(RESAEndLineL, RESAEndXL, middleY, RESAEndXL, screenHeight*0.6);

        topDownViewController.setLinePos(BlastStartLineR, BlastStartXR,middleY, BlastStartXR,screenHeight*0.42);
        topDownViewController.setLinePos(BlastEndLineR, BlastEndXR,middleY, BlastEndXR,screenHeight*0.42);
        topDownViewController.setLinePos(BlastStartLineL,BlastStartXL, middleY, BlastStartXL, screenHeight*0.58);
        topDownViewController.setLinePos(BlastEndLineL, BlastEndXL, middleY, BlastEndXL, screenHeight*0.58);



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

        arrowTakeoff = new Arrow(0.03*screenWidth,0.10*screenHeight,0.10*screenWidth,0.10*screenHeight);
        arrowLanding = new Arrow(0.97*screenWidth,0.95*screenHeight,0.90*screenWidth,0.95*screenHeight);

        if(!isColorDefault)
            initArrowsColors();
        else
            initArrowsColoursDefault();


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
        text.getChildren().add(textTakeoff);
        text.getChildren().add(textLanding);




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
        arrows.getChildren().add(arrowTakeoff);
        arrows.getChildren().add(arrowLanding);
    }

    private void updateObstacle(){
        Obstacle tempObstacle = topDownViewController.getCurrentObstacle();
        obstacle.setX(tempObstacle.getDistanceRThreshold()*xScale+topDownViewController.runwayBeginX);
        obstacle.setY(0.5*screenHeight-tempObstacle.getHeight()*yScale);

        obstacle.setWidth((currentRunway.getRRunway().getTora()-(tempObstacle.getDistanceRThreshold()+tempObstacle.getDistanceLThreshold()))*xScale);
        obstacle.setHeight(tempObstacle.getHeight()*yScale);
    }

    private void updateStopwayClearWay(){
        stopwayR.setX(topDownViewController.stopwayR.getX());
        stopwayR.setY(middleY);
        stopwayR.setWidth(topDownViewController.stopwayR.getWidth());
        stopwayR.setHeight(yScale*100);

        stopwayL.setX(topDownViewController.stopwayL.getX());
        stopwayL.setY(middleY);
        stopwayL.setWidth(topDownViewController.stopwayL.getWidth());
        stopwayL.setHeight(yScale*100);

        clearwayR.setX(topDownViewController.clearwayR.getX());
        clearwayR.setY(middleY);
        clearwayR.setWidth(topDownViewController.clearwayR.getWidth());
        clearwayR.setHeight(yScale*200);

        clearwayL.setX(topDownViewController.clearwayL.getX());
        clearwayL.setY(middleY);
        clearwayL.setWidth(topDownViewController.clearwayL.getWidth());
        clearwayL.setHeight(yScale*200);
    }


    private void rescale(){
        topDownViewController.updateUI();
        graphics.setPrefWidth(sideView.getWidth());
        graphics.setPrefHeight(sideView.getHeight());

        currentRunway = topDownViewController.getCurrentRunway();

        xScale = topDownViewController.xScaler;
        yScale = xScale*4;
        screenHeight = topDownViewController.screenHeight;
        screenWidth = topDownViewController.screenWidth;
        middleY = topDownViewController.screenHeight*0.5;
        grass.setY(screenHeight*0.5);
        grass.setWidth(screenWidth);
        grass.setHeight(screenHeight/2);
    }

    private void updateSlopes(){
        Double top = obstacle.getX()+obstacle.getWidth();
        if(topDownViewController.getCurrentObstacle().getDistanceRThreshold()>topDownViewController.getCurrentObstacle().getDistanceLThreshold()){
            topDownViewController.setLinePos(slope, top, obstacle.getY(), top-(50*topDownViewController.getCurrentObstacle().getHeight())*xScale,screenHeight/2);
        }
        else{
            topDownViewController.setLinePos(slope, top, obstacle.getY(), top+(50*topDownViewController.getCurrentObstacle().getHeight())*xScale,screenHeight/2);
        }
    }

    private void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();


        sideView.getChildren().add(grass);
        graphics.getChildren().add(runway);
        graphics.getChildren().add(obstacle);
        graphics.getChildren().add(slope);
        graphics.getChildren().add(clearwayL);
        graphics.getChildren().add(clearwayR);
        graphics.getChildren().add(stopwayL);
        graphics.getChildren().add(stopwayR);

        sideView.getChildren().add(graphics);
        sideView.getChildren().add(arrows);
        sideView.getChildren().add(text);

        sideView.getChildren().add(PrimaryWindowController.getInstance().getSideLegend());
        AnchorPane.setTopAnchor(PrimaryWindowController.getInstance().getSideLegend(), 20d);
        AnchorPane.setRightAnchor(PrimaryWindowController.getInstance().getSideLegend(), 20d);
    }


    public AnchorPane getSideView() {
        return sideView;
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

        arrowTakeoff.changeColour(UtilsUI.Colors.DIRECTION);
        arrowTakeoff.setStrokeWidth(4f);

        arrowLanding.changeColour(UtilsUI.Colors.DIRECTION);
        arrowLanding.setStrokeWidth(4f);

        PrimaryWindowController primary =  PrimaryWindowController.getInstance();

        primary.setSideLegend(primary.getSideLegendBlind());
        primary.setTopLegend(primary.getTopLegendBlind());

        isColorDefault = false;

    }

    public void initArrowsColoursDefault(){

        arrowTORAL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowTORAL.setStrokeWidth(4f);

        arrowTORAR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowTORAR.setStrokeWidth(4f);

        arrowTODAL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowTODAL.setStrokeWidth(4f);

        arrowTODAR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowTODAR.setStrokeWidth(4f);

        arrowLDAL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowLDAL.setStrokeWidth(4f);

        arrowLDAR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowLDAR.setStrokeWidth(4f);

        arrowASDAR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowASDAR.setStrokeWidth(4f);

        arrowASDAL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowASDAL.setStrokeWidth(4f);

        arrowRESAL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowRESAL.setStrokeWidth(4f);

        arrowRESAR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowRESAR.setStrokeWidth(4f);

        arrowBlastL.changeColour(UtilsUI.Colors.DEFAULT);
        arrowBlastL.setStrokeWidth(4f);

        arrowBlastR.changeColour(UtilsUI.Colors.DEFAULT);
        arrowBlastR.setStrokeWidth(4f);

        arrowTakeoff.changeColour(UtilsUI.Colors.DEFAULT);
        arrowTakeoff.setStrokeWidth(4f);

        arrowLanding.changeColour(UtilsUI.Colors.DEFAULT);
        arrowLanding.setStrokeWidth(4f);

        PrimaryWindowController primary =  PrimaryWindowController.getInstance();

        primary.setSideLegend(primary.getSideMapLegendDefault());
        primary.setTopLegend(primary.getTopMapLegendDefault());

        isColorDefault = true;
    }
}