package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.Runway;

import java.net.URL;
import java.util.ResourceBundle;


public class SideViewController implements Initializable {
    private static final Logger logger = LogManager.getLogger("SideViewController");

    private TopDownViewController topDownViewController = TopDownViewController.getInstance();
    private Runway currentRunway;


    @FXML AnchorPane sideView;

    private Pane graphics = new Pane();
    private Pane text = new Pane();

    private Double xScale;
    private Double screenHeight;
    private Double usableRunwayStartR;
    private Double usableRunwayStartL;

    private Rectangle runway = new Rectangle();

    private Line startTODAR = new Line();
    private Line startASDAR = new Line();
    private Line startTORAR = new Line();
    private Line startLDAR  = new Line();

    private Line startTODAL = new Line();
    private Line startASDAL = new Line();
    private Line startTORAL = new Line();
    private Line startLDAL  = new Line();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logger.info("Init Side View");
        initRectangles();
        sideView.setMinWidth(0);
        sideView.setMinHeight(0);

        rescale();
        updateUI();
        addChildren();

        sideView.widthProperty().addListener((observable, oldValue, newValue) -> {
            rescale();
            updateUI();
            addChildren();
        });
        sideView.heightProperty().addListener((observable, oldValue, newValue) -> {
            rescale();
            updateUI();
            addChildren();
        });
    }

    public void initRectangles(){
        runway.setFill(Color.GRAY);
    }

    public void updateUI(){
        updateRunway();
        updateTODAs();
    }

    public void updateRunway(){
        runway.setX(xScale*currentRunway.getRRunway().getClearway());
        runway.setY(0.5*screenHeight);
        runway.setWidth(xScale*currentRunway.getRRunway().getTora());
        runway.setHeight(0.1*screenHeight);
    }

    public void updateTODAs(){
        startTODAR.setStartX(usableRunwayStartR);
        startTODAR.setStartY(0.5*screenHeight);
        startTODAR.setEndX(usableRunwayStartR);
        startTODAR.setEndY(screenHeight);

        startTODAR.setStartX(usableRunwayStartL);
        startTODAR.setStartY(0.5*screenHeight);
        startTODAR.setEndX(usableRunwayStartL);
        startTODAR.setEndY(screenHeight);
    }

    public void rescale(){
        graphics.setPrefWidth(sideView.getWidth());
        graphics.setPrefHeight(sideView.getHeight());
        currentRunway = topDownViewController.getCurrentRunway();
        xScale = sideView.getWidth()/(currentRunway.getRRunway().getToda() + currentRunway.getLRunway().getClearway());
        screenHeight = sideView.getHeight();
        usableRunwayStartR = xScale*currentRunway.getRRunway().getClearway();
        usableRunwayStartL = usableRunwayStartR +xScale*currentRunway.getRRunway().getTora();
    }

    public void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();
        text.getChildren().clear();

        graphics.getChildren().add(runway);

        graphics.getChildren().add(startTODAR);
        graphics.getChildren().add(startASDAR);
        graphics.getChildren().add(startTORAR);
        graphics.getChildren().add(startLDAR);

        graphics.getChildren().add(startTODAL);
        graphics.getChildren().add(startASDAL);
        graphics.getChildren().add(startTORAL);
        graphics.getChildren().add(startLDAL);

        sideView.getChildren().add(graphics);
    }
}