package com.interview.interviewbit.arrays;

import java.util.*;

public class RotateArray {

    //Time complexity: O(n^2) - smaller constant factor
    public void rotate(ArrayList<ArrayList<Integer>> a) {
        int n = a.size();

        for(int i = 0; i < n / 2; i++) {
            for(int j = i; j < n - 1 - i; j++) {
                int temp = a.get(i).get(j);
                a.get(i).set(j, a.get(n - 1 - j).get(i));
                a.get(n - 1 - j).set(i, a.get(n - 1 - i).get(n - 1 - j));
                a.get(n - 1 - i).set(n - 1 - j, a.get(j).get(n - 1 - i));
                a.get(j).set(n - 1 - i, temp);
            }
        }
    }

    //Time complexity: O(n^2)
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
    }
}
