package aoc2024;

import java.io.File;
import java.security.KeyPair;
import java.util.*;

public class Day6 {
    static int N = 130;
    static List<List<String>> graph = new ArrayList<>();
    static String filePath = "src/aoc2024/tests/day6.txt";

    static boolean inBounds(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public static int part1() {
        int d = -1;
        int[][] directions = {
                {-1, 0}, {0, 1},
                {1, 0}, {0, -1}
        };

        int currI = 0, currJ = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!Objects.equals(graph.get(i).get(j), "^") && !Objects.equals(graph.get(i).get(j), "v") &&
                        !Objects.equals(graph.get(i).get(j), ">") && !Objects.equals(graph.get(i).get(j), "<")) {
                    continue;
                }

                d = switch (graph.get(i).get(j)) {
                    case "^" -> 0;
                    case ">" -> 1;
                    case "v" -> 2;
                    case "<" -> 3;
                    default -> -1;
                };

                currI = i;
                currJ = j;
                break;
            }
        }

        Set<List<Integer>> visited = new HashSet<>();
        while (inBounds(currI, currJ)) {
            visited.add(new ArrayList<>(List.of(currI, currJ)));

            if (!inBounds(currI+directions[d][0], currJ+directions[d][1])) {
                break;
            }

            if (Objects.equals(graph.get(currI+directions[d][0]).get(currJ+directions[d][1]), "#")) {
                d = (d+1) % 4;
            }

            currI += directions[d][0];
            currJ += directions[d][1];
        }

        return visited.size();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("");
                graph.add(List.of(line));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(part1());
    }
}
