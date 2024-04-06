package DataLayer;

public class Dive {

    private String diveId;

    private double difficulty;


    public Dive(String diveId, double difficulty){
        this.diveId = diveId;
        this.difficulty = difficulty;
    }

    public Dive(){};

    public double getDifficulty() {
        return difficulty;
    }

    public String getDiveId() {
        return diveId;
    }

    public void setDiveId(String diveId) {
        this.diveId = diveId;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
}
