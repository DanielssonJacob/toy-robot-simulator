package com.danielssonjacob.entities;

public class Board {
    private final int xDimension;
    private final int yDimension;

    public Board(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
    }

    public int getXDimension() {
        return xDimension;
    }

    public int getYDimension() {
        return yDimension;
    }


}
