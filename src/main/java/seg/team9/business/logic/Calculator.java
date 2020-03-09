package seg.team9.business.logic;

import javafx.css.CssParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import java.util.HashMap;

public class Calculator {

    private static final Logger logger = LogManager.getLogger("Calculator");
    private static final Double BLAST_PROTECTION = 300d;
    private static final Double SLOPE = 50d;
    private static final Double STRIPEND = 60d;
    private HashMap<String, String> calculationBreakdown = new HashMap<String, String>();

    //Running this method on a runway will calculate the redesignated values and store them in the WorkingXXXX variables.
    //The method does not return anything - merely used as a gateway.
    public void redesignate(Runway runway, Obstacle obstacle){
        findDirection(runway, obstacle);
    }


    //This method finds which situation for take off & landing applies to each runway.
    //TOTLT - Taking off towards, landing towards
    //TOALO - Taking off away, landing over
    //The relevant calculations are then carried out for the necessary runway.
    //The method doesn't return anything, but writes the calculation breakdown to the hash map.
    private void findDirection(Runway runway, Obstacle obstacle){
        if(obstacle.getDistanceLThreshold() > obstacle.getDistanceRThreshold()){
            calculationBreakdown.put("TOTLT", "Taking off towards, landing towards: " + runway.getLRunway().toString());
            calculateTOTLT(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight());
            calculationBreakdown.put("TOALO", "Taking off away, landing over: " + runway.getRRunway().toString());
            calculateTOALO(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight());
        }
        else{
            calculationBreakdown.put("TOTLT", "Taking off towards, landing towards: " + runway.getRRunway().toString());
            calculateTOTLT(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight());
            calculationBreakdown.put("TOALO", "Taking off away, landing over: " + runway.getLRunway().toString());
            calculateTOALO(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight());
        }
    }

    //This method calculates the redesignated values for the runway that is taking off away from and landing over the obstacle.
    //The calculation breakdown for this runway are added to the hashmap.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private void calculateTOALO(DirectedRunway runway, Double distance, Double height){
        runway.setWorkingTORA(
                runway.getTora() - BLAST_PROTECTION - distance - runway.getThreshold()
        );
        calculationBreakdown.put("TOALOTORAdesc", "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold");
        calculationBreakdown.put("TOALOTORAval", "TORA: " + runway.getTora() + " - " + BLAST_PROTECTION + " - " + distance + " - " + runway.getThreshold() + " = " + runway.getWorkingTORA());

        runway.setWorkingASDA(
                runway.getWorkingTORA() + runway.getStopway()
        );
        calculationBreakdown.put("TOALOASDAdesc", "ASDA: Working TORA + Stopway");
        calculationBreakdown.put("TOALOASDAval", "ASDA: " + runway.getWorkingTORA() + " + " + runway.getStopway() + " = " + runway.getWorkingASDA());

        runway.setWorkingTODA(
                runway.getWorkingTORA() + runway.getClearway()
        );
        calculationBreakdown.put("TOALOTODAdesc", "TODA: Working TORA + Clearway");
        calculationBreakdown.put("TOALOTODAval", "TODA: " + runway.getWorkingTORA() + " + " + runway.getClearway() + " = " + runway.getWorkingTODA());

        runway.setWorkingLDA(
                runway.getLda() - distance - (height * SLOPE) - STRIPEND
        );
        calculationBreakdown.put("TOALOLDAdesc", "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End");
        calculationBreakdown.put("TOALOLDAval", "LDA: " + runway.getLda() + " - " + distance + " - (" + height + " * " + SLOPE + ") - " + STRIPEND + " = " + runway.getWorkingLDA());
    }

    //This method calculates the redesignated values for the runway that is taking off towards and landing towards the obstacle.
    //The calculation breakdown for this runway are stored in the hashmap.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private void calculateTOTLT(DirectedRunway runway, Double distance, Double height){
        runway.setWorkingTORA(
                distance + runway.getThreshold() - (height * SLOPE)  - STRIPEND
        );
        calculationBreakdown.put("TOTLTTORAdesc", "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End");
        calculationBreakdown.put("TOTLTTORAval", "TORA: " + distance + " + " + runway.getThreshold() + " - (" + height + " * " + SLOPE + ") - " + STRIPEND + " = " + runway.getWorkingTORA());
        runway.setWorkingASDA(runway.getWorkingTORA());
        calculationBreakdown.put("TOTLTASDAdesc", "ASDA: Working TORA");
        calculationBreakdown.put("TOTLTASDAval", "ASDA: " + runway.getWorkingTORA() + " = " + runway.getWorkingTORA());
        runway.setWorkingTODA(runway.getWorkingTORA());
        calculationBreakdown.put("TOTLTTODAdesc", "TODA: Working TORA");
        calculationBreakdown.put("TOTLTTODAval", "TODA: " + runway.getWorkingTORA() + " = " + runway.getWorkingTORA());
        runway.setWorkingLDA(
                distance - runway.getResa() - STRIPEND
        );
        calculationBreakdown.put("TOTLTLDAdesc", "LDA: Distance from threshold - RESA - Strip End");
        calculationBreakdown.put("TOTLTLDAval", "LDA: " + distance + " - " + runway.getResa() + " - " + STRIPEND + " = " + runway.getWorkingLDA());
    }

    //The calculations breakdown for the calculator are stored as a hashmap, pairing up a key for the part of the calculation with its value.
    //The key can be one of the following:
        //TOTLT/TOALO: representing the header for that set of calculations (TOTLT = taking off towards, landing towards, TOALO = taking off over, landing over)
            //This includes the name of the runway and which set of calculations are being used.
        //<TOTLT/TOALO><TORA/TODA/ASDA/LDA><desc/val> - the part of the calculation you want, then the specific value, then the formula or values.
            //I.e. TOALOASDAval would be something like "ASDA: 3330.0 - 54.0 + (12 * 50) = 3876.0
    //This method simply returns the hashmap containing the breakdown for the last carried out redesignation.
    public HashMap<String, String> getCalculationsBreakdown(){
        return calculationBreakdown;
    }
}
