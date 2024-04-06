package UI.Window;

import DataLayer.Competition;
import DataLayer.Dive;
import DataLayer.Swimmer;
import UI.*;
import UI.Component.DiveCreationComponent;
import UI.Component.DiverCreationComponent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class CompetitionCreationWindow extends JFrame implements CompetitionWindow {

    private final static int WIDTH = 600;

    private final static int HEIGHT = 600;

    private int amountOfDivesPerPerson;

    private int amountOfJudges;

    private Stack<DiverCreationComponent>playerPanelStack;

    private Stack<DiveCreationComponent>divePanelStack;

    private String competitionName;

    private Competition competition;

    private EventBus eventBus;

    private JButton cancelButton;

    private JButton matchButton;

    private JSpinner diveSpinner;

    private JSpinner playerSpinner;

    private JPanel swimmerContainer;

    private JPanel diveContainer;
    private JTextField matchNameField;

    public CompetitionCreationWindow() {
        this.amountOfDivesPerPerson = 0;
        this.amountOfJudges = 0;
        this.playerPanelStack = new Stack<>();
        this.divePanelStack = new Stack<>();
        this.competitionName = "";
        this.setVisible(false);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("v1.0.0");
    }

    private void initializeButtons(){
        this.matchButton = new JButton("Assign dives to Players");
        this.cancelButton = new JButton("Cancel");
    }

    private void initializeSpinners(){
        this.playerSpinner = new JSpinner();
        this.diveSpinner = new JSpinner();
    }

    private void initializeContainers(){
        this.diveContainer = new JPanel();
        this.diveContainer.setLayout(new BoxLayout(diveContainer,BoxLayout.Y_AXIS));
        this.swimmerContainer = new JPanel();
        this.swimmerContainer.setLayout(new BoxLayout(swimmerContainer,BoxLayout.Y_AXIS));
    }

    private void initializeRemainingComponents(){
        JPanel mainPanel = new JPanel();
        this.matchNameField = new JTextField();
        matchNameField.setColumns(10);
        JLabel matchNameLabel = new JLabel("Competition Name:");
        JPanel matchNamePanel = new JPanel();
        matchNamePanel.add(matchNameLabel);
        matchNamePanel.add(matchNameField);
        matchNamePanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));
        mainPanel.add(matchNamePanel);
        JPanel playerCreationPanel = new JPanel();
        playerCreationPanel.setLayout(new GridBagLayout());
        JPanel diveCreationPanel = new JPanel();
        diveCreationPanel.setLayout(new GridBagLayout());
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(2,1));
        JPanel subPlayerLabelPanel = new JPanel();
        JPanel subDiveLabelPanel = new JPanel();
        JPanel lowerUpperPanel = new JPanel();
        lowerUpperPanel.setLayout(new GridLayout(2,1));
        JPanel lowerLowerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        playerCreationPanel.add(subPlayerLabelPanel, gbc);
        diveCreationPanel.add(subDiveLabelPanel,gbc);
        lowerPanel.add(lowerUpperPanel);
        lowerPanel.add(lowerLowerPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        JScrollPane playerScrollPane = new JScrollPane (this.swimmerContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane diverScrollPane = new JScrollPane(this.diveContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        playerScrollPane.setBackground(Color.DARK_GRAY);
        diverScrollPane.setBackground(Color.DARK_GRAY);
        gbc.gridy = 1;
        gbc.weightx = 5.0;
        gbc.weighty = 8.0;
        playerCreationPanel.add(playerScrollPane, gbc);
        diveCreationPanel.add(diverScrollPane,gbc);
        mainPanel.add(playerCreationPanel);
        mainPanel.add(diveCreationPanel);
        mainPanel.add(lowerPanel);
        JLabel playerSelectLabel = new JLabel("Choose the amount of players");
        JLabel diveSelectLabel = new JLabel("Choose the amount of jumps");
        subPlayerLabelPanel.add(playerSpinner);
        subPlayerLabelPanel.add(playerSelectLabel);
        subDiveLabelPanel.add(diveSpinner);
        subDiveLabelPanel.add(diveSelectLabel);
        JPanel upperLabelContainer = new JPanel();
        JPanel lowerLabelContainer = new JPanel();
        lowerUpperPanel.add(upperLabelContainer);
        lowerUpperPanel.add(lowerLabelContainer);
        lowerLowerPanel.add(matchButton);
        lowerLowerPanel.add(cancelButton);
    }


    private void distributeActionListeners(){
        ChangeListener addPlayerlistener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)playerSpinner.getValue() <= 0){
                    playerSpinner.setValue(0);
                    if(!playerPanelStack.empty()){
                        var lastPanel = playerPanelStack.pop();
                        swimmerContainer.remove(lastPanel);
                    }
                    DiverCreationComponent.setPanelCounter(0);
                }else if((int)playerSpinner.getValue()<playerPanelStack.size()){
                    var panel = playerPanelStack.pop();
                    DiverCreationComponent.setPanelCounter(DiverCreationComponent.getPanelCounter() - 1);
                    swimmerContainer.remove(panel);
                }else{
                    System.out.println("Added");
                    DiverCreationComponent.setPanelCounter(DiverCreationComponent.getPanelCounter() + 1);
                    var panel = new DiverCreationComponent();
                    playerPanelStack.push(panel);
                    swimmerContainer.add(panel);
                }
                swimmerContainer.revalidate();
                swimmerContainer.repaint();
            }
        };
        this.playerSpinner.addChangeListener(addPlayerlistener);

        ChangeListener addDiveListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((int)diveSpinner.getValue()<=0){
                    diveSpinner.setValue(0);
                    if(!divePanelStack.empty()){
                        var lastPanel = divePanelStack.pop();
                        diveContainer.remove(lastPanel);
                    }
                    DiveCreationComponent.setPanelCounter(0);
                }else if((int)diveSpinner.getValue()<=divePanelStack.size()){
                    DiveCreationComponent.setPanelCounter(DiveCreationComponent.getPanelCounter() - 1);
                    var panel = divePanelStack.pop();
                    diveContainer.remove(panel);
                }else{
                    DiveCreationComponent.setPanelCounter(DiveCreationComponent.getPanelCounter() + 1);
                    var panel = new DiveCreationComponent();
                    divePanelStack.push(panel);
                    diveContainer.add(panel);
                }
                diveContainer.revalidate();
                diveContainer.repaint();
            }
        };
        this.diveSpinner.addChangeListener(addDiveListener);

        CompetitionCreationWindow thisFrame = this;
        ActionListener matchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Competition competition = thisFrame.fillCompetition();
                for(Swimmer s : competition.getSwimmers()){
                    System.out.println("Name: " + s.getName()+", Year: " + s.getYearOfBirth());
                }
                for(Dive d : competition.getDives()){
                    System.out.println("ID: " + d.getDiveId()+", Difficulty: " + d.getDifficulty());
                }
                System.out.println("Number of Judges: " + competition.getNumberOfJudges());
                System.out.println("Number of Dives: " + competition.getDivesPerSwimmer());
                competitionName = matchNameField.getText();

                if(competitionName.isEmpty()){
                    matchNameField.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));
                }else if(competition != null){
                    var window = new CompetitionMatchingWindow();
                }
            }
        };

        this.matchButton.addActionListener(matchButtonListener);

        ActionListener cancelButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        };
        this.cancelButton.addActionListener(cancelButtonListener);
    }

    public static List<Component> extractComponents(Container parent) {
        List<Component> components = new ArrayList<>();
        Component[] children = parent.getComponents();
        for (Component child : children) {
            components.add(child);
            if (child instanceof Container) {
                components.addAll(extractComponents((Container) child));
            }
        }
        return components;
    }

    private boolean sanitizeInputs(){
        return true;
    }
    private Competition fillCompetition(){
        ArrayList<JPanel> stackPlayerList = new ArrayList<JPanel>(playerPanelStack);
        int amountOfSwimmers = stackPlayerList.size();
        ArrayList<JPanel> stackDiveList = new ArrayList<JPanel>(divePanelStack);
        stackPlayerList.addAll(stackDiveList);
        boolean emptyFieldFound = false;
        int counter = 0;
        Object recentInstance = null;
        for(JPanel panel : stackPlayerList){
            List<Component> extractedComponents = extractComponents(panel);
            for (Component component : extractedComponents) {
                if(component instanceof JTextField){
                    if(((JTextField) component).getText().isEmpty()){
                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));
                        emptyFieldFound = true;
                    }else {
                        if(counter%2 == 0){
                            if(counter/2<amountOfSwimmers){
                                Swimmer s = new Swimmer();
                                s.setName(((JTextField) component).getText());
                                recentInstance = s;
                            }else{
                                Dive d = new Dive();
                                d.setDiveId(((JTextField) component).getText());
                                recentInstance = d;
                            }
                        }else{
                            if(counter/2<amountOfSwimmers){
                                Swimmer s = (Swimmer)recentInstance;
                                if(s != null){
                                    try{
                                        int yearOfBirth = Integer.parseInt(((JTextField) component).getText());
                                        s.setYearOfBirth(yearOfBirth);
                                    } catch(NumberFormatException e){
                                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));
                                    }
                                }
                                this.competition.addSwimmer(s);
                            }else{
                                Dive d = (Dive)recentInstance;
                                if(d != null){
                                    try{
                                        double difficulity = Double.parseDouble(((JTextField)component).getText());
                                        d.setDifficulty(difficulity);
                                    } catch (NumberFormatException e){
                                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.RED));
                                    }
                                }
                                this.competition.addDive(d);
                            }
                        }
                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GREEN));
                    }
                    counter++;
                }
            }
        }
        if(emptyFieldFound){
            return null;
        }
        this.competition.setDivesPerSwimmer(amountOfDivesPerPerson);
        this.competition.setNumberOfJudges(amountOfJudges);
        return this.competition;
    }

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
        this.initializeButtons();
        this.initializeSpinners();
        this.distributeActionListeners();
        this.initializeContainers();
        this.initializeRemainingComponents();
        this.setVisible(true);
    }

    @Override
    public void exit() {
        this.dispose();
    }
}
