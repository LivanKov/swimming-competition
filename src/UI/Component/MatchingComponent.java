package UI.Component;

import DataLayer.Dive;
import DataLayer.Swimmer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MatchingComponent extends JPanel {

    Swimmer swimmer;

    List<Dive> diveList;

    public MatchingComponent(Swimmer s, List<Dive> list){
        this.swimmer = s;
        this.diveList = list;
        this.createComponent();
    }

    private void createComponent(){
        this.setLayout(new GridLayout(1,2));
        this.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.BLACK));
        JPanel swimmerContainer = new JPanel();
        JPanel diveContainer = new JPanel();
        swimmerContainer.setLayout(new BoxLayout(swimmerContainer,BoxLayout.Y_AXIS));
        diveContainer.setBorder(BorderFactory.createMatteBorder(0,1,0,0,Color.BLACK));
        diveContainer.setLayout(new GridLayout());

        this.add(swimmerContainer);
        this.add(diveContainer);
        JLabel nameLabel = new JLabel("Name: "+swimmer.getName());
        JLabel yearLabel = new JLabel("Year of Birth: "+swimmer.getYearOfBirth());

        for(Dive d : diveList){
            diveContainer.add(new DiveCheckBoxComponent(d));
        }

        swimmerContainer.add(nameLabel);
        swimmerContainer.add(yearLabel);
    }
}
