package Main;

import DataLayer.Competition;
import UI.EventBus;
import UI.Window.CompetitionCreationWindow;
import UI.Window.CompetitionMatchingWindow;
import UI.Window.CompetitionStartingWindow;

public class Main {
    public static void main(String[] args) {
        Competition competition = new Competition();
        EventBus eventBus = new EventBus();
        CompetitionStartingWindow startingWindow = new CompetitionStartingWindow();
        CompetitionCreationWindow creationWindow = new CompetitionCreationWindow();
        CompetitionMatchingWindow matchingWindow = new CompetitionMatchingWindow();
        startingWindow.addEventBus(eventBus);
        creationWindow.addEventBus(eventBus);
        matchingWindow.addEventBus(eventBus);
    }
}