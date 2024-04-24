package UI.Panels;

import DataLayer.Dive;
import DataLayer.Swimmer;
import UI.Component.MatchingComponent;
import UI.EventBus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CPanel extends AbstractPanel {

    private static final int WIDTH = 500;

    private static final int HEIGHT = 500;

    private final ArrayList<MatchingComponent> matchingComponentList = new ArrayList<MatchingComponent>();
    private EventBus eventBus;

    @Override
    public void init() {
        isInitialized = true;
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

        JButton finishButton = new JButton("Next");
        JButton goBackButton = new JButton("Back");
        finishButton.addActionListener(e -> {
            for (MatchingComponent m : matchingComponentList) {
                eventBus.getCompetitionObject().addSwimmerDiveMatch(m.getSwimmer(), new ArrayList<>(m.getSelectedDives()));
            }
            eventBus.finishMatching();
        });
        goBackButton.addActionListener(e ->{
           eventBus.resetCompetition();
           eventBus.showSecondPanel();
        });
        labelContainer.add(titleLabel);
        buttonContainer.add(finishButton);
        buttonContainer.add(goBackButton);
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
            MatchingComponent matchComp = new MatchingComponent(s, diveList);
            matchingComponentList.add(matchComp);
            mainContainer.add(matchComp);
        }
    }

    @Override
    public void refresh() {
        this.matchingComponentList.clear();
        this.removeAll();
        this.init();
    }

    @Override
    public void triggerEvent() {
    }

    @Override
    public void addEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.subscribe(this);
    }
}
