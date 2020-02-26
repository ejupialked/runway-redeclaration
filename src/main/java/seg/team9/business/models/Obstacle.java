package seg.team9.business.models;

public class Obstacle {
    private Double height,width;
    private Double distanceCenter,distanceThrehold;

    public Obstacle (Double height, Double width, Double distanceCenter, Double distanceThrehold)
    {
        this.height = height;
        this.width = width;
        this.distanceCenter = distanceCenter;
        this.distanceThrehold = distanceThrehold;
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

    public Double getDistanceThrehold() {
        return distanceThrehold;
    }

    public void setDistanceThrehold(Double distanceThrehold) {
        this.distanceThrehold = distanceThrehold;
    }
}
