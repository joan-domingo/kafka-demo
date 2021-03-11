package cat.xojan.kafkademo.model;

import java.util.List;

public class ExternalServiceResponse {
    private List<Feature> features;

    public ExternalServiceResponse() {}

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "ExternalServiceResponse{" +
                "features=" + features +
                '}';
    }
}
