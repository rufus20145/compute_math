package ru.rufus20145.computemath.hw2;

import lombok.Getter;

@Getter
public class NewtonInfo {
    private double startX;
    private double startY;
    private double a;
    private double alpha;
    private double beta;

    public NewtonInfo(double a, double alpha, double betta, double x, double y) {
        this.startX = x;
        this.startY = y;
        this.a = a;
        this.alpha = alpha;
        this.beta = betta;
    }
}
