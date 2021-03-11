package cat.xojan.kafkademo.model;

public class Properties {
    private String id;
    private String lin;
    private String nc;
    private String dir;
    private String en;
    private String cv;
    private String origen;
    private String desti;
    private boolean en_hora;
    private String retard;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLin(String lin) {
        this.lin = lin;
    }

    public String getLin() {
        return lin;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDesti(String desti) {
        this.desti = desti;
    }

    public void setEn_hora(boolean en_hora) {
        this.en_hora = en_hora;
    }

    public void setRetard(String retard) {
        this.retard = retard;
    }

    @Override
    public String toString() {
        return "TrainProperties{" +
                "id='" + id + '\'' +
                ", lin='" + lin + '\'' +
                ", nc='" + nc + '\'' +
                ", dir='" + dir + '\'' +
                ", en='" + en + '\'' +
                ", cv='" + cv + '\'' +
                ", origen='" + origen + '\'' +
                ", desti='" + desti + '\'' +
                ", en_hora=" + en_hora +
                ", retard='" + retard + '\'' +
                '}';
    }
}
