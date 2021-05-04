package com.dsalgo.practice;

public class RobotBoundedInCircle {

    public static boolean isRobotBoundedWithAngle(String instructions) {

        int x = 0, y = 0;

        // up / bottom to top
        // down/ top to bottom
        // to left
        // to right
        // 0
        // 90  | 360 - 90 = 270
        // 180 | 90 - 90 = 0
        // 270 | 180 - 90 = 90
        int angle = 0;
        for(int i=0;i<instructions.length();i++) {
            if (instructions.charAt(i)=='G') {
                switch(angle) {
                    case 0:
                    case 360:
                        y++;
                        break;
                    case 90:
                    case -90:
                        x--;
                        break;
                    case 180:
                    case -180:
                        y--;
                        break;
                    case 270:
                    case -270:
                        x++;
                        break;
                }
            }
            if (instructions.charAt(i)=='L') {
                angle += 90;
            }
            if (instructions.charAt(i)=='R') {
                angle -= 90;
            }
            if (angle == 360 || angle == -360) {
                angle = 0;
            }
        }
        return (x == 0 && y == 0) || angle != 0;
    }

    public static boolean isRobotBounded(String instructions) {
        // to up = 0
        // to right = 1
        // to down = 2
        // to left = 3
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial position is in the center
        int x = 0, y = 0;
        // facing upwards
        int idx = 0;
        for (char i : instructions.toCharArray()) {
            if (i == 'L')
                idx = (idx + 3) % 4;
            else if (i == 'R')
                idx = (idx + 1) % 4;
            else {
                x += directions[idx][0];
                y += directions[idx][1];
            }
        }

        // after one cycle either robot returns into initial position or robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);
    }
    public static void main(String args[]) {
        System.out.println("GLGL \n"+RobotBoundedInCircle.isRobotBounded("GLGL"));
        System.out.println("GLGL \n"+RobotBoundedInCircle.isRobotBoundedWithAngle("GLGL"));

        System.out.println("RGL \n"+RobotBoundedInCircle.isRobotBounded("RGL"));
        System.out.println("RGL \n"+RobotBoundedInCircle.isRobotBoundedWithAngle("RGL"));

        System.out.println("RRGRRGLLLRLGGLGLLGRLRLGLRLRRGLGGLLRRRLRLRLLGRGLGRRRGRLG \n"+RobotBoundedInCircle.isRobotBounded("RRGRRGLLLRLGGLGLLGRLRLGLRLRRGLGGLLRRRLRLRLLGRGLGRRRGRLG"));
        System.out.println("RRGRRGLLLRLGGLGLLGRLRLGLRLRRGLGGLLRRRLRLRLLGRGLGRRRGRLG \n"+RobotBoundedInCircle.isRobotBoundedWithAngle("RRGRRGLLLRLGGLGLLGRLRLGLRLRRGLGGLLRRRLRLRLLGRGLGRRRGRLG"));
    }
}
