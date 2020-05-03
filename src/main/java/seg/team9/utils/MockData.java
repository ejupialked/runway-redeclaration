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

        //HENRYS TEST LAND
        DirectedRunway d7 = new DirectedRunway("09R",4000D,4100D,4000D,4000D,0D,100D,50D);
        DirectedRunway d8 = new DirectedRunway("27L",4000D,4100D,4000D,4000D,0D,100D,50D);
        Runway r4 = new Runway(d7, d8, 4000);
        Airport a4 = new Airport("Henrys Test Land");
        a4.addRunway(r4);

        //HEATHROW
        DirectedRunway d1 = new DirectedRunway("09L", 3902d, 3902d, 3902d, 3902d, 306d, 0d, 0d);
        DirectedRunway d2 = new DirectedRunway("27R", 3902d, 3902d, 3884d, 3902d, 0d, 0d, 0d);
        Runway r1 = new Runway(d1, d2, 4000d);
        DirectedRunway dd1 = new DirectedRunway("09R", 3660d, 3660d, 3660d, 3660d, 306d, 0d, 0d);
        DirectedRunway dd2 = new DirectedRunway("27L", 3660d, 3660d, 3660d, 3660d, 0d, 0d, 0d);
        Runway rr1 = new Runway(dd1, dd2, 4000d);
        Airport a1 = new Airport("(LHR) London Heathrow");
        a1.addRunway(r1);
        a1.addRunway(rr1);


        //GATWICK
        DirectedRunway d3 = new DirectedRunway("08L", 2565d, 2565d, 2565d, 2565d, 307d, 0d, 0d);
        DirectedRunway d4 = new DirectedRunway("26R", 2565d, 2565d, 2565d, 2565d, 0d, 0d, 0d);
        Runway r2 = new Runway(d3, d4, 2565d);
        DirectedRunway dd3 = new DirectedRunway("08R", 3316d, 3316d, 3316d, 3316d, 307d, 0d, 0d);
        DirectedRunway dd4 = new DirectedRunway("26L", 3316d, 3316d, 3316d, 3316d, 0d, 0d, 0d);
        Runway rr2 = new Runway(dd3, dd4, 3316d);
        Airport a2 = new Airport("(LGW) Gatwick Airport");
        a2.addRunway(r2);
        a2.addRunway(rr2);

        //LUTON
        DirectedRunway d5 = new DirectedRunway("08",2162d,2162d,2162d,2162d,0D,0D,0D);
        DirectedRunway d6 = new DirectedRunway("26",2162d,2162d,2162d,2162d,0D,0D,0D);
        Runway r3 = new Runway(d5, d6, 2162d);
        Airport a3 = new Airport("(LTN) London Luton Airport");
        a3.addRunway(r3);

        //Southampton
        DirectedRunway d11 = new DirectedRunway("02",1723d,1723d,1723d,1723d,0D,0D,0D);
        DirectedRunway d12 = new DirectedRunway("20",1723d,1723d,1723d,1723d,0D,0D,0D);
        Runway r5 = new Runway(d11, d12, 1723d);
        Airport a5 = new Airport("(SOU) Southampton Airport");
        a5.addRunway(r5);

        //Birminghan
        DirectedRunway d13 = new DirectedRunway("15",3052d,3052d,3052d,3052d,0D,0D,0D);
        DirectedRunway d14 = new DirectedRunway("33",3052d,3052d,3052d,3052d,0D,0D,0D);
        Runway r6 = new Runway(d13, d14, 3052d);
        Airport a6 = new Airport("(SOU) Southampton Airport");
        a6.addRunway(r6);






        airportArrayList.add(a4);
        airportArrayList.add(a1);
        airportArrayList.add(a2);
        airportArrayList.add(a3);
        airportArrayList.add(a5);
        return airportArrayList;
    }

    private static List<Obstacle> obstacles(){
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle("Boeing777", 25d, 100d, 20d, 2853d, 500d));
        obstacles.add(new Obstacle("Boeing737", 12d, 100d, 0d, 3646d, -50d));
        return obstacles;
    }

}