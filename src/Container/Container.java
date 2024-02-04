package Container;

import DataLayer.Host.Competition;

public class Container {

    private Competition competition;

    public void createCompetition(String competitionName){
        this.competition = new Competition(competitionName);
    }

    public Competition getCompetition() {
        return this.competition;
    }
}
