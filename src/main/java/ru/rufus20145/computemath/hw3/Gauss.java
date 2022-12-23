package ru.rufus20145.computemath.hw3;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализующий решение СЛАУ методом Гаусса
 */
public class Gauss {

    /**
     * Решение СЛАУ методом Гаусса для списка СЛАУ
     * 
     * @param problems - список СЛАУ
     * @param accuracy - требуемая точность
     * @return список векторов-решений СЛАУ
     */
    public static List<double[]> forList(List<double[][]> problems) {
        List<double[]> result = new ArrayList<>();
        for (double[][] problem : problems) {
            result.add(run(problem));
        }
        return result;
    }

    /**
     * Решение СЛАУ методом Гаусса
     * 
     * @param problem  - СЛАУ
     * @param accuracy - требуемая точность
     * @return вектор-решение СЛАУ
     */
    public static double[] run(double[][] problem) {
        int n = problem.length;
        double[] result = new double[n];

        // Прямой ход
        for (int k = 1; k < n; k++) {
            for (int j = k; j < n; j++) {
                double m = problem[j][k - 1] / problem[k - 1][k - 1];
                for (int i = 0; i < n + 1; i++) {
                    problem[j][i] -= m * problem[k - 1][i];
                }
            }
        }

        // Обратный ход
        for (int k = n - 1; k >= 0; k--) {
            double sum = 0;
            for (int j = k + 1; j < n; j++) {
                sum += problem[k][j] * result[j];
            }
            result[k] = (problem[k][n] - sum) / problem[k][k];
        }
        return result;
    }
}
