package Main;

import DataLayer.Competition;
import UI.EventBus;
import UI.Window.CompetitionStartingWindow;
import UI.Window.CompetitionCreationWindow;

public class Main {
    public static void main(String[] args) {
        //Easter egg
        Competition competition = new Competition();
        EventBus eventBus = new EventBus();
        CompetitionStartingWindow wrapper = new CompetitionStartingWindow();
        CompetitionCreationWindow matchCreatorFrame = new CompetitionCreationWindow();
        //PlayerDiveMatchingWindow matchingWindow = new PlayerDiveMatchingWindow();

        wrapper.addEventBus(eventBus);
        matchCreatorFrame.addEventBus(eventBus);
        //matchingWindow.addEventBus(eventBus);

        wrapper.setCompetition(competition);
        matchCreatorFrame.setCompetition(competition);
        //matchingWindow.setCompetition(competition);

    }
}