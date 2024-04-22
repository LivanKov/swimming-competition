package UI.Window;

import UI.EventBus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CompetitionStartingWindow extends JFrame implements CompetitionWindow {

    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;

    private final JTextArea textArea;

    private final JButton exportButton;

    private EventBus eventBus;

    public CompetitionStartingWindow() {
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("v1.0.0");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel upperPanel = new JPanel();
        Border upperPanelBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        upperPanel.setBorder(upperPanelBorder);
        JPanel lowerPanel = new JPanel();
        JPanel upperLeftPanel = new JPanel();
        upperLeftPanel.setLayout(new BoxLayout(upperLeftPanel, BoxLayout.Y_AXIS));
        JPanel upperRightPanel = new JPanel();
        this.textArea = new JTextArea("SwimOlympics v1.0.0\nWelcome...\n");
        this.textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.textArea.setEditable(false);
        lowerPanel.setLayout(new BorderLayout());
        lowerPanel.add(this.textArea);
        Border upperLeftPanelBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);
        upperLeftPanel.setBorder(upperLeftPanelBorder);
        upperPanel.setLayout(new GridLayout(1, 2));
        upperPanel.add(upperLeftPanel);
        upperPanel.add(upperRightPanel);
        JButton createCustomCompetitionButton = new JButton("Create a custom competition");
        JButton createDefaultCompetitionButton = new JButton("Create a default competition");
        JPanel customCompButtonContainer = new JPanel(new GridBagLayout());
        JPanel defaultCompButtonContainer = new JPanel(new GridBagLayout());

        customCompButtonContainer.add(createCustomCompetitionButton);
        defaultCompButtonContainer.add(createDefaultCompetitionButton);
        createCustomCompetitionButton.addActionListener(e -> {
            eventBus.showMatchCreatorWindow();
        });
        createDefaultCompetitionButton.addActionListener(e -> {
            eventBus.createDefaultCompetition();
        });
        this.exportButton = new JButton("Export File");
        this.exportButton.setEnabled(false);
        exportButton.addActionListener(e -> {
            System.out.println("Event checked");
            eventBus.printCompetitionResults();
        });
        upperRightPanel.setLayout(new GridBagLayout());
        upperLeftPanel.add(customCompButtonContainer);
        upperLeftPanel.add(defaultCompButtonContainer);
        upperRightPanel.add(exportButton);

        this.setLayout(new GridLayout(2, 1));
        this.add(upperPanel);
        this.add(lowerPanel);
        this.add(lowerPanel);
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(false);
        this.setVisible(true);
    }

    @Override
    public void addEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.subscribe(this);
    }

    @Override
    public void triggerEvent() {
        this.textArea.append("Created a competition\n");
        this.textArea.append(eventBus.getCompetitionObject().getSwimmers().size() + " participants\n");
        this.textArea.append(eventBus.getCompetitionObject().getDives().size() + " unique dives\n");
        this.textArea.append("Calculating results...\n");
        this.exportButton.setEnabled(true);
        this.exportButton.setBackground(Color.GREEN);
    }

    @Override
    public void start() {
    }

    @Override
    public void exit() {
        this.dispose();
    }
}
