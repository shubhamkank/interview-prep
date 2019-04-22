package com.interview.leetcode;

import java.util.*;

public class KeysAndRooms {

    /* Depth first search
       Time complexity: O(V + E), Space complexity: O(V) */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(0, rooms, visited);

        int count = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                count++;
            }
        }
        return count == n;
    }

    private static void dfs(int u, List<List<Integer>> graph, boolean[] visited) {
        visited[u] = true;
        for(int v : graph.get(u)) {
            if(!visited[v]) {
                dfs(v, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(canVisitAllRooms(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList())));
        System.out.println(canVisitAllRooms(Arrays.asList(Arrays.asList(1, 3), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList(0))));
    }
}
