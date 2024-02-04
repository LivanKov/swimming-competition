package DataLayer;

import DataLayer.Host.Judge;

import java.text.DecimalFormat;

public class Rating {

    private Judge judge;
    private Swimmer swimmer;
    private Dive dive;

    private final double SCORE;

    public Rating(Judge judge, Swimmer swimmer, Dive dive){
        this.judge = judge;
        this.swimmer = swimmer;
        this.dive = dive;
        DecimalFormat df = new DecimalFormat("#.#");
        String formattedValue = df.format(Math.random()*10);
        this.SCORE = Double.parseDouble(formattedValue);
    }

    public Dive getDive() {
        return dive;
    }

    public double getSCORE() {
        return SCORE;
    }

    public Judge getJudge() {
        return judge;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }
}
