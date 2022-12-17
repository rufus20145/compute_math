package ru.rufus20145.computemath.hw1;

import java.util.List;

import ru.rufus20145.computemath.Computable;

public class Newton {

    private Newton() {
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
        double approx;
        do {
            double curX = point.getCoordinate();
            double argInc = curX * accuracy;
            double funcVal = equation.compute(curX);
            double diffFuncVal = (equation.compute(curX + argInc) - funcVal) / argInc;
            double nextX = curX - (funcVal / diffFuncVal);
            approx = Math.abs(nextX - curX);
            point.setCoordinate(nextX);
            numberOfIterations++;
            System.out.printf("Iteration: %2d. Current accuracy: %.16f.%n", numberOfIterations, approx);
        } while (approx > accuracy);
        System.out.println("RESULT: x = " + point.getCoordinate());

        return numberOfIterations;
    }

    /**
     * @param newtonPoints Лист, в который добавляются точки для метода Ньютона.
     */
    static void addNewtonPoints(List<NewtPoint> newtonPoints) {
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(0.5));
        newtonPoints.add(new NewtPoint(2));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(1));
        newtonPoints.add(new NewtPoint(3));
        newtonPoints.add(new NewtPoint(3));
    }

}
