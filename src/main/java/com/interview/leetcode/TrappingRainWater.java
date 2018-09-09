package com.interview.leetcode;

import java.util.Stack;

public class TrappingRainWater {

    //Brute Force
    // Time complexity: O(n^2), Space complexity: O(1)
    public int trap(int[] height) {
        int answer = 0;
        int size = height.length;

        /* For every element find the maximum level of water it can trap.
           maxElement = min(maxLeft[element], maxRight[element]) - height[element]
         */
        for(int i = 1; i < size - 1; i++) {
            int maxLeft = 0, maxRight = 0;

            for(int j = i; j >= 0; j --) {
                maxLeft = Math.max(maxLeft, height[j]);
            }

            for(int j = i; j < size; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }
            answer += Math.min(maxLeft, maxRight) - height[i];
        }
        return answer;
    }

    // Dynamic Programming
    // Time complexity: O(n), Space complexity: O(n)
    public int trap2(int[] height) {
        if(height == null || height.length == 0) {
            return 0;
        }

        int answer = 0;
        int size = height.length;
        int [] leftMax = new int[size];
        int [] rightMax = new int[size];

        //Pre-compute the left max and the right max at each element
        leftMax[0] = height[0];
        for(int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i-1]);
        }

        rightMax[size-1] = height[size-1];
        for(int i = size-2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i+1]);
        }

        for(int i = 1; i < size - 1; i++) {
            answer += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return answer;
    }

    // Using Stack
    // Time complexity: O(n), Space complexity: O(n)
    public int trap3(int[] height) {
        int size = height.length;
        int answer = 0, current = 0;
        Stack<Integer> stack = new Stack<>();

        /* Add the index of the bar to the stack if bar is smaller than or equal to the bar at top of stack,
           which means that the current bar is bounded by the previous bar in the stack. If we found a bar longer than
           that at the top, we are sure that the bar at the top of the stack is bounded by the current bar and
           a previous bar in the stack, hence, we can pop it and add resulting trapped water to ans
         */
        while (current < size) {
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int top = stack.peek();
                stack.pop();
                if(stack.isEmpty()) {
                    break;
                }
                int distance = current - stack.peek() - 1;
                int boundedHeight = Math.min(height[current], height[stack.peek()]) - height[top];
                answer += distance * boundedHeight;
            }
            stack.push(current);
            current++;
        }
        return answer;
    }

    // Using 2 Pointers
    // Time complexity: O(n), Space complexity: O(1)
    public int trap4(int[] height) {
        int left = 0, right = height.length - 1;
        int answer = 0;

        int leftMax = 0, rightMax = 0;

        /* As long as right_max[i]>left_max[i], the water trapped depends upon the left_max, and similar is the case
           when left_max[i]>right_max[i]). So, we can say that if there is a larger bar at one end (say right),
           we are assured that the water trapped would be dependant on height of bar in current direction
           (from left to right). As soon as we find the bar at other end (right) is smaller, we start iterating in
           opposite direction (from right to left).
         */
        /* In the previous approaches, we always needed to find the minimum of the left max and right max. We did not
           care about the value other than minimum. So essentially, it is as good as comparing the left height and
           the right height and updating the left max and right max as we traverse the array instead of pre-computing.
         */
        while (left < right) {
            if(height[left] < height[right]) {
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    answer += (leftMax - height[left]);
                }
                left++;
            } else {
                if(height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    answer += (rightMax - height[right]);
                }
                right--;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trap4(new int [] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
