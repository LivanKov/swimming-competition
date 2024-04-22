package UI.Component;

import DataLayer.Dive;

import javax.swing.*;
import java.awt.*;

public class DiveCheckBoxComponent extends JPanel {

    private final Dive dive;

    private JCheckBox checkBox;

    public DiveCheckBoxComponent(Dive dive) {
        this.dive = dive;
        this.createComponent();
    }

    private void createComponent() {
        this.setLayout(new GridLayout(1, 2));
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        this.checkBox = new JCheckBox();
        JLabel idLabel = new JLabel("ID: " + dive.getDiveId());
        JLabel difficultyLabel = new JLabel("Difficulty: " + dive.getDifficulty());
        JPanel checkBoxContainer = new JPanel();
        JPanel labelContainer = new JPanel();
        this.add(checkBoxContainer);
        this.add(labelContainer);
        this.setSize(100, 100);
        checkBoxContainer.add(checkBox);
        labelContainer.add(idLabel);
        labelContainer.add(difficultyLabel);
        labelContainer.setLayout(new GridLayout(2, 1));
    }

    public boolean isSelected() {
        return this.checkBox.isSelected();
    }

    public Dive getDive() {
        return dive;
    }


}
