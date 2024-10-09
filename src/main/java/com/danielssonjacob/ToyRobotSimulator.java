package com.danielssonjacob;

import com.danielssonjacob.entities.Board;
import com.danielssonjacob.entities.Robot;

import java.io.*;

public class ToyRobotSimulator {
    private static final int NORTH_ROTATION = 0;
    private static final int EAST_ROTATION = 90;
    private static final int SOUTH_ROTATION = 180;
    private static final int WEST_ROTATION = 270;
    private final Board board;
    private final Robot robot;
    private final File file;

    public ToyRobotSimulator(Board board, Robot robot, File file) {
        this.board = board;
        this.file = file;
        this.robot = robot;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] command = line.split(" ");
                switch (command[0]) {
                    case "PLACE":
                        String[] placement = command[1].split(",");
                        placeRobot(Integer.parseInt(placement[0]), Integer.parseInt(placement[1]), placement[2]);
                        break;
                    case "MOVE":
                        moveRobot();
                        break;
                    case "LEFT":
                        rotateRobotLeft();
                        break;
                    case "RIGHT":
                        rotateRobotRight();
                        break;
                    case "REPORT":
                        report();
                        break;
                    default:
                        throw new RuntimeException("Invalid command");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void rotateRobotLeft() {
        if(robot.isOnBoard()) {
            robot.rotateLeft();
        }
    }

    private void rotateRobotRight() {
        if(robot.isOnBoard()) {
            robot.rotateRight();
        }
    }

    private void placeRobot(int x, int y, String direction) {
        if (x < 0
                || x >= board.getXDimension()
                || y < 0
                || y >= board.getXDimension()
                || robot.isOnBoard()) {
            return;
        }
        robot.setXPlacement(x);
        robot.setYPlacement(y);
        robot.setRotation(getRotation(direction));
        robot.setOnBoard(true);
    }

    private void moveRobot() {
        if (!robot.isOnBoard()) {
            return;
        }
        int x = robot.getXPlacement();
        int y = robot.getYPlacement();
        switch (robot.getRotation()) {
            case NORTH_ROTATION:
                y++;
                break;
            case EAST_ROTATION:
                x++;
                break;
            case SOUTH_ROTATION:
                y--;
                break;
            case WEST_ROTATION:
                x--;
                break;
            default:
                throw new RuntimeException("Invalid rotation");
        }
        if (x < 0 || x >= board.getXDimension() || y < 0 || y >= board.getYDimension()) {
            return;
        }
        robot.setXPlacement(x);
        robot.setYPlacement(y);
    }

    private void report() {
        if (!robot.isOnBoard()) {
            return;
        }
        System.out.println(robot.getXPlacement() + "," + robot.getYPlacement() + "," + getDirection(robot.getRotation()));
    }

    private String getDirection(int rotation) {
        return switch (rotation) {
            case NORTH_ROTATION -> "NORTH";
            case EAST_ROTATION -> "EAST";
            case SOUTH_ROTATION -> "SOUTH";
            case WEST_ROTATION -> "WEST";
            default -> throw new RuntimeException("Invalid rotation");
        };
    }

    private int getRotation(String direction) {
        return switch (direction) {
            case "NORTH" -> NORTH_ROTATION;
            case "EAST" -> EAST_ROTATION;
            case "SOUTH" -> SOUTH_ROTATION;
            case "WEST" -> WEST_ROTATION;
            default -> throw new RuntimeException("Invalid direction");
        };
    }

}
