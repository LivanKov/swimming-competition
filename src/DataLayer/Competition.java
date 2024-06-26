package DataLayer;

import java.util.*;
import java.util.stream.Collectors;

public class Competition {

    private final HashMap<String, Swimmer> swimmerNameMap;
    private final HashMap<String, Dive> diveIdMap;
    private final HashMap<Swimmer, ArrayList<Dive>> swimmerDiveMatching;
    private final HashMap<Swimmer, ArrayList<Rating>> swimmerRatingMatch;
    private String competitionName;

    public Competition() {
        this.competitionName = "";
        this.swimmerNameMap = new HashMap<>();
        this.diveIdMap = new HashMap<>();
        this.swimmerDiveMatching = new HashMap<>();
        this.swimmerRatingMatch = new HashMap<>();
    }

    //sets All values to default
    public static Competition getDefaultCompetition() {
        Competition defaultCompetition = new Competition();
        defaultCompetition.setCompetitionName("Default Competition");
        List<Swimmer> defaultSwimmerList = Arrays.asList(new Swimmer("Anthony Harding", 2001, "GBR"),
                new Swimmer("James Heatley", 1997, "GBR"),
                new Swimmer("Andrew John Capobianco", 1999, "USA"),
                new Swimmer("Jordan Houlden", 1998, "GBR"),
                new Swimmer("Yona Knight-Wisdom", 1995, "JAM"),
                new Swimmer("Patrick Hausding", 1989, "GER"),
                new Swimmer("Alexis Jandard", 1997, "FRA"));
        defaultSwimmerList.forEach(defaultCompetition::addSwimmer);
        HashSet<Dive> defaultDiveList = new HashSet<>(Arrays.asList(
                new Dive("5154B", 3.4),
                new Dive("407C", 3.4),
                new Dive("307C", 3.5),
                new Dive("207C", 3.6),
                new Dive("5353B", 3.3),
                new Dive("109C", 3.8),
                new Dive("5337D", 3.5),
                new Dive("205B", 3.0),
                new Dive("107B", 3.1),
                new Dive("5152B", 3.0),
                new Dive("5156B", 3.9),
                new Dive("405B", 3.0)
        ));
        defaultDiveList.forEach(defaultCompetition::addDive);
        defaultCompetition.assignDefaultSwimmerDiveMatching();
        defaultCompetition.assignDefaultRatings();
        defaultCompetition.calculateScore();
        return defaultCompetition;
    }

    public void addSwimmer(Swimmer s) {
        this.swimmerNameMap.put(s.getName(), s);
    }

    public void addDive(Dive d) throws IllegalArgumentException {
        if (this.diveIdMap.containsKey(d.getDiveId())) {
            throw new IllegalArgumentException("Cannot use the same id twice! Faulty Id: " + d.getDiveId());
        }
        this.diveIdMap.put(d.getDiveId(), d);
    }

    public void assignRandomRatings() {
        for (Swimmer s : this.swimmerDiveMatching.keySet()) {
            this.swimmerRatingMatch.put(s, new ArrayList<>());
            for (Dive d : this.swimmerDiveMatching.get(s)) {
                ArrayList<Double> ratingList = new Random().doubles(7, 0.0, 10.0)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
                Rating newRating = new Rating(s, d, ratingList);
                swimmerRatingMatch.get(s).add(newRating);
            }
        }
        this.calculateScore();
    }

    public void reset() {
        this.swimmerNameMap.clear();
        this.diveIdMap.clear();
        this.swimmerDiveMatching.clear();
        this.swimmerRatingMatch.clear();
        this.competitionName = "";
    }

    public void addSwimmerDiveMatch(Swimmer s, ArrayList<Dive> diveList) {
        swimmerDiveMatching.computeIfAbsent(s, k -> diveList);
    }

    public ArrayList<Dive> getDives() {
        return new ArrayList<>(diveIdMap.values());
    }

    public ArrayList<Swimmer> getSwimmers() {
        return new ArrayList<>(swimmerNameMap.values());
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public ArrayList<Rating> getRatingsForSwimmer(Swimmer s) {
        return this.swimmerRatingMatch.get(s);
    }

    private void assignTDD() {
        for (Swimmer s : this.swimmerDiveMatching.keySet()) {
            s.setTDD(this.swimmerDiveMatching.get(s).stream().mapToDouble(Dive::getDifficulty).sum());
        }
    }

    private void assignTotalScore() {
        for (Swimmer s : this.swimmerRatingMatch.keySet()) {
            s.setTotalPoints(this.swimmerRatingMatch.get(s).stream().mapToDouble(Rating::calculatePT).sum());
        }
    }

    public HashMap<String, Swimmer> getSwimmerNameMap() {
        return swimmerNameMap;
    }

    public HashMap<Swimmer, ArrayList<Dive>> getSwimmerDiveMatching() {
        return swimmerDiveMatching;
    }

    public HashMap<Swimmer, ArrayList<Rating>> getSwimmerRatingMatch() {
        return swimmerRatingMatch;
    }

    private void assignDefaultSwimmerDiveMatching() {
        this.swimmerDiveMatching.put(swimmerNameMap.get("Anthony Harding"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("5154B"),
                this.diveIdMap.get("407C"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("207C"),
                this.diveIdMap.get("5353B"),
                this.diveIdMap.get("109C"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("James Heatley"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("205B"),
                this.diveIdMap.get("407C"),
                this.diveIdMap.get("5154B"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("5337D"),
                this.diveIdMap.get("109C"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Andrew John Capobianco"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("5154B"),
                this.diveIdMap.get("109C"),
                this.diveIdMap.get("205B"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("5337D"),
                this.diveIdMap.get("407C"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Jordan Houlden"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("5154B"),
                this.diveIdMap.get("407C"),
                this.diveIdMap.get("109C"),
                this.diveIdMap.get("5337D"),
                this.diveIdMap.get("207C"),
                this.diveIdMap.get("307C"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Yona Knight-Wisdom"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("107B"),
                this.diveIdMap.get("407C"),
                this.diveIdMap.get("5152B"),
                this.diveIdMap.get("205B"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("5154B"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Patrick Hausding"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("205B"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("5154B"),
                this.diveIdMap.get("407C"),
                this.diveIdMap.get("109C"),
                this.diveIdMap.get("5156B"))));

        this.swimmerDiveMatching.put(swimmerNameMap.get("Alexis Jandard"), new ArrayList<>(Arrays.asList(
                this.diveIdMap.get("405B"),
                this.diveIdMap.get("107B"),
                this.diveIdMap.get("205B"),
                this.diveIdMap.get("5337D"),
                this.diveIdMap.get("307C"),
                this.diveIdMap.get("5154B"))));
    }

    private void assignDefaultRatings() {
        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Anthony Harding"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.0, 5.5, 6.0, 6.0, 6.0, 6.0, 6.0))),
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        5.5, 5.5, 6.0, 5.5, 5.0, 5.5, 5.5))),
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 7.0, 6.0, 7.5, 6.5, 7.0))),
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("207C"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 6.5, 6.5, 6.5, 6.0, 6.5))),
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("5353B"), new ArrayList<>(Arrays.asList(
                        6.5, 5.5, 6.5, 6.0, 6.5, 6.5, 6.5))),
                new Rating(this.swimmerNameMap.get("Anthony Harding"), this.diveIdMap.get("109C"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 6.0, 6.0, 7.0, 7.0, 7.0))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("James Heatley"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("205B"), new ArrayList<>(Arrays.asList(
                        7.5, 8.0, 7.5, 7.0, 7.5, 7.5, 7.5))),
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        5.0, 4.5, 4.5, 4.5, 4.0, 4.5, 4.5))),
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 6.5, 6.5, 6.0, 6.0, 6.0))),
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        7.0, 6.5, 7.5, 7.0, 7.5, 6.5, 7.0))),
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("5337D"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 7.5, 6.5, 7.0, 7.0, 7.0))),
                new Rating(this.swimmerNameMap.get("James Heatley"), this.diveIdMap.get("109C"), new ArrayList<>(Arrays.asList(
                        5.5, 6.0, 6.5, 5.0, 5.0, 5.0, 5.0))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Andrew John Capobianco"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.5, 7., 7.0, 6.5, 7.0, 7.0, 7.0))),
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("109C"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 6.0, 6.0, 5.5, 6.5, 7.0))),
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("205B"), new ArrayList<>(Arrays.asList(
                        5.0, 5.5, 5.5, 6.0, 5.5, 6.0, 6.0))),
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        4.0, 3.5, 3.5, 4.0, 3.0, 4.0, 4.0))),
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("5337D"), new ArrayList<>(Arrays.asList(
                        6.5, 7.5, 7.0, 7.0, 7.0, 7.5, 7.0))),
                new Rating(this.swimmerNameMap.get("Andrew John Capobianco"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        6.5, 7.5, 7.0, 7.0, 7.0, 7.5, 7.5))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Jordan Houlden"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.0, 5.5, 6.0, 6.0, 6.5, 6.5, 6.0))),
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        2.0, 2.0, 2.5, 2.0, 2.0, 2.0, 2.5))),
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("109C"), new ArrayList<>(Arrays.asList(
                        7.5, 7.5, 7.5, 6.5, 7.5, 7.0, 7.0))),
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("5337D"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 5.0, 6.5, 6.0, 6.5, 6.5))),
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("207C"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 7.0, 8.0, 7.5, 7.5, 8.0))),
                new Rating(this.swimmerNameMap.get("Jordan Houlden"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 5.5, 5.5, 6.0, 6.0, 6.0))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Yona Knight-Wisdom"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("107B"), new ArrayList<>(Arrays.asList(
                        8.0, 8.5, 7.5, 7.5, 8.0, 7.5, 7.5))),
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        5.5, 5.5, 6.0, 5.5, 5.5, 4.5, 5.0))),
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("5152B"), new ArrayList<>(Arrays.asList(
                        7.5, 8.0, 8.0, 7.5, 7.5, 7.5, 8.0))),
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("205B"), new ArrayList<>(Arrays.asList(
                        7.5, 7.0, 8.0, 7.0, 7.5, 7.0, 7.5))),
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        4.0, 3.5, 3.5, 3.5, 3.0, 3.5, 4.0))),
                new Rating(this.swimmerNameMap.get("Yona Knight-Wisdom"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.5, 6.5, 7.0, 7.0, 6.5, 6.5, 7.0))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Patrick Hausding"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("205B"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 6.5, 7.0, 7.0, 7.0, 7.0))),
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 7.0, 6.0, 6.5, 6.5, 6.5))),
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 7.0, 6.5, 6.0, 6.5, 6.5))),
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("407C"), new ArrayList<>(Arrays.asList(
                        6.5, 7.0, 7.0, 7.0, 6.5, 7.0, 7.0))),
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("109C"), new ArrayList<>(Arrays.asList(
                        4.5, 4.5, 4.5, 5.0, 4.5, 4.5, 5.0))),
                new Rating(this.swimmerNameMap.get("Patrick Hausding"), this.diveIdMap.get("5156B"), new ArrayList<>(Arrays.asList(
                        4.0, 4.0, 4.0, 4.5, 4.0, 4.0, 4.5))))));

        this.swimmerRatingMatch.put(this.swimmerNameMap.get("Alexis Jandard"), new ArrayList<>(Arrays.asList(
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("405B"), new ArrayList<>(Arrays.asList(
                        7.0, 7.5, 7.5, 7.0, 7.5, 7.0, 7.5))),
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("107B"), new ArrayList<>(Arrays.asList(
                        7.0, 7.0, 6.5, 6.0, 6.5, 6.0, 6.5))),
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("205B"), new ArrayList<>(Arrays.asList(
                        7.0, 6.5, 7.0, 7.5, 7.0, 6.5, 7.0))),
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("5337D"), new ArrayList<>(Arrays.asList(
                        5.0, 5.0, 4.5, 4.5, 4.0, 5.0, 5.5))),
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("307C"), new ArrayList<>(Arrays.asList(
                        6.0, 5.5, 6.0, 6.0, 6.5, 6.0, 6.5))),
                new Rating(this.swimmerNameMap.get("Alexis Jandard"), this.diveIdMap.get("5154B"), new ArrayList<>(Arrays.asList(
                        6.0, 6.5, 6.0, 5.5, 5.5, 5.5, 6.0))))));
    }

    public void calculateScore() {
        assignTDD();
        assignTotalScore();
    }
}
