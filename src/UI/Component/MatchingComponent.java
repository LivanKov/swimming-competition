package UI.Component;

import DataLayer.Dive;
import DataLayer.Swimmer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MatchingComponent extends JPanel {

    Swimmer swimmer;

    List<Dive> diveList;

    List<DiveCheckBoxComponent> checkBoxes;

    public MatchingComponent(Swimmer s, List<Dive> list) {
        this.swimmer = s;
        this.diveList = list;
        this.checkBoxes = new ArrayList<DiveCheckBoxComponent>();
        this.createComponent();
        this.setSize(400,100);
    }

    private void createComponent() {
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        this.setPreferredSize(new Dimension(400, 50));
        JPanel swimmerContainer = new JPanel();
        JPanel diveContainer = new JPanel();
        swimmerContainer.setLayout(new BoxLayout(swimmerContainer, BoxLayout.Y_AXIS));
        //diveContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        diveContainer.setLayout(new GridLayout(0, 1));
        JScrollPane diveContainerScroll = new JScrollPane(diveContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(swimmerContainer);
        this.add(diveContainerScroll);
        JLabel nameLabel = new JLabel("Name: " + swimmer.getName());
        JLabel yearLabel = new JLabel("Year of Birth: " + swimmer.getYearOfBirth());

        for (Dive d : diveList) {
            DiveCheckBoxComponent checkBoxComponent = new DiveCheckBoxComponent(d);
            checkBoxes.add(checkBoxComponent);
            diveContainer.add(checkBoxComponent);
        }

        swimmerContainer.add(nameLabel);
        swimmerContainer.add(yearLabel);
    }


    public List<Dive> getSelectedDives() {
        ArrayList<Dive> selectedObjects = new ArrayList<Dive>();
        for (DiveCheckBoxComponent c : this.checkBoxes) {
            if (c.isSelected()) {
                selectedObjects.add(c.getDive());
            }
        }
        return selectedObjects;
    }

    public Swimmer getSwimmer() {
        return swimmer;
    }

    public List<Dive> getDiveList() {
        return diveList;
    }
}
