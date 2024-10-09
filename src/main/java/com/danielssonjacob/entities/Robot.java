package com.danielssonjacob.entities;

public class Robot {
    private int xPlacement = -1;
    private int yPlacement = -1;
    private boolean onBoard;
    private int rotation;

    public int getXPlacement() {
        return xPlacement;
    }

    public void setXPlacement(int xPlacement) {
        this.xPlacement = xPlacement;
    }

    public int getYPlacement() {
        return yPlacement;
    }

    public void setYPlacement(int yPlacement) {
        this.yPlacement = yPlacement;
    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public void rotateLeft() {
        rotation = rotation - 90;
        if (rotation < 0) {
            rotation = rotation + 360;
        }
    }

    public void rotateRight() {
        rotation = rotation + 90;
        if (rotation >= 360) {
            rotation = rotation - 360;
        }
    }
}
