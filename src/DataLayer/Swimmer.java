package DataLayer;

public class Swimmer {

    private int yearOfBirth;

    private String name;

    private double TDD;
    private double totalPoints;

    public Swimmer(String name, int yearOfBirth){
        this.yearOfBirth = yearOfBirth;
        this.name = name;
    }

    public Swimmer(){}

    public String getName() {
        return name;
    }

    public double getTDD() {
        return TDD;
    }

    public double getTotalPoints() {
        return totalPoints;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTDD(double TDD) {
        this.TDD = TDD;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getTotalScore(){
        return this.totalPoints;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
