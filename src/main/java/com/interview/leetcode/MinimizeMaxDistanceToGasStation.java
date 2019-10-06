package com.interview.leetcode;

public class MinimizeMaxDistanceToGasStation {

    // Binary search
    // Time complexity: O(N logM) where M = stations[N - 1] - stations[0]
    public static double minmaxGasDist(int[] stations, int K) {
        int N = stations.length;

        double left = 0, right = stations[N - 1] - stations[0];
        double mid;

        while (left + 1e-6 < right) {
            mid = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < N - 1; i++) {
                count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
            }
            if(count > K) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {

    }
}
