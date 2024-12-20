package aoc2024;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Day4 {
    static int N = 140;

    public static boolean inBounds(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public static boolean equals(String a, String b) {
        return Objects.equals(a, b);
    }

    public static boolean validLetters(ArrayList<ArrayList<String>> graph, int i, int j) {
        return Objects.equals(graph.get(i).get(j), "M") || Objects.equals(graph.get(i).get(j), "S");
    }

    public static int part2(ArrayList<ArrayList<String>> graph) {
        int result = 0;

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                if (!Objects.equals(graph.get(i).get(j), "A")) {
                    continue;
                }
                if (!inBounds(i-1, j-1) || !inBounds(i+1, j+1) || !inBounds(i-1, j+1) || !inBounds(i+1, j-1)) {
                    continue;
                }
                if (!validLetters(graph, i-1, j-1) || !validLetters(graph, i+1, j+1) || !validLetters(graph, i-1, j+1) || !validLetters(graph, i+1, j-1)) {
                    continue;
                }

                if (!equals(graph.get(i-1).get(j-1), graph.get(i+1).get(j+1)) && !equals(graph.get(i-1).get(j+1), graph.get(i+1).get(j-1))) {
                    result++;
                }
            }
        }

        return result;
    }

    public static int part1(ArrayList<ArrayList<String>> graph) {
        int result = 0;

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                if (!Objects.equals(graph.get(i).get(j), "X")) {
                    continue;
                }

                int[][] directions = {
                        {-1, 0}, {1, 0},
                        {0, -1}, {0, 1},
                        {1, -1}, {1, 1},
                        {-1, -1}, {-1, 1}
                };

                for (int[] direction: directions) {
                    int position = 0;
                    int currI = i;
                    int currJ = j;
                    for (int l = 0; l < 4; l++) {
                        if (!inBounds(currI, currJ)) {
                            break;
                        }
                        if (!Objects.equals(graph.get(currI).get(currJ), String.valueOf("XMAS".charAt(position)))) {
                            break;
                        }

                        currI += direction[0];
                        currJ += direction[1];
                        position++;
                    }

                    if (position == 4) {
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> graph = new ArrayList<>();

        String filePath = "src/aoc2024/tests/Day4.txt";
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("");
                graph.add(new ArrayList<>(Arrays.asList(line)));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(part1(graph));
        System.out.println(part2(graph));
    }
}
