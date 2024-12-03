package aoc2024;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static int mul(String input) {
        Pattern mulRegex = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher m1 = mulRegex.matcher(input);

        int mul = 1;
        if (m1.find()) {
            do {
                Pattern numRegex = Pattern.compile("\\d+");
                Matcher m2 = numRegex.matcher(m1.group());

                if (m2.find()) {
                    do {
                        mul *= Integer.parseInt(m2.group());
                    } while (m2.find(m2.start() + m2.group().length()));
                }

            } while(m1.find(m1.start() + 1));
        }

        return mul;
    }

    public static int part2(String input) {
        int result = 0;

        boolean instructions = true;
        Pattern mulRegex = Pattern.compile("(mul\\(\\d+,\\d+\\))|((don't|do).*?mul\\(\\d+,\\d+\\))");
        Matcher m1 = mulRegex.matcher(input);

        if (m1.find()) {
            do {
                Pattern instructionRegex = Pattern.compile("(don't|do)+");
                Matcher m2 = instructionRegex.matcher(m1.group());

                if (m2.find()) {
                    do {
                        if (Objects.equals(m2.group(), "don't")) {
                            instructions = false;
                        } else if (Objects.equals(m2.group(), "do")) {
                            instructions = true;
                        }
                    } while (m2.find(m2.start() + m2.group().length()));
                }

                if (instructions) {
                    result += mul(m1.group());
                }
            } while(m1.find(m1.start() + m1.group().length()));
        }

        return result;
    }

    public static int part1(String input) {
        int result = 0;

        Pattern mulRegex = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher m1 = mulRegex.matcher(input);

        if (m1.find()) {
            do {
                Pattern numRegex = Pattern.compile("\\d+");
                Matcher m2 = numRegex.matcher(m1.group());

                int mul = 1;
                if (m2.find()) {
                    do {
                        mul *= Integer.parseInt(m2.group());
                    } while (m2.find(m2.start() + m2.group().length()));
                }
                result += mul;

            } while(m1.find(m1.start() + 1));
        }

        return result;
    }

    public static void main(String[] args) {
        String filePath = "src/aoc2024/tests/day3.txt";
        StringBuilder input = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                input.append(scanner.nextLine());
                input.append(System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }

        // System.out.println(part1(input.toString()));
        System.out.println(part2(input.toString()));
    }
}
