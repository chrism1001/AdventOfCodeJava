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

    public static int dfs(ArrayList<ArrayList<String>> graph, int i, int j, String currString, String goal, int position, int[] direction) {
        if (Objects.equals(currString, goal)) {
            return 1;
        }
        if (!inBounds(i, j)) {
            return 0;
        }
        if (!Objects.equals(graph.get(i).get(j), String.valueOf(goal.charAt(position)))) {
            return 0;
        }

        int result = 0;
        result += dfs(graph, i+direction[0], j+direction[1], currString+graph.get(i).get(j), goal, position+1, direction);

        return result;
    }

    public static int part1(ArrayList<ArrayList<String>> graph) {
        int result = 0;

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.size(); j++) {
                int[][] directions = {
                        {-1, 0}, {1, 0},
                        {0, -1}, {0, 1},
                        {1, -1}, {1, 1},
                        {-1, -1}, {-1, 1}
                };

                if (Objects.equals(graph.get(i).get(j), "X")) {
                    for (int[] direction: directions) {
                        result += dfs(graph, i, j, "", "XMAS", 0, direction);
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
    }
}
