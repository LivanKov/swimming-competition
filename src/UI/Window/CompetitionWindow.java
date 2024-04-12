package UI.Window;

import UI.EventBus;

public interface CompetitionWindow {

    void triggerEvent();

    void addEventBus(EventBus eventBus);

    void start();

    void exit();
}
