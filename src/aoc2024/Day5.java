package aoc2024;

import java.io.File;
import java.util.*;

public class Day5 {
    static int numRules = 1176;
    static int numOrders = 198;
    // static int numRules = 21;
    // static int numOrders = 6;
    static String filePath = "src/aoc2024/tests/day5.txt";

    public static int part1() {
        int result = 0;
        Map<String, Set<String>> orderRules = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            for (int i = 0; i < numRules; i++) {
                String[] numbers = scanner.nextLine().split("\\|");

                if (!orderRules.containsKey(numbers[0])) {
                    orderRules.put(numbers[0], new HashSet<>(List.of(numbers[1])));
                } else {
                    orderRules.get(numbers[0]).add(numbers[1]);
                }
            }

            scanner.nextLine();
            for (int i = 0; i < numOrders; i++) {
                String[] numbers = scanner.nextLine().split(",");

                boolean ok = true;
                for (int j = 0; j < numbers.length - 1; j++) {
                    if (!orderRules.containsKey(numbers[j])) {
                        ok = false;
                        break;
                    }

                    for (int k = j+1; k < numbers.length; k++) {
                        if (!orderRules.get(numbers[j]).contains(numbers[k])) {
                            ok = false;
                            break;
                        }
                    }
                }

                if (ok) {
                    int mid = numbers.length / 2;
                    result += Integer.parseInt(numbers[mid]);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(part1());
    }
}
