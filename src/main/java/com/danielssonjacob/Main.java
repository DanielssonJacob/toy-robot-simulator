package com.danielssonjacob;

import com.danielssonjacob.entities.Board;
import com.danielssonjacob.entities.Robot;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        final Robot robot = new Robot();
        final Board board = new Board(5, 5);
        final ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/main/resources/input.txt"));
        toyRobotSimulator.run();
    }
}