package ru.rufus20145.computemath.hw3;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;

import lombok.Getter;

public enum Norm {

    NormInf("||X||∞", new ComputableArray() {
        @Override
        public double compute(double[] currValues, double[] prevValues) {
            int size = Math.min(currValues.length, prevValues.length);
            double[] values = new double[size];
            for (int i = 0; i < size; ++i) {
                values[i] = Math.abs(currValues[i] - prevValues[i]);
            }
            DoubleSummaryStatistics stat = Arrays.stream(values).summaryStatistics();
            // System.out.println(stat.getMax());
            return stat.getMax();
        }
    }),
    Norm1("||X||₁", new ComputableArray() {
        @Override
        public double compute(double[] currValues, double[] prevValues) {
            int size = Math.min(currValues.length, prevValues.length);
            double norm = 0;
            for (int i = 0; i < size; ++i) {
                norm += Math.abs(currValues[i] - prevValues[i]);
            }
            return norm;
        }
    }),
    Norm2l("||X||₂ₗ", new ComputableArray() {
        @Override
        public double compute(double[] currValues, double[] prevValues) {
            int size = Math.min(currValues.length, prevValues.length);
            double norm = 0;
            for (int i = 0; i < size; ++i) {
                norm += Math.pow(currValues[i] - prevValues[i], L * 2);
            }
            return Math.pow(norm, 1. / (L * 2));
        }
    });

    /**
     * Параметр для нормы {@code}Norm2l{@code}
     */
    private static final double L = 2;

    /**
     * Название нормы
     */
    @Getter
    private String name;

    /**
     * Функция для вычисления нормы
     */
    private ComputableArray cmp;

    private Norm(String name, ComputableArray cmp) {
        this.name = name;
        this.cmp = cmp;
    }

    public double compute(double[] currValues, double[] prevValues) {
        return cmp.compute(currValues, prevValues);
    }

    private static interface ComputableArray {
        double compute(double[] currValues, double[] prevValues);
    }
}
