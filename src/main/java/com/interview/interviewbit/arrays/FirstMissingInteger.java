package com.interview.interviewbit.arrays;

public class FirstMissingInteger {

    public int firstMissingPositive(int[] A) {
        boolean containsOne = false;
        for(int i = 0; i < A.length; i++) {
            if(A[i] == 1) {
                containsOne = true;
                break;
            }
        }

        if(!containsOne) {
            return 1;
        }

        for(int i = 0; i < A.length; i++) {
            if(A[i] <= 0 || A[i] > A.length) {
                A[i] = 1;
            }
        }

        for(int i = 0; i < A.length; i++) {
            int val = Math.abs(A[i]);
            if(val < A.length) {
                if(A[val] > 0) {
                    A[val] = -1 * A[val];
                }

            } else {
                A[0] = -1 * A[0];
            }
        }

        for(int i = 1; i < A.length; i++) {
            if(A[i] > 0) {
                return i;
            }
        }
        return A[0] > 0 ? A.length : A.length + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            while(nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
        }
        for(int i = 0; i < n; i++)
            if(nums[i] != i + 1)
                return i + 1;
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }
}
