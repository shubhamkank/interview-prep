package com.interview.leetcode;

import java.util.Arrays;
import java.util.Stack;

public class MaximalRectangle {

    /* Time complexity: O(m * n), Space complexity: O(n)
       Sub-problem: Area of tallest rectangle which includes element (i, j)
     */
    public static int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        /* height counts the number of successive '1's above (plus the current one).
           The value of left & right means the boundaries of the rectangle which contains the current point with a
           height of value height
         */
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        Arrays.fill(right, n);

        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;

            //compute height
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0') {
                    height[j] = 0;
                } else {
                    height[j]++;
                }
            }

            //compute left boundary
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            //compute right boundary
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], curRight);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }

            /* Let the maximal rectangle area at row i and column j be computed by [right(i,j) - left(i,j)]*height(i,j).
               Actually, that single expression cannot guarantee it is the largest maximal rectangle containing (i, j)
               and up to row i. It is actually, the tallest rectangle containing (i, j) and up to row i while making as
               wide as possible. And the key point is that the global maximal rectangle must be a such tallest rectangle
               somewhere at (i, j)
             */
            for(int j = 0; j < n; j++) {
                maxArea = Math.max(height[j] * (right[j] - left[j]), maxArea);
            }
        }
        return maxArea;
    }

    /* Time complexity: O(m * n), Space complexity: O(n)
     */
    public static int maximalRectangle2(char[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[] heights = new int[n];
        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, longestRectangleInHistogram(heights));
        }
        return maxArea;
    }

    private static int longestRectangleInHistogram(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while(i < heights.length) {
            if(stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                maxArea = Math.max(maxArea, heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
            }
        }

        while(!stack.isEmpty()) {
            int top = stack.pop();
            maxArea = Math.max(maxArea, heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        char[][] matrix1 = new char[][]{
                {'0', '0', '0', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '1', '0', '0'},
                {'0', '1', '1', '1', '1', '1', '0'}
        };

        System.out.println(maximalRectangle2(matrix1));

        char[][] matrix2 = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        System.out.println(maximalRectangle2(matrix2));
    }
}
