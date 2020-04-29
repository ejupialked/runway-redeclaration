package seg.team9.business.models;

public class Runway {
    private final DirectedRunway rightRunway, leftRunway;

    public Runway(DirectedRunway rightRunway, DirectedRunway leftRunway){
        this.rightRunway = rightRunway;
        this.leftRunway = leftRunway;
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
