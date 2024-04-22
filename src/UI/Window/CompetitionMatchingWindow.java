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

    private static final int WIDTH = 500;

    private static final int HEIGHT = 500;

    private final Map<Swimmer, ArrayList<Dive>> swimmerDiveMap = new HashMap<Swimmer, ArrayList<Dive>>();
    private final ArrayList<MatchingComponent> matchingComponentList;
    private EventBus eventBus;

    public CompetitionMatchingWindow() {
        this.matchingComponentList = new ArrayList<MatchingComponent>();
    }

    private void initializeMainContainer() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        JPanel mainContainer = new JPanel();
        JPanel labelContainer = new JPanel();
        JPanel buttonContainer = new JPanel();

        JScrollPane mainContainerScrollPane = new JScrollPane(mainContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JLabel titleLabel = new JLabel("Assign dives to specific players");
        GridBagConstraints gbcUpper = new GridBagConstraints();
        gbcUpper.gridy = 0;
        gbcUpper.weighty = 0.1;
        gbcUpper.weightx = 1.0;
        gbcUpper.fill = GridBagConstraints.BOTH;

        GridBagConstraints gbcMiddle = new GridBagConstraints();
        gbcMiddle.gridy = 1;
        gbcMiddle.weighty = 0.8;
        gbcUpper.weightx = 1.0;
        gbcMiddle.fill = GridBagConstraints.BOTH;

        GridBagConstraints gbcLower = new GridBagConstraints();
        gbcLower.gridy = 2;
        gbcLower.weighty = 0.1;
        gbcUpper.weightx = 1.0;
        gbcLower.fill = GridBagConstraints.BOTH;

        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(e -> {
            for (MatchingComponent m : matchingComponentList) {
                eventBus.getCompetitionObject().addSwimmerDiveMatch(m.getSwimmer(), new ArrayList<>(m.getSelectedDives()));
                System.out.println("Added match between " + m.getSwimmer() + " and " + m.getSelectedDives());
            }
            eventBus.finishMatching();
        });
        labelContainer.add(titleLabel);
        buttonContainer.add(finishButton);
        this.add(labelContainer, gbcUpper);
        this.add(mainContainerScrollPane, gbcMiddle);
        this.add(buttonContainer, gbcLower);


        if(this.eventBus.getCompetitionObject().getSwimmers().size()>4){
            mainContainer.setLayout(new GridLayout(this.eventBus.getCompetitionObject().getSwimmers().size(), 1));
        }else{
            mainContainer.setLayout(new GridLayout(4,1));
        }
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
