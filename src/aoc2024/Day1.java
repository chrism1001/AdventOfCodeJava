package aoc2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Day1 {
    public static int part2(ArrayList<String> input) {
        ArrayList<Integer> leftList = new ArrayList<>();
        HashMap<Integer, Integer> rightFreq = new HashMap<>();

        for (String line: input) {
            String[] numbers = line.split(" {3}");
            leftList.add(Integer.valueOf(numbers[0]));

            if (rightFreq.containsKey(Integer.valueOf(numbers[1]))) {
                int n = rightFreq.get(Integer.valueOf(numbers[1]));
                rightFreq.put(Integer.valueOf(numbers[1]), n + 1);
            } else {
                rightFreq.put(Integer.valueOf(numbers[1]), 1);
            }
        }

        int result = 0;
        for (Integer n: leftList) {
            if (rightFreq.containsKey(n)) {
                result += n * rightFreq.get(n);
            }
        }

        return result;
    }

    public static int part1(ArrayList<String> input) {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        for (String line: input) {
            String[] numbers = line.split(" {3}");
            leftList.add(Integer.valueOf(numbers[0]));
            rightList.add(Integer.valueOf(numbers[1]));
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        int result = 0;
        for (int i = 0; i < leftList.size(); i++) {
            result += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        String filePath = "src/aoc2024/tests/day1.txt";
        ArrayList<String> input = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                input.add(line);
            }

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        System.out.println(part1(input));
        System.out.println(part2(input));
    }
}
