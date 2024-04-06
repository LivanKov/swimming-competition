package UI.Component;

import DataLayer.Dive;

import javax.swing.*;

public class DiveEntityDisplayComponent extends JPanel {

    private Dive dive;

    public DiveEntityDisplayComponent(Dive dive){
        this.dive = dive;
        JLabel idLabel = new JLabel(String.valueOf(dive.getDiveId()));
        this.add(idLabel);
    }

}
