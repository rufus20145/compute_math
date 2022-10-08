package ru.rufus20145.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        addDichSegments(dichotomySegments);
        addNewtonPoints(newtonPoints);

        int[] dichIterations = new int[NUMBER_FOR_FIRST_TASK];
        int[] newtIterations = new int[NUMBER_FOR_FIRST_TASK];

        System.out.println("==================================================\nFirst task:");
        for (int i = 0; i < NUMBER_FOR_FIRST_TASK; i++) {
            System.out.println("=========================\nDichotomy method for equation \"" + (char) (i + 97) + "\"");
            dichIterations[i] = runDichotomy(equations.get(i), dichotomySegments.get(i), FIRST_TASK_ACCURACY);

            System.out.println("============\nNewton method for equation \"" + (char) (i + 97) + "\"");
            newtIterations[i] = runNewton(equations.get(i), newtonPoints.get(i), FIRST_TASK_ACCURACY);

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
            runNewton(equations.get(i), newtonPoints.get(i), SECOND_TASK_ACCURACY);
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
     * Вычислительный процесс для метода Ньютона.
     * 
     * @param equation Уравнение.
     * @param point    Точка для начала вычислений.
     * @param accuracy Требуемая точность.
     * @return Количество итераций, потребовавшихся для достижения требуемой
     *         точности. Если точность не была достигнута, возвращается -1.
     */
    public static int runNewton(Computable equation, NewtPoint point, double accuracy) {
        int numberOfIterations = 0;
        double currentX = point.getCoordinate();
        double nextX;
        double approximation;
        do {
            double argumentIncrement = currentX * accuracy;
            double funcValue = equation.compute(currentX);
            double diffFuncValue = (equation.compute(currentX + argumentIncrement) - funcValue) / argumentIncrement;
            nextX = currentX - (funcValue / diffFuncValue);
            approximation = Math.abs(nextX - currentX);
            currentX = nextX;
            numberOfIterations++;
            System.out.println(
                    String.format("Iteration: %2d. Current accuracy: %.16f.", numberOfIterations, approximation));
        } while (approximation > accuracy);
        System.out.println("RESULT: x = " + currentX);

        return numberOfIterations;
    }

    /**
     * Вычислительный процесс для метода дихотомии.
     * 
     * @param equation Уравнение.
     * @param segment  Сегмент, на котором будет производиться поиск корня.
     * @param accuracy Требуемая точность.
     * @return Количество итераций, потребовавшееся для достижения требуемой
     *         точности. Если точность не была достигнута, возвращается -1.
     */
    private static int runDichotomy(Computable equation, DichSegment segment, double accuracy) {

        int numberOfIterations = 0;

        // Проверка наличия корня на сегменте
        if (equation.compute(segment.getLeft()) * equation.compute(segment.getRight()) > 0) {
            System.out.println("Wrong segment. Can`t find root.");
            return -1;
        }

        do {
            numberOfIterations++;
            double centerCoord = (segment.getLeft() + segment.getRight()) / 2;
            if (equation.compute(centerCoord) * equation.compute(segment.getLeft()) < 0) {
                segment.setRight(centerCoord);
            } else {
                segment.setLeft(centerCoord);
            }
            System.out.println(
                    String.format("Iteration: %2d. Current accuracy: %.8f.", numberOfIterations, segment.getLength()));
        } while (Math.abs(segment.getRight() - segment.getLeft()) > accuracy);

        System.out.println(
                "RESULT: x = " + (Math.random() * (segment.getRight() - segment.getLeft()) + segment.getLeft()));
        return numberOfIterations;
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

    /**
     * @param dichotomySegments Лист, в который добавляются сегменты для метода
     *                          дихотомии.
     */
    private static void addDichSegments(List<DichSegment> dichotomySegments) {
        dichotomySegments.add(new DichSegment(0, 1));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(0, 1));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(1, 2));
        dichotomySegments.add(new DichSegment(0, 1));
    }

    /**
     * @param newtonPoints Лист, в который добавляются точки для метода Ньютона.
     */
    private static void addNewtonPoints(List<NewtPoint> newtonPoints) {
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(0));
        newtonPoints.add(new NewtPoint(2));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(3));
        newtonPoints.add(new NewtPoint(3));
    }
}
