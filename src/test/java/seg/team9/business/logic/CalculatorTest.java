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

    @BeforeClass
    public static void beforeClass(){
        logger.debug("Testing calculator class..");
        //init all objects needed to test
    }

    @Test
    public void testResults() {
        logger.debug("Testing calculation method...");
        Calculator c = new Calculator();
        Obstacle o = new Obstacle(12d, 100d, 0d, 3646d, -50d);
        DirectedRunway lr = new DirectedRunway("09L", 3902d, 3902d, 3902d, 3595d, 306d, 360d, 360d);
        DirectedRunway rr = new DirectedRunway("27R", 3884d, 3962d, 3884d, 3884d, 0d, 360d, 360d);
        Runway r = new Runway(rr, lr);
        c.redesignate(r,o);

        Assert.assertEquals("Testing right TORA",2986d, rr.getWorkingTORA(), 0.0f);
        Assert.assertEquals("Testing right TODA",2986d, rr.getWorkingTODA(), 0.0f);
        Assert.assertEquals("Testing right ASDA",2986d, rr.getWorkingASDA(), 0.0f);
        Assert.assertEquals("Testing right LDA",3346d, rr.getWorkingLDA(), 0.0f);

        Assert.assertEquals("Testing left TORA",3346d, lr.getWorkingTORA(), 0.0f);
        Assert.assertEquals("Testing left TODA", 3346d, lr.getWorkingTODA(), 0.0f);
        Assert.assertEquals("Testing left ASDA",3346d, lr.getWorkingASDA(), 0.0f);
        Assert.assertEquals("Testing left LDA",2985d, lr.getWorkingLDA(), 0.0f);
    }
}