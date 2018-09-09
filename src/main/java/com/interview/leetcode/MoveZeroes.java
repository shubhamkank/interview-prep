package com.interview.leetcode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        int nonzeroIndex = 0;

        while(true) {
            while(zeroIndex < nums.length && nums[zeroIndex] != 0) {
                zeroIndex++;
            }

            nonzeroIndex = zeroIndex + 1;
            while(nonzeroIndex < nums.length && nums[nonzeroIndex] == 0) {
                nonzeroIndex++;
            }

            if(zeroIndex >= nums.length || nonzeroIndex >= nums.length) {
                break;
            }
            int temp = nums[zeroIndex];
            nums[zeroIndex] = nums[nonzeroIndex];
            nums[nonzeroIndex] = temp;
        }
    }

    // Time complexity: O(n), Space complexity: O(1) - Optimal no. of ops
    public void moveZeroes2(int[] nums) {
        int lastNonZeroIndex = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[lastNonZeroIndex];
                nums[lastNonZeroIndex] = temp;
                lastNonZeroIndex++;
            }
        }
    }

    public static void main(String [] args) {
        MoveZeroes mz = new MoveZeroes();
        int [] nums = new int [] {0, 0, 0, 0, 1};
        mz.moveZeroes2(nums);
        Arrays.stream(nums).forEach(System.out::println);
    }
}
