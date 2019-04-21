package com.interview.leetcode;

import java.util.*;

public class IsGraphBipartite {

    /* Depth First Search
       Time complexity: O(v + e), Space complexity: O(d) d = d is the maximum depth of the recursion tree */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];

        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                if(!dfs(i, graph, visited, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int u, int[][] graph, int[] visited, int colour) {
        visited[u] = colour;
        for(int v : graph[u]) {
            if(visited[v] == 0) {
                if(!dfs(v, graph, visited, -1 * colour)) {
                    return false;
                }
            }
            if(visited[v] == colour) {
                return false;
            }
        }
        return true;
    }

    /* Breath First Search
       Time complexity: O(v + e), Space complexity: O(v) */
    public static boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];

        for(int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = 1;
                while (!queue.isEmpty()) {
                    int u = queue.remove();
                    for(int v : graph[u]) {
                        if(visited[v] == visited[u]) {
                            return false;
                        }
                        else if(visited[v] == 0) {
                            queue.add(v);
                            visited[v] = -1 * visited[u];
                        }
                    }
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isBipartite(new int[][] {{1,3}, {0,2}, {1,3}, {0,2}}));
        System.out.println(isBipartite(new int[][] {{1,2,3}, {0,2}, {0,1,3}, {0,2}}));
    }
}
