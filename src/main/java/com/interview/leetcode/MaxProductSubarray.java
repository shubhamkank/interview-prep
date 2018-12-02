package com.interview.leetcode;

public class MaxProductSubarray {

    /* Time complexity: O(n), Space complexity: O(1) */
    public static int maxProduct(int[] nums) {
        int result = nums[0];

        int prevMax = nums[0];
        int prevMin = nums[0];

        for(int i = 1; i < nums.length; i++) {
            //If the nums[i] is negative then (prevMin * nums[i]) will be bigger than (prevMax * nums[i])
            if(nums[i] < 0) {
                int temp = prevMax;
                prevMax = prevMin;
                prevMin = temp;
            }

            prevMax = Math.max(prevMax * nums[i], nums[i]);
            prevMin = Math.min(prevMin * nums[i], nums[i]);
            result = Math.max(result, prevMax);
        }
        return result;
    }

    /* Time complexity: O(n), Space complexity: O(1) */
    public static int maxProduct2(int[] nums) {
        int result = nums[0];

        int prevMax = nums[0];
        int prevMin = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int temp = prevMax;
            prevMax = Math.max(Math.max(prevMax * nums[i], prevMin * nums[i]), nums[i]);
            prevMin = Math.min(Math.min(prevMin * nums[i], temp * nums[i]), nums[i]);
            result = Math.max(result, prevMax);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxProduct(new int[] {-1, -2, -3}));
        System.out.println(maxProduct(new int[] {1, -2, -3}));
        System.out.println(maxProduct(new int[] {1, 2, -3}));
        System.out.println(maxProduct(new int[] {1, 2, 3}));
        System.out.println(maxProduct(new int[] {2, 3, -2, 4}));
        System.out.println(maxProduct(new int[] {-2, 0, -1}));
    }
}
