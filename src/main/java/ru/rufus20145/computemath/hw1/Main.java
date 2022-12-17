package ru.rufus20145.computemath.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.rufus20145.computemath.Computable;

/**
 * Первое домашнее задание по вычислительной математике.
 */
public final class Main {
    private static final double FIRST_TASK_ACCURACY = 1e-4;
    private static final double SECOND_TASK_ACCURACY = 1e-8;

    private static final int NUMBER_FOR_FIRST_TASK = 6;
    private static final int SECOND_TASK_START = 6;
    private static final int NUMBER_FOR_SECOND_TASK = 8;

    private static final double N = 12;
    private static final double A = 6;

    public static void main(String[] args) {
        List<Computable> equations = new ArrayList<>();
        List<DichSegment> dichotomySegments = new ArrayList<>();
        List<NewtPoint> newtonPoints = new ArrayList<>();

        addEquations(equations);
        Dichotomy.addDichSegments(dichotomySegments);
        Newton.addNewtonPoints(newtonPoints);

        int[] dichIterations = new int[NUMBER_FOR_FIRST_TASK];
        int[] newtIterations = new int[NUMBER_FOR_FIRST_TASK];

        System.out.println("==================================================\nFirst task:");
        for (int i = 0; i < NUMBER_FOR_FIRST_TASK; i++) {
            System.out.println("=========================\nDichotomy method for equation \"" + (char) (i + 97) + "\"");
            dichIterations[i] = Dichotomy.runDichotomy(equations.get(i), dichotomySegments.get(i), FIRST_TASK_ACCURACY);

            System.out.println("============\nNewton method for equation \"" + (char) (i + 97) + "\"");
            newtIterations[i] = Newton.runNewton(equations.get(i), newtonPoints.get(i), FIRST_TASK_ACCURACY);

            System.out.println("=========================\n");
        }

        String[][] array = prepareArrays(dichIterations, newtIterations, newtIterations, newtIterations,
                newtIterations);
        System.out.println("\nDichotomy iterations: " + Arrays.toString(array[0]));
        System.out.println("Newton iterations   : " + Arrays.toString(array[1]));
        System.out.println("==================================================");

        System.out.println("\n\n==================================================\nSecond task:");
        for (int i = SECOND_TASK_START; i < NUMBER_FOR_SECOND_TASK; i++) {
            System.out.printf("Newton method for %s constant:%n", i == 6 ? "pi" : "e");
            Newton.runNewton(equations.get(i), newtonPoints.get(i), SECOND_TASK_ACCURACY);
        }
        System.out.println("==================================================");
    }

    /**
     * @param inArrays Массивы, которые нужно отформатировать в один массив.
     * @return Массив, в котором каждый элемент - это строка заданной длины.
     */
    private static String[][] prepareArrays(int[]... inArrays) {
        int maxLength = 0;
        for (int[] is : inArrays) {
            for (int is2 : is) {
                if (String.valueOf(is2).length() > maxLength) {
                    maxLength = String.valueOf(is2).length();
                }
            }
        }

        String[][] outArrays = new String[inArrays.length][NUMBER_FOR_FIRST_TASK];
        String format = "%" + maxLength + "d";
        for (int i = 0; i < outArrays.length; i++) {
            for (int j = 0; j < outArrays[i].length; j++) {
                outArrays[i][j] = String.format(format, inArrays[i][j]);
            }
        }

        System.out.println(inArrays.length + " arrays was prepared.");
        return outArrays;
    }

    /**
     * @param equations Лист, в который добавляются уравнения.
     */
    private static void addEquations(List<Computable> equations) {
        equations.add(x -> Math.sin(x) - 2 * Math.pow(x, 2) + 0.5);
        equations.add(x -> Math.pow(x, N) - A);
        equations.add(x -> Math.sqrt(1 - Math.pow(x, 2)) - Math.exp(x) + 0.1);
        equations.add(x -> Math.pow(x, 6) - 5 * Math.pow(x, 3) - 2);
        equations.add(x -> Math.log(x) - (1 / (1 + Math.pow(x, 2))));
        equations.add(x -> Math.pow(3, x) - 5 * Math.pow(x, 2) + 1);
        equations.add(Math::sin);
        equations.add(x -> Math.log(x) - 1);
    }
}
