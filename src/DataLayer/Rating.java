package DataLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rating {

    private Swimmer swimmer;
    private Dive dive;
    private ArrayList<Double> scores;
    private double PT;

    public Rating(Swimmer s, Dive dive, ArrayList<Double> scores){
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

    public double calculatePT(){
        int maxIndex = 0;
        for(int i = 0; i<scores.size();i++){
            if(scores.get(i)>scores.get(maxIndex)){
                maxIndex = i;
            }
        }
        scores.remove(maxIndex);

        int minIndex = 0;
        for(int i = 0; i<scores.size();i++){
            if(scores.get(i)<scores.get(minIndex)){
                minIndex = i;
            }
        }
        scores.remove(minIndex);

        System.out.println(scores.toString());
        this.PT = scores.stream().mapToDouble(Double::doubleValue).sum()*Double.valueOf(dive.getDifficulty())*0.6;
        return this.PT;
    }

    public List<Double> getScores(){
        return this.scores;
    }

    public void setScores(List<Double>scores){
        this.scores.stream().forEach(this.scores::add);
    }

    public void generateScores(){
        this.scores.addAll(IntStream.range(0,7).mapToObj(x -> Math.ceil(Math.random()*10)).collect(Collectors.toList()));
    }

    public String toString(){
        return "[ Swimmer: " +
                this.swimmer.toString()+"\n"+
                "Dive" +
                this.dive.toString()+"\n"+
                " ]";
    }
}
