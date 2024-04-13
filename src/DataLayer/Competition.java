package DataLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Competition {

    private String competitionName;

    private ArrayList<Swimmer>swimmers;

    private ArrayList<Dive> dives;

    private HashMap<Swimmer,ArrayList<Dive>>swimmerDiveMatching;

    private HashMap<Swimmer,ArrayList<Rating>>swimmerRatingMatch;

    private int numberOfJudges;

    private int divesPerSwimmer;


    public Competition(){
        this.competitionName = "";
        this.swimmers = new ArrayList<>();
        this.dives = new ArrayList<>();
        this.numberOfJudges = 0;
        this.divesPerSwimmer = 0;
        this.swimmerDiveMatching = new HashMap<>();
        this.swimmerRatingMatch = new HashMap<>();
    }
    public void addSwimmer(Swimmer s){
        this.swimmers.add(s);
    }

    public void addDive(Dive d){
        this.dives.add(d);
    }

    public String getName() {
        return this.competitionName;
    }

    public void addSwimmerDiveMatch(Swimmer s, ArrayList<Dive>diveList){
        swimmerDiveMatching.computeIfAbsent(s, k -> diveList);
    }

    public void addSwimmerRatingMatch(Swimmer s,Rating r){
        swimmerRatingMatch.computeIfAbsent(s, k -> new ArrayList<>()).add(r);
    }

    public void setNumberOfJudges(int numberOfJudges) {
        this.numberOfJudges = numberOfJudges;
    }

    public void setDivesPerSwimmer(int divesPerSwimmer) {
        this.divesPerSwimmer = divesPerSwimmer;
    }

    public int getDivesPerSwimmer() {
        return divesPerSwimmer;
    }

    public int getNumberOfJudges() {
        return numberOfJudges;
    }

    public ArrayList<Dive> getDives() {
        return dives;
    }

    public ArrayList<Swimmer> getSwimmers() {
        return swimmers;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public ArrayList<Rating> getRatingsForSwimmer(Swimmer s){
        return this.swimmerRatingMatch.get(s);
    }

    private void assignTDD(){
        for(Swimmer s : this.swimmerDiveMatching.keySet()){
            s.setTDD(this.swimmerDiveMatching.get(s).stream().mapToDouble(x -> Double.valueOf(x.getDifficulty())).sum());
        }
    }

    private void assignTotalScore(){
        for(Swimmer s : this.swimmerRatingMatch.keySet()){
            s.setTotalPoints(this.swimmerRatingMatch.get(s).stream().mapToDouble(x -> x.calculatePT()).sum());
        }
    }

    public void calculateScore(){
        assignTDD();
        assignTotalScore();
    }

    public void conductCompetition(){

    }

    //sets All values to default
    public void conductDefaultCompetition(){
        List<Swimmer> defaultSwimmerList = Arrays.asList(new Swimmer[]{
                new Swimmer("Anthony Harding",2001,"GBR"),
                new Swimmer("James Heatley", 1997,"GBR"),
                new Swimmer("Andrew John Capobianco", 1999,"USA"),
                new Swimmer("Jordan Houlden", 1998, "GBR"),
                new Swimmer("Yona Knight-Wisdom",1995,"JAM"),
                new Swimmer("Patrick Hausding", 1989,"GER"),
                new Swimmer("Alexis Jandard", 1997,"FRA")
        });
        this.swimmers = new ArrayList<>(defaultSwimmerList);


    }

    public static void main(String[]args){
        /*Competition testCompetition = new Competition();
        testCompetition.setCompetitionName("Test");
        testCompetition.setDivesPerSwimmer(6);
        testCompetition.setNumberOfJudges(7);
        //Swimmer testSwimmer = new Swimmer("Anthony Harding",2001);
        Dive d1 = new Dive("5154B",3.4);
        Dive d2 = new Dive("407C",3.4);
        Dive d3 = new Dive("307C",3.5);
        Dive d4 = new Dive("207C",3.6);
        Dive d5 = new Dive("5353B",3.3);
        Dive d6 = new Dive("109C",3.8);
        /*Rating r1 = new Rating(testSwimmer,d1,new ArrayList<>(Arrays.asList(6.0, 5.5, 6.0, 6.0, 6.0, 6.0, 6.0)));
        Rating r2 = new Rating(testSwimmer,d2,new ArrayList<>(Arrays.asList(5.5, 5.5, 6.0, 5.5, 5.0, 5.5, 5.5)));
        Rating r3 = new Rating(testSwimmer,d3,new ArrayList<>(Arrays.asList(6.5, 7.0, 7.0, 6.0, 7.5, 6.5, 7.0)));
        Rating r4 = new Rating(testSwimmer,d4,new ArrayList<>(Arrays.asList(6.0, 6.5, 6.5, 6.5, 6.5, 6.0, 6.5)));
        Rating r5 = new Rating(testSwimmer,d5,new ArrayList<>(Arrays.asList(6.5, 5.5, 6.5, 6.0, 6.5, 6.5, 6.5)));
        Rating r6 = new Rating(testSwimmer,d6,new ArrayList<>(Arrays.asList(6.5, 7.0, 6.0, 6.0, 7.0, 7.0, 7.0)));

        testCompetition.addSwimmerDiveMatch(testSwimmer,new ArrayList<>(Arrays.asList(d1, d2, d3, d4, d5, d6)));
        testCompetition.addSwimmerRatingMatch(testSwimmer,r1);
        testCompetition.addSwimmerRatingMatch(testSwimmer,r2);
        testCompetition.addSwimmerRatingMatch(testSwimmer,r3);
        testCompetition.addSwimmerRatingMatch(testSwimmer,r4);
        testCompetition.addSwimmerRatingMatch(testSwimmer,r5);
        testCompetition.addSwimmerRatingMatch(testSwimmer,r6);
        testCompetition.calculateScore();
        for(Rating r : testCompetition.getRatingsForSwimmer(testSwimmer)){
            System.out.println(r.getPT());
        }
        System.out.println(testSwimmer.getTotalPoints());
        System.out.println(testSwimmer.getTDD());*/
    }
}
