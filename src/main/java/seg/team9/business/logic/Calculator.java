package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.controllers.breakdown.CalculationBreakdownController;

import java.util.HashMap;
import java.util.Map;


public class Calculator {

    private static final Logger logger = LogManager.getLogger("Calculator");
    private static final Double BLAST_PROTECTION = 300d;
    private static final Double SLOPE = 50d;
    private static final Double STRIPEND = 60d;
    private Map<String, String> overBreakdown = new HashMap<>();
    private Map<String, String> towardsBreakdown = new HashMap<>();

    private final Map<String , Map<String, String>> calculationBreakdown = new HashMap<>();


    //Running this method on a runway will calculate the redesignated values and store them in the WorkingXXXX variables.
    //The method does not return anything - merely used as a gateway.
    public void redesignate(Runway runway, Obstacle obstacle){
        calculationBreakdown.put("over", overBreakdown);
        calculationBreakdown.put("towards", towardsBreakdown);

        findDirection(runway, obstacle);
        runway.setCalculationBreakdow(calculationBreakdown);
        CalculationBreakdownController.getInstance().showBreakdown(calculationBreakdown);
    }


    //This method finds which situation for take off & landing applies to each runway.
    //TOTLT - Taking off towards, landing towards
    //TOALO - Taking off away, landing over
    //The relevant calculations are then carried out for the necessary runway.
    //The method doesn't return anything, but writes the calculation breakdown to the hash map.
    private void findDirection(Runway runway, Obstacle obstacle){
        String towards = " (Taking Off Towards, Landing Towards)";
        String over = " (Taking Off Away, Landing Over)";

        if(obstacle.getDistanceLThreshold() > obstacle.getDistanceRThreshold()){
            //left
            calculationBreakdown.get("towards").put("title", runway.getLRunway().toString()  + towards);
            calculateTOTLT(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight(), "towards");
            //right
            calculationBreakdown.get("over").put("title", runway.getRRunway().toString() + over);
            calculateTOALO(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight(),"over");

        }
        else{
            //right
            calculationBreakdown.get("towards").put("title", runway.getRRunway().toString() + towards);
            calculateTOTLT(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight(), "towards");
            //left
            calculationBreakdown.get("over").put("title", runway.getLRunway().toString() + over);
            calculateTOALO(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight(), "over");
        }
    }

    //This method calculates the redesignated values for the runway that is taking off away from and landing over the obstacle.
    //The calculation breakdown for this runway are added to the hashmap.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private void calculateTOALO(DirectedRunway runway, Double distance, Double height, String direction){
        runway.setWorkingTORA(runway.getTora() - BLAST_PROTECTION - distance - runway.getThreshold());

        calculationBreakdown.get(direction).put("TORA", "Original TORA - Blast Protection" + " - Distance From Threshold - Displaced Threshold");
        calculationBreakdown.get(direction).put("TORA1",  runway.getTora() + " - " + BLAST_PROTECTION + " - " + distance + " - " + runway.getThreshold() );
        calculationBreakdown.get(direction).put("TORA2", runway.getWorkingTORA().toString());


        runway.setWorkingASDA(runway.getWorkingTORA() + runway.getStopway());

        calculationBreakdown.get(direction).put("ASDA", "(R) TORA + STOPWAY");
        calculationBreakdown.get(direction).put("ASDA1", runway.getWorkingTORA() + " + " + runway.getStopway());
        calculationBreakdown.get(direction).put("ASDA2",  runway.getWorkingASDA().toString());

        runway.setWorkingTODA(runway.getWorkingTORA()  + runway.getClearway());

        calculationBreakdown.get(direction).put("TODA", "(R) TORA + Clearway");
        calculationBreakdown.get(direction).put("TODA1", runway.getWorkingTORA() + " + " + runway.getClearway());
        calculationBreakdown.get(direction).put("TODA2", runway.getWorkingTODA().toString());


        runway.setWorkingLDA(runway.getLda() - distance - (height * SLOPE) - STRIPEND);

        calculationBreakdown.get(direction).put("LDA", "Original LDA - Distance From Threshold - Slope Calculation - Strip End");
        calculationBreakdown.get(direction).put("LDA1",runway.getLda() + " - " + distance + " - (" + height + " * " + SLOPE + ") - " + STRIPEND);
        calculationBreakdown.get(direction).put("LDA2", runway.getWorkingLDA().toString());
        
        if(runway.getWorkingTORA() < 0) runway.setWorkingTORA(0d);
        if(runway.getWorkingTODA() < 0) runway.setWorkingTODA(0d);
        if(runway.getWorkingASDA() < 0) runway.setWorkingASDA(0d);
        if(runway.getWorkingLDA() < 0) runway.setWorkingLDA(0d);
    }

    //This method calculates the redesignated values for the runway that is taking off towards and landing towards the obstacle.
    //The calculation breakdown for this runway are stored in the hashmap.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private void calculateTOTLT(DirectedRunway runway, Double distance, Double height, String direction){
        runway.setWorkingTORA(distance + runway.getThreshold() - (height * SLOPE)  - STRIPEND);

        calculationBreakdown.get(direction).put("TORA", "Distance From Threshold + Runway Threshold - (Slope Calculation) - Strip End");
        calculationBreakdown.get(direction).put("TORA1", distance + " + " + runway.getThreshold() + " - (" + height + " * " + SLOPE + ") - " + STRIPEND );
        calculationBreakdown.get(direction).put("TORA2", runway.getWorkingTORA().toString());

        runway.setWorkingASDA(runway.getWorkingTORA());

        calculationBreakdown.get(direction).put("ASDA", "(R) TORA");
        calculationBreakdown.get(direction).put("ASDA1", runway.getWorkingASDA().toString());

        runway.setWorkingTODA(runway.getWorkingTORA());
        calculationBreakdown.get(direction).put("TODA", "(R) TODA");
        calculationBreakdown.get(direction).put("TODA1", runway.getWorkingTODA().toString());

        runway.setWorkingLDA(distance - runway.getResa() - STRIPEND);
        calculationBreakdown.get(direction).put("LDA", "Distance from Threshold - RESA - Strip End");
        calculationBreakdown.get(direction).put("LDA1", distance + " - " + runway.getResa() + " - " + STRIPEND);
        calculationBreakdown.get(direction).put("LDA2", runway.getWorkingLDA().toString());
        
        if(runway.getWorkingTORA() < 0) runway.setWorkingTORA(0d);
        if(runway.getWorkingTODA() < 0) runway.setWorkingTODA(0d);
        if(runway.getWorkingASDA() < 0) runway.setWorkingASDA(0d);
        if(runway.getWorkingLDA() < 0) runway.setWorkingLDA(0d);

    }

    //The calculations breakdown for the calculator are stored as a hashmap, pairing up a key for the part of the calculation with its value.
    //The key can be one of the following:
        //TOTLT/TOALO: representing the header for that set of calculations (TOTLT = taking off towards, landing towards, TOALO = taking off over, landing over)
            //This includes the name of the runway and which set of calculations are being used.
        //<TOTLT/TOALO><TORA/TODA/ASDA/LDA><desc/val> - the part of the calculation you want, then the specific value, then the formula or values.
            //I.e. TOALOASDAval would be something like "ASDA: 3330.0 - 54.0 + (12 * 50) = 3876.0
    //This method simply returns the hashmap containing the breakdown for the last carried out redesignation.
    public Map<String, Map<String, String>> getCalculationsBreakdown(){
        return calculationBreakdown;
    }

    public static Double getSLOPE() {
        return SLOPE;
    }
}
