package DataLayer.Host;

import DataLayer.Dive;
import DataLayer.Rating;
import DataLayer.Swimmer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Competition {


    private final String COMPETITION_NAME;

    private ArrayList<Swimmer> participants;
    private ArrayList<Dive> dives;

    private HashSet<String> nationalities;

    protected ArrayList<Rating> ratings;


    public Competition(String compName){
        this.COMPETITION_NAME = compName;
        this.participants = new ArrayList<>();
        this.dives = new ArrayList<>();
        this.nationalities = new HashSet<>();
    }

    void addParticipants(List<Swimmer> swimmerList){
        for(Swimmer s : swimmerList){
            participants.add(s);
        }
    }

    void addParticipant(Swimmer s){
        participants.add(s);
    }

    void addDives(List<Dive> dives){
        for(Dive d : dives){
            dives.add(d);
        }
    }

    void addNationality(String nat){
        nationalities.add(nat);
    }

    public String getName() {
        return COMPETITION_NAME;
    }
}
