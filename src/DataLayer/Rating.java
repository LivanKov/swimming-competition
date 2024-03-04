package DataLayer;

import java.util.ArrayList;

public class Rating {

    private Swimmer swimmer;
    private Dive dive;

    private ArrayList<Double> scores;

    public Rating(Swimmer swimmer, Dive dive, ArrayList<Double> scores){
        this.swimmer = swimmer;
        this.dive = dive;
        this.scores = scores;
    }

    public Dive getDive() {
        return this.dive;
    }
    public Swimmer getSwimmer() {
        return this.swimmer;
    }

    public double calculateTotalSum(){
        double sum = 0;
        for(int i = 0;i<this.scores.size();i++){
            sum = sum + this.scores.get(i);
        }
        return sum;
    }
}
