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

    @BeforeClass
    public static void beforeClass(){
        logger.debug("Testing calculator class...");
        c = new Calculator();

        Obstacle o = new Obstacle("Boeing737", 12d, 100d, 0d, 3646d, -50d);
        lr = new DirectedRunway("09L", 3902d, 3902d, 3902d, 3595d, 306d, 360d, 360d);
        rr = new DirectedRunway("27R", 3884d, 3962d, 3884d, 3884d, 0d, 360d, 360d);
        Runway r = new Runway(rr, lr);

        c.redesignate(r,o);
    }

    @Test
    public void testTORARight() {
        logger.debug("Testing right TORA...");
        Assert.assertEquals(2986d, rr.getWorkingTORA(), 0.0f);
    }

    @Test
    public void testTODARight() {
        logger.debug("Testing right TODA...");
        Assert.assertEquals(2986d, rr.getWorkingTODA(), 0.0f);
    }

    @Test
    public void testASDARight() {
        logger.debug("Testing right ASDA...");
        Assert.assertEquals(2986d, rr.getWorkingASDA(), 0.0f);
    }

    @Test
    public void testLDARight() {
        logger.debug("Testing right LDA...");
        Assert.assertEquals(3346d, rr.getWorkingLDA(), 0.0f);
    }

    @Test
    public void testTORALeft() {
        logger.debug("Testing left TORA...");
        Assert.assertEquals(3346d, lr.getWorkingTORA(), 0.0f);
    }

    @Test
    public void testTODALeft() {
        logger.debug("Testing left TODA...");
        Assert.assertEquals(3346d, lr.getWorkingTODA(), 0.0f);
    }

    @Test
    public void testASDALeft() {
        logger.debug("Testing right ASDA...");
        Assert.assertEquals(3346d, lr.getWorkingASDA(), 0.0f);
    }

    @Test
    public void testLDALeft() {
        logger.debug("Testing left LDA...");
        Assert.assertEquals(2985d, lr.getWorkingLDA(), 0.0f);
    }

}