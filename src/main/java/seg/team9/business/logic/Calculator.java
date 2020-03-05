package seg.team9.business.logic;

import javafx.css.CssParser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

public class Calculator {

    private static final Logger logger = LogManager.getLogger("Calculator");
    private static final Double BLAST_PROTECTION = 300d;
    private static final Double SLOPE = 50d;
    private static final Double STRIPEND = 60d;

    //Running this method on a runway will calculate the redesignated values and store them in the WorkingXXXX variables.
    //The return of this method is a 2D array containing the calculation breakdowns split by each directed runway, and making it clear which direction the plane is landing or taking off.
    public String[][] redesignate(Runway runway, Obstacle obstacle){
        return findDirection(runway, obstacle);
    }


    //This method finds which situation for take off & landing applies to each runway.
    //TOTLT - Taking off towards, landing towards
    //TOALO - Taking off away, landing over
    //The relevant calculations are then carried out for the necessary runway.
    //The method returns the same calculation breakdown as the redesignate method.
    private String[][] findDirection(Runway runway, Obstacle obstacle){
        String[][] calculations = new String[2][];
        String[] TOTLT;
        String[] TOALO;

        if(obstacle.getDistanceLThreshold() > obstacle.getDistanceRThreshold()){
            TOTLT = calculateTOTLT(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight());
            TOTLT[0] = "Taking off towards, landing towards: " + runway.getLRunway().toString();
            TOALO = calculateTOALO(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight());
            TOALO[0] = "Taking off away, landing over: " + runway.getRRunway().toString();
        }
        else{
            TOTLT = calculateTOTLT(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight());
            TOTLT[0] = "Taking off towards, landing towards: " + runway.getRRunway().toString();
            TOALO = calculateTOALO(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight());
            TOALO[0] = "Taking off away, landing over: " + runway.getLRunway().toString();
        }

        calculations[0] = TOTLT;
        calculations[1] = TOALO;
        return calculations;
    }

    //This method calculates the redesignated values for the runway that is taking off away from and landing over the obstacle.
    //The calculation breakdown for this runway are returned in an array.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private String[] calculateTOALO(DirectedRunway runway, Double distance, Double height){
        String[] calculations = new String[9];

        runway.setWorkingTORA(
                runway.getTora() - BLAST_PROTECTION - distance - runway.getThreshold()
        );
        calculations[1] = "TORA: Actual TORA - Blast Protection - Distance From Threshold - Runway Threshold" ;
        calculations[2] = "TORA: " + runway.getTora() + " - " + BLAST_PROTECTION + " - " + distance + " - " + runway.getThreshold() + " = " + runway.getWorkingTORA();

        runway.setWorkingASDA(
                runway.getWorkingTORA() + runway.getStopway()
        );
        calculations[3] = "ASDA: Working TORA + Stopway";
        calculations[4] = "ASDA: " + runway.getWorkingTORA() + " + " + runway.getStopway() + " = " + runway.getWorkingASDA();

        runway.setWorkingTODA(
                runway.getWorkingTORA() + runway.getClearway()
        );
        calculations[5] = "TODA: Working TORA + Clearway";
        calculations[6] = "TODA: " + runway.getWorkingTORA() + " + " + runway.getClearway() + " = " + runway.getWorkingTODA();

        runway.setWorkingLDA(
                runway.getLda() - distance - (height * SLOPE) - STRIPEND
        );
        calculations[7] = "LDA: Actual LDA - Distance From Threshold - (Height * Slope) - Strip End";
        calculations[8] = "LDA: " + runway.getLda() + " - " + distance + " - (" + height + " * " + SLOPE + ") - " + STRIPEND;

        return calculations;
    }

    //This method calculates the redesignated values for the runway that is taking off towards and landing towards the obstacle.
    //The calculation breakdown for this runway are returned in an array.
    //The parameters are the runway to calculate on, the distance of the obstacle from that runway's threshold and the height of the obstacle.
    private String[] calculateTOTLT(DirectedRunway runway, Double distance, Double height){
        String[] calculations = new String[9];

        runway.setWorkingTORA(
                distance + runway.getThreshold() - (height * SLOPE)  - STRIPEND
        );
        calculations[1] = "TORA: Distance From Threshold + Runway Threshold - (Height * Slope) - Strip End";
        calculations[2] = "TORA: " + distance + " + " + runway.getThreshold() + " - (" + height + " * " + SLOPE + ") - " + STRIPEND + " = " + runway.getWorkingTORA();
        runway.setWorkingASDA(runway.getWorkingTORA());
        calculations[3] = "ASDA: Working TORA";
        calculations[4] = "ASDA: " + runway.getWorkingTORA() + " = " + runway.getWorkingTORA();
        runway.setWorkingTODA(runway.getWorkingTORA());
        calculations[5] = "TODA: Working TORA";
        calculations[6] = "TODA: " + runway.getWorkingTORA() + " = " + runway.getWorkingTORA();
        runway.setWorkingLDA(
                distance - runway.getResa() - STRIPEND
        );
        calculations[7] = "LDA: Distance from threshold - RESA - Strip End";
        calculations[8] = "LDA: " + distance + " - " + runway.getResa() + " - " + STRIPEND;

        return calculations;
    }
}
