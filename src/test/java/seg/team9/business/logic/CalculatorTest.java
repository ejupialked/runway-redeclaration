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
        Runway r = new Runway(rr, lr, 4000d);
        c.redesignate(r,o);
        bd = (HashMap) c.getCalculationsBreakdown().clone();

        Obstacle o2 = new Obstacle("Boeing737", 25d, 100d, 20d, 2853d, 500d);
        rr2 = new DirectedRunway("09R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d);
        lr2 = new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway r2 = new Runway(rr2, lr2, 4000d);
        c.redesignate(r2,o2);
        bd2 = (HashMap) c.getCalculationsBreakdown().clone();
    }

    //Tests for calculated values.

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

    //Tests for calculations breakdown

    @Test
    //Testing the first right runway's values will be calculated in the correct way (27R).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOTLTHeader(){
        logger.debug("Testing Taking off towards, landing towards header...");
        String expected = "Taking off towards, landing towards: 27R";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd.get("TOTLT"));
        Assert.assertEquals(expected, bd.get("TOTLT"));
    }

    @Test
    //Testing the first left runway's values are will be calculated in the correct way (09L).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOALOHeader(){
        logger.debug("Testing Taking off away, landing over header...");
        String expected = "Taking off away, landing over: 09L";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd.get("TOALO"));
        Assert.assertEquals(expected, bd.get("TOALO"));
    }

    @Test
    //Testing the first right runway's TORA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTORA(){
        logger.debug("Testing TOTLT TORA calculation and values...");
        String expecteddesc = "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOTLTTORAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOTLTTORAdesc"));
        String expectedval = "TORA: 3646.0 + 0.0 - (12.0 * 50.0) - 60.0 = 2986.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOTLTTORAval"));
        Assert.assertEquals(expectedval, bd.get("TOTLTTORAval"));
    }

    @Test
    //Testing the first right runway's TODA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTODA(){
        logger.debug("Testing TOTLT TODA calculation and values...");
        String expecteddesc = "TODA: Working TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOTLTTODAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOTLTTODAdesc"));
        String expectedval = "TODA: 2986.0 = 2986.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOTLTTODAval"));
        Assert.assertEquals(expectedval, bd.get("TOTLTTODAval"));
    }

    @Test
    //Testing the first right runway's ASDA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTASDA(){
        logger.debug("Testing TOTLT ASDA calculation and values...");
        String expecteddesc = "ASDA: Working TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOTLTASDAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOTLTASDAdesc"));
        String expectedval = "ASDA: 2986.0 = 2986.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOTLTASDAval"));
        Assert.assertEquals(expectedval, bd.get("TOTLTASDAval"));
    }

    @Test
    //Testing the first right runway's LDA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTLDA(){
        logger.debug("Testing TOTLT LDA calculation and values...");
        String expecteddesc = "LDA: Distance from threshold - RESA - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOTLTLDAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOTLTLDAdesc"));
        String expectedval = "LDA: 3646.0 - 240.0 - 60.0 = 3346.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOTLTLDAval"));
        Assert.assertEquals(expectedval, bd.get("TOTLTLDAval"));
    }

    @Test
    //Testing the first left runway's TORA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTORA(){
        logger.debug("Testing TOALO TORA calculation and values...");
        String expecteddesc = "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOALOTORAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOALOTORAdesc"));
        String expectedval = "TORA: 3902.0 - 300.0 - -50.0 - 306.0 = 3346.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOALOTORAval"));
        Assert.assertEquals(expectedval, bd.get("TOALOTORAval"));
    }

    @Test
    //Testing the first left runway's TODA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTODA(){
        logger.debug("Testing TOALO TODA calculation and values...");
        String expecteddesc = "TODA: Working TORA + Clearway";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOALOTODAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOALOTODAdesc"));
        String expectedval = "TODA: 3346.0 + 0.0 = 3346.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOALOTODAval"));
        Assert.assertEquals(expectedval, bd.get("TOALOTODAval"));
    }

    @Test
    //Testing the first left runway's ASDA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOASDA(){
        logger.debug("Testing TOALO ASDA calculation and values...");
        String expecteddesc = "ASDA: Working TORA + Stopway";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOALOASDAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOALOASDAdesc"));
        String expectedval = "ASDA: 3346.0 + 0.0 = 3346.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOALOASDAval"));
        Assert.assertEquals(expectedval, bd.get("TOALOASDAval"));
    }

    @Test
    //Testing the first left runway's LDA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOLDA(){
        logger.debug("Testing TOALO LDA calculation and values...");
        String expecteddesc = "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("TOALOLDAdesc"));
        Assert.assertEquals(expecteddesc, bd.get("TOALOLDAdesc"));
        String expectedval = "LDA: 3595.0 - -50.0 - (12.0 * 50.0) - 60.0 = 2985.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("TOALOLDAval"));
        Assert.assertEquals(expectedval, bd.get("TOALOLDAval"));
    }

    @Test
    //Testing the second right runway's values will be calculated in the correct way (09R).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOTLTHeader2(){
        logger.debug("Testing Taking off towards, landing towards header 2...");
        String expected = "Taking off towards, landing towards: 09R";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd2.get("TOTLT"));
        Assert.assertEquals(expected, bd2.get("TOTLT"));
    }

    @Test
    //Testing the second left runway's values will be calculated in the correct way (27L).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOALOHeader2(){
        logger.debug("Testing Taking off away, landing over header 2...");
        String expected = "Taking off away, landing over: 27L";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd2.get("TOALO"));
        Assert.assertEquals(expected, bd2.get("TOALO"));
    }

    @Test
    //Testing the second right runway's TORA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTORA2(){
        logger.debug("Testing TOTLT TORA calculation and values 2...");
        String expecteddesc = "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOTLTTORAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTTORAdesc"));
        String expectedval = "TORA: 2853.0 + 307.0 - (25.0 * 50.0) - 60.0 = 1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOTLTTORAval"));
        Assert.assertEquals(expectedval, bd2.get("TOTLTTORAval"));
    }

    @Test
    //Testing the second right runway's TODA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTODA2(){
        logger.debug("Testing TOTLT TODA calculation and values 2...");
        String expecteddesc = "TODA: Working TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOTLTTODAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTTODAdesc"));
        String expectedval = "TODA: 1850.0 = 1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOTLTTODAval"));
        Assert.assertEquals(expectedval, bd2.get("TOTLTTODAval"));
    }

    @Test
    //Testing the second right runway's ASDA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTASDA2(){
        logger.debug("Testing TOTLT ASDA calculation and values 2...");
        String expecteddesc = "ASDA: Working TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOTLTASDAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTASDAdesc"));
        String expectedval = "ASDA: 1850.0 = 1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOTLTASDAval"));
        Assert.assertEquals(expectedval, bd2.get("TOTLTASDAval"));
    }

    @Test
    //Testing the second right runway's LDA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTLDA2(){
        logger.debug("Testing TOTLT LDA calculation and values 2...");
        String expecteddesc = "LDA: Distance from threshold - RESA - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOTLTLDAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOTLTLDAdesc"));
        String expectedval = "LDA: 2853.0 - 240.0 - 60.0 = 2553.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOTLTLDAval"));
        Assert.assertEquals(expectedval, bd2.get("TOTLTLDAval"));
    }

    @Test
    //Testing the second left runway's TORA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTORA2(){
        logger.debug("Testing TOALO TORA calculation and values 2...");
        String expecteddesc = "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOALOTORAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOALOTORAdesc"));
        String expectedval = "TORA: 3660.0 - 300.0 - 500.0 - 0.0 = 2860.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOALOTORAval"));
        Assert.assertEquals(expectedval, bd2.get("TOALOTORAval"));
    }

    @Test
    //Testing the second left runway's TODA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTODA2(){
        logger.debug("Testing TOALO TODA calculation and values 2...");
        String expecteddesc = "TODA: Working TORA + Clearway";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOALOTODAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOALOTODAdesc"));
        String expectedval = "TODA: 2860.0 + 0.0 = 2860.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOALOTODAval"));
        Assert.assertEquals(expectedval, bd2.get("TOALOTODAval"));
    }

    @Test
    //Testing the second left runway's ASDA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOASDA2(){
        logger.debug("Testing TOALO ASDA calculation and values 2...");
        String expecteddesc = "ASDA: Working TORA + Stopway";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOALOASDAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOALOASDAdesc"));
        String expectedval = "ASDA: 2860.0 + 0.0 = 2860.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOALOASDAval"));
        Assert.assertEquals(expectedval, bd2.get("TOALOASDAval"));
    }

    @Test
    //Testing the second left runway's LDA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOLDA2(){
        logger.debug("Testing TOALO LDA calculation and values 2...");
        String expecteddesc = "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("TOALOLDAdesc"));
        Assert.assertEquals(expecteddesc, bd2.get("TOALOLDAdesc"));
        String expectedval = "LDA: 3660.0 - 500.0 - (25.0 * 50.0) - 60.0 = 1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("TOALOLDAval"));
        Assert.assertEquals(expectedval, bd2.get("TOALOLDAval"));
    }
}