package com.interview.leetcode;

import java.util.Arrays;

public class MaximumVacationDays {

    //Dynamic Programming
    //dp[i][j] - max vacation days when we will stay in city j on week i
    //dp[i][j] = max(days[j][i] + dp[i - 1][k]) for k = 0 to c - 1 if flights[k][j] == 1 or k == j
    //Time complexity: O(WC^2), Space complexity: O(WC) where W = number of weeks, C = number of cities
    public static int maxVacationDays(int[][] flights, int[][] days) {
        int c = days.length;
        int w = days[0].length;

        int[][] dp = new int[w][c];

        for (int i = 0; i < w; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for(int j = 0; j < c; j++) {
            if(j == 0 || flights[0][j] == 1) {
                dp[0][j] = days[j][0];
            }
        }

        for(int i = 1; i < w; i++) {
            for(int j = 0; j < c; j++) {
                for(int k = 0; k < c; k++) {
                    if(k == j || flights[k][j] == 1) {
                        dp[i][j] = Math.max(dp[i][j], days[j][i] + dp[i - 1][k]);
                    }
                }
            }
        }

        int max = 0;
        for(int j = 0; j < c; j++) {
            max = Math.max(max, dp[w - 1][j]);
        }
        return max;
    }

    //Time complexity: O(WC^2), Space complexity: O(C)
    public static int maxVacationDays2(int[][] flights, int[][] days) {
        int c = days.length;
        int w = days[0].length;

        int[] dp = new int[c];
        Arrays.fill(dp, Integer.MIN_VALUE);

        for(int j = 0; j < c; j++) {
            if(j == 0 || flights[0][j] == 1) {
                dp[j] = days[j][0];
            }
        }

        for(int i = 1; i < w; i++) {
            int[] temp = new int[c];
            Arrays.fill(temp, Integer.MIN_VALUE);

            for(int j = 0; j < c; j++) {
                for(int k = 0; k < c; k++) {
                    if(k == j || flights[k][j] == 1) {
                        temp[j] = Math.max(temp[j], days[j][i] + dp[k]);
                    }
                }
            }
            dp = temp;
        }

        int max = 0;
        for(int j = 0; j < c; j++) {
            max = Math.max(max, dp[j]);
        }
        return max;
    }

    //Naive Depth first search
    //Time complexity: O(n^k) where n is the number of cities and k = number of weeks
    //Space complexity: O(k)
    public static int maxVacationDays3(int[][] flights, int[][] days) {
        return dfs(flights, days, 0, 0);
    }

    private static int dfs(int[][] flights, int[][] days, int curCity, int curWeek) {
        if(curWeek == days[0].length) {
            return 0;
        }

        int maxVac = 0;
        for (int i = 0; i < days.length; i++) {
            if(curCity == i || flights[curCity][i] == 1) {
                maxVac = Math.max(maxVac, days[curCity][i] + dfs(flights, days, i, curWeek + 1));
            }
        }
        return maxVac;
    }

    //Depth first search with memoization
    //Time complexity: O(n^2 * k) where n is the number of cities and k = number of weeks
    //Space complexity: O(nk)
    public static int maxVacationDays4(int[][] flights, int[][] days) {
        int[][] memo = new int[flights.length][days[0].length];
        for (int[] l : memo) {
            Arrays.fill(l, Integer.MIN_VALUE);
        }
        return dfs(flights, days, 0, 0, memo);
    }

    private static int dfs(int[][] flights, int[][] days, int curCity, int curWeek, int[][] memo) {
        if (curWeek == days[0].length) {
            return 0;
        }

        if (memo[curCity][curWeek] != Integer.MIN_VALUE) {
            return memo[curCity][curWeek];
        }

        int maxvac = 0;
        for (int i = 0; i < flights.length; i++) {
            if (flights[curCity][i] == 1 || i == curCity) {
                int vac = days[i][curWeek] + dfs(flights, days, i, curWeek + 1, memo);
                maxvac = Math.max(maxvac, vac);
            }
        }
        memo[curCity][curWeek] = maxvac;
        return maxvac;
    }

    public static void main(String[] args) {
        System.out.println(maxVacationDays4(new int[][]{{0,0,0}, {0,0,0}, {0,0,0}}, new int[][]{{1,1,1},{7,7,7},{7,7,7}}));
    }
}
