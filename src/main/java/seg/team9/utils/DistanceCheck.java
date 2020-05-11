package seg.team9.utils;

import seg.team9.business.logic.Calculator;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;
import seg.team9.exceptions.ObstacleOutsideOfRunwayException;

public class DistanceCheck {
    public static void checkObstacleDistances(Runway r, Double centerLine, Double right, Double left) throws ObstacleOutsideOfRunwayException {
        if(right > (r.getLength()*1.43)) throw new ObstacleOutsideOfRunwayException();
        if(right < (-r.getLength()*0.15)) throw new ObstacleOutsideOfRunwayException();
        if(left > (r.getLength()*1.43)) throw new ObstacleOutsideOfRunwayException();
        if(left < (-r.getLength()*0.15)) throw new ObstacleOutsideOfRunwayException();
        if((r.getLength()-(right+left) <= 0)) throw new ObstacleOutsideOfRunwayException();
        if(centerLine>40) throw new ObstacleOutsideOfRunwayException();
    }
}
