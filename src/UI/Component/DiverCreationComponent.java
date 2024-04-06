package UI.Component;

import javax.swing.*;
import java.awt.*;

public class DiverCreationComponent extends JPanel {

    private static final int WIDTH = 40;

    private static final int HEIGHT = 40;

    private static int PANEL_COUNTER = 0;

    public DiverCreationComponent(){
        JTextField nameField = new JTextField();
        JTextField yearField = new JTextField();
        JLabel counterLabel = new JLabel("Player "+PANEL_COUNTER);
        JLabel nameLabel = new JLabel("Name:");
        JLabel yearLabel = new JLabel("Year of Birth:");
        JPanel firstContainer = new JPanel();
        firstContainer.setLayout(new GridLayout(3,1));
        JPanel secondContainer = new JPanel();
        secondContainer.setLayout(new GridLayout(2,1));
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(firstContainer);
        this.add(secondContainer);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        firstContainer.add(counterLabel);
        firstContainer.add(nameLabel);
        firstContainer.add(nameField);
        secondContainer.add(yearLabel);
        secondContainer.add(yearField);
    }

    public static int getPanelCounter(){
        return PANEL_COUNTER;
    }

    public static void setPanelCounter(int newVal){
        PANEL_COUNTER=newVal;
    }
}
