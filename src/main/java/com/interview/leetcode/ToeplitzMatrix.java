package com.interview.leetcode;

import java.util.*;

public class ToeplitzMatrix {

    //Time complexity: O(MN), Space complexity: O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {
        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[0].length; j++) {
                //Compare every element with its top left element
                if(matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    //Time complexity: O(MN), Space complexity: O(M + N)
    //Two coordinates (r1, c1) and (r2, c2) are on the same diagonal if and only if r1 - c1 == r2 - c2
    public boolean isToeplitzMatrix2(int[][] matrix) {
        Map<Integer, Integer> groups = new HashMap();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                if (!groups.containsKey(r-c))
                    groups.put(r-c, matrix[r][c]);
                else if (groups.get(r-c) != matrix[r][c])
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
