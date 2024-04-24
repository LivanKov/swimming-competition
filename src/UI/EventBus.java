package UI;

import DataLayer.Competition;
import UI.Frame.MainFrame;
import UI.Panels.APanel;
import UI.Panels.AbstractPanel;

import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private final List<AbstractPanel> panels = new ArrayList<>();

    private Competition competitionObject;

    private MainFrame mainFrameObject;

    public void subscribe(AbstractPanel panel) {
        panels.add(panel);
    }

    public void showFirstPanel(){
        mainFrameObject.switchToFirstPanel();
    }

    public void showSecondPanel() {
        mainFrameObject.switchToSecondPanel();
    }

    public void showThirdPanel() {
        mainFrameObject.switchToThirdPanel();
    }

    public void finishMatching() {
        competitionObject.assignRandomRatings();
        mainFrameObject.switchToFirstPanel();
        for(AbstractPanel p : panels){
            if(p instanceof APanel){
                p.triggerEvent();
            }
        }
    }

    public void printCompetitionResults() {
        TablePrinter.printCompetitionResults(this.competitionObject);
    }

    public void createDefaultCompetition() {
        this.competitionObject = Competition.getDefaultCompetition();
        this.setPrintGreenLight();
    }

    public void setPrintGreenLight() {
        for (AbstractPanel panel : panels) {
            if (panel instanceof APanel) {
                panel.triggerEvent();
            }
        }
    }

    public void resetCompetition(){
        this.competitionObject.reset();
    }

    public Competition getCompetitionObject() {
        return competitionObject;
    }

    public void setCompetitionObject(Competition competitionObject) {
        this.competitionObject = competitionObject;
    }

    public void setMainFrameObject(MainFrame mainFrameObject){ this.mainFrameObject = mainFrameObject; }
}
