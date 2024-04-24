package UI.Panels;

import UI.EventBus;

public interface CompetitionWindow {

    void triggerEvent();

    void addEventBus(EventBus eventBus);

    void start();

    void exit();
}
