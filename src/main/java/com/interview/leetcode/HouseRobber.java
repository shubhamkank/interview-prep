package com.interview.leetcode;

public class HouseRobber {

    /* Time complexity: O(n), Space complexity: O(n) */
    public static int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
             return 0;
        }

        int n = nums.length;
        int[] maxMoney = new int[n];
        for(int i = 0; i < n; i++) {
            maxMoney[i] = Math.max(nums[i] + (i-2 < 0 ? 0 : maxMoney[i-2]), (i-1 < 0 ? 0 : maxMoney[i-1]));
        }
        return maxMoney[n-1];
    }

    /* Time complexity: O(n), Space complexity: O(1) */
    public static int rob2(int[] nums) {
        int prevMax = 0;
        int curMax = 0;

        for(int num : nums) {
            int temp = curMax;
            curMax = Math.max(prevMax + num, curMax);
            prevMax = temp;
        }
        return curMax;
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(rob(new int[]{2, 1, 1, 2}));
    }
}
