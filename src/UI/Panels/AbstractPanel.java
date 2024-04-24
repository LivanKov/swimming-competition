package UI.Panels;

import UI.EventBus;

import javax.swing.*;

public abstract class AbstractPanel extends JPanel {

    boolean isInitialized = false;

    public abstract void triggerEvent();

    public abstract void addEventBus(EventBus eventBus);

    public abstract void init();

    public boolean isInitialized(){
        return isInitialized;
    }

    public abstract void refresh();


}
