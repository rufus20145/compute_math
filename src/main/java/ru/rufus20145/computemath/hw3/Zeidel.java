package ru.rufus20145.computemath.hw3;

import java.util.ArrayList;
import java.util.List;

public class Zeidel {
    public static List<double[]> forList(List<double[][]> problems, Norm norm) {
        List<double[]> result = new ArrayList<>();
        for (double[][] problem : problems) {
            result.add(run(problem, norm));
        }
        return result;
    }

    public static List<double[]> forList(List<double[][]> problems) {

        List<double[]> result = new ArrayList<>();
        for (double[][] problem : problems) {
            System.out.println("Уравнение " + (problems.indexOf(problem) + 1) + ":");
            for (Norm norm : Norm.values()) {
                result.add(run(problem, norm));
            }
            // TODO: удалить лишнее
            System.out.println("Результаты:");
            Main.printMatrix(result.get(result.size() - 1));
        }

        return result;
    }

    public static double[] run(double[][] problem, Norm norm) {
        int iterNum = 0;

        double[] currValues = new double[problem.length];
        double[] prevValues = new double[problem.length];
        double currAccuracy = 0;

        do {
            currValues = new double[problem.length];

            for (int i = 0; i < problem.length; i++) {
                double sum = 0;
                for (int j = 0; j < problem.length; j++) {
                    if (j < i) {
                        sum += problem[i][j] * currValues[j];
                    } else if (j > i) {
                        sum += problem[i][j] * prevValues[j];
                    }
                }
                currValues[i] = (problem[i][problem.length] - sum) / problem[i][i];
            }

            currAccuracy = norm.compute(currValues, prevValues);
            prevValues = currValues;
            iterNum++;
        } while (currAccuracy > Main.getAccuracy());
        System.out.printf("Норма: %7s, количество итераций: %2d, точность: %19.18f%n", norm, iterNum, currAccuracy);

        return currValues;
    }
}
