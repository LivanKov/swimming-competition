package UI;

import DataLayer.Competition;
import DataLayer.Rating;
import DataLayer.Swimmer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class TablePrinter {

    private static TableDataHolder dataHolder;

    public static void printCompetitionResults(Competition competition) {
        String fileName = "results" + LocalDate.now() + ".txt";
        System.out.println(fileName);
        TablePrinter.fillDataHolder(competition);
        try {
            FileWriter f = new FileWriter(fileName);
            f.write(TablePrinter.createTableString(competition));
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createTableString(Competition competition) {
        String table = "";
        table += TablePrinter.createBar(competition, true);
        table += TablePrinter.createTableData(competition);
        table += TablePrinter.createBar(competition, false);
        return table;
    }

    private static String createBar(Competition competition, boolean upper) {

        String bar = upper ? "╔" : "╚";
        bar += "═".repeat(TablePrinter.dataHolder.getNumLength());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestNameLen());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestNationalityNameLen());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestDiveIdLen());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestDiveDifficultyLen());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat((TablePrinter.dataHolder.getLongestRatingLen() + 1) * 7 - 1);
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestPT());
        bar += upper ? "╦" : "╩";
        bar += "═".repeat(TablePrinter.dataHolder.getLongestTDD());
        bar += upper ? "╦" : "╩";
        bar += upper ? "═".repeat(TablePrinter.dataHolder.getLongestTotalScore()) + "╗\n" : "═".repeat(TablePrinter.dataHolder.getLongestTotalScore()) + "╝\n";
        if (upper) {
            bar += "║" + " ".repeat(TablePrinter.dataHolder.getNumLength() - 2) + "RA║";
            bar += "NAME" + " ".repeat(TablePrinter.dataHolder.getLongestNameLen() - 4) + "║";
            bar += "NAT║";
            bar += "DIVE" + " ".repeat(TablePrinter.dataHolder.getLongestDiveIdLen() - 4) + "║";
            bar += "DD" + " ".repeat(TablePrinter.dataHolder.getLongestDiveDifficultyLen() - 2) + "║";
            bar += "J1" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J2" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J3" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J4" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J5" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J6" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "J7" + " ".repeat(TablePrinter.dataHolder.getLongestRatingLen() - 2) + "║";
            bar += "PT" + " ".repeat(TablePrinter.dataHolder.getLongestPT() - 2) + "║";
            bar += "TDD" + " ".repeat(TablePrinter.dataHolder.getLongestTDD() - 3) + "║";
            bar += "TOTAL" + " ".repeat(TablePrinter.dataHolder.getLongestTotalScore() - 5) + "║\n";
        }
        return bar;
    }

    private static String createTableData(Competition competition) {
        String table = "";
        int counter = 0;
        for (Swimmer s : competition.getSwimmers()) {
            table += TablePrinter.createScoreBox(s, competition.getRatingsForSwimmer(s), ++counter);
        }
        return table;
    }

    private static String createScoreBox(Swimmer s, ArrayList<Rating> ratings, int counter) {
        String boxString = "║";
        boxString += counter + " ".repeat(dataHolder.getNumLength() - String.valueOf(counter).length()) + "║";
        boxString += s.getName() + " ".repeat(dataHolder.getLongestNameLen() - s.getName().length()) + "║";
        boxString += s.getNationality() + " ".repeat(dataHolder.getLongestNationalityNameLen() - s.getNationality().length()) + "║";
        boxString += generateScoreMesh(ratings);
        return boxString;
    }

    private static String generateScoreMesh(ArrayList<Rating> ratings) {
        String scoreMesh = "";
        DecimalFormat df = new DecimalFormat("#.##");
        int counter = 0;
        for (Rating r : ratings) {
            if (counter != 0) {
                scoreMesh += "║" + " ".repeat(dataHolder.getNumLength()) + "║" + " ".repeat(dataHolder.getLongestNameLen()) + "║" + " ".repeat(dataHolder.getLongestNationalityNameLen()) + "║";
            }
            scoreMesh += r.getDive().getDiveId() + " ".repeat(dataHolder.getLongestDiveIdLen() - r.getDive().getDiveId().length()) + "║";
            scoreMesh += df.format(r.getDive().getDifficulty()) + " ".repeat(dataHolder.getLongestDiveDifficultyLen() - df.format(r.getDive().getDifficulty()).length()) + "║";

            DecimalFormat df_ = new DecimalFormat("0.0");
            for (Double d : r.getScores()) {
                scoreMesh += df_.format(d) + " ".repeat(dataHolder.getLongestRatingLen() - df_.format(d).length()) + "║";
            }
            scoreMesh += df.format(r.getPT()) + " ".repeat(dataHolder.getLongestPT() - df.format(r.getPT()).length()) + "║";
            scoreMesh += (counter == 0 ? df.format(r.getSwimmer().getTDD()) : " ".repeat(df.format(r.getSwimmer().getTDD()).length())) + " ".repeat(dataHolder.getLongestTDD() - df.format(r.getSwimmer().getTDD()).length()) + "║";
            scoreMesh += df.format(r.getSwimmer().getTotalScore()) + " ".repeat(dataHolder.getLongestTotalScore() - df.format(r.getSwimmer().getTotalScore()).length()) + "║\n";
            counter++;
        }
        return scoreMesh;
    }

    private static void fillDataHolder(Competition competition) {
        TablePrinter.dataHolder = new TableDataHolder();
        DecimalFormat df = new DecimalFormat("#.##");
        dataHolder.setNumLength(String.valueOf(competition.getSwimmerDiveMatching().size()).length() < 2 ? 2 : String.valueOf(competition.getSwimmerDiveMatching().size()).length());
        dataHolder.setLongestNameLen(competition.getSwimmerNameMap().keySet().stream().map(x -> x.length()).max(Comparator.naturalOrder()).orElse(30));
        dataHolder.setLongestNationalityNameLen(3);
        dataHolder.setLongestDiveIdLen(competition.getDives().stream().map(x -> x.getDiveId().length()).max(Comparator.naturalOrder()).orElse(10));
        dataHolder.setLongestDiveDifficultyLen(competition.getDives().stream().map(x -> df.format(x.getDifficulty()).length()).max(Comparator.naturalOrder()).orElse(5));
        dataHolder.setLongestRatingLen(competition.getSwimmerRatingMatch().values().stream().map(x -> x.stream().map(y -> y.getScores().stream().map(z -> df.format(z).length()).max(Comparator.naturalOrder()).orElse(5)).max(Comparator.naturalOrder()).orElse(4)).max(Comparator.naturalOrder()).orElse(4));
        System.out.println("Longest rating length: " + dataHolder.getLongestRatingLen());
        dataHolder.setLongestPT(competition.getSwimmerRatingMatch().values().stream().map(x -> x.stream().map(y -> df.format(y.getPT()).length()).max(Comparator.naturalOrder()).orElse(5)).max(Comparator.naturalOrder()).orElse(5));
        System.out.println("Longest PT: " + dataHolder.getLongestPT());
        dataHolder.setLongestTDD(competition.getSwimmers().stream().map(x -> df.format(x.getTDD()).length()).max(Comparator.naturalOrder()).orElse(4));
        System.out.println("Longest TDD: " + dataHolder.getLongestTDD());
        dataHolder.setLongestTotalScore(competition.getSwimmers().stream().map(x -> df.format(x.getTotalScore()).length()).max(Comparator.naturalOrder()).orElse(6));
    }


}
