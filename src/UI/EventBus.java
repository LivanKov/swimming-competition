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

    public void showMatchCreatorWindow() {
        mainFrameObject.switchToSecondPanel();
    }

    public void showMatchingWindow() {
        mainFrameObject.switchToThirdPanel();
    }

    public void finishMatching() {
        competitionObject.assignRandomRatings();
        for (AbstractPanel panel : panels) {
            mainFrameObject.switchToFirstPanel();
            if (panel instanceof APanel) {
                panel.triggerEvent();
            }
        }
    }

    public void printCompetitionResults() {
        TablePrinter.printCompetitionResults(this.competitionObject);
    }

    public void createDefaultCompetition() {
        System.out.println("Default competition created");
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

    public Competition getCompetitionObject() {
        return competitionObject;
    }

    public void setCompetitionObject(Competition competitionObject) {
        this.competitionObject = competitionObject;
    }

    public void setMainFrameObject(MainFrame mainFrameObject){ this.mainFrameObject = mainFrameObject; }
}
