package ru.rufus20145.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Первое домашнее задание по вычислительной математике.
 */
public final class App {
    private static final int NUMBER_FOR_FIRST_TASK = 6;
    private static double n = 12;
    private static double a = 6;

    public static void main(String[] args) {
        List<Computable> equations = new ArrayList<>();
        List<DichSegment> dichotomySegments = new ArrayList<>();
        List<NewtPoint> newtonPoints = new ArrayList<>();

        addEquations(equations);
        addDichSegments(dichotomySegments);
        addNewtonPoints(newtonPoints);

        int[] dichotomyIterations;
        int[] newtonIterations;

        dichotomyIterations = runDichotomy(equations, dichotomySegments, 1e-4);
        newtonIterations = runNewton(equations, newtonPoints, 1e-4);
        // computeConstants(equations, newtonPoints, 1e-8);
        System.out.println("Dichotomy iterations: " + Arrays.toString(dichotomyIterations));
        System.out.println("Newton iterations: " + Arrays.toString(newtonIterations));
    }

    /**
     * Вычислительный процесс для метода дихотомии.
     * 
     * @param equations         список уравнений
     * @param dichotomySegments список сегментов для метода дихотомии
     * @param accuracy          требуемая точность
     * @return Массив с количеством итераций для каждого уравнения.
     */
    private static int[] runDichotomy(List<Computable> equations, List<DichSegment> dichotomySegments,
            double accuracy) {
        int[] numbersOfIterations = new int[NUMBER_FOR_FIRST_TASK];
        for (int i = 0; i < NUMBER_FOR_FIRST_TASK; i++) {
            int numberOfIterations = 0;
            Computable equation = equations.get(i);
            DichSegment segment = dichotomySegments.get(i);

            if (equation.compute(segment.getLeft()) * equation.compute(segment.getRight()) > 0) {
                System.out.println("Wrong segment");
                continue;
            }

            System.out.println("Dichotomy method for equation \"" + (char) (i + 97) + "\"");
            do {
                numberOfIterations++;
                double centerCoord = (segment.getLeft() + segment.getRight()) / 2;
                if (equation.compute(centerCoord) * equation.compute(segment.getLeft()) < 0) {
                    segment.setRight(centerCoord);
                } else {
                    segment.setLeft(centerCoord);
                }
                System.out.println(
                        "Iteration: " + numberOfIterations + ". " + "Current accuracy: "
                                + String.format("%.8f.", segment.getLength()));
            } while (Math.abs(segment.getRight() - segment.getLeft()) > accuracy);
            System.out.println(
                    "RESULT: x = " + (Math.random() * (segment.getRight() - segment.getLeft()) + segment.getLeft())
                            + "\n");
            numbersOfIterations[i] = numberOfIterations;
        }
        return numbersOfIterations;
    }

    /**
     * Вычислительный процесс для метода Ньютона.
     * 
     * @param equations    список уравнений
     * @param newtonPoints список точек для метода Ньютона
     * @param accuracy     требуемая точность
     * @return Массив с количеством итераций для каждого уравнения.
     */
    public static int[] runNewton(List<Computable> equations, List<NewtPoint> newtonPoints, double accuracy) {
        int[] numbersOfIterations = new int[NUMBER_FOR_FIRST_TASK];
        for (int i = 0; i < NUMBER_FOR_FIRST_TASK; i++) {
            int numberOfIterations = 0;
            Computable equation = equations.get(i);
            NewtPoint point = newtonPoints.get(i);
            double x = point.getCoordinate();
            double newX;

            System.out.println("Newton method for equation \"" + (char) (i + 97) + "\"");
            do {
                numberOfIterations++;

                newX = x
                        - equation.compute(x) * accuracy / (equation.compute(x + accuracy) - equation.compute(x));
                System.out.println("Iteration: " + numberOfIterations + ". " + "Current accuracy: "
                        + String.format("%.8f", Math.abs(newX - x)));
            } while (Math.abs(newX - x) > accuracy);
            System.out.println("RESULT: x = " + point.getCoordinate() + "\n");
            numbersOfIterations[i] = numberOfIterations;
        }
        return numbersOfIterations;
    }

    /**
     * @param equations Лист, в который добавляются уравнения.
     */
    private static void addEquations(List<Computable> equations) {
        equations.add(x -> Math.sin(x) - 2 * Math.pow(x, 2) + 0.5);
        equations.add(x -> Math.pow(x, n) - a);
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
        newtonPoints.add(new NewtPoint(60));
        newtonPoints.add(new NewtPoint(3));
    }
}
