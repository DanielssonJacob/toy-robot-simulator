package com.danielssonjacob.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void shouldNotRotateLeftPast0() {
        Robot robot = new Robot();
        robot.setRotation(0);
        robot.rotateLeft();
        assertEquals(270, robot.getRotation());
    }

    @Test
    void shouldNotRotateRightPast360() {
        Robot robot = new Robot();
        robot.setRotation(270);
        robot.rotateRight();
        assertEquals(0, robot.getRotation());
    }
}