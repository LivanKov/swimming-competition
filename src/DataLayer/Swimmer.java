package DataLayer;

public class Swimmer {

    private int yearOfBirth;

    private String name;

    private String nationality;

    private double totalDiveDifficulty;

    private double totalScore;

    public Swimmer(int yearOfBirth, String name, String nationality){
        this.yearOfBirth = yearOfBirth;
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public double getTotalDiveDifficulty() {
        return this.totalDiveDifficulty;
    }

    public void setTotalDiveDifficulty(double totalDiveDifficulty) {
        this.totalDiveDifficulty = totalDiveDifficulty;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public String getNationality() {
        return nationality;
    }
}
