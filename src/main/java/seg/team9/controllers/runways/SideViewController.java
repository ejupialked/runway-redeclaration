package seg.team9.controllers.runways;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private Double screenWidth;
    private Double usableRunwayStartR;
    private Double usableRunwayStartL;
    private Double landingRunwayStartR;
    private Double landingRunwayStartL;

    private Label labelASDAR = new Label("ASDA");
    private Label labelTORAR = new Label("TORA");
    private Label labelTODAR = new Label("TODA");
    private Label labelLDAR = new Label("LDA");

    private Label labelASDAL = new Label("ASDA");
    private Label labelTORAL = new Label("TORA");
    private Label labelTODAL = new Label("TODA");
    private Label labelLDAL = new Label("LDA");

    private Double endASDAxR;
    private Double endTORAxR;
    private Double endTODAxR;
    private Double endLDAxR;

    private Double endASDAxL;
    private Double endTORAxL;
    private Double endTODAxL;
    private Double endLDAxL;

    private Rectangle runway = new Rectangle();

    private Line usableStartR = new Line();
    private Line landingStartR = new Line();

    private Line usableStartL = new Line();
    private Line landingStartL = new Line();

    private Arrow arrowASDAR;
    private Arrow arrowTORAR;
    private Arrow arrowTODAR;
    private Arrow arrowLDAR;

    private Arrow arrowASDAL;
    private Arrow arrowTORAL;
    private Arrow arrowTODAL;
    private Arrow arrowLDAL;

    private Line endASDALineR = new Line();
    private Line endTORALineR = new Line();
    private Line endTODALineR = new Line();
    private Line endLDALineR = new Line();

    private Line endASDALineL = new Line();
    private Line endTORALineL = new Line();
    private Line endTODALineL = new Line();
    private Line endLDALineL = new Line();

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
        updateUsableStarts();
        updateLandingStarts();
        updateEnds();
        updateArrows();
        moveLabels();
    }

    public void updateRunway(){
        runway.setX(xScale*currentRunway.getRRunway().getClearway());
        runway.setY(0.5*screenHeight);
        runway.setWidth(xScale*currentRunway.getRRunway().getTora());
        runway.setHeight(0.01*screenHeight);
    }

    public void updateUsableStarts(){
        updateStarts(usableStartR, usableRunwayStartR, usableStartL, usableRunwayStartL);
    }

    private void updateLandingStarts(){
        updateStarts(landingStartR, landingRunwayStartR, landingStartL, landingRunwayStartL);
    }

    private void updateStarts(Line landingStartR, Double landingRunwayStartR, Line landingStartL, Double landingRunwayStartL) {
        landingStartR.setStartX(landingRunwayStartR);
        landingStartR.setStartY(0.5*screenHeight);
        landingStartR.setEndX(usableRunwayStartR);
        landingStartR.setEndY(0);

        landingStartL.setStartX(landingRunwayStartL);
        landingStartL.setStartY(0.5*screenHeight);
        landingStartL.setEndX(usableRunwayStartL);
        landingStartL.setEndY(screenHeight);
    }

    private void updateArrows(){
        arrowASDAR = new Arrow(usableRunwayStartR, 0.2*screenHeight,endASDAxR,0.2*screenHeight,5);
        arrowASDAL = new Arrow(usableRunwayStartL, 0.85*screenHeight,endASDAxL,0.85*screenHeight,5);

        arrowLDAR = new Arrow(landingRunwayStartR, 0.4*screenHeight,endLDAxR,0.4*screenHeight,5);
        arrowLDAL = new Arrow(landingRunwayStartL, 0.65*screenHeight,endLDAxL,0.65*screenHeight,5);

        arrowTORAR = new Arrow(usableRunwayStartR, 0.3*screenHeight,endTORAxR,0.3*screenHeight,5);
        arrowTORAL = new Arrow(usableRunwayStartL, 0.75*screenHeight,endTORAxL,0.75*screenHeight,5);

        arrowTODAR = new Arrow(usableRunwayStartR, 0.1*screenHeight,endTODAxR,0.1*screenHeight,5);
        arrowTODAL = new Arrow(usableRunwayStartL, 0.95*screenHeight,endTODAxL,0.95*screenHeight,5);
    }

    private void moveLabels(){
        labelASDAR.setLayoutX(0.2*screenWidth);
        labelASDAR.setLayoutY(0.2*screenHeight);

        labelASDAL.setLayoutX(0.8*screenWidth);
        labelASDAL.setLayoutY(0.85*screenHeight);


        labelTODAR.setLayoutX(0.2*screenWidth);
        labelTODAR.setLayoutY(0.1*screenHeight);

        labelTODAL.setLayoutX(0.8*screenWidth);
        labelTODAL.setLayoutY(0.95*screenHeight);


        labelTORAR.setLayoutX(0.2*screenWidth);
        labelTORAR.setLayoutY(0.3*screenHeight);

        labelTORAL.setLayoutX(0.8*screenWidth);
        labelTORAL.setLayoutY(0.75*screenHeight);


        labelLDAR.setLayoutX(0.2*screenWidth);
        labelLDAR.setLayoutY(0.4*screenHeight);

        labelLDAL.setLayoutX(0.8*screenWidth);
        labelLDAL.setLayoutY(0.65*screenHeight);
    }

    private void updateEnds(){
        endASDALineR.setStartX(endASDAxR);
        endASDALineR.setStartY(0.5*screenHeight);
        endASDALineR.setEndX(endASDAxR);
        endASDALineR.setEndY(0.2*screenHeight);

        endASDALineL.setStartX(endASDAxL);
        endASDALineL.setStartY(0.51*screenHeight);
        endASDALineL.setEndX(endASDAxL);
        endASDALineL.setEndY(0.85*screenHeight);


        endLDALineR.setStartX(endLDAxR);
        endLDALineR.setStartY(0.5*screenHeight);
        endLDALineR.setEndX(endLDAxR);
        endLDALineR.setEndY(0.4*screenHeight);

        endLDALineL.setStartX(endLDAxL);
        endLDALineL.setStartY(0.51*screenHeight);
        endLDALineL.setEndX(endLDAxL);
        endLDALineL.setEndY(0.65*screenHeight);


        endTODALineR.setStartX(endTODAxR);
        endTODALineR.setStartY(0.5*screenHeight);
        endTODALineR.setEndX(endTODAxR);
        endTODALineR.setEndY(0.3*screenHeight);

        endTODALineL.setStartX(endTODAxL);
        endTODALineL.setStartY(0.51*screenHeight);
        endTODALineL.setEndX(endTODAxL);
        endTODALineL.setEndY(0.75*screenHeight);


        endTORALineR.setStartX(endTORAxR);
        endTORALineR.setStartY(0.5*screenHeight);
        endTORALineR.setEndX(endTORAxR);
        endTORALineR.setEndY(0.1*screenHeight);

        endTORALineL.setStartX(endTORAxL);
        endTORALineL.setStartY(0.51*screenHeight);
        endTORALineL.setEndX(endTORAxL);
        endTORALineL.setEndY(0.95*screenHeight);
    }

    private void rescale(){
        graphics.setPrefWidth(sideView.getWidth());
        graphics.setPrefHeight(sideView.getHeight());

        currentRunway = topDownViewController.getCurrentRunway();

        xScale = sideView.getWidth()/(currentRunway.getRRunway().getToda() + currentRunway.getLRunway().getClearway());
        screenHeight = sideView.getHeight();
        screenWidth = sideView.getWidth();

        usableRunwayStartR = xScale*currentRunway.getRRunway().getClearway();
        usableRunwayStartL = usableRunwayStartR +xScale*currentRunway.getRRunway().getTora();

        landingRunwayStartR = xScale*currentRunway.getRRunway().getClearway();
        landingRunwayStartL = landingRunwayStartR + xScale*currentRunway.getRRunway().getTora();

        endASDAxR = usableRunwayStartR + currentRunway.getRRunway().getWorkingASDA()*xScale;
        endASDAxL = usableRunwayStartL - currentRunway.getLRunway().getWorkingASDA()*xScale;

        endLDAxR = landingRunwayStartR + currentRunway.getRRunway().getWorkingLDA()*xScale;
        endLDAxL = landingRunwayStartL - currentRunway.getLRunway().getWorkingLDA()*xScale;

        endTODAxR = usableRunwayStartR + currentRunway.getRRunway().getWorkingTODA()*xScale;
        endTODAxL = usableRunwayStartL - currentRunway.getLRunway().getWorkingTODA()*xScale;

        endTORAxR = usableRunwayStartR + currentRunway.getRRunway().getWorkingTORA()*xScale;
        endTORAxL = usableRunwayStartL - currentRunway.getLRunway().getWorkingTORA()*xScale;
    }

    private void addChildren(){
        sideView.getChildren().clear();
        graphics.getChildren().clear();
        text.getChildren().clear();

        graphics.getChildren().add(runway);

        graphics.getChildren().add(usableStartR);
        graphics.getChildren().add(landingStartR);
        graphics.getChildren().add(arrowASDAR);
        graphics.getChildren().add(arrowLDAR);
        graphics.getChildren().add(arrowTODAR);
        graphics.getChildren().add(arrowTORAR);
        graphics.getChildren().add(endASDALineR);
        graphics.getChildren().add(endLDALineR);
        graphics.getChildren().add(endTODALineR);
        graphics.getChildren().add(endTORALineR);
        graphics.getChildren().add(labelASDAR);
        graphics.getChildren().add(labelLDAR);
        graphics.getChildren().add(labelTODAR);
        graphics.getChildren().add(labelTORAR);



        graphics.getChildren().add(usableStartL);
        graphics.getChildren().add(landingStartL);
        graphics.getChildren().add(arrowASDAL);
        graphics.getChildren().add(arrowLDAL);
        graphics.getChildren().add(arrowTODAL);
        graphics.getChildren().add(arrowTORAL);
        graphics.getChildren().add(endASDALineL);
        graphics.getChildren().add(endLDALineL);
        graphics.getChildren().add(endTODALineL);
        graphics.getChildren().add(endTORALineL);
        graphics.getChildren().add(labelASDAL);
        graphics.getChildren().add(labelLDAL);
        graphics.getChildren().add(labelTODAL);
        graphics.getChildren().add(labelTORAL);

        sideView.getChildren().add(graphics);
    }
}