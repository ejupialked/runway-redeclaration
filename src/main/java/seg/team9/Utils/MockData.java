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
        runways.add(new Runway(directedRunways().get(0), directedRunways().get(1)));
        return runways;
    }


    public static List<DirectedRunway> directedRunways(){
        ArrayList<DirectedRunway> directedRunways = new ArrayList<>();
        directedRunways.add(new DirectedRunway("09/R"));
        directedRunways.add(new DirectedRunway("13/L"));
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
        for (int i = 0; i < 10 ; i++) {
           obstacles.add(new Obstacle("obstacle" + i));
        }
        return obstacles;
    }
}
