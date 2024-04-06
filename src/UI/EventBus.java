package UI;

import UI.Window.CompetitionCreationWindow;
import UI.Window.CompetitionMatchingWindow;
import UI.Window.CompetitionStartingWindow;
import UI.Window.CompetitionWindow;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private List<CompetitionWindow>frameList = new ArrayList<>();

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
        for(CompetitionWindow frame : frameList){
            if(frame instanceof CompetitionCreationWindow){
                frame.exit();
            }
            if(frame instanceof CompetitionMatchingWindow){
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


}
