package com.interview.leetcode;

import java.util.HashMap;

public class SingleNumber {

    //Time complexity: O(n), Space Complexity: O(n)
    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.remove(num);
            } else {
                map.put(num, 0);
            }
        }

        int result = 0;
        for(int num : map.keySet()) {
            result = num;
            break;
        }
        return result;
    }

    //Time complexity: O(n), Space Complexity: O(1)
    public static int singleNumber2(int[] nums) {
        int result = 0;
        for(int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String [] args) {
        System.out.println(singleNumber2(new int[] {2, 2, 1}));
        System.out.println(singleNumber2(new int[] {4, 1, 2, 1, 2}));
    }
}
