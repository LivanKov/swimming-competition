package DataLayer;

import java.util.*;
import java.util.stream.Collectors;

public class Competition {

    private String competitionName;

    private HashMap<String, Swimmer>swimmerNameMap;

    private HashMap<String,Dive> diveIdMap;

    private HashMap<Swimmer,ArrayList<Dive>>swimmerDiveMatching;

    private HashMap<Swimmer,ArrayList<Rating>>swimmerRatingMatch;

    private int numberOfJudges;

    private int divesPerSwimmer;


    public Competition(){
        this.competitionName = "";
        this.swimmerNameMap = new HashMap<String,Swimmer>();
        this.diveIdMap = new HashMap<String,Dive>();
        this.numberOfJudges = 0;
        this.divesPerSwimmer = 0;
        this.swimmerDiveMatching = new HashMap<>();
        this.swimmerRatingMatch = new HashMap<>();
    }
    public void addSwimmer(Swimmer s){
        this.swimmerNameMap.put(s.getName(), s);
    }

    public void addDive(Dive d) throws IllegalArgumentException{
        if(this.diveIdMap.containsKey(d.getDiveId())){
            throw new IllegalArgumentException("Cannot use the same id twice");
        }
        this.diveIdMap.put(d.getDiveId(),d);
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
        return new ArrayList<Dive>(diveIdMap.values());
    }

    public ArrayList<Swimmer> getSwimmers() { return new ArrayList<Swimmer>(swimmerNameMap.values()); }

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

    private void assignDefaultSwimmerDiveMatching(){
        this.swimmerDiveMatching.put(swimmerNameMap.get("Anthony Harding"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("5154B"),
                        this.diveIdMap.get("407C"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("207C"),
                        this.diveIdMap.get("5353B"),
                        this.diveIdMap.get("109C"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("James Heatley"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("205B"),
                        this.diveIdMap.get("407C"),
                        this.diveIdMap.get("5154B"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("5337D"),
                        this.diveIdMap.get("109C"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Andrew John Capobianco"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("5154B"),
                        this.diveIdMap.get("109C"),
                        this.diveIdMap.get("205B"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("5337D"),
                        this.diveIdMap.get("407C"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Jordan Houlden"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("5154B"),
                        this.diveIdMap.get("407C"),
                        this.diveIdMap.get("109C"),
                        this.diveIdMap.get("5337D"),
                        this.diveIdMap.get("207C"),
                        this.diveIdMap.get("307C"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Yona Knight-Wisdom"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("107B"),
                        this.diveIdMap.get("407C"),
                        this.diveIdMap.get("5152B"),
                        this.diveIdMap.get("205B"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("5154B"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Patrick Hausding"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("205B"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("5154B"),
                        this.diveIdMap.get("407C"),
                        this.diveIdMap.get("109C"),
                        this.diveIdMap.get("5156B"),
                }
        )));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Alexis Jandard"),new ArrayList<>(Arrays.asList(
                new Dive[]{
                        this.diveIdMap.get("405B"),
                        this.diveIdMap.get("107B"),
                        this.diveIdMap.get("205B"),
                        this.diveIdMap.get("5337D"),
                        this.diveIdMap.get("307C"),
                        this.diveIdMap.get("5154B"),
                }
        )));
    }

    private void assignDefaultRatings(){
        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Anthony Harding"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 5.5, 6.0, 6.0, 6.0, 6.0, 6.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.5, 5.5, 6.0, 5.5, 5.0, 5.5, 5.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 7.0, 6.0, 7.5, 6.5, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("207C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 6.5, 6.5, 6.5, 6.0, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("5353B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 5.5, 6.5, 6.0, 6.5, 6.5, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("109C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 6.0, 6.0, 7.0, 7.0, 7.0 }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("James Heatley"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("205B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.5, 8.0, 7.5, 7.0, 7.5, 7.5, 7.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.0, 4.5, 4.5, 4.5, 4.0, 4.5, 4.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 6.5, 6.5, 6.0, 6.0, 6.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.0, 6.5, 7.5, 7.0, 7.5, 6.5, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("5337D"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 7.5, 6.5, 7.0, 7.0, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("109C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.5, 6.0, 6.5, 5.0, 5.0, 5.0, 5.0  }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Andrew John Capobianco"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7., 7.0, 6.5, 7.0, 7.0, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("109C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 6.0, 6.0, 5.5, 6.5, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("205B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.0, 5.5, 5.5, 6.0, 5.5, 6.0, 6.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 4.0, 3.5, 3.5, 4.0, 3.0, 4.0, 4.0  }
                        ))),
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("5337D"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.5, 7.0, 7.0, 7.0, 7.5, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.5, 7.0, 7.0, 7.0, 7.5, 7.5   }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Jordan Houlden"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 5.5, 6.0, 6.0, 6.5, 6.5, 6.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 2.0, 2.0, 2.5, 2.0, 2.0, 2.0, 2.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("109C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.5, 7.5, 7.5, 6.5, 7.5, 7.0, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("5337D"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 5.0, 6.5, 6.0, 6.5, 6.5  }
                        ))),
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("207C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 7.0, 8.0, 7.5, 7.5, 8.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 5.5, 5.5, 6.0, 6.0, 6.0 }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Yona Knight-Wisdom"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("107B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 8.0, 8.5, 7.5, 7.5, 8.0, 7.5, 7.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.5, 5.5, 6.0, 5.5, 5.5, 4.5, 5.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("5152B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.5, 8.0, 8.0, 7.5, 7.5, 7.5, 8.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("205B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.5, 7.0, 8.0, 7.0, 7.5, 7.0, 7.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 4.0, 3.5, 3.5, 3.5, 3.0, 3.5, 4.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 6.5, 7.0, 7.0, 6.5, 6.5, 7.0  }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Patrick Hausding"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("205B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 6.5, 7.0, 7.0, 7.0, 7.0  }
                        ))),
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 7.0, 6.0, 6.5, 6.5, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 7.0, 6.5, 6.0, 6.5, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("407C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.5, 7.0, 7.0, 7.0, 6.5, 7.0, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("109C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 4.5, 4.5, 4.5, 5.0, 4.5, 4.5, 5.0  }
                        ))),
                        new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("5156B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 4.0, 4.0, 4.0, 4.5, 4.0, 4.0, 4.5   }
                        )))
                }
        )));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Alexis Jandard"),new ArrayList<>(Arrays.asList(
                new Rating[]{
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("405B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.0, 7.5, 7.5, 7.0, 7.5, 7.0, 7.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("107B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.0, 7.0, 6.5, 6.0, 6.5, 6.0, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("205B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 7.0, 6.5, 7.0, 7.5, 7.0, 6.5, 7.0 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("5337D"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 5.0, 5.0, 4.5, 4.5, 4.0, 5.0, 5.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("307C"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 5.5, 6.0, 6.0, 6.5, 6.0, 6.5 }
                        ))),
                        new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("5154B"),new ArrayList<>(Arrays.asList(
                                new Double[]{ 6.0, 6.5, 6.0, 5.5, 5.5, 5.5, 6.0 }
                        )))
                }
        )));
    }

    public void calculateScore(){
        assignTDD();
        assignTotalScore();
    }

    public void conductCompetition(){

    }

    public void generateRandomJudgeScores(){

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
        this.swimmerNameMap = new HashMap<String,Swimmer>(defaultSwimmerList.stream().collect(Collectors.toMap(Swimmer::getName, x->x)));

        HashSet<Dive> defaultDiveList = new HashSet<Dive>(Arrays.asList(
                new Dive("5154B", 3.4),
                new Dive("407C", 3.4),
                new Dive("307C", 3.5),
                new Dive("207C", 3.6 ),
                new Dive("5353B", 3.3),
                new Dive("109C",3.8),
                new Dive("205B", 3.0),
                new Dive("5337D" ,3.5 ),
                new Dive("205B", 3.0),
                new Dive("107B",3.1),
                new Dive("5152B", 3.0 ),
                new Dive("307C", 3.5),
                new Dive("5156B",3.9),
                new Dive("405B", 3.0)
        ));
        this.diveIdMap = new HashMap<>(defaultDiveList.stream().collect(Collectors.toMap(Dive::getDiveId, x->x)));
        this.assignDefaultSwimmerDiveMatching();
        this.assignDefaultRatings();
        this.calculateScore();
    }
}
