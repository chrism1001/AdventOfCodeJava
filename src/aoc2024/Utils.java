package aoc2024;

import java.nio.file.Paths;
import java.util.Scanner;

public class Utils {
    public String readFile(String filePath) {
        StringBuilder string = new StringBuilder();

        try (Scanner scanner = new Scanner(Paths.get(filePath))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                string.append(line);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return string.toString();
    }
}
