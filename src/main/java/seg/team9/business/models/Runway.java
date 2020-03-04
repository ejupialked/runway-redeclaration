package seg.team9.business.models;

public class Runway {
    private DirectedRunway rRunway, lRunway;

    public Runway(DirectedRunway rRunway, DirectedRunway lRunway){
        this.rRunway = rRunway;
        this.lRunway = lRunway;
    }

    public DirectedRunway getRRunway(){
        return rRunway;
    }

    public DirectedRunway getLRunway(){
        return lRunway;
    }

    @Override
    public String toString(){
        return lRunway.toString()+" "+rRunway.toString();
    }

}
