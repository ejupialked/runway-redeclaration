package seg.team9.business.models;

import java.util.ArrayList;

public class Airport {
    private ArrayList<Runway> runwayList;

    private String name;
    private String city;
    private Double latitude;
    private Double longitude;

    public Airport(String name) {
        this.name = name;
    }
    public Airport(String name, String city, Double latitude, Double longitude)
    {
        this.runwayList = new ArrayList<>();
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addRunway(Runway runway) {
        runwayList.add(runway);
    }
    public ArrayList<Runway> getRunwayList() {
        return runwayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name;
    }
}
