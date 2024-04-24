package UI.Panels;

import UI.EventBus;

public interface AbstractPanel {

    void triggerEvent();

    void addEventBus(EventBus eventBus);

    void start();
}
