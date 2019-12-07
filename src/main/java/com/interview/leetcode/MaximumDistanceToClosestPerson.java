package com.interview.leetcode;

public class MaximumDistanceToClosestPerson {

    public static int maxDistToClosest(int[] seats) {
        int res = 0, n = seats.length, last = -1;
        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                res = last < 0 ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        res = Math.max(res, n - last - 1);
        return res;
    }

    public static int maxDistToClosest2(int[] seats) {
        int max = 0;
        int i = 0;
        int n = seats.length;

        while(i < n) {
            while(i < n && seats[i] == 1) {
                i++;
            }

            int j = i;
            while(i < n && seats[i] == 0) {
                i++;
            }

            if(j == 0 || i == n) {
                max = Math.max(max, i - j);
            } else {
                max = Math.max(max, (i - j + 1)/2);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxDistToClosest(new int[] {0, 0, 0, 1}));
        System.out.println("--++".replaceFirst("\\+\\+", "--"));
    }
}
