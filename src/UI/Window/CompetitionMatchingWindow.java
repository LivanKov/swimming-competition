package UI.Window;

import DataLayer.Dive;
import DataLayer.Swimmer;
import UI.Component.MatchingComponent;
import UI.EventBus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompetitionMatchingWindow extends JFrame implements CompetitionWindow {

    private static final int WIDTH = 300;

    private static final int HEIGHT = 500;
    private final Map<Swimmer, ArrayList<Dive>> swimmerDiveMap = new HashMap<Swimmer, ArrayList<Dive>>();
    private final ArrayList<MatchingComponent> matchingComponentList;
    private EventBus eventBus;

    public CompetitionMatchingWindow() {
        this.matchingComponentList = new ArrayList<MatchingComponent>();
    }

    public static void main(String[] args) {

    }

    private void initializeMainContainer() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        JPanel mainContainer = new JPanel();
        JPanel labelContainer = new JPanel();
        JPanel buttonContainer = new JPanel();

        JLabel titleLabel = new JLabel("Assign dives to specific players");
        mainContainer.setLayout(new GridLayout(2, 1));
        GridBagConstraints gbcUpper = new GridBagConstraints();
        gbcUpper.gridx = 0;
        gbcUpper.gridy = 0;
        gbcUpper.weighty = 0.1;
        gbcUpper.fill = GridBagConstraints.BOTH;

        GridBagConstraints gbcMiddle = new GridBagConstraints();
        gbcMiddle.gridx = 0;
        gbcMiddle.gridy = 1;
        gbcMiddle.weighty = 0.8;
        gbcMiddle.fill = GridBagConstraints.BOTH;

        GridBagConstraints gbcLower = new GridBagConstraints();
        gbcLower.gridx = 0;
        gbcLower.gridy = 2;
        gbcLower.weighty = 0.1;
        gbcLower.fill = GridBagConstraints.BOTH;

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(e -> {
            for (MatchingComponent m : matchingComponentList) {
                eventBus.getCompetitionObject().addSwimmerDiveMatch(m.getSwimmer(), new ArrayList<>(m.getSelectedDives()));
                System.out.println("Added match between " + m.getSwimmer().getName() + " and " + m.getSelectedDives());
            }
            eventBus.finishMatching();
        });
        labelContainer.add(titleLabel);
        buttonContainer.add(finishButton);
        this.add(labelContainer, gbcUpper);
        this.add(mainContainer, gbcMiddle);
        this.add(buttonContainer, gbcLower);


        ArrayList<Dive> diveList = this.eventBus.getCompetitionObject().getDives();
        for (Swimmer s : this.eventBus.getCompetitionObject().getSwimmers()) {
            System.out.println("Swimmer");
            MatchingComponent matchComp = new MatchingComponent(s, diveList);
            matchingComponentList.add(matchComp);
            mainContainer.add(matchComp);
        }
    }

    @Override
    public void triggerEvent() {
    }

    @Override
    public void addEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.subscribe(this);
    }

    @Override
    public void start() {
        this.initializeMainContainer();
    }

    @Override
    public void exit() {
        this.dispose();
    }
}
