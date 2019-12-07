package com.interview.interviewbit.arrays;

public class SpiralOrderMatrixII {

    public int[][] generateMatrix(int A) {
        int[][] result = new int[A][A];

        int count = 1;
        int direction = 0;
        int top = 0, bottom = A - 1, left = 0, right = A - 1;

        while(top <= bottom && left <= right) {
            if(direction == 0) {
                for(int i = left; i <= right; i++) {
                    result[top][i] = count;
                    count++;
                }
                top++;
            } else if(direction == 1) {
                for(int i = top; i <= bottom; i++) {
                    result[i][right] = count;
                    count++;
                }
                right--;
            } else if(direction == 2) {
                for(int i = right; i >= left; i--) {
                    result[bottom][i] = count;
                    count++;
                }
                bottom--;
            } else if(direction == 3) {
                for(int i = bottom; i >= top; i--) {
                    result[i][left] = count;
                    count++;
                }
                left++;
            }
            direction = (direction + 1) % 4;
        }

        return result;
    }
}
