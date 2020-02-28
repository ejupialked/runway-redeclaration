package seg.team9.business.models;

public class Obstacle {
    private Double height,width;
    private Double distanceCenter,distanceRThreshold, distanceLThreshold;

    public Obstacle (Double height, Double width, Double distanceCenter, Double distanceRThreshold,Double distanceLThreshold)
    {
        this.height = height;
        this.width = width;
        this.distanceCenter = distanceCenter;
        this.distanceRThreshold = distanceRThreshold;
        this.distanceLThreshold = distanceLThreshold;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDistanceCenter() {
        return distanceCenter;
    }

    public void setDistanceCenter(Double distanceCenter) {
        this.distanceCenter = distanceCenter;
    }

    public Double getDistanceRThreshold() { return distanceRThreshold; }

    public void setDistanceRThreshold(Double distanceRThreshold) {
        this.distanceRThreshold = distanceRThreshold;
    }

    public Double getDistanceLThreshold() { return distanceLThreshold; }

    public void setDistanceLThreshold(Double distanceLThreshold) {
        this.distanceLThreshold = distanceLThreshold;
    }
}
