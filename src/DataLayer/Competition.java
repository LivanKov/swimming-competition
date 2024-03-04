package DataLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class Competition {

    private String competitionName;

    private ArrayList<Swimmer>swimmers;
    private ArrayList<Dive> dives;

    private HashMap<Swimmer,ArrayList<Rating>> swimmerRatings;

    private int x;

    public Competition(String compName){
        this.competitionName = compName;
        this.swimmers = new ArrayList<>();
        this.dives = new ArrayList<>();
        this.swimmerRatings = new HashMap<>();
    }
    void addSwimmer(Swimmer s){
        this.swimmers.add(s);
    }

    void addDive(Dive d){
        this.dives.add(d);
    }

    public String getName() {
        return this.competitionName;
    }


    public void addJumpRating(Swimmer s,Dive d, ArrayList<Double>scores){
        Rating newRating = new Rating(s,d,scores);
        this.swimmerRatings.get(s).add(newRating);
        s.setTotalScore(s.getTotalScore() + newRating.calculateTotalSum());
        s.setTotalDiveDifficulty(s.getTotalDiveDifficulty() + d.getDifficulty());
    }


}
