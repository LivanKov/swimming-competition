package Parser;

import Container.Container;
import DataLayer.Host.Competition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    private String filePath;

    private Container dataContainer;
    public Parser(String filePath){
        this.filePath = filePath;
        this.dataContainer = new Container();
    }

    public void readFileToOutput(){
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    public void convertFileToObjects(){
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int lineIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(lineIndex == 0){
                    String competitionName = line.split(":")[1];
                    competitionName.trim();
                    Competition comp = new Competition(competitionName);
                    System.out.println("New Competition created");
                    System.out.println("Competition name: "+ comp.getName());
                }
                lineIndex++;

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }
}
