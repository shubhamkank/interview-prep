package com.interview.leetcode;

import java.util.*;

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

    /* Dijkstra's Algorithm
       Time complexity: O((V+E)logV) = O(ElogV), Space complexity: O(V) */
    public static int networkDelayTime2(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] time : times) {
            graph.putIfAbsent(time[0], new ArrayList<>());
            graph.get(time[0]).add(new int[] {time[1], time[2]});
        }

        int[] distTo = new int[N + 1];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[K] = 0;

        boolean[] visited = new boolean[N + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{K, 0});

        int result = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.remove();
            int curNode = cur[0];
            if(visited[curNode]) {
                continue;
            }
            visited[curNode] = true;

            int curDist = cur[1];
            result = curDist;
            N--;

            if(graph.containsKey(curNode)) {
                for(int[] next : graph.get(curNode)) {
                    int nextNode = next[0];
                    int nextDist = next[1];

                    if(!visited[nextNode] && distTo[nextNode] > curDist + nextDist) {
                        distTo[nextNode] = curDist + nextDist;
                        pq.add(new int[] {nextNode, distTo[nextNode]});
                    }
                }
            }
        }
        return N == 0 ? result : -1;
    }

    public static void main(String[] args) {
        System.out.println(networkDelayTime2(new int[][]{{2,1,1}, {2,3,1}, {3,4,1}}, 4, 2));
    }
}
