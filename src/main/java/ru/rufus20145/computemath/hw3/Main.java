package ru.rufus20145.computemath.hw3;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final double ACCURACY = 1e-15;

    public static void main(String[] args) {

        List<double[][]> problems = new ArrayList<>();
        int number = 1;
        addTask1(problems);

        System.out.println("Задание 1:");
        for (double[] matrix : Gauss.forList(problems)) {
            System.out.println("Решение системы " + number++ + ":");
            printMatrix(matrix);
        }

        problems.clear();
        number = 1;
        addTask2(problems);

        System.out.println("Задание 2:");

        System.out.println("     МПИ:");
        SimpleIterations.forList(problems);

        // System.out.println("     Метод Зейделя:");
        // Zeidel.forList(problems);
    }

    private static void addTask1(List<double[][]> problems) {
        problems.add(
                new double[][] { { 2, 2, -1, 1, 4 }, { 4, 3, -1, 2, 6 }, { 8, 5, -3, 4, 12 }, { 3, 3, -2, 2, 6 } });
        problems.add(new double[][] { { 1, 7, -9, -8, -7 }, { -3, -18, 23, 28, 5 }, { 0, -3, 6, -1, 8 },
                { -1, -1, 1, 18, -29 } });
        problems.add(new double[][] { { 3, -3, 7, -4, 0 }, { -6, 9, -21, 9, 9 }, { 9, -12, 30, -22, -2 },
                { 6, 0, 6, -31, 37 } });
        problems.add(
                new double[][] { { 9, -5, -6, 3, -8 }, { 1, -7, 1, 0, 38 }, { 3, -4, 9, 0, 47 }, { 6, -1, 9, 8, -8 } });
        problems.add(new double[][] { { -6, -5, -3, -8, 101 }, { 5, -1, -5, -4, 51 }, { -6, 0, 5, 5, -53 },
                { -7, -2, 8, 5, -63 } });
    }

    private static void addTask2(List<double[][]> problems) {
        problems.add(
                new double[][] { { 12, -3, -1, 3, -31 }, { 5, 20, 9, 1, 90 }, { 6, -3, -21, -7, 119 },
                        { 8, -7, 3, -27, 71 } });
        problems.add(
                new double[][] { { 28, 9, -3, -7, -159 }, { -5, 21, -5, -3, 63 }, { -8, 1, -16, 5, -45 },
                        { 0, -2, 5, 8, 24 } });
        problems.add(
                new double[][] { { 21, 1, -8, 4, -119 }, { -9, -23, -2, 4, 79 }, { 7, -1, -17, 6, -24 },
                        { 8, 8, -4, -26, -52 } });
        problems.add(
                new double[][] { { 14, -4, -2, 3, 38 }, { -3, 23, -6, -9, -195 }, { -7, -8, 21, -5, -27 },
                        { -2, -2, 8, 18, 142 } });
    }

    /**
     * Вывод матрицы в консоль
     * 
     * @param matrix - матрица
     */
    static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%4.2f ", matrix[i][j]);
            }
        }
    }

    /**
     * Вывод вектора (матрицы 1xN) в консоль
     * 
     * @param matrix - вектор (матрица 1xN)
     */
    static void printMatrix(double[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("%5.2f%n", matrix[i]);
        }
    }

    /**
     * Возвращает точность вычислений (в данный момент {@value #ACCURACY})
     * 
     * @return точность вычислений
     */
    public static double getAccuracy() {
        return ACCURACY;
    }
}
