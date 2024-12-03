package aoc2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {
    public static int part2(ArrayList<String> input) {
        int result = 0;
        for (String line: input) {
            String[] numbers = line.split(" ");

            for (int i = 0; i < numbers.length; i++) {
                String[] newNumbers = new String[numbers.length-1];
                int j = 0;
                for (int k = 0; k < numbers.length; k++) {
                    if (k != i) {
                        newNumbers[j++] = numbers[k];
                    }
                }

                if (isValid(newNumbers)) {
                    result += 1;
                    break;
                }
            }
        }

        return result;
    }

    public static int part1(ArrayList<String> input) {
        int result = 0;
        for (String line: input) {
            String[] numbers = line.split(" ");

            if (isValid(numbers)) {
                result += 1;
            }
        }

        return result;
    }

    public static Boolean isValid(String[] numbers) {
        int direction = 0;
        for (int i = 1; i < numbers.length; i++) {
            int diff = Integer.parseInt(numbers[i]) - Integer.parseInt(numbers[i-1]);

            if (i == 1 && diff < 0) {
                direction = -1;
            } else if (i == 1 && diff > 0) {
                direction = 1;
            }

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            if (i > 1 && (direction == -1 && diff > 0) || (direction == 1 && diff < 0)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String filePath = "src/aoc2024/tests/day2.txt";
        ArrayList<String> input = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.add(line);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
