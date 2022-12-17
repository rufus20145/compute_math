package ru.rufus20145.computemath.hw1;

public class DichSegment {
    private double leftCoordinate;
    private double rightCoordinate;

    public DichSegment(double leftCoordinate, double rightCoordinate) {
        // swap if left > right
        if (leftCoordinate > rightCoordinate) {
            double temp = leftCoordinate;
            leftCoordinate = rightCoordinate;
            rightCoordinate = temp;
        }
        this.leftCoordinate = leftCoordinate;
        this.rightCoordinate = rightCoordinate;
    }

    public double getLength() {
        return rightCoordinate - leftCoordinate;
    }

    public double getLeft() {
        return leftCoordinate;
    }

    public double getRight() {
        return rightCoordinate;
    }

    public void setLeft(double leftCoordinate) {
        this.leftCoordinate = leftCoordinate;
    }

    public void setRight(double rightCoordinate) {
        this.rightCoordinate = rightCoordinate;
    }
}
