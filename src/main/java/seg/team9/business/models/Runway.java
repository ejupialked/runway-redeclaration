package seg.team9.business.models;

import java.util.HashMap;
import java.util.Map;

public class Runway {
    private final DirectedRunway rightRunway, leftRunway;
    private final double length;
    private Map<String, Map<String, String>> calculationBreakdow;

    public Runway(DirectedRunway rightRunway, DirectedRunway leftRunway, double length){
        this.rightRunway = rightRunway;
        this.leftRunway = leftRunway;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public DirectedRunway getRRunway(){
        return rightRunway;
    }

    public DirectedRunway getLRunway(){
        return leftRunway;
    }

    public void setCalculationBreakdow(Map<String, Map<String, String>> calculationBreakdow) {
        this.calculationBreakdow = calculationBreakdow;
    }

    public Map<String, Map<String, String>> getCalculationBreakdow() {
        return calculationBreakdow;
    }

    @Override
    public String toString(){
        return leftRunway.toString()+"/"+ rightRunway.toString();
    }

}
