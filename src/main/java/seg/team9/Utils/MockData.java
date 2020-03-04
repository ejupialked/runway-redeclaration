package seg.team9.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seg.team9.business.models.Airport;
import seg.team9.business.models.DirectedRunway;
import seg.team9.business.models.Obstacle;
import seg.team9.business.models.Runway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockData {

    static public ObservableList<Obstacle> obstacles = FXCollections.observableArrayList();


    public MockData() {

    }

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

    public static ObservableList<Obstacle> obstacleList(){
//        ArrayList<Obstacle> obstacles = new ArrayList<>();
//        for (int i = 0; i < 10 ; i++) {
//           addObstacle(new Obstacle("obstacle" + i));
//        }
        obstacles.add(new Obstacle("obstacle1", 100D, 10D, 0D, 3000D, 660D));
        obstacles.add(new Obstacle("obstacle2", 1D, 10D, 0D, 3000D, 660D));
        return obstacles;
    }

    public static ObservableList<Obstacle> addObstacle(Obstacle obstacle){
        obstacles.add(obstacle);
        return obstacles;
    }
}
