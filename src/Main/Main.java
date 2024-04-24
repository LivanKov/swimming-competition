package Main;

import DataLayer.Competition;
import UI.EventBus;
import UI.Frame.MainFrame;
import UI.Panels.APanel;
import UI.Panels.BPanel;
import UI.Panels.CPanel;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        Competition competition = new Competition();
        EventBus eventBus = new EventBus();
        eventBus.setCompetitionObject(competition);
        eventBus.setMainFrameObject(frame);
        APanel firstPanel = new APanel();
        BPanel secondPanel = new BPanel();
        CPanel thirdPanel = new CPanel();
        firstPanel.addEventBus(eventBus);
        secondPanel.addEventBus(eventBus);
        thirdPanel.addEventBus(eventBus);
        frame.addPanel(firstPanel);
        frame.addPanel(secondPanel);
        frame.addPanel(thirdPanel);
        frame.init();
    }
}