package seg.team9.business.models;

import java.util.HashMap;

public class Runway {
    private final DirectedRunway rightRunway, leftRunway;
    private final double length;
    private HashMap<String, String> calculationBreakdow;

    public Runway(DirectedRunway rightRunway, DirectedRunway leftRunway, double length){
        this.rightRunway = rightRunway;
        this.leftRunway = leftRunway;
        this.length = length;
    }

    public DirectedRunway getRRunway(){
        return rightRunway;
    }

    public DirectedRunway getLRunway(){
        return leftRunway;
    }

    public void setCalculationBreakdow(HashMap<String, String> calculationBreakdow) {
        this.calculationBreakdow = calculationBreakdow;
    }

    @Override
    public String toString(){
        return leftRunway.toString()+" "+ rightRunway.toString();
    }

}
