package seg.team9.controllers.runways;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;/**
 * THIS CODE WAS NOT WRITTEN BY US, AUTHOR CREDITED BELOW
 * @author kn
 */
public class Arrow extends Path{
    private static final double defaultArrowHeadSize = 5.0;    private double startingX;
    private double startingY;
    private double endingX;
    private double endingY;
    private double arrowSize;


    public Arrow(double startX, double startY, double endX, double endY, double arrowHeadSize, Color color){
        super();
        startingX = startX;
        startingY = startY;
        endingX = endX;
        endingY = endY;

        arrowSize = arrowHeadSize;

        strokeProperty().bind(fillProperty());
        setFill(color);

        //Line
        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, endY));        //ArrowHead
        double angle = Math.atan2((endY - startY), (endX - startX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        //point1
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;
        //point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowHeadSize + endX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowHeadSize + endY;        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endX, endY));
    }

    public Arrow(double startX, double startY, double endX, double endY){
        this(startX, startY, endX, endY, defaultArrowHeadSize);
    }

    public Arrow(double startX, double startY, double endX, double endY, double arrowHeadSize){
        this(startX,startY,endX,endY,arrowHeadSize, Color.BLACK);
    }

    public void changeColour(Color color){
        getElements().clear();
        strokeProperty().bind(fillProperty());

        setFill(color);

        //Line
        getElements().add(new MoveTo(startingX, startingY));
        getElements().add(new LineTo(endingX, endingY));

        //ArrowHead
        double angle = Math.atan2((endingY - startingY), (endingX - startingX)) - Math.PI / 2.0;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        //point1
        double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowSize + endingX;
        double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowSize + endingY;
        //point2
        double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * arrowSize + endingX;
        double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * arrowSize + endingY;

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(endingX, endingY));
    }

}