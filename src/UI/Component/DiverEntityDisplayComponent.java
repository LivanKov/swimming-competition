package UI.Component;

import DataLayer.Swimmer;

import javax.swing.*;
import java.awt.*;

public class DiverEntityDisplayComponent extends JPanel {

    private Swimmer swimmer;

    public DiverEntityDisplayComponent(Swimmer swimmer){
        this.swimmer = swimmer;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setSize(new Dimension(100,50));
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));
        JLabel nameLabel = new JLabel(swimmer.getName());
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        JLabel yearLabel = new JLabel(String.valueOf(swimmer.getYearOfBirth()));
        this.add(nameLabel);
        this.add(yearLabel);
    }





}
