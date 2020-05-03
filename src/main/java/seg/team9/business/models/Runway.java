package seg.team9.business.models;

public class Runway {
    private final DirectedRunway rightRunway, leftRunway;
    private final double length;

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

    @Override
    public String toString(){
        return leftRunway.toString()+" "+ rightRunway.toString();
    }

}
