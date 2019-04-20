package com.interview.leetcode;

import java.util.Arrays;

public class NetworkDelayTime {

    /* Bellman Ford
       Time complexity: O(VE), Space complexity: O(V) */
    public static int networkDelayTime(int[][] times, int N, int K) {
        int[] distTo = new int[N + 1];
        Arrays.fill(distTo, Integer.MAX_VALUE);

        distTo[K] = 0;

        for(int i = 0; i < N - 1; i++) {
            for(int[] time : times) {
                int u = time[0], v = time[1], w= time[2];
                if(distTo[u] != Integer.MAX_VALUE && (distTo[v] > distTo[u] + w)) {
                    distTo[v] = distTo[u] + w;
                }
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            result = Math.max(result, distTo[i]);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{2,1,1}, {2,3,1}, {3,4,1}}, 4, 2));
    }
}
