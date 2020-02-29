package seg.team9.business.models;

public class Runway {

    private DirectedRunway rRunway, lRunway;

    public Runway(DirectedRunway right, DirectedRunway left){
        rRunway = right;
        lRunway = left;
    }

    public DirectedRunway getRRunway(){
        return rRunway;
    }

    public DirectedRunway getLRunway(){
        return lRunway;
    }

}
