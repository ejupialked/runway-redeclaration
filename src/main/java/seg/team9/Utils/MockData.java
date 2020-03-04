package seg.team9.Utils;

import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockData {


    public static List<Runway> runwayList(){
        ArrayList<Runway> runways = new ArrayList<>();
        runways.add(new Runway(directedRunways().get(0), directedRunways().get(1)));
        runways.add(new Runway(directedRunways().get(1), directedRunways().get(0)));
        return runways;
    }


    public static List<DirectedRunway> directedRunways(){
        ArrayList<DirectedRunway> directedRunways = new ArrayList<>();
        directedRunways.add(new DirectedRunway("09/R", 3660D,3660D,3660D,3660D,0D,500D,100D));
        directedRunways.add(new DirectedRunway("270/L", 3660D,3660D,3660D,3660D,0D,250D,200D));
        return directedRunways;
    }


    public static List<Airport> airportList(){
        ArrayList<Airport> airports = new ArrayList<>();
        airports.add(new Airport("Heathrow"));
        airports.add(new Airport("Gatwick"));
        airports.add(new Airport("Stansted"));
        airports.add(new Airport("Luton"));
        airports.add(new Airport("City Airport"));
        return airports;
    }

    public static List<Obstacle> obstacleList(){
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle("obstacle1", 100D, 200D, 0D, 1000D, 3000D));
        obstacles.add(new Obstacle("obstacle2", 100D, 100D, 100D, 2000D, 1400D));
        return obstacles;
    }
}
