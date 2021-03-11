package cat.xojan.kafkademo.model;

import java.util.List;

public class GeoPosition {
    private List<Feature> features;

    public GeoPosition() {}

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "features=" + features +
                '}';
    }
}
