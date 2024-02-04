import DataLayer.Swimmer;
import Parser.Parser;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("./Competition.txt");
        //testMethod
        parser.convertFileToObjects();
        Swimmer swimmer1 = new Swimmer(2001,"Ivan", null,30,50,true);

    }
}