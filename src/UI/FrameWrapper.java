package UI;

import javax.swing.*;
import java.awt.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class FrameWrapper extends JFrame {

    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;

    private ArrayList<Path> rootPaths;
    public FrameWrapper(){
        rootPaths = new ArrayList<>();
        this.setVisible(true);
        this.setSize(new Dimension(WIDTH,HEIGHT));
        this.setTitle("v1.0.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addRootDirectories();
        JPanel panel = new JPanel();
        //this.add(panel);
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setControlButtonsAreShown(false);
        //this.add(fileChooser, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);
    }


    private void addRootDirectories(){
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs) {
            rootPaths.add(name);
        }
    }

}
