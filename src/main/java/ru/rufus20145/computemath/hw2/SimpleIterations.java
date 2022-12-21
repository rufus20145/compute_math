package ru.rufus20145.computemath.hw2;

import java.util.List;

import ru.rufus20145.computemath.Computable;

public class SimpleIterations {

    private SimpleIterations() {
    }

    static void simpleIterationsForList(List<Computable> problems, List<Computable> derProblems) {
        for (int i = 0; i < problems.size(); i++) {
            System.out.println("Equation " + (i + 1) + ":");
            localizeRoots(problems.get(i), derProblems.get(i));
            System.out.println("\n\n");
        }
    }

    static void localizeRoots(Computable problem, Computable derProblem) {
        int numberOfSolution = 1;
        for (double i = Main.MIN_VALUE; i < Main.MAX_VALUE; i += Main.STEP) {
            if (problem.compute(i) == 0 || problem.compute(i) * problem.compute(i + Main.STEP) < 0) {
                System.out.printf("%nSolution №%d: %.12f%n%n", numberOfSolution++,
                        simpleIterations(problem, i, 1 / derProblem.compute(i)));
            }
        }
    }

    static double simpleIterations(Computable problem, double x, double lambda) {
        double x0;
        int n = 1;
        do {
            System.out.println("Iteration: " + n++);
            x0 = x;
            x = x0 - lambda * problem.compute(x0);
            System.out.printf("x = %.12f%n", x);
        } while (Math.abs(x - x0) > Main.ACCURACY);
        return x;
    }
}
