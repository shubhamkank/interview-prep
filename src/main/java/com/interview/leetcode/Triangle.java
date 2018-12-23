package com.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {

    /* Dynamic Programming - Bottom-Up Approach
       Time complexity: O(n^2), Space complexity: O(n^2)
       dp[i][j] - minimum total when jth element of level i is included
       dp[i][j] = dp[i-1][j] + nums[i][j] if j == 0
       dp[i][j] = dp[i-1][j-1] + nums[i][j] if j == i
       dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + nums[i][j] otherwise
       Adjacent members for jth element = jth and (j+1)th element in the next level. Therefore, for ith element to be
       part of min total, the previous element in the level above has to be ith element or (i-1)th element
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                int num = triangle.get(i).get(j);
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + num;
                } else if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + num;
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + num;
                }
            }
        }

        int minTotal = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++) {
            minTotal = Math.min(minTotal, dp[n-1][j]);
        }
        return minTotal;
    }

    /* Dynamic Programming - Bottom-Up Approach
       Time complexity: O(n^2), Space complexity: O(n)
       dp[j] at any times depends upon its previous value dp[j] or value of element to its left diagonal
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);

        for(int i = 1; i < n; i++) {
            int prev = 0;
            for(int j = 0; j <= i; j++) {
                int num = triangle.get(i).get(j);
                if(j == 0) {
                    prev = dp[j];
                    dp[j] = dp[j] + num;
                } else if(j == i) {
                    dp[j] = prev + num;
                } else {
                    int temp = dp[j];
                    dp[j] = Math.min(dp[j], prev) + num;
                    prev = temp;
                }
            }
        }

        int minTotal = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++) {
            minTotal = Math.min(minTotal, dp[j]);
        }
        return minTotal;
    }

    //TODO: Implement BFS solution

    public static void main(String[] args) {
        List<List<Integer>> input1 = new ArrayList<>();
        input1.add(Arrays.asList(2));
        input1.add(Arrays.asList(3, 4));
        input1.add(Arrays.asList(6, 5, 7));
        input1.add(Arrays.asList(4, 1, 8, 3));

        System.out.println(minimumTotal2(input1));
    }
}
