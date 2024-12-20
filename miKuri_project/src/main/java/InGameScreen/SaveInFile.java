package InGameScreen;

import java.io.*;
import java.time.LocalDateTime;

public class SaveInFile {
    public static void FileSave(String winner) {
        String file = "results.txt";
        String time = LocalDateTime.now().toString();
        String info = time + " - " + winner;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(info);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error printing to file: " + e.getMessage());
        }
    }
}
