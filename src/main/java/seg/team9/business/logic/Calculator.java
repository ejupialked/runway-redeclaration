package seg.team9.business.logic;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

public class Calculator {

    private static final Logger logger = LogManager.getLogger("Calculator");
    private static final Double blastProtection = 300d;
    private static final Double slope = 50d;
    private static final Double stripend = 60d;

    public Runway redesignate(Runway runway, Obstacle obstacle){
        findDirection(runway, obstacle);
        return runway;
    }

    private void findDirection(Runway runway, Obstacle obstacle){
        if(obstacle.getDistanceLThreshold() > obstacle.getDistanceRThreshold()){
            calculateTOTLT(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight());
            calculateTOALO(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight(), runway.getLRunway().getWorkingTORA());
        }
        else{
            calculateTOTLT(runway.getRRunway(), obstacle.getDistanceRThreshold(), obstacle.getHeight());
            calculateTOALO(runway.getLRunway(), obstacle.getDistanceLThreshold(), obstacle.getHeight(), runway.getRRunway().getWorkingTORA());
        }
    }

    private void calculateTOALO(DirectedRunway runway, Double distance, Double height, Double tora){
        runway.setWorkingTORA(
                runway.getTora() - blastProtection - distance - runway.getThreshold()
        );
        runway.setWorkingASDA(
                tora + runway.getStopway()
        );
        runway.setWorkingTODA(
                tora + runway.getClearway()
        );
        runway.setWorkingLDA(
                runway.getLda() - distance - (height * slope) - stripend
        );

    }

    private void calculateTOTLT(DirectedRunway runway, Double distance, Double height){
        runway.setWorkingTORA(
                distance - (height * slope)  - stripend
        );
        runway.setWorkingASDA(runway.getWorkingTORA());
        runway.setWorkingTODA(runway.getWorkingTORA());
        runway.setWorkingLDA(
                distance - runway.getResa() - stripend
        );
    }
}
