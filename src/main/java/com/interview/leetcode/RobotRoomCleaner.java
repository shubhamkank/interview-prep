package com.interview.leetcode;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner {

    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0);
    }

    public void dfs(Robot robot, Set<String> visited, int x, int y, int dir) {
        String key = x + "," + y;
        if(visited.contains(key)) {
            return;
        }

        robot.clean();
        visited.add(key);

        for (int i = 0; i < 4; i++) {
            int newDir = (dir + i) % 4;
            if(!robot.move()) {
                robot.turnRight();
                continue;
            }

            int newX = x, newY = y;

            switch (newDir) {
                case 0:
                    newX = x - 1;
                    break;
                case 1:
                    newY = y + 1;
                    break;
                case 2:
                    newX = x + 1;
                    break;
                case 3:
                    newY = y - 1;
                    break;
                default:
                    break;
            }

            dfs(robot, visited, newX, newY, newDir);

            robot.turnRight();
            robot.turnRight();
            robot.move();
            robot.turnLeft();
        }
    }

    public static void main(String[] args) {

    }
}


interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}