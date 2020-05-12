package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.obstacle.ObstacleAddFormController;
import seg.team9.exceptions.ObstacleOutsideOfRunwayException;
import seg.team9.exceptions.TextFieldEmptyException;
import seg.team9.utils.MockData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObstacleViewTest {
    private static final Logger logger = LogManager.getLogger("ObstacleViewTest");

    static List<Obstacle> obstacleListTest;
    static Airport airport;
    static Runway runway;

    @BeforeClass
    public static void beforeClass(){
        logger.debug("Testing obstacle view...");
        obstacleListTest = MockData.obstacles;
        airport = MockData.aiports.get(0);
        logger.debug("Airport: " + airport.getName());
        logger.debug("Runway: " + airport.getRunwayList().get(0).getRRunway().getDesignator() + "/" +
                airport.getRunwayList().get(0).getLRunway().getDesignator());

        runway = airport.getRunwayList().get(0);
        logger.debug("Predefined Obstacles: " + obstacleListTest);
    }

    @Test
    public void testingEmptyString() {

        System.out.println();

        String name = "Air777";
        String heightD = "32"; // <-- field that causes the exception
        String widthD =  "34";
        String centerD = "40";
        String rightD =  "45";
        String leftD = "300";


        String wrongWidth = "   ";
        logger.debug("Testing empty string...");
        logger.debug("User's input: " + "'" + wrongWidth + "'" + "[Width textfield]");

        Exception exception = assertThrows(TextFieldEmptyException.class, () -> {
            ObstacleAddFormController.validateUserInput(
                    true, runway, name, wrongWidth, heightD, centerD, rightD, leftD
            );
        });

        String expectedMessage = "Please complete the Width text field.";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

        logger.debug("Expected message: " + expectedMessage);
        logger.debug("Actual: " + actualMessage);

        logger.debug("");

        String leftDBugged = "    ";
        logger.debug("User's input: " + "'" + leftDBugged + "'" + "[Right threshold distance textfield]");

        Exception exception2 = assertThrows(TextFieldEmptyException.class, () -> {
            ObstacleAddFormController.validateUserInput(
                    true, runway, name, widthD, heightD, centerD, rightD, leftDBugged
            );
        });

        String expectedMessage2 = "Please complete the Distance threshold (right) text field.";
        String actualMessage2 = exception2.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

        logger.debug("Expected message: " + expectedMessage2);
        logger.debug("Actual: " + actualMessage2);

    }

    @Test
    public void testingObstacleOutsideRunway() {


        System.out.println();

        String name = "Air777";
        String heightD = "32"; // <-- field that causes the exception
        String widthD =  "34";
        String centerD = "40";
        String rightD =  "45";
        String leftD = "300";


        String wrongLeft = "9000";
        logger.debug("Testing values that causes the obstacle to be outside the runway...");
        logger.debug("User's input: " + "'" + wrongLeft + "'" + " [Left edge of runway textField]");

        Exception exception = assertThrows(ObstacleOutsideOfRunwayException.class, () -> {
            ObstacleAddFormController.validateUserInput(
                    true, runway, name, widthD, heightD, centerD, rightD, wrongLeft
            );
        });

        String expectedMessage = "Obstacle outside of valid bounds.";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

        logger.debug("Expected message: " + expectedMessage);
        logger.debug("Actual: " + actualMessage);

        logger.debug("");

        String wrongRight = "100000000";
        logger.debug("User's input: " + "'" + wrongRight + "'" + " [Right edge of runway textField]");

        Exception exception2 = assertThrows(ObstacleOutsideOfRunwayException.class, () -> {
            ObstacleAddFormController.validateUserInput(
                    true, runway, name, widthD, heightD, centerD, wrongRight, leftD
            );
        });

        String expectedMessage2 = "Obstacle outside valid bounds.";
        String actualMessage2 = exception2.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

        logger.debug("Expected message: " + expectedMessage2);
        logger.debug("Actual: " + actualMessage2);

    }


    @Test
    public void testingObstacleWithSameName() {

    }

    @Test
    public void testingNonNumericValues() {

    }

    @Test
    public void testingNegativeAndZeroValues() {

    }
}
