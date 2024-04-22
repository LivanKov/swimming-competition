package UI.Window;

import DataLayer.Competition;
import DataLayer.Dive;
import DataLayer.Swimmer;
import UI.Component.DiveCreationComponent;
import UI.Component.DiverCreationComponent;
import UI.EventBus;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CompetitionCreationWindow extends JFrame implements CompetitionWindow {

    private final static int WIDTH = 600;

    private final static int HEIGHT = 600;

    private final int amountOfDivesPerPerson;

    private final int amountOfJudges;

    private final Stack<DiverCreationComponent> playerPanelStack;

    private final Stack<DiveCreationComponent> divePanelStack;

    private EventBus eventBus;

    private JButton cancelButton;

    private JButton matchButton;

    private JSpinner diveSpinner;

    private JSpinner playerSpinner;

    private JPanel swimmerContainer;

    private JPanel diveContainer;

    private JTextField matchNameField;

    private JScrollPane playerScrollPane;

    private JScrollPane diveScrollPane;

    public CompetitionCreationWindow() {
        this.amountOfDivesPerPerson = 0;
        this.amountOfJudges = 0;
        this.playerPanelStack = new Stack<>();
        this.divePanelStack = new Stack<>();
        this.setVisible(false);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setTitle("v1.0.0");
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

    private void initializeButtons() {
        this.matchButton = new JButton("Assign dives to Players");
        this.cancelButton = new JButton("Cancel");
    }

    private void initializeSpinners() {
        this.playerSpinner = new JSpinner();
        this.diveSpinner = new JSpinner();
    }

    private void initializeContainers() {
        this.diveContainer = new JPanel();
        this.diveContainer.setLayout(new GridLayout(0, 1));
        this.swimmerContainer = new JPanel();
        this.swimmerContainer.setLayout(new GridLayout(2, 1));
    }

    private void initializeRemainingComponents() {
        JPanel mainPanel = new JPanel();
        this.matchNameField = new JTextField();
        matchNameField.setColumns(10);
        JLabel matchNameLabel = new JLabel("Competition Name:");
        JPanel matchNamePanel = new JPanel();
        matchNamePanel.add(matchNameLabel);
        matchNamePanel.add(matchNameField);
        matchNamePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        mainPanel.add(matchNamePanel);
        JPanel playerCreationPanel = new JPanel();
        playerCreationPanel.setLayout(new GridBagLayout());
        JPanel diveCreationPanel = new JPanel();
        diveCreationPanel.setLayout(new GridBagLayout());
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(2, 1));
        JPanel subPlayerLabelPanel = new JPanel();
        JPanel subDiveLabelPanel = new JPanel();
        JPanel lowerUpperPanel = new JPanel();
        lowerUpperPanel.setLayout(new GridLayout(2, 1));
        JPanel lowerLowerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        playerCreationPanel.add(subPlayerLabelPanel, gbc);
        diveCreationPanel.add(subDiveLabelPanel, gbc);
        lowerPanel.add(lowerUpperPanel);
        lowerPanel.add(lowerLowerPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        this.playerScrollPane = new JScrollPane(this.swimmerContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.diveScrollPane = new JScrollPane(this.diveContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gbc.gridy = 1;
        gbc.weightx = 5.0;
        gbc.weighty = 8.0;
        playerCreationPanel.add(this.playerScrollPane, gbc);
        diveCreationPanel.add(this.diveScrollPane, gbc);
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

    private void distributeActionListeners() {
        this.playerSpinner.addChangeListener(e -> {
            if ((int) playerSpinner.getValue() <= 0) {
                playerSpinner.setValue(0);
                if (!playerPanelStack.empty()) {
                    var lastPanel = playerPanelStack.pop();
                    swimmerContainer.remove(lastPanel);
                }
                DiverCreationComponent.setPanelCounter(0);
            } else if ((int) playerSpinner.getValue() < playerPanelStack.size()) {
                var panel = playerPanelStack.pop();
                DiverCreationComponent.setPanelCounter(DiverCreationComponent.getPanelCounter() - 1);
                swimmerContainer.remove(panel);
            } else {
                DiverCreationComponent.setPanelCounter(DiverCreationComponent.getPanelCounter() + 1);
                var panel = new DiverCreationComponent();
                playerPanelStack.push(panel);
                swimmerContainer.add(panel);
                panel.setSize(100, 100);
            }
            if (playerPanelStack.size() == 1) {
                playerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                swimmerContainer.setLayout(new GridLayout(2, 1));
            } else {
                playerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                swimmerContainer.setLayout(new GridLayout(playerPanelStack.size(), 1));
            }
            swimmerContainer.revalidate();
            swimmerContainer.repaint();
        });

        this.diveSpinner.addChangeListener(e -> {
            if ((int) diveSpinner.getValue() <= 0) {
                diveSpinner.setValue(0);
                if (!divePanelStack.empty()) {
                    var lastPanel = divePanelStack.pop();
                    diveContainer.remove(lastPanel);
                }
                DiveCreationComponent.setPanelCounter(0);
            } else if ((int) diveSpinner.getValue() <= divePanelStack.size()) {
                DiveCreationComponent.setPanelCounter(DiveCreationComponent.getPanelCounter() - 1);
                var panel = divePanelStack.pop();
                diveContainer.remove(panel);
            } else {
                DiveCreationComponent.setPanelCounter(DiveCreationComponent.getPanelCounter() + 1);
                var panel = new DiveCreationComponent();
                divePanelStack.push(panel);
                diveContainer.add(panel);
            }
            if (divePanelStack.size() == 1) {
                diveScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                diveContainer.setLayout(new GridLayout(2, 1));
            } else {
                diveScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                diveContainer.setLayout(new GridLayout(divePanelStack.size(), 1));
            }
            diveContainer.revalidate();
            diveContainer.repaint();
        });

        this.matchButton.addActionListener(e -> {
            Competition competition = this.fillCompetition();
            if (competition == null || competition.getCompetitionName().isEmpty()) {
                matchNameField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
            } else {
                eventBus.setCompetitionObject(competition);
                eventBus.showMatchingWindow();
            }
        });

        this.cancelButton.addActionListener(e -> {
            exit();
        });
    }

    private boolean sanitizeInputs() {
        return true;
    }

    private Competition fillCompetition() {
        Competition newComp = new Competition();
        ArrayList<JPanel> stackPlayerList = new ArrayList<JPanel>(playerPanelStack);
        int amountOfSwimmers = stackPlayerList.size();
        ArrayList<JPanel> stackDiveList = new ArrayList<JPanel>(divePanelStack);
        stackPlayerList.addAll(stackDiveList);
        boolean emptyFieldFound = false;
        int counter = 0;
        Object recentInstance = null;
        for (JPanel panel : stackPlayerList) {
            List<Component> extractedComponents = extractComponents(panel);
            for (Component component : extractedComponents) {
                if (component instanceof JTextField) {
                    if (((JTextField) component).getText().isEmpty()) {
                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
                        emptyFieldFound = true;
                    } else {
                        if (counter % 2 == 0) {
                            if (counter / 2 < amountOfSwimmers) {
                                Swimmer s = new Swimmer();
                                s.setName(((JTextField) component).getText());
                                recentInstance = s;
                            } else {
                                Dive d = new Dive();
                                d.setDiveId(((JTextField) component).getText());
                                recentInstance = d;
                            }
                        } else {
                            if (counter / 2 < amountOfSwimmers) {
                                Swimmer s = (Swimmer) recentInstance;
                                if (s != null) {
                                    try {
                                        int yearOfBirth = Integer.parseInt(((JTextField) component).getText());
                                        s.setYearOfBirth(yearOfBirth);
                                    } catch (NumberFormatException e) {
                                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
                                    }
                                }
                                newComp.addSwimmer(s);
                            } else {
                                Dive d = (Dive) recentInstance;
                                if (d != null) {
                                    try {
                                        double difficulity = Double.parseDouble(((JTextField) component).getText());
                                        d.setDifficulty(difficulity);
                                    } catch (NumberFormatException e) {
                                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
                                    }
                                }
                                newComp.addDive(d);
                            }
                        }
                        ((JTextField) component).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
                    }
                    counter++;
                } else if (component instanceof JComboBox<?>) {
                    Swimmer s = (Swimmer) recentInstance;
                    String nationality = (String) ((JComboBox) component).getSelectedItem();
                    s.setNationality(nationality);
                }
            }
        }
        if (emptyFieldFound) {
            return null;
        }
        newComp.setDivesPerSwimmer(amountOfDivesPerPerson);
        newComp.setNumberOfJudges(amountOfJudges);
        newComp.setCompetitionName(matchNameField.getText());
        return newComp;
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
