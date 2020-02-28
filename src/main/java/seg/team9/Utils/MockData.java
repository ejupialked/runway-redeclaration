package seg.team9.Utils;

import seg.team9.business.models.Airport;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.util.ArrayList;
import java.util.List;

public class MockData {


    public static List<Runway> runwayList(){
        ArrayList<Runway> runways = new ArrayList<>();
        runways.add(new Runway("09/R"));
        runways.add(new Runway("13/L"));
        return runways;
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
           obstacles.add(new Obstacle("obstacle" + i,3.1*i ,
                   1.4*i,2.1*i,0.4*i));
        }
        return obstacles;
    }
}
