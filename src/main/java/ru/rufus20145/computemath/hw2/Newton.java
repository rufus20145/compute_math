package ru.rufus20145.computemath.hw2;

import java.util.List;

import ru.rufus20145.computemath.CompNewton;

public class Newton {

    private Newton() {
    }

    public static void newtonForList(List<CompNewton> equations, List<Dot> dots) {
        for (int i = 0; i < dots.size(); i++) {
            System.out.println("Task 2.1." + (i + 1));
            newton(equations, dots.get(i));
        }
    }

    public static void newton(List<CompNewton> equations, Dot dot) {
        CompNewton f1 = equations.get(0);
        CompNewton f2 = equations.get(1);
        CompNewton partDerivXF1 = equations.get(2);
        CompNewton partDerivYF1 = equations.get(3);
        CompNewton partDerivXF2 = equations.get(4);
        CompNewton partDerivYF2 = equations.get(5);

        double x1 = dot.x;
        double y1 = dot.y;
        double a = dot.a;
        double alpha = dot.alpha;
        double betta = dot.betta;

        int numberOfIterations = 0;

        System.out.println("Start values: x1 = " + x1 + " x2 = " + y1);

        double x;
        double y;
        do {
            x = x1;
            y = y1;

            double f1Value = f1.calculate(x, y, a, alpha, betta);
            double f2Value = f2.calculate(x, y, a, alpha, betta);

            double partDerivF1XValue = partDerivXF1.calculate(x, y, a, alpha, betta);
            double partDerivF1YValue = partDerivYF1.calculate(x, y, a, alpha, betta);
            double partDerivF2XValue = partDerivXF2.calculate(x, y, a, alpha, betta);
            double partDerivF2YValue = partDerivYF2.calculate(x, y, a, alpha, betta);

            // double detA1 = f1Value * partDerivF1YValue - f2Value * partDerivF2YValue;
            // double detA2 = f2Value * partDerivF1XValue - f1Value * partDerivF2XValue;

            double detJacobi = partDerivF1XValue * partDerivF2YValue - partDerivF1YValue * partDerivF2XValue;

            x1 = x - f1.calculate(x, y, a, alpha, betta) * detJacobi;
            y1 = y - f2.calculate(x, y, a, alpha, betta) * detJacobi;
            numberOfIterations++;
        } while (norm(f1.calculate(x1, y1, a, alpha, betta), f2.calculate(x1, y1, a, alpha, betta))
                && norm(x - x1, y1 - y));

        System.out.printf("Answer (%.12f, %.12f)%n", x1, y1);
        System.out.println("Number of iterations: " + numberOfIterations);
    }

    private static boolean norm(double a, double b) {
        return Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2))) > Main.ACCURACY;
    }
}
