package com.danielssonjacob;

import com.danielssonjacob.entities.Board;
import com.danielssonjacob.entities.Robot;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ToyRobotSimulatorTest {

    @Test
    void shouldNotPlaceRobotBeforePlaceCommand() {
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/placeCommand.txt"));
        assertFalse(robot.isOnBoard());
        toyRobotSimulator.run();
        assertTrue(robot.isOnBoard());
    }

    @Test
    void shouldNotPlaceRobotOutsideBoard() {
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/placeOutsideBoard.txt"));
        assertFalse(robot.isOnBoard());
        toyRobotSimulator.run();
        assertFalse(robot.isOnBoard());
    }

    @Test
    void shouldNotMoveRobotBeforePlaceCommand() {
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/moveCommand.txt"));
        int initialX = robot.getXPlacement();
        int initialY = robot.getYPlacement();
        assertFalse(robot.isOnBoard());
        toyRobotSimulator.run();
        assertFalse(robot.isOnBoard());
        assertEquals(initialX, robot.getXPlacement());
        assertEquals(initialY, robot.getYPlacement());
    }

    @Test
    void shouldNotMoveRobotOutsideBoard() {
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/moveOutsideBoard.txt"));
        toyRobotSimulator.run();
        assertTrue(robot.getXPlacement() < board.getXDimension());
        assertTrue(robot.getYPlacement() < board.getYDimension());
        assertTrue(robot.getXPlacement() >= 0);
        assertTrue(robot.getYPlacement() >= 0);
    }

    @Test
    void shouldOnlyReportActionsAfterPlaceCommand() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/reportCommand.txt"));
        toyRobotSimulator.run();
        assertEquals(outputStreamCaptor.toString().trim(),"3,3,NORTH");
    }

    @Test
    void shouldThrowExceptionForInvalidDirection() {
        Board board = new Board(5, 5);
        Robot robot = new Robot();
        ToyRobotSimulator toyRobotSimulator = new ToyRobotSimulator(board, robot, new File("src/test/resources/invalidDirection.txt"));
        assertThrows(RuntimeException.class, toyRobotSimulator::run);
    }

}