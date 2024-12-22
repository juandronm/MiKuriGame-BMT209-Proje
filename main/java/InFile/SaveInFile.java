package InFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.pokemonlikegame.mikuri_project.MikuriController;
import javafx.application.Platform;

public class SaveInFile {
    private static MikuriController controller;

    public static void setController(MikuriController mikuriController) {
        controller = mikuriController;
    }

    public static void FileSave(String winner) {
        String file = "results.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);
        String info = time + " - " + winner;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(info);
            writer.newLine();
        } catch (IOException e) {
            //System.err.println("Error printing to file: " + e.getMessage());
        }
    }

    public static void printInfo(){
        if(controller == null){
            System.out.println("Error: controller is null");
        }

        StringBuilder content = new StringBuilder();
        List<String> filteredResults = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line).append("\n");

                String []parts = line.split(" - ");
                if(parts.length > 1){
                    LocalDateTime entryTime = LocalDateTime.parse(parts[0], formatter);

                    if(ChronoUnit.HOURS.between(entryTime, now) < 24){
                        filteredResults.add(line);
                    }
                }
            }
            Platform.runLater(() -> {
                String allResults = content.toString();
                controller.updateVictoriesText(allResults);

                String filteredContent = String.join("\n", filteredResults);
                controller.updateVictoriesText("In the last 24 hours:\n" + filteredContent);
            });
        } catch (IOException e) {
            System.out.println("There are not previous matches registered in the system.");
        } finally {
            System.out.println("Previous victory data processing finished.");
        }
    }
}
