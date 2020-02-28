package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;



public class CalculatorTest {
    private static final Logger logger = LogManager.getLogger("CalculatorTest");

    @BeforeClass
    public static void beforeClass(){
        logger.debug("Testing calculator class..");
        //init all objects needed to test
    }


    @Test
    public void calculateTODA() {
        logger.debug("Testing TODA method...");

    }

    @Test
    public void calculateTORA() {
        logger.debug("Testing TORA method...");
    }

    @Test
    public void calculateASDA() {
        logger.debug("Testing ASDA method...");
    }

    @Test
    public void calculateLDA() {
        logger.debug("Testing LDA method...");
    }

    @Test
    public void calculateRESA() {
        logger.debug("Testing RESA method...");
    }

    @Test
    public void calculateSlope() {
        logger.debug("Testing Slope method...");
    }

    @Test
    public void calculateALS() {
        logger.debug("Testing ALS method...");
    }

    @Test
    public void calculateTOCS() {
        logger.debug("Testing TOCS method...");
    }
}