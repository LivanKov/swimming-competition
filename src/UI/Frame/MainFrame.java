package UI.Frame;

import UI.Panels.APanel;
import UI.Panels.AbstractPanel;
import UI.Panels.BPanel;
import UI.Panels.CPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final static int WIDTH = 600;

    private final static int HEIGHT = 600;

    private ArrayList<AbstractPanel>panelStorage = new ArrayList<>();

    private AbstractPanel currentPanel;

    public MainFrame(){
        super();
    }

    public void init(){
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setTitle("v1.0.0");
        this.setTitle("v1.0.0");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(AbstractPanel p : this.panelStorage){
            if(p instanceof APanel){
                this.currentPanel = (APanel)p;
                p.init();
                this.add(currentPanel);
                this.revalidate();
                this.repaint();
            }
        }
        if(this.currentPanel == null  || panelStorage.size()<3){
            throw new RuntimeException("Panels have not been initialized");
        }
    }


    public void addPanel(AbstractPanel panel){
        this.panelStorage.add(panel);
    }

    public void switchToFirstPanel(){
        this.remove(currentPanel);
        for(AbstractPanel p : this.panelStorage){
            if(p instanceof APanel){
                this.currentPanel = p;
                currentPanel.refresh();
                this.add(currentPanel);
                this.revalidate();
                this.repaint();
            }
        }
    }


    public void switchToSecondPanel(){
        this.remove(currentPanel);
        for(AbstractPanel p : this.panelStorage){
            if(p instanceof BPanel){
                this.currentPanel = p;
                currentPanel.refresh();
                this.add(currentPanel);
                this.revalidate();
                this.repaint();
            }
        }
    }


    public void switchToThirdPanel(){
        this.remove(currentPanel);
        for(AbstractPanel p : this.panelStorage){
            if(p instanceof CPanel){
                this.currentPanel = p;
                currentPanel.refresh();
                this.add(currentPanel);
                this.revalidate();
                this.repaint();
            }
        }
    }
}
