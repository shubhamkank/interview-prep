package com.interview.leetcode;

public class MinSizeSubarraySum {

    //Sliding Window Approach
    //Time complexity: O(n), Space complexity: O(1)
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int sum = 0;

        while(right < nums.length) {
            sum += nums[right];
            right++;

            while(sum >= s) {
                minLen = Math.min(minLen, right - left);
                sum -= nums[left];
                left++;
            }
        }

        if(minLen == Integer.MAX_VALUE) {
            return 0;
        }
        return minLen;
    }

    //Binary Search
    //Time complexity: O(nlogn), Space complexity: O(n)
    public static int minSubArrayLen2(int s, int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }

        int minLen = Integer.MAX_VALUE;
        int[] sums = new int[n];
        sums[0] = nums[0];

        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            int end = binarySearch(sums, i, n - 1, i == 0 ? s : sums[i - 1] + s);
            if(end >= n) break;
            if(end - i + 1 < minLen) {
                minLen = end - i + 1;
            }
        }
        if(minLen == Integer.MAX_VALUE) {
            return 0;
        }
        return minLen;
    }

    private static int binarySearch(int[] sums, int low, int high, int key) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if(sums[mid] >= key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(minSubArrayLen2(7, new int[]{2,3,1,2,4,3}));
    }
}
