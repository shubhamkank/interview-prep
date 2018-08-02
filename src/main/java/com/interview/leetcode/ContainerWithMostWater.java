package com.interview.leetcode;

public class ContainerWithMostWater {

    //Time complexity: O(n^2), Space complexity: O(1)
    public static int maxArea(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length; i++) {
            for(int j = i+1; j < height.length; j++) {
                int length = j-i;
                int breadth = Math.min(height[i], height[j]);
                int area = length * breadth;
                if(area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    //Time complexity: O(n), Space complexity: O(1)
    public static int maxArea2(int[] height) {
        int maxArea = 0;
        int low = 0;
        int high = height.length-1;

        while(low < high) {
            maxArea = Math.max(maxArea, Math.min(height[low], height[high]) * (high - low));
            if(height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxArea;
    }

    public static void main(String [] args) {
        System.out.println(maxArea2(new int [] {1,8,6,2,5,4,8,3,7}));
    }
}
