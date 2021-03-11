package cat.xojan.kafkademo.model;

public class Feature {
    private Geometry geometry;
    private Properties properties;

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "Train{" +
                "geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }
}
