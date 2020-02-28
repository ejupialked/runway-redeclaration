package seg.team9.business.models;

public class Runway {

    private DirectedRunway rRunway, lRunway;

    public Runway(DirectedRunway right, DirectedRunway left){
        rRunway = right;
        lRunway = left;
    }

}
