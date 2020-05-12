package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.*;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {
    private static final Logger logger = LogManager.getLogger("CalculatorTest");
    private static Calculator c;
    private static  DirectedRunway lr;
    private static  DirectedRunway rr;
    private static  DirectedRunway lr2;
    private static  DirectedRunway rr2;
    private static Map<String, Map<String, String>>  bd;
    private static Map<String, Map<String, String>>  bd2;

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
        c.testRedesignate(r,o);
        bd = c.getCalculationsBreakdown();
        Map<String, Map<String, String>> temp = new HashMap<String, Map<String, String>>();
        for(String key : bd.keySet()){
            Map<String, String> temp2 = new HashMap<>();
            for(String key2 : bd.get(key).keySet()){
                temp2.put(key2, bd.get(key).get(key2));
            }
            temp.put(key, temp2);
        }
        bd = temp;


        Obstacle o2 = new Obstacle("Boeing737", 25d, 100d, 20d, 2853d, 500d);
        rr2 = new DirectedRunway("09R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d);
        lr2 = new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway r2 = new Runway(rr2, lr2, 4000d);
        c.testRedesignate(r2,o2);
        bd2 = c.getCalculationsBreakdown();
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
        String expected = "27R (Taking Off Towards, Landing Towards)";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd.get("towards").get("title"));
        Assert.assertEquals(expected, bd.get("towards").get("title"));
    }

    @Test
    //Testing the first left runway's values are will be calculated in the correct way (09L).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOALOHeader(){
        logger.debug("Testing Taking off away, landing over header...");
        String expected = "09L (Taking Off Away, Landing Over)";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd.get("over").get("title"));
        Assert.assertEquals(expected, bd.get("over").get("title"));
    }

    @Test
    //Testing the first right runway's TORA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTORA(){
        logger.debug("Testing TOTLT TORA calculation and values...");
        String expecteddesc = "Distance From Threshold + Runway Threshold - (Slope Calculation) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("towards").get("TORA"));
        Assert.assertEquals(expecteddesc, bd.get("towards").get("TORA"));
        String expectedval = "3646.0 + 0.0 - (12.0 * 50.0) - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("towards").get("TORA1"));
        Assert.assertEquals(expectedval, bd.get("towards").get("TORA1"));
        logger.debug("Expected result: " + 2986.0);
        logger.debug("Actual result: " + bd.get("towards").get("TORA2"));
        Assert.assertEquals("2986.0", bd.get("towards").get("TORA2"));
    }

    @Test
    //Testing the first right runway's TODA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTODA(){
        logger.debug("Testing TOTLT TODA calculation and values...");
        String expecteddesc = "(R) TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("towards").get("TODA"));
        Assert.assertEquals(expecteddesc, bd.get("towards").get("TODA"));
        String expectedval = "2986.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("towards").get("TODA1"));
        Assert.assertEquals(expectedval, bd.get("towards").get("TODA1"));
    }

    @Test
    //Testing the first right runway's ASDA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTASDA(){
        logger.debug("Testing TOTLT ASDA calculation and values...");
        String expecteddesc = "(R) TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("towards").get("ASDA"));
        Assert.assertEquals(expecteddesc, bd.get("towards").get("ASDA"));
        String expectedval = "2986.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("towards").get("ASDA1"));
        Assert.assertEquals(expectedval, bd.get("towards").get("ASDA1"));
    }

    @Test
    //Testing the first right runway's LDA is being calculated in the correct way (27R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTLDA(){
        logger.debug("Testing TOTLT LDA calculation and values...");
        String expecteddesc = "Distance from Threshold - RESA - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("towards").get("LDA"));
        Assert.assertEquals(expecteddesc, bd.get("towards").get("LDA"));
        String expectedval = "3646.0 - 240.0 - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("towards").get("LDA1"));
        Assert.assertEquals(expectedval, bd.get("towards").get("LDA1"));
        logger.debug("Expected result: " + 3346.0);
        logger.debug("Actual result: " + bd.get("towards").get("LDA2"));
        Assert.assertEquals("3346.0", bd.get("towards").get("LDA2"));


    }

    @Test
    //Testing the first left runway's TORA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTORA(){
        logger.debug("Testing TOALO TORA calculation and values...");
        String expecteddesc = "Original TORA - Blast Protection - Distance From Threshold - Displaced Threshold";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("over").get("TORA"));
        Assert.assertEquals(expecteddesc, bd.get("over").get("TORA"));
        String expectedval = "3902.0 - 300.0 - -50.0 - 306.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("over").get("TORA1"));
        Assert.assertEquals(expectedval, bd.get("over").get("TORA1"));
        logger.debug("Expected result: " + 3346.0);
        logger.debug("Actual result: " + bd.get("over").get("TORA2"));
        Assert.assertEquals("3346.0", bd.get("over").get("TORA2"));
    }

    @Test
    //Testing the first left runway's TODA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTODA(){
        logger.debug("Testing TOALO TODA calculation and values...");
        String expecteddesc = "(R) TORA + CLEARWAY";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("over").get("TODA"));
        Assert.assertEquals(expecteddesc, bd.get("over").get("TODA"));
        String expectedval = "3346.0 + 0.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("over").get("TODA1"));
        Assert.assertEquals(expectedval, bd.get("over").get("TODA1"));
        logger.debug("Expected result: " + 3346.0);
        logger.debug("Actual result: " + bd.get("over").get("TODA2"));
        Assert.assertEquals("3346.0", bd.get("over").get("TODA2"));
    }

    @Test
    //Testing the first left runway's ASDA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOASDA(){
        logger.debug("Testing TOALO ASDA calculation and values...");
        String expecteddesc = "(R) TORA + STOPWAY";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("over").get("ASDA"));
        Assert.assertEquals(expecteddesc, bd.get("over").get("ASDA"));
        String expectedval = "3346.0 + 0.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("over").get("ASDA1"));
        Assert.assertEquals(expectedval, bd.get("over").get("ASDA1"));
        logger.debug("Expected result: " + 3346.0);
        logger.debug("Actual result: " + bd.get("over").get("ASDA2"));
        Assert.assertEquals("3346.0", bd.get("over").get("ASDA2"));
    }

    @Test
    //Testing the first left runway's LDA is being calculated in the correct way (09L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOLDA(){
        logger.debug("Testing TOALO LDA calculation and values...");
        String expecteddesc = "Original LDA - Distance From Threshold - Slope Calculation - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd.get("over").get("LDA"));
        Assert.assertEquals(expecteddesc, bd.get("over").get("LDA"));
        String expectedval = "3595.0 - -50.0 - (12.0 * 50.0) - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd.get("over").get("LDA1"));
        Assert.assertEquals(expectedval, bd.get("over").get("LDA1"));
        logger.debug("Expected result: " + 2985.0);
        logger.debug("Actual result: " + bd.get("over").get("LDA2"));
        Assert.assertEquals("2985.0", bd.get("over").get("LDA2"));
    }

    @Test
    //Testing the second right runway's values will be calculated in the correct way (09R).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOTLTHeader2(){
        logger.debug("Testing Taking off towards, landing towards header...");
        String expected = "09R (Taking Off Towards, Landing Towards)";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd2.get("towards").get("title"));
        Assert.assertEquals(expected, bd2.get("towards").get("title"));
    }

    @Test
    //Testing the second left runway's values will be calculated in the correct way (27L).
    //Also testing the correct header is applied for the output.
    public void testBreakdownTOALOHeader2(){
        logger.debug("Testing Taking off away, landing over header...");
        String expected = "27L (Taking Off Away, Landing Over)";
        logger.debug("Expected header: " + expected);
        logger.debug("Actual header: " + bd2.get("over").get("title"));
        Assert.assertEquals(expected, bd2.get("over").get("title"));
    }

    @Test
    //Testing the second right runway's TORA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTORA2(){
        logger.debug("Testing TOTLT TORA calculation and values...");
        String expecteddesc = "Distance From Threshold + Runway Threshold - (Slope Calculation) - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("towards").get("TORA"));
        Assert.assertEquals(expecteddesc, bd2.get("towards").get("TORA"));
        String expectedval = "2853.0 + 307.0 - (25.0 * 50.0) - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("towards").get("TORA1"));
        Assert.assertEquals(expectedval, bd2.get("towards").get("TORA1"));
        logger.debug("Expected result: " + 1850.0);
        logger.debug("Actual result: " + bd2.get("towards").get("TORA2"));
        Assert.assertEquals("1850.0", bd2.get("towards").get("TORA2"));
    }

    @Test
    //Testing the second right runway's TODA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTTODA2(){
        logger.debug("Testing TOTLT TODA calculation and values...");
        String expecteddesc = "(R) TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("towards").get("TODA"));
        Assert.assertEquals(expecteddesc, bd2.get("towards").get("TODA"));
        String expectedval = "1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("towards").get("TODA1"));
        Assert.assertEquals(expectedval, bd2.get("towards").get("TODA1"));
    }

    @Test
    //Testing the second right runway's ASDA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTASDA2(){
        logger.debug("Testing TOTLT ASDA calculation and values...");
        String expecteddesc = "(R) TORA";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("towards").get("ASDA"));
        Assert.assertEquals(expecteddesc, bd2.get("towards").get("ASDA"));
        String expectedval = "1850.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("towards").get("ASDA1"));
        Assert.assertEquals(expectedval, bd2.get("towards").get("ASDA1"));
    }

    @Test
    //Testing the second right runway's LDA is being calculated in the correct way (09R).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOTLTLDA2(){
        logger.debug("Testing TOTLT LDA calculation and values...");
        String expecteddesc = "Distance from Threshold - RESA - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("towards").get("LDA"));
        Assert.assertEquals(expecteddesc, bd2.get("towards").get("LDA"));
        String expectedval = "2853.0 - 240.0 - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("towards").get("LDA1"));
        Assert.assertEquals(expectedval, bd2.get("towards").get("LDA1"));
        logger.debug("Expected result: " + 2553.0);
        logger.debug("Actual result: " + bd2.get("towards").get("LDA2"));
        Assert.assertEquals("2553.0", bd2.get("towards").get("LDA2"));
    }

    @Test
    //Testing the second left runway's TORA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTORA2(){
        logger.debug("Testing TOALO TORA calculation and values...");
        String expecteddesc = "Original TORA - Blast Protection - Distance From Threshold - Displaced Threshold";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("over").get("TORA"));
        Assert.assertEquals(expecteddesc, bd2.get("over").get("TORA"));
        String expectedval = "3660.0 - 300.0 - 500.0 - 0.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("over").get("TORA1"));
        Assert.assertEquals(expectedval, bd2.get("over").get("TORA1"));
        logger.debug("Expected result: " + 2860.0);
        logger.debug("Actual result: " + bd2.get("over").get("TORA2"));
        Assert.assertEquals("2860.0", bd2.get("over").get("TORA2"));
    }

    @Test
    //Testing the second left runway's TODA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOTODA2(){
        logger.debug("Testing TOALO TODA calculation and values...");
        String expecteddesc = "(R) TORA + CLEARWAY";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("over").get("TODA"));
        Assert.assertEquals(expecteddesc, bd2.get("over").get("TODA"));
        String expectedval = "2860.0 + 0.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("over").get("TODA1"));
        Assert.assertEquals(expectedval, bd2.get("over").get("TODA1"));
        logger.debug("Expected result: " + 2860.0);
        logger.debug("Actual result: " + bd2.get("over").get("TODA2"));
        Assert.assertEquals("2860.0", bd2.get("over").get("TODA2"));
    }

    @Test
    //Testing the second left runway's ASDA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOASDA2(){
        logger.debug("Testing TOALO ASDA calculation and values...");
        String expecteddesc = "(R) TORA + STOPWAY";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("over").get("ASDA"));
        Assert.assertEquals(expecteddesc, bd2.get("over").get("ASDA"));
        String expectedval = "2860.0 + 0.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("over").get("ASDA1"));
        Assert.assertEquals(expectedval, bd2.get("over").get("ASDA1"));
        logger.debug("Expected result: " + 2860.0);
        logger.debug("Actual result: " + bd2.get("over").get("ASDA2"));
        Assert.assertEquals("2860.0", bd2.get("over").get("ASDA2"));
    }

    @Test
    //Testing the second left runway's LDA is being calculated in the correct way (27L).
    //This includes the formula being used and the values being subbed in.
    public void testBreakdownTOALOLDA2(){
        logger.debug("Testing TOALO LDA calculation and values...");
        String expecteddesc = "Original LDA - Distance From Threshold - Slope Calculation - Strip End";
        logger.debug("Expected formula: " + expecteddesc);
        logger.debug("Actual formula: " + bd2.get("over").get("LDA"));
        Assert.assertEquals(expecteddesc, bd2.get("over").get("LDA"));
        String expectedval = "3660.0 - 500.0 - (25.0 * 50.0) - 60.0";
        logger.debug("Expected formula: " + expectedval);
        logger.debug("Actual formula: " + bd2.get("over").get("LDA1"));
        Assert.assertEquals(expectedval, bd2.get("over").get("LDA1"));
        logger.debug("Expected result: " + 1850.0);
        logger.debug("Actual result: " + bd2.get("over").get("LDA2"));
        Assert.assertEquals("1850.0", bd2.get("over").get("LDA2"));
    }
}