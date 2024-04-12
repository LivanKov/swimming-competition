package UI;

import DataLayer.Competition;
import UI.Window.CompetitionCreationWindow;
import UI.Window.CompetitionMatchingWindow;
import UI.Window.CompetitionStartingWindow;
import UI.Window.CompetitionWindow;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private List<CompetitionWindow>frameList = new ArrayList<>();

    private Competition competitionObject;

    public void subscribe(CompetitionWindow frame){
        frameList.add(frame);
    }

    public void notifyBaseWindow(){
        for(CompetitionWindow frame : frameList){
            if(frame instanceof CompetitionStartingWindow){
                frame.triggerEvent();
            }
        }
    }

    public void showMatchCreatorWindow(){
        for(CompetitionWindow frame : frameList){
            if(frame instanceof CompetitionCreationWindow){
                frame.start();
            }
        }
    }

    public void showMatchingWindow(){
        System.out.println("Event triggered");
        for(CompetitionWindow frame : frameList){
            if(frame instanceof CompetitionCreationWindow){
                System.out.println("Exit");
                frame.exit();
            }
            if(frame instanceof CompetitionMatchingWindow){
                System.out.println("Start");
                frame.start();
            }
        }
    }

    public void finishMatching(){
        for(CompetitionWindow frame : frameList){
            if(frame instanceof CompetitionMatchingWindow){
                frame.exit();
            }
            if(frame instanceof CompetitionStartingWindow){
                frame.triggerEvent();
            }
        }
    }

    public void setCompetitionObject(Competition competitionObject) {
        this.competitionObject = competitionObject;
    }

    public Competition getCompetitionObject() {
        return competitionObject;
    }
}
