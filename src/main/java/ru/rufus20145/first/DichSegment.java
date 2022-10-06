package ru.rufus20145.first;

public class DichSegment {
    private double leftCoordinate;
    private double rightCoordinate;

    public DichSegment(double leftCoordinate, double rightCoordinate) {
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
