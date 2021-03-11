package cat.xojan.kafkademo.model;

public class Train {
    private String id;
    private String line;
    private Double longitude;
    private Double latitude;

    public Train() {}

    public Train(String id, String line, double longitude, double latitude) {
        this.id = id;
        this.line = line;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id='" + id + '\'' +
                ", line='" + line + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
