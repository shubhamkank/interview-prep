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

    /* Stack based approach II
       Time complexity: O(n)
       Space complexity: O(n) - worst case - sorted array
    */
    public static int largestRectangleArea5(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
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
