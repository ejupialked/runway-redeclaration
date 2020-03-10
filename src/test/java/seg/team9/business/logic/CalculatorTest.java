package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.*;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;


public class CalculatorTest {
    private static final Logger logger = LogManager.getLogger("CalculatorTest");
    private static Calculator c;
    private static  DirectedRunway lr;
    private  static  DirectedRunway rr;
    private static  DirectedRunway lr2;
    private  static  DirectedRunway rr2;

    @BeforeClass
    //This is the instantiation of all needed objects for the tests to be run.
    //The values for the runways mirror those used in the example calculations from Heathrow airport provided, as do the values used in the tests.
    public static void beforeClass(){
        logger.debug("Testing calculator class...");
        c = new Calculator();

        Obstacle o = new Obstacle("Boeing737", 12d, 100d, 0d, 3646d, -50d);
        lr = new DirectedRunway("09L", 3902d, 3902d, 3902d, 3595d, 306d, 0d, 0d);
        rr = new DirectedRunway("27R", 3884d, 3962d, 3884d, 3884d, 0d, 0d, 0d);
        Runway r = new Runway(rr, lr);

        //Var used to view the calculation breakdown output.
        String[][] test = c.redesignate(r,o);

        Obstacle o2 = new Obstacle("Boeing737", 25d, 100d, 20d, 2853d, 500d);
        rr2 = new DirectedRunway("09R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d);
        lr2 = new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway r2 = new Runway(rr2, lr2);

        //Var used to view the calculation breakdown output.
        String[][] test2 = c.redesignate(r2,o2);
    }

    @Test
    //Testing the redesignated TORA value of the first right runway (27R).
    public void testTORARight() {
        logger.debug("Testing right TORA...");
        logger.debug("Expected: 2986.0");
        logger.debug("Actual: "+rr.getWorkingTORA());
        Assert.assertEquals(2986d, rr.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the first right runway (27R).
    public void testTODARight() {
        logger.debug("Testing right TODA...");
        logger.debug("Expected: 2986.0");
        logger.debug("Actual: "+rr.getWorkingTODA());
        Assert.assertEquals(2986d, rr.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the first right runway (27R).
    public void testASDARight() {
        logger.debug("Testing right ASDA...");
        logger.debug("Expected: 2986.0");
        logger.debug("Actual: "+rr.getWorkingASDA());
        Assert.assertEquals(2986d, rr.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the first right runway (27R).
    public void testLDARight() {
        logger.debug("Testing right LDA...");
        logger.debug("Expected: 3346.0");
        logger.debug("Actual: "+rr.getWorkingLDA());
        Assert.assertEquals(3346d, rr.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the first left runway (09L).
    public void testTORALeft() {
        logger.debug("Testing left TORA...");
        logger.debug("Expected: 3346.0");
        logger.debug("Actual: "+lr.getWorkingTORA());
        Assert.assertEquals(3346d, lr.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the first left runway (09L).
    public void testTODALeft() {
        logger.debug("Testing left TODA...");
        logger.debug("Expected: 3346.0");
        logger.debug("Actual: "+lr.getWorkingTODA());
        Assert.assertEquals(3346d, lr.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the first left runway (09L).
    public void testASDALeft() {
        logger.debug("Testing right ASDA...");
        logger.debug("Expected: 3346.0");
        logger.debug("Actual: "+lr.getWorkingASDA());
        Assert.assertEquals(3346d, lr.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the first left runway (09L).
    public void testLDALeft() {
        logger.debug("Testing left LDA...");
        logger.debug("Expected: 2985.0");
        logger.debug("Actual: "+lr.getWorkingLDA());
        Assert.assertEquals(2985d, lr.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the second right runway (09R).
    public void testTORARight2() {
        logger.debug("Testing right TORA 2...");
        logger.debug("Expected: 1850.0");
        logger.debug("Actual: "+rr2.getWorkingTORA());
        Assert.assertEquals(1850d, rr2.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the second right runway (09R).
    public void testTODARight2() {
        logger.debug("Testing right TODA 2...");
        logger.debug("Expected: 1850.0");
        logger.debug("Actual: "+rr2.getWorkingTODA());
        Assert.assertEquals(1850d, rr2.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the second right runway (09R).
    public void testASDARight2() {
        logger.debug("Testing right ASDA 2...");
        logger.debug("Expected: 1850.0");
        logger.debug("Actual: "+rr2.getWorkingASDA());
        Assert.assertEquals(1850d, rr2.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the second right runway (09R).
    public void testLDARight2() {
        logger.debug("Testing right LDA 2...");
        logger.debug("Expected: 2553.0");
        logger.debug("Actual: "+rr2.getWorkingLDA());
        Assert.assertEquals(2553d, rr2.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the second left runway (27L).
    public void testTORALeft2() {
        logger.debug("Testing left TORA 2...");
        logger.debug("Expected: 2860.0");
        logger.debug("Actual: "+lr2.getWorkingTORA());
        Assert.assertEquals(2860d, lr2.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the second left runway (27L).
    public void testTODALeft2() {
        logger.debug("Testing left TODA 2...");
        logger.debug("Expected: 2860.0");
        logger.debug("Actual: "+lr2.getWorkingTODA());
        Assert.assertEquals(2860d, lr2.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the second left runway (27L).
    public void testASDALeft2() {
        logger.debug("Testing right ASDA 2...");
        logger.debug("Expected: 2860.0");
        logger.debug("Actual: "+lr2.getWorkingASDA());
        Assert.assertEquals(2860d, lr2.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the second left runway (27L).
    public void testLDALeft2() {
        logger.debug("Testing left LDA 2...");
        logger.debug("Expected: 1850.0");
        logger.debug("Actual: "+lr2.getWorkingLDA());
        Assert.assertEquals(1850d, lr2.getWorkingLDA(), 0.0f);
    }
}