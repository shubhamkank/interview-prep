package com.interview.leetcode;

public class IsGraphBipartite {

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


    public static void main(String[] args) {
        System.out.println(isBipartite(new int[][] {{1,3}, {0,2}, {1,3}, {0,2}}));
        System.out.println(isBipartite(new int[][] {{1,2,3}, {0,2}, {0,1,3}, {0,2}}));
    }
}
