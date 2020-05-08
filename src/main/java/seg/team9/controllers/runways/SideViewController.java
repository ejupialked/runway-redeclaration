package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.PrimaryWindowController;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Stream;


public class SideViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    private TopDownViewController topDownViewController = TopDownViewController.getInstance();
    private Runway currentRunway;

    public boolean isSelected = false;


    @FXML AnchorPane sideView;

    private static SideViewController instance;

    private Pane graphics = new Pane();
    private Pane text = new Pane();

    private Double xScale;
    private Double screenHeight;
    private Double screenWidth;
    private Double yScale;


    private Rectangle runway = new Rectangle();
    private Rectangle grass = new Rectangle();


    private Rectangle obstacle = new Rectangle();

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

    public void initRectangles(){
        runway.setFill(Color.GRAY);
        grass.setFill(Color.GREEN);
        grass.setX(0);
        grass.setY(0);
    }

    public void updateUI(){
        rescale();
        updateRunway();
        updateArrows();
        updateObstacle();
        updateSlopes();
        addChildren();
    }

    public void updateRunway(){
        runway.setX(screenWidth*0.15);
        runway.setY(screenHeight*0.5);
        runway.setWidth(screenWidth*0.7);
        runway.setHeight(0.01*screenHeight);
    }

    private void updateArrows(){
        topDownViewController.updateUI();
    }

    private void updateObstacle(){
        Obstacle tempObstacle = topDownViewController.getCurrentObstacle();
        obstacle.setX(tempObstacle.getDistanceRThreshold()*xScale+topDownViewController.runwayBeginX);
        obstacle.setY(0.5*screenHeight-tempObstacle.getHeight()*yScale);

        obstacle.setWidth((currentRunway.getRRunway().getTora()-(tempObstacle.getDistanceRThreshold()+tempObstacle.getDistanceLThreshold()))*xScale);
        obstacle.setHeight(tempObstacle.getHeight()*yScale);
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
        grass.setY(screenHeight*0.5);
        grass.setWidth(screenWidth);
        grass.setHeight(screenHeight/2);
    }

    private void updateSlopes(){
        if(topDownViewController.getCurrentObstacle().getDistanceRThreshold()>topDownViewController.getCurrentObstacle().getDistanceLThreshold()){
            topDownViewController.setLinePos(slope, obstacle.getX(), obstacle.getY(), obstacle.getX()-(50*topDownViewController.getCurrentObstacle().getHeight())*xScale,screenHeight/2);
        }
        else{
            topDownViewController.setLinePos(slope, obstacle.getX()+obstacle.getWidth(), obstacle.getY(), obstacle.getX()+obstacle.getWidth()+(50*topDownViewController.getCurrentObstacle().getHeight())*xScale,screenHeight/2);
        }
    }

    private void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();



        text.getChildren().clear();


        sideView.getChildren().add(grass);
        graphics.getChildren().add(runway);
        graphics.getChildren().add(obstacle);
        graphics.getChildren().add(slope);

        sideView.getChildren().add(graphics);
        if(isSelected) {
            sideView.getChildren().add(topDownViewController.arrows);
        }

        sideView.getChildren().add(PrimaryWindowController.getInstance().getSideLegend());
        AnchorPane.setTopAnchor(PrimaryWindowController.getInstance().getSideLegend(), 20d);
        AnchorPane.setRightAnchor(PrimaryWindowController.getInstance().getSideLegend(), 20d);
    }


    public AnchorPane getSideView() {
        return sideView;
    }
}