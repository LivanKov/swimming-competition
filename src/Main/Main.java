package Main;

import DataLayer.Competition;
import UI.EventBus;
import UI.Window.CompetitionCreationWindow;
import UI.Window.CompetitionMatchingWindow;
import UI.Window.CompetitionStartingWindow;

public class Main {
    public static void main(String[] args) {
        Competition competition = new Competition();
        EventBus eventBus = new EventBus();
        CompetitionStartingWindow startingWindow = new CompetitionStartingWindow();
        CompetitionCreationWindow creationWindow = new CompetitionCreationWindow();
        CompetitionMatchingWindow matchingWindow = new CompetitionMatchingWindow();

        startingWindow.addEventBus(eventBus);
        creationWindow.addEventBus(eventBus);
        matchingWindow.addEventBus(eventBus);


        /*SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Vertical Alignment Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel mainPanel = new JPanel(new GridBagLayout());

            // Create three panels
            JPanel upperPanel = new JPanel();
            JPanel middlePanel = new JPanel();
            JPanel lowerPanel = new JPanel();

            // Set background colors for visibility
            upperPanel.setBackground(Color.RED);
            middlePanel.setBackground(Color.GREEN);
            lowerPanel.setBackground(Color.BLUE);

            // Set up GridBagConstraints for upper panel
            GridBagConstraints gbcUpper = new GridBagConstraints();
            gbcUpper.gridx = 0;
            gbcUpper.gridy = 0;
            gbcUpper.weighty = 0.1;
            gbcUpper.fill = GridBagConstraints.BOTH;

            // Set up GridBagConstraints for middle panel
            GridBagConstraints gbcMiddle = new GridBagConstraints();
            gbcMiddle.gridx = 0;
            gbcMiddle.gridy = 1;
            gbcMiddle.weighty = 0.8;
            gbcMiddle.fill = GridBagConstraints.BOTH;

            // Set up GridBagConstraints for lower panel
            GridBagConstraints gbcLower = new GridBagConstraints();
            gbcLower.gridx = 0;
            gbcLower.gridy = 2;
            gbcLower.weighty = 0.1;
            gbcLower.fill = GridBagConstraints.BOTH;

            // Add panels to the main panel
            mainPanel.add(upperPanel, gbcUpper);
            mainPanel.add(middlePanel, gbcMiddle);
            mainPanel.add(lowerPanel, gbcLower);

            // Add the main panel to the frame
            frame.getContentPane().add(mainPanel);

            frame.setVisible(true);
        });*/
    }
}