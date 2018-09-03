package com.interview.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MajorityElement {

    // Sorting Method
    // Time complexity: O(nlogn), Space complexity: O(1)
    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Brute force
    // Time complexity: O(n^2), Space complexity: O(1)
    public static int majorityElement2(int[] nums) {
        int majorityCount = nums.length/2;

        for (int num : nums) {
            int count = 0;
            for (int num1 : nums) {
                if (num == num1) {
                    count++;
                }
            }
            if (count > majorityCount) {
                return num;
            }
        }
        return -1;
    }

    // Hash map
    // Time complexity: O(n), Space complexity: O(n)
    public static int majorityElement3(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        int majorityCount = nums.length/2;
        for(int x : map.keySet()) {
            if(map.get(x) > majorityCount) {
                return x;
            }
        }
        return -1;
    }

    // Randomization
    // Time complexity: Worst case O(INFINITY) / Expected time O(n), Space complexity: O(1)
    public static int majorityElement4(int[] nums) {
        Random rand = new Random();
        int majorityCount = nums.length/2;

        while (true) {
            int candidate = nums[rand.nextInt(nums.length)];
            if(countOccurrences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

    private static int countOccurrences(int [] nums, int num) {
        int count = 0;
        for(int x : nums) {
            if(x == num) {
                count++;
            }
        }
        return count;
    }

    //Divide and Conquer
    //Time complexity: O(nlogn), Space complexity: O(logn)
    //Recurrence Relation: T(n) = 2T(n/2) + 2n
    public static int majorityElement5(int[] nums) {
        return majorityElementRecursive(nums, 0, nums.length - 1);
    }

    private static int majorityElementRecursive(int[] nums, int low, int high) {
        if(low == high) {
            return nums[low];
        }

        int mid = (low + high)/2;
        int left = majorityElementRecursive(nums, low, mid);
        int right = majorityElementRecursive(nums, mid+1, high);

        if(left == right) {
            return left;
        }

        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);

        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for(int i = low; i <= high; i++) {
            if(nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    //Boyer-Moore Voting Algorithm
    //Time complexity: O(n), Space complexity: O(1)
    public static int majorityElement6(int[] nums) {
        int count = 0;
        int candidate = -1;

        for(int num : nums) {
            if(count == 0) {
                candidate = num;
            }

            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement6(new int [] {3,2,3}));
        System.out.println(majorityElement6(new int [] {2,2,1,1,1,2,2}));
    }
}
