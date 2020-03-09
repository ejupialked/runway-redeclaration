package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.*;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import java.util.HashMap;

public class CalculatorTest {
    private static final Logger logger = LogManager.getLogger("CalculatorTest");
    private static Calculator c;
    private static  DirectedRunway lr;
    private static  DirectedRunway rr;
    private static  DirectedRunway lr2;
    private static  DirectedRunway rr2;
    private static HashMap<String, String> bd;
    private static HashMap<String, String> bd2;

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
        c.redesignate(r,o);
        bd = (HashMap) c.getCalculationsBreakdown().clone();

        Obstacle o2 = new Obstacle("Boeing737", 25d, 100d, 20d, 2853d, 500d);
        rr2 = new DirectedRunway("09R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d);
        lr2 = new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway r2 = new Runway(rr2, lr2);
        c.redesignate(r2,o2);
        bd2 = (HashMap) c.getCalculationsBreakdown().clone();
    }

    @Test
    //Testing the redesignated TORA value of the first right runway (27R).
    public void testTORARight() {
        logger.debug("Testing right TORA...");
        Assert.assertEquals(2986d, rr.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the first right runway (27R).
    public void testTODARight() {
        logger.debug("Testing right TODA...");
        Assert.assertEquals(2986d, rr.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the first right runway (27R).
    public void testASDARight() {
        logger.debug("Testing right ASDA...");
        Assert.assertEquals(2986d, rr.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the first right runway (27R).
    public void testLDARight() {
        logger.debug("Testing right LDA...");
        Assert.assertEquals(3346d, rr.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the first left runway (09L).
    public void testTORALeft() {
        logger.debug("Testing left TORA...");
        Assert.assertEquals(3346d, lr.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the first left runway (09L).
    public void testTODALeft() {
        logger.debug("Testing left TODA...");
        Assert.assertEquals(3346d, lr.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the first left runway (09L).
    public void testASDALeft() {
        logger.debug("Testing right ASDA...");
        Assert.assertEquals(3346d, lr.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the first left runway (09L).
    public void testLDALeft() {
        logger.debug("Testing left LDA...");
        Assert.assertEquals(2985d, lr.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the second right runway (09R).
    public void testTORARight2() {
        logger.debug("Testing right TORA 2...");
        Assert.assertEquals(1850d, rr2.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the second right runway (09R).
    public void testTODARight2() {
        logger.debug("Testing right TODA 2...");
        Assert.assertEquals(1850d, rr2.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the second right runway (09R).
    public void testASDARight2() {
        logger.debug("Testing right ASDA 2...");
        Assert.assertEquals(1850d, rr2.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the second right runway (09R).
    public void testLDARight2() {
        logger.debug("Testing right LDA 2...");
        Assert.assertEquals(2553d, rr2.getWorkingLDA(), 0.0f);
    }

    @Test
    //Testing the redesignated TORA value of the second left runway (27L).
    public void testTORALeft2() {
        logger.debug("Testing left TORA 2...");
        Assert.assertEquals(2860d, lr2.getWorkingTORA(), 0.0f);
    }

    @Test
    //Testing the redesignated TODA value of the second left runway (27L).
    public void testTODALeft2() {
        logger.debug("Testing left TODA 2...");
        Assert.assertEquals(2860d, lr2.getWorkingTODA(), 0.0f);
    }

    @Test
    //Testing the redesignated ASDA value of the second left runway (27L).
    public void testASDALeft2() {
        logger.debug("Testing right ASDA 2...");
        Assert.assertEquals(2860d, lr2.getWorkingASDA(), 0.0f);
    }

    @Test
    //Testing the redesignated LDA value of the second left runway (27L).
    public void testLDALeft2() {
        logger.debug("Testing left LDA 2...");
        Assert.assertEquals(1850d, lr2.getWorkingLDA(), 0.0f);
    }

    @Test
    public void testBreakdownTOTLTHeader(){
        logger.debug("Testing Taking off towards, landing towards header...");
        String expected = "Taking off towards, landing towards: 27R";
        Assert.assertEquals(expected, bd.get("TOTLT"));
    }

    @Test
    public void testBreakdownTOALOHeader(){
        logger.debug("Testing Taking off away, landing over header...");
        String expected = "Taking off away, landing over: 09L";
        Assert.assertEquals(expected, bd.get("TOALO"));
    }

    @Test
    public void testBreakdownTOTLTTORA(){
        logger.debug("Testing TOTLT TORA calculation and values...");
        String expecteddesc = "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End";
        Assert.assertEquals(expecteddesc, bd.get("TOTLTTORAdesc"));
        String expectedval = "TORA: 3646.0 + 0.0 - (12.0 * 50.0) - 60.0 = 2986.0";
        Assert.assertEquals(expectedval, bd.get("TOTLTTORAval"));
    }

    @Test
    public void testBreakdownTOTLTTODA(){
        logger.debug("Testing TOTLT TODA calculation and values...");
        String expecteddesc = "TODA: Working TORA";
        Assert.assertEquals(expecteddesc, bd.get("TOTLTTODAdesc"));
        String expectedval = "TODA: 2986.0 = 2986.0";
        Assert.assertEquals(expectedval, bd.get("TOTLTTODAval"));
    }

    @Test
    public void testBreakdownTOTLTASDA(){
        logger.debug("Testing TOTLT ASDA calculation and values...");
        String expecteddesc = "ASDA: Working TORA";
        Assert.assertEquals(expecteddesc, bd.get("TOTLTASDAdesc"));
        String expectedval = "ASDA: 2986.0 = 2986.0";
        Assert.assertEquals(expectedval, bd.get("TOTLTASDAval"));
    }

    @Test
    public void testBreakdownTOTLTLDA(){
        logger.debug("Testing TOTLT LDA calculation and values...");
        String expecteddesc = "LDA: Distance from threshold - RESA - Strip End";
        Assert.assertEquals(expecteddesc, bd.get("TOTLTLDAdesc"));
        String expectedval = "LDA: 3646.0 - 240.0 - 60.0 = 3346.0";
        Assert.assertEquals(expectedval, bd.get("TOTLTLDAval"));
    }

    @Test
    public void testBreakdownTOALOTORA(){
        logger.debug("Testing TOALO TORA calculation and values...");
        String expecteddesc = "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold";
        Assert.assertEquals(expecteddesc, bd.get("TOALOTORAdesc"));
        String expectedval = "TORA: 3902.0 - 300.0 - -50.0 - 306.0 = 3346.0";
        Assert.assertEquals(expectedval, bd.get("TOALOTORAval"));
    }

    @Test
    public void testBreakdownTOALOTODA(){
        logger.debug("Testing TOALO TODA calculation and values...");
        String expecteddesc = "TODA: Working TORA + Clearway";
        Assert.assertEquals(expecteddesc, bd.get("TOALOTODAdesc"));
        String expectedval = "TODA: 3346.0 + 0.0 = 3346.0";
        Assert.assertEquals(expectedval, bd.get("TOALOTODAval"));
    }

    @Test
    public void testBreakdownTOALOASDA(){
        logger.debug("Testing TOALO ASDA calculation and values...");
        String expecteddesc = "ASDA: Working TORA + Stopway";
        Assert.assertEquals(expecteddesc, bd.get("TOALOASDAdesc"));
        String expectedval = "ASDA: 3346.0 + 0.0 = 3346.0";
        Assert.assertEquals(expectedval, bd.get("TOALOASDAval"));
    }

    @Test
    public void testBreakdownTOALOLDA(){
        logger.debug("Testing TOALO LDA calculation and values...");
        String expecteddesc = "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End";
        Assert.assertEquals(expecteddesc, bd.get("TOALOLDAdesc"));
        String expectedval = "LDA: 3595.0 - -50.0 - (12.0 * 50.0) - 60.0 = 2985.0";
        Assert.assertEquals(expectedval, bd.get("TOALOLDAval"));
    }

    @Test
    public void testBreakdownTOTLTHeader2(){
        logger.debug("Testing Taking off towards, landing towards header 2...");
        String expected = "Taking off towards, landing towards: 09R";
        Assert.assertEquals(expected, bd2.get("TOTLT"));
    }

    @Test
    public void testBreakdownTOALOHeader2(){
        logger.debug("Testing Taking off away, landing over header 2...");
        String expected = "Taking off away, landing over: 27L";
        Assert.assertEquals(expected, bd2.get("TOALO"));
    }

    @Test
    public void testBreakdownTOTLTTORA2(){
        logger.debug("Testing TOTLT TORA calculation and values 2...");
        String expecteddesc = "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End";
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTTORAdesc"));
        String expectedval = "TORA: 2853.0 + 307.0 - (25.0 * 50.0) - 60.0 = 1850.0";
        Assert.assertEquals(expectedval, bd2.get("TOTLTTORAval"));
    }

    @Test
    public void testBreakdownTOTLTTODA2(){
        logger.debug("Testing TOTLT TODA calculation and values 2...");
        String expecteddesc = "TODA: Working TORA";
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTTODAdesc"));
        String expectedval = "TODA: 1850.0 = 1850.0";
        Assert.assertEquals(expectedval, bd2.get("TOTLTTODAval"));
    }

    @Test
    public void testBreakdownTOTLTASDA2(){
        logger.debug("Testing TOTLT ASDA calculation and values 2...");
        String expecteddesc = "ASDA: Working TORA";
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTASDAdesc"));
        String expectedval = "ASDA: 1850.0 = 1850.0";
        Assert.assertEquals(expectedval, bd2.get("TOTLTASDAval"));
    }

    @Test
    public void testBreakdownTOTLTLDA2(){
        logger.debug("Testing TOTLT LDA calculation and values 2...");
        String expecteddesc = "LDA: Distance from threshold - RESA - Strip End";
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTLDAdesc"));
        String expectedval = "LDA: 2853.0 - 240.0 - 60.0 = 2553.0";
        Assert.assertEquals(expectedval, bd2.get("TOTLTLDAval"));
    }

    @Test
    public void testBreakdownTOALOTORA2(){
        logger.debug("Testing TOALO TORA calculation and values 2...");
        String expecteddesc = "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold";
        Assert.assertEquals(expecteddesc, bd2.get("TOALOTORAdesc"));
        String expectedval = "TORA: 3660.0 - 300.0 - 500.0 - 0.0 = 2860.0";
        Assert.assertEquals(expectedval, bd2.get("TOALOTORAval"));
    }

    @Test
    public void testBreakdownTOALOTODA2(){
        logger.debug("Testing TOALO TODA calculation and values 2...");
        String expecteddesc = "TODA: Working TORA + Clearway";
        Assert.assertEquals(expecteddesc, bd2.get("TOALOTODAdesc"));
        String expectedval = "TODA: 2860.0 + 0.0 = 2860.0";
        Assert.assertEquals(expectedval, bd2.get("TOALOTODAval"));
    }

    @Test
    public void testBreakdownTOALOASDA2(){
        logger.debug("Testing TOALO ASDA calculation and values 2...");
        String expecteddesc = "ASDA: Working TORA + Stopway";
        Assert.assertEquals(expecteddesc, bd2.get("TOALOASDAdesc"));
        String expectedval = "ASDA: 2860.0 + 0.0 = 2860.0";
        Assert.assertEquals(expectedval, bd2.get("TOALOASDAval"));
    }

    @Test
    public void testBreakdownTOALOLDA2(){
        logger.debug("Testing TOALO LDA calculation and values 2...");
        String expecteddesc = "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End";
        Assert.assertEquals(expecteddesc, bd2.get("TOALOLDAdesc"));
        String expectedval = "LDA: 3660.0 - 500.0 - (25.0 * 50.0) - 60.0 = 1850.0";
        Assert.assertEquals(expectedval, bd2.get("TOALOLDAval"));
    }
}