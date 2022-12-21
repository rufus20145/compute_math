package ru.rufus20145.computemath.hw2;

import java.util.ArrayList;
import java.util.List;

import ru.rufus20145.computemath.CompNewton;
import ru.rufus20145.computemath.Computable;

public class Main {
    private static final int A = 2;
    static final int MIN_VALUE = -100;
    static final int MAX_VALUE = 100;
    static final double STEP = 0.05;
    static final double ACCURACY = 1e-8;

    public static void main(String[] args) {
        List<Computable> task2problems = new ArrayList<>();
        List<Computable> task2derivatives = new ArrayList<>();

        addEquations(task2problems);
        addDerivatives(task2derivatives);

        SimpleIterations.simpleIterationsForList(task2problems, task2derivatives);

        List<CompNewton> task21equations = new ArrayList<>();
        List<Dot> task21dots = new ArrayList<>();
        addNewtons21(task21equations);
        addDots21(task21dots);

        // Newton.newtonForList(task21equations, task21dots);

        List<CompNewton> task22equations = new ArrayList<>();
        addNewtons22(task22equations);

        // System.out.println("Task 2.2");
        // Newton.newton(task22equations, new Dot(0, 0, 0, -0.5, 1));
    }

    private static void addNewtons22(List<CompNewton> list) {
        list.add((x, y, a, alpha, beta) -> Math.pow(x, 2) + Math.pow(y, 2) - 2);
        list.add(((x, y, a, alpha, beta) -> Math.exp(x - 1) + Math.pow(y, 3) - 2));
        list.add((x, y, a, alpha, beta) -> 2 * x);
        list.add((x, y, a, alpha, beta) -> 2 * y);
        list.add((x, y, a, alpha, beta) -> Math.exp(x - 1));
        list.add((x, y, a, alpha, beta) -> 2 * Math.pow(y, 2));
    }

    private static void addDots21(List<Dot> list) {
        list.add(new Dot(0.2, 0.6, 2, 0.8, 0.5));
        list.add(new Dot(0.4, 0.8, 2, -0.3, 0.6));
        list.add(new Dot(0.3, 0.2, 3, 1, 0.5));
        list.add(new Dot(0, 0.6, 2, 0.6, 0.6));
    }

    private static void addNewtons21(List<CompNewton> list) {
        list.add((x, y, a, alpha, beta) -> Math.tan(x * y + a) - Math.pow(x, 2));
        list.add((x, y, a, alpha, beta) -> Math.pow(x, 2) * alpha + Math.pow(y, 2) * beta - 1);
        list.add((x, y, a, alpha, beta) -> (1 / Math.pow(Math.cos(x * y + a), 2)) * y - 2 * x);
        list.add((x, y, a, alpha, beta) -> (1 / Math.pow(Math.cos(x * y + a), 2)) * x);
        list.add((x, y, a, alpha, beta) -> 2 * x * alpha);
        list.add((x, y, a, alpha, beta) -> 2 * y * beta);
    }

    private static void addEquations(List<Computable> list) {
        list.add(x -> Math.pow(x, 3) + Math.pow(x, 2) - x + 0.5);
        list.add(x -> Math.pow(Math.E, x) / A - x - 1);
        list.add(x -> Math.pow(x, 3) - 20 * x + 1);
        list.add(x -> Math.pow(2, x) + Math.pow(x, 2) - 2);
        list.add(x -> x * Math.log(x + 2) - 1 + Math.pow(x, 2));
        list.add(x -> Math.pow(x, 3) / A - A * Math.cos(x));
    }

    private static void addDerivatives(List<Computable> list) {
        list.add(x -> 3 * Math.pow(x, 2) + 2 * x - 1);
        list.add(x -> Math.pow(Math.E, x) / A - 1);
        list.add(x -> 3 * Math.pow(x, 2) - 20);
        list.add(x -> Math.pow(2, x) * Math.log(2) + 2 * x);
        list.add(x -> 2 * x + x / (x + 2) + Math.log(x + 2));
        list.add(x -> 3 * Math.pow(x, 2) / A + A * Math.sin(x));
    }
}
