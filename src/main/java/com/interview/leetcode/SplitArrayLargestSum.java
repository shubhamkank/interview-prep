package com.interview.leetcode;

public class SplitArrayLargestSum {

    //Dynamic Programming
    //Time complexity: O(n^2 * m), Space complexity: O(n * m)
    //Recurrence Relation: dp(i, k) = min(max(dp(j, k - 1), sum(arr[x]) where j+1 <= x <= i) where 0 <= j < i
    //sum(arr[x]) where j+1 <= x <= i can also be written as dp[i][1] - dp[j][1] since dp[x][1] is prefix sum column
    public static int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n][m + 1];

        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            dp[i][1] = prefixSum;
        }

        for(int i = 0; i < n; i++) {
            for(int k = 2; k <= m; k++) {
                int min = Integer.MAX_VALUE;
                for(int j = 0; j < i; j++) {
                    min = Math.min(min, Math.max(dp[j][k - 1], dp[i][1] - dp[j][1]));

                }
                dp[i][k] = min;
            }
        }
        return dp[n - 1][m];
    }

    private int ans;
    private int n, m;


    //Brute force
    //Time complexity: O(n^m), Space complexity: O(n)
    public int splitArray2(int[] nums, int m) {
        ans = Integer.MAX_VALUE;
        n = nums.length;
        this.m = m;
        dfs(nums, 0, 0, 0, 0);
        return ans;
    }

    // We can use depth-first search to generate all possible splitting plan. For each element in the array, we can
    // choose to append it to the previous subarray or start a new subarray starting with that element (if the number
    // of subarrays does not exceed m). The sum of the current subarray can be updated at the same time.
    private void dfs(int[] nums, int i, int cntSubarrays, int curSum, int curMax) {
        if(i == n && cntSubarrays == m) {
            ans = Math.min(ans, curMax);
        }

        if(i == n) {
            return;
        }

        if(i > 0) {
            dfs(nums, i + 1, cntSubarrays, curSum + nums[i], Math.max(curMax, curSum + nums[i]));
        }

        if(cntSubarrays < m) {
            dfs(nums, i + 1, cntSubarrays + 1, nums[i], Math.max(curMax, nums[i]));
        }
    }

    //Binary Search
    //Time complexity: O(n * log(sum - max))
    public static int splitArray3(int[] nums, int m) {
        int max = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }

        if(m == 1) {
            return sum;
        }

        int l = max, r = sum;

        while(l < r) {
            int mid = (l + r) / 2;
            if(valid(nums, m, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //Time complexity: O(n)
    private static boolean valid(int[] nums, int m, int target) {
        int count = 1;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if(total > target) {
                total = nums[i];
                count++;
                if(count > m) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(splitArray3(new int[] {7, 2, 5, 10, 8}, 1));
        System.out.println(splitArray3(new int[] {7, 2, 5, 10, 8}, 2));
        System.out.println(splitArray3(new int[] {7, 2, 5, 10, 8}, 3));
        System.out.println(splitArray3(new int[] {7, 2, 5, 10, 8}, 4));
        System.out.println(splitArray3(new int[] {7, 2, 5, 10, 8}, 5));
    }

}
