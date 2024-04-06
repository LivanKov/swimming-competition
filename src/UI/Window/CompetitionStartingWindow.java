package UI.Window;

import DataLayer.Competition;
import UI.EventBus;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class CompetitionStartingWindow extends JFrame implements CompetitionWindow {

    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;

    private Competition competition;

    private JTextArea textArea;

    private JButton exportButton;

    private EventBus eventBus;

    public CompetitionStartingWindow() {
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setTitle("v1.0.0");
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
        createCustomCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventBus.showMatchCreatorWindow();
            }
        });
        this.exportButton = new JButton("Export File");
        this.exportButton.setEnabled(false);
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

    public void exportDocument(){
        String fileName = competition.getName()+" "+ LocalDate.now()+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            int height = 2+((competition.getDivesPerSwimmer()+1)*competition.getSwimmers().size());
            String titleDate = competition.getCompetitionName()+" - "+LocalDate.now();
            writer.write(titleDate);
            String upperBar = "╠════════════════════════";
            for(int i = 0;i < competition.getNumberOfJudges();i++){
                upperBar.concat("═════");
            }
            upperBar.concat("════════════════════════╣");
            String columnNames = "║ RA ║ Name ║ Dive ║ DD ║";
            for(int i = 0;i < competition.getNumberOfJudges();i++){
                columnNames.concat(" J"+(i+1)+" ║");
            }
            columnNames.concat(" PE ║ PT ║ TDD ║ Total  ║");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void addEventBus(EventBus eventBus){
        this.eventBus = eventBus;
        this.eventBus.subscribe(this);
    }

    @Override
    public void triggerEvent() {
        this.textArea.append("Created a custom competition\n");
        this.textArea.append(this.competition.getSwimmers().size()+" participants");
        this.textArea.append(this.competition.getDives().size()+" unique dives");
        this.textArea.append("Calculating results\n");
        this.exportButton.setEnabled(true);
        this.exportButton.setBackground(Color.GREEN);
    }

    @Override
    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public void start() {}

    @Override
    public void exit() {
        this.dispose();
    }
}
