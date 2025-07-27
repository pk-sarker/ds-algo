package algo.Backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 * <p>
 * The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
 * <p>
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 * <p>
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 * <p>
 * Design an algorithm to clean the entire room using the following APIs:
 * <p>
 * interface Robot {
 * // returns true if next cell is open and robot moves into the cell.
 * // returns false if next cell is obstacle and robot stays on the current cell.
 * boolean move();
 * <p>
 * // Robot will stay on the same cell after calling turnLeft/turnRight.
 * // Each turn will be 90 degrees.
 * void turnLeft();
 * void turnRight();
 * <p>
 * // Clean the current cell.
 * void clean();
 * }
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 */
public class RobotRoomCleaner {
    // directions, 0-> Up, 1-> Down, 2-> Left, 3-> Right
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    Set<int[]> visited = new HashSet<>();
    Robot robot;

    public void cleanRoom(Robot robot) {
        this.robot = robot; // saving the robot object so that we don't have to pass it every recursive call
        backtrack(0, 0, 0);
    }

    public void backtrack(int row, int col, int direction) {
        this.visited.add(new int[]{row, col});
        robot.clean();

        // Explore all 4 direction from current location
        for (int i = 0; i < 4; i++) { // 4 for four direction
            int nextDir = (direction + i) % 4;

            // check if the new cell is already visited, if visited then trun right and go to next
            if (!this.visited.contains(new int[]{row + directions[nextDir][0], col + directions[nextDir][1]}) && robot.move()) {
                // It will keep making backtrack() call until gets blocked
                backtrack(row + directions[nextDir][0], col + directions[nextDir][1], nextDir);
                // When gets blocked it should move back
                robot.turnRight(); // turn 90 degree right from current position
                // turn 90 degree right from current position, which is 180 degree from initial direction
                // which is just the opposite direction
                robot.turnRight();
                robot.move(); // move 1 step back
                // Now keep the direction same as before
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }

    public static void main(String args[]) {

    }
}