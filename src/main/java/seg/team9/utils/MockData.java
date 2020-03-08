package seg.team9.utils;

import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Airport> aiports = airports();
    public static List<Obstacle> obstacles = obstacles();


    private static List<Airport> airports(){
        ArrayList<Airport> airportArrayList = new ArrayList<>();

        //HEATHROW
        DirectedRunway d1 = new DirectedRunway("09L", 3902d, 3902d, 3902d, 3595d, 306d, 0d, 0d);
        DirectedRunway d2 = new DirectedRunway("27R", 3884d, 3962d, 3884d, 3884d, 0d, 0d, 0d);
        Runway r1 = new Runway(d1, d2);
        Airport a1 = new Airport("Heathrow");
        a1.addRunway(r1);

        //GATWICK
        DirectedRunway d3 = new DirectedRunway("19R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d);
        DirectedRunway d4 = new DirectedRunway("30L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway r2 = new Runway(d3, d2);
        Airport a2 = new Airport("Gatwick");
        a2.addRunway(r2);

        //LUTON
        DirectedRunway d5 = new DirectedRunway("4R",4000D,4000D,4000D,4000D,0D,0D,0D);
        DirectedRunway d6 = new DirectedRunway("32R",4000D,4000D,4000D,4000D,0D,0D,0D);
        Runway r3 = new Runway(d5, d6);
        Airport a3 = new Airport("Luton");
        a3.addRunway(r3);

        airportArrayList.add(a1);
        airportArrayList.add(a2);
        airportArrayList.add(a3);

        return airportArrayList;
    }

    private static List<Obstacle> obstacles(){
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle("Boeing737", 12d, 100d, 0d, 3646d, -50d));
        obstacles.add(new Obstacle("Boeing777", 25d, 100d, 20d, 2853d, 500d));
        return obstacles;
    }

}
