package UI.Window;

import DataLayer.Competition;
import UI.EventBus;

public interface CompetitionWindow {

    void triggerEvent();

    void addEventBus(EventBus eventBus);

    void setCompetition(Competition comp);

    void start();

    void exit();
}
