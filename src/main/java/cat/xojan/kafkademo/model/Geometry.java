package cat.xojan.kafkademo.model;

import java.util.List;

public class Geometry {
    private List<Double> coordinates;

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "coordinates=" + coordinates +
                '}';
    }
}
