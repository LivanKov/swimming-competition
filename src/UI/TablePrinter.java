package UI;

public class TablePrinter {
    /*public void exportDocument(){
        String fileName = competition.getName()+" "+ LocalDate.now()+".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            int height = 2+((competition.getDivesPerSwimmer()+1)*competition.getSwimmers().size());
            String titleDate = competition.getCompetitionName()+" - "+LocalDate.now();
            writer.write(titleDate);
            String upperBar = "╠════════════════════════";
            for(int i = 0;i < competition.getNumberOfJudges();i++){
                upperBar.concat("═════");
            }
            upperBar.concat("════════════════════════╣");
            String columnNames = "║ RA ║ Name ║ Dive ║ DD ║";
            for(int i = 0;i < competition.getNumberOfJudges();i++){
                columnNames.concat(" J"+(i+1)+" ║");
            }
            columnNames.concat(" PE ║ PT ║ TDD ║ Total  ║");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/
}
