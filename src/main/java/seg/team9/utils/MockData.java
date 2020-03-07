package seg.team9.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    static public ObservableList<Obstacle> obstacles = FXCollections.observableArrayList();


    public MockData() {

    }

    public static List<Runway> runwayList(){
        ArrayList<Runway> runways = new ArrayList<>();
        runways.add(new Runway(directedRunways().get(0), directedRunways().get(1)));
        runways.add(new Runway(directedRunways().get(2), directedRunways().get(3)));
        runways.add(new Runway(directedRunways().get(4), directedRunways().get(5)));
        return runways;
    }


    public static List<DirectedRunway> directedRunways(){
        ArrayList<DirectedRunway> directedRunways = new ArrayList<>();
        directedRunways.add(new DirectedRunway("09L", 3902d, 3902d, 3902d, 3595d, 306d, 0d, 0d));
        directedRunways.add(new DirectedRunway("27R", 3884d, 3962d, 3884d, 3884d, 0d, 0d, 0d));
        directedRunways.add(new DirectedRunway("09R", 3660d, 3660d, 3660d, 3353d, 307d, 0d, 0d));
        directedRunways.add(new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d));
        directedRunways.add(new DirectedRunway("4R",4000D,4000D,4000D,4000D,0D,0D,0D));
        directedRunways.add(new DirectedRunway("32R",4000D,4000D,4000D,4000D,0D,0D,0D));
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

    public static ObservableList<Obstacle> obstacleList(){
//        ArrayList<Obstacle> obstacles = new ArrayList<>();
//        for (int i = 0; i < 10 ; i++) {
//           addObstacle(new Obstacle("obstacle" + i));
//        }
        obstacles.add(new Obstacle("Boeing737 on Left", 12d, 100d, 0d, 3646d, -50d));
        obstacles.add(new Obstacle("Boeing737 on Right", 25d, 100d, 20d, 2853d, 500d));
        return obstacles;
    }

    public static ObservableList<Obstacle> addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
        return obstacles;
    }
}
