package UI.Component;

import DataLayer.Dive;

import javax.swing.*;
import java.awt.*;

public class DiveCheckBoxComponent extends JPanel {

    Dive dive;

    public DiveCheckBoxComponent(Dive dive){
        this.dive = dive;
        this.createComponent();
    }

    private void createComponent(){
        this.setLayout(new GridLayout(1,2));

        JCheckBox checkBox = new JCheckBox();
        JLabel idLabel = new JLabel("ID: "+dive.getDiveId());
        JLabel difficultyLabel = new JLabel("Difficulty: "+dive.getDifficulty());
        JPanel checkBoxContainer = new JPanel();
        JPanel labelContainer = new JPanel();
        labelContainer.setBorder(BorderFactory.createMatteBorder(0,1,0,0, Color.BLACK));

        this.add(checkBoxContainer);
        this.add(labelContainer);
        this.setSize(100,100);
        checkBoxContainer.add(checkBox);
        labelContainer.add(idLabel);
        labelContainer.add(difficultyLabel);
        labelContainer.setLayout(new GridLayout(2,1));
    }


    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(500,500);
        testFrame.setVisible(true);
        Dive testDive = new Dive("asdf",123);
        DiveCheckBoxComponent checkBoxComp = new DiveCheckBoxComponent(testDive);
        testFrame.add(checkBoxComp);
    }


}
