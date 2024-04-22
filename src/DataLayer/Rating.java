package DataLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rating {

    private final Swimmer swimmer;
    private final Dive dive;
    private final ArrayList<Double> scores;
    private double PT;

    public Rating(Swimmer s, Dive dive, ArrayList<Double> scores) {
        this.swimmer = s;
        this.dive = dive;
        this.scores = scores;
        this.PT = 0;
    }

    public double getPT() {
        return PT;
    }

    public Dive getDive() {
        return this.dive;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public double calculatePT() {
        int maxIndex = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > scores.get(maxIndex)) {
                maxIndex = i;
            }
        }

        int minIndex = 0;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) < scores.get(minIndex)) {
                minIndex = i;
            }
        }
        double savedMinIndex = scores.get(minIndex);
        double savedMaxIndex = scores.get(maxIndex);
        scores.set(minIndex, Double.MIN_VALUE);
        scores.set(maxIndex, Double.MAX_VALUE);
        this.PT = scores.stream()
                .filter(i -> i != Double.MIN_VALUE && i != Double.MAX_VALUE)
                .mapToDouble(Double::doubleValue)
                .sum() * Double.valueOf(dive.getDifficulty()) * 0.6;
        scores.set(maxIndex, savedMaxIndex);
        scores.set(minIndex, savedMinIndex);
        return this.PT;
    }

    public List<Double> getScores() {
        return this.scores;
    }

    public void setScores(List<Double> scores) {
        this.scores.stream().forEach(this.scores::add);
    }

    public void generateScores() {
        this.scores.addAll(IntStream.range(0, 7).mapToObj(x -> Math.ceil(Math.random() * 10)).collect(Collectors.toList()));
    }

    public String toString() {
        return "[ Swimmer: " +
                this.swimmer.toString() + "\n" +
                "Dive" +
                this.dive.toString() + "\n" +
                " ]";
    }
}
