package ru.rufus20145.computemath.hw2;

import java.util.List;

import ru.rufus20145.computemath.CompNewton;

public class Newton {

    private Newton() {
    }

    /**
     * Метод для нахождения корней уравнений F(x) = 0 из списка методом Ньютона
     * 
     * @param equations - список уравнений
     * @param dots      - список начальных приближений
     */
    public static void forList(List<CompNewton> equations, List<NewtonInfo> dots) {
        for (int i = 0; i < dots.size(); i++) {
            System.out.println("Task 2.1." + (i + 1));
            Newton.run(equations, dots.get(i));
        }
    }

    /**
     * Метод для нахождения корней уравнения F(x) = 0 методом Ньютона
     * 
     * @param list     - TODO
     * @param startDot - начальное приближение
     */
    public static void run(List<CompNewton> list, NewtonInfo startDot) {
        CompNewton f1 = list.get(0);
        CompNewton f2 = list.get(1);
        CompNewton dxF1 = list.get(2);
        CompNewton dyF1 = list.get(3);
        CompNewton dxF2 = list.get(4);
        CompNewton dyF2 = list.get(5);

        double x1 = startDot.getStartX();
        double y1 = startDot.getStartY();
        double a = startDot.getA();
        double alpha = startDot.getAlpha();
        double beta = startDot.getBeta();

        int numberOfIterations = 0;

        System.out.println("Start values: x1 = " + x1 + " x2 = " + y1);

        double x;
        double y;
        do {
            x = x1;
            y = y1;

            double f1Value = f1.calculate(x, y, a, alpha, beta);
            double f2Value = f2.calculate(x, y, a, alpha, beta);

            double dxF1Value = dxF1.calculate(x, y, a, alpha, beta);
            double dyF1Value = dyF1.calculate(x, y, a, alpha, beta);
            double dxF2Value = dxF2.calculate(x, y, a, alpha, beta);
            double dyF2Value = dyF2.calculate(x, y, a, alpha, beta);

            x1 = x - (f1Value * dyF2Value - f2Value * dyF1Value) / (dxF1Value * dyF2Value - dyF1Value * dxF2Value);
            y1 = y - (f2Value * dxF1Value - f1Value * dxF2Value) / (dxF1Value * dyF2Value - dyF1Value * dxF2Value);
            numberOfIterations++;
        } while (norm2(f1.calculate(x1, y1, a, alpha, beta), f2.calculate(x1, y1, a, alpha, beta))
                && norm2(x - x1, y - y1));

        System.out.printf("Answer (%.12f, %.12f)%n", x1, y1);
        System.out.println("Number of iterations: " + numberOfIterations);
    }

    /**
     * Метод нахождения нормы в виде корня из суммы квадратов двух чисел
     * 
     * @param a - первое число
     * @param b - второе число
     * @return true, если норма больше точности, иначе false
     */
    private static boolean norm2(double a, double b) {
        return Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2))) > Main.ACCURACY;
    }
}
