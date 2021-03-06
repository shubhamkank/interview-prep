package com.interview.leetcode;

import java.util.Stack;

public class LargestRectangleHistogram {

    /* Brute force approach
       Time complexity: O(n^3), Space complexity: O(1)
       Area between any two bars is limited by the min height between them
    */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int minHeight = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++) {
                    minHeight = Math.min(heights[k], minHeight);
                }
                maxArea = Math.max(minHeight * (j - i + 1), maxArea);
            }
        }
        return maxArea;
    }

    /* Better brute force approach
       Time complexity: O(n^2), Space complexity: O(1)
       Area between any two bars is limited by the min height between them.
       Find the bar of min height for current pair by using the min height bar
       of the previous pair: minHeight = min(minHeight, height[j])
    */
    public static int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        for(int i = 0; i < n; i++) {
            int minHeight = Integer.MAX_VALUE;
            for(int j = i; j < n; j++) {
                minHeight = Math.min(heights[j], minHeight);
                maxArea = Math.max(minHeight * (j - i + 1), maxArea);
            }
        }
        return maxArea;
    }


    /* Divide and conquer approach
       Time complexity: O(nlogn) - Worst case: O(n^2) - sorted array
       Space complexity: O(n) - worst case n recursive calls
    */
    public static int largestRectangleArea3(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }

    private static int calculateArea(int[] heights, int start, int end) {
        if(start > end) {
            return 0;
        }

        int minIndex = start;
        for(int i = start; i <= end; i++) {
            if(heights[minIndex] > heights[i]) {
                minIndex = i;
            }
        }

        return Math.max(heights[minIndex] * (end - start + 1), Math.max(calculateArea(heights, start, minIndex - 1),
                calculateArea(heights, minIndex + 1, end)));
    }


    /* Stack based approach
       Time complexity: O(n)
       Space complexity: O(n) - worst case - sorted array
    */
    public static int largestRectangleArea4(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0, i = 0;

        /* 1. For each bar, take its height as the rectangle's height. Then find the left and right boundaries of this rectangle.
           2. (The second top bar in stack) is always the first bar lower than (the top bar in stack) on the left.
           3. (The bar that i points to) is always the first bar lower than (the top bar in stack) on the right.
           4. After step 2 and 3, we know the left and right boundaries, then know the width, then know the area
        */
        while(i < heights.length) {
            if(stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                //right boundary: i-1, left boundary = stack.peek()+1 : width = (i-1) - (stack.peek()+1) + 1 = i-1-stack.peek()
                int area = heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek());
                maxArea = Math.max(maxArea, area);
            }
        }

        while(!stack.isEmpty()) {
            int top = stack.pop();
            int area = heights[top] * (stack.isEmpty() ? i : i - 1 - stack.peek());
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    /* Dynamic Programming
       area(i) = height[i] * (r-l-1)
       maxArea = max(area(i)) i = 0 to n-1
       l: the first coordinate of the bar to the left with height h[l] < h[i].
       r: the first coordinate of the bar to the right with height h[r] < h[i]
       Time complexity: O(n), Space complexity: O(n)
    */
    public static int largestRectangleArea5(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        int[] lessFromLeft = new int[heights.length];
        int[] lessFromRight = new int[heights.length];

        lessFromLeft[0] = -1;
        lessFromRight[heights.length-1] = heights.length;

        for(int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while(p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for(int i = heights.length-2; i >= 0; i--) {
            int p = i + 1;
            while(p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        for(int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{1, 2, 0, 4, 3}));
        System.out.println(largestRectangleArea2(new int[]{1, 2, 0, 4, 3}));
        System.out.println(largestRectangleArea3(new int[]{1, 2, 0, 4, 3}));
        System.out.println(largestRectangleArea4(new int[]{1, 2, 0, 4, 3}));

        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea2(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea3(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea4(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
