package UI.Window;

import DataLayer.Competition;
import DataLayer.Dive;
import DataLayer.Swimmer;
import UI.EventBus;
import UI.Component.DiverEntityDisplayComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CompetitionMatchingWindow extends JFrame implements CompetitionWindow {

    private static final int WIDTH = 450;

    private static final int HEIGHT = 500;

    private Competition competition;

    private EventBus eventBus;

    private JPanel mainContainer;

    private JPanel entityContainer;

    private Map<Swimmer, ArrayList<Dive>> swimmerDiveMap = new HashMap<Swimmer,ArrayList<Dive>>();

    public CompetitionMatchingWindow(){
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setVisible(false);
    }

    private void initializeMainContainer(){
        this.mainContainer = new JPanel();
        this.add(mainContainer);
        JLabel titleLabel = new JLabel("Assign dives to specific players");
        mainContainer.setLayout(new BoxLayout(mainContainer,BoxLayout.Y_AXIS));
        mainContainer.add(titleLabel);
        this.entityContainer = new JPanel();
        mainContainer.add(this.entityContainer);
        JButton finishButton = new JButton("Finish");
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventBus.finishMatching();
            }
        });
        mainContainer.add(finishButton);
    }

    private void addElements(){
        for(Swimmer s : this.competition.getSwimmers()){
            HashSet<JButton> activatedButtons = new HashSet<>();
            swimmerDiveMap.put(s,new ArrayList<Dive>());
            JPanel entityContainerPanel = new JPanel();
            entityContainerPanel.setLayout(new GridLayout(1,2));
            entityContainerPanel.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
            entityContainerPanel.add(new DiverEntityDisplayComponent(s));
            JPanel diveEntitySelectorPanel = new JPanel();
            diveEntitySelectorPanel.setLayout(new GridLayout(5,1));
            JScrollPane diveScrollPane = new JScrollPane (diveEntitySelectorPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            entityContainerPanel.add(diveScrollPane);
            this.entityContainer.add(entityContainerPanel);
            for(Dive d : competition.getDives()){
                JButton btn = new JButton("Dive ID: "+d.getDiveId());
                btn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(activatedButtons.contains(btn)){
                            swimmerDiveMap.get(s).remove(d);
                            btn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
                            activatedButtons.remove(btn);
                        }else{
                            swimmerDiveMap.get(s).add(d);
                            if(activatedButtons.size()<competition.getDivesPerSwimmer()){
                                btn.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GREEN));
                                activatedButtons.add(btn);
                            }
                        }
                    }
                });
                diveEntitySelectorPanel.add(btn);
            }
        }
    }

    private void fillCompetition(){
        for(Swimmer s : this.swimmerDiveMap.keySet()){
            this.competition.addSwimmerDiveMatch(s,this.swimmerDiveMap.get(s));
        }
    }

    /*public static void main(String[]args){
        Competition testComp = new Competition();
        testComp.setCompetitionName("Test Competition");
        testComp.setNumberOfJudges(5);
        testComp.setDivesPerSwimmer(1);
        testComp.addSwimmer(new Swimmer("Ivan","2001"));
        testComp.addSwimmer(new Swimmer("Nigger","2002"));
        testComp.addDive(new Dive("1337","94"));
        testComp.addDive(new Dive("84","96"));

        var window = new PlayerDiveMatchingWindow();

        System.out.println(javax.swing.UIManager.getDefaults().getFont("Label.font"));
    }*/

    @Override
    public void triggerEvent() {}

    @Override
    public void addEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.subscribe(this);
    }

    @Override
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void start() {
        this.setVisible(true);
    }

    @Override
    public void exit() {
        this.dispose();
    }
}
