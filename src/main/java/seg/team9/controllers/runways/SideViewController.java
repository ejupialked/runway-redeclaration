package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;


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


    private Rectangle runway = new Rectangle();
    private Rectangle grass = new Rectangle();


    private Rectangle obstacle = new Rectangle();

    private Line TOCS = new Line();
    private Line ALS = new Line();

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
        sideView.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

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
        obstacle.setY(0.5*screenHeight-tempObstacle.getHeight()*xScale);

        obstacle.setWidth((currentRunway.getRRunway().getTora()-(tempObstacle.getDistanceRThreshold()+tempObstacle.getDistanceLThreshold()))*xScale);
        obstacle.setHeight(tempObstacle.getHeight()*xScale);
    }

    private void rescale(){
        topDownViewController.updateUI();
        graphics.setPrefWidth(sideView.getWidth());
        graphics.setPrefHeight(sideView.getHeight());

        currentRunway = topDownViewController.getCurrentRunway();

        xScale = topDownViewController.xScaler;
        screenHeight = topDownViewController.screenHeight;
        screenWidth = topDownViewController.screenWidth;
        grass.setY(screenHeight*0.5);
        grass.setWidth(screenWidth);
        grass.setHeight(screenHeight);
    }

    private void updateSlopes(){
        if(topDownViewController.getCurrentObstacle().getDistanceRThreshold()>topDownViewController.getCurrentObstacle().getDistanceLThreshold()){
            double middleOfObstacle = obstacle.getX()+(obstacle.getWidth()/2);
            topDownViewController.setLinePos(ALS, middleOfObstacle, obstacle.getY(), middleOfObstacle-currentRunway.getRRunway().getAls(),screenHeight/2);
        }
    }

    private void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();
        text.getChildren().clear();

        sideView.getChildren().add(grass);

        graphics.getChildren().add(runway);
        graphics.getChildren().add(obstacle);
        graphics.getChildren().add(TOCS);
        graphics.getChildren().add(ALS);



        sideView.getChildren().add(graphics);
        if(isSelected) {
            sideView.getChildren().add(topDownViewController.arrows);
        }
    }
}