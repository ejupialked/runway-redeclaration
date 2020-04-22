package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        addChildren();
    }

    public void updateRunway(){
        runway.setX(xScale*currentRunway.getRRunway().getClearway());
        runway.setY(0.5*screenHeight);
        runway.setWidth(xScale*currentRunway.getRRunway().getTora());
        runway.setHeight(0.01*screenHeight);
    }


    private void updateArrows(){
        topDownViewController.updateUI();
    }


    private void updateObstacle(){
        Obstacle tempObstacle = topDownViewController.getCurrentObstacle();
        obstacle.setX(tempObstacle.getDistanceRThreshold()*xScale);
        obstacle.setY(0.5*screenHeight-tempObstacle.getHeight()*xScale);

        obstacle.setWidth((currentRunway.getRRunway().getTora()-(tempObstacle.getDistanceRThreshold()+tempObstacle.getDistanceLThreshold()))*xScale);
        obstacle.setHeight(tempObstacle.getHeight()*xScale);
    }

    private void rescale(){
        graphics.setPrefWidth(sideView.getWidth());
        graphics.setPrefHeight(sideView.getHeight());

        currentRunway = topDownViewController.getCurrentRunway();

        xScale = graphics.getWidth()/(currentRunway.getRRunway().getToda() + currentRunway.getLRunway().getClearway());
        screenHeight = graphics.getHeight();
        screenWidth = graphics.getWidth();
        grass.setY(screenHeight*0.5);
        grass.setWidth(screenWidth);
        grass.setHeight(screenHeight);
    }

    private void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();
        text.getChildren().clear();

        sideView.getChildren().add(grass);

        graphics.getChildren().add(runway);
        graphics.getChildren().add(obstacle);

        graphics.setScaleX(0.8);
        graphics.setScaleY(0.8);
        graphics.setLayoutX(0.05*sideView.getWidth());


        sideView.getChildren().add(graphics);
        if(isSelected) {
            sideView.getChildren().add(topDownViewController.arrows);
        }
    }
}