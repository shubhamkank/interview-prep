package com.interview.leetcode;

public class RangeSumQuery2DImmutable {

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }

    //Space complexity: O(m * n)
    static class NumMatrix {

        int[][] dp;
        int m, n;

        public NumMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            m = matrix.length;
            n = matrix[0].length;
            dp = new int[m + 1][n + 1];
            populateDPMatrix(matrix);
        }

        //Time complexity: O(m * n)
        private void populateDPMatrix(int[][] matrix) {
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        //Time complexity: O(1)
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
        }
    }
}
