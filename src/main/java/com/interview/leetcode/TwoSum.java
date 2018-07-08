package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    //Runtime: O(n), Space: O(n)
    public static int[] twoSum(int [] nums, int target) {
        Map<Integer, Integer> diffMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(diffMap.containsKey(nums[i])) {
                return new int[] { diffMap.get(nums[i]), i};
            }
            diffMap.put(target - nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String [] args) {
        int [] nums = {2, 7, 11, 2};
        int target = 13;

        int [] indexes = twoSum(nums, target);
        for (int i : indexes) {
            System.out.println(i);
        }
    }
}
