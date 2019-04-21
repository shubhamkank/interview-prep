package com.interview.leetcode;

import java.util.*;

public class RedundantConnection {

    /* Union Find Data Structure
       Time complexity: O(n), Space complexity: O(n)  n is the number of vertices */
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] result = new int[2];
        UnionFind uf = new UnionFind(n + 1);
        for(int[] edge : edges) {
            if(uf.find(edge[0]) == uf.find(edge[1])) {
                result[0] = edge[0];
                result[1] = edge[1];
            } else {
                uf.union(edge[0], edge[1]);
            }
        }
        return result;
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot == jRoot) {
                return;
            }

            if(rank[iRoot] < rank[jRoot]) {
                parent[iRoot] = jRoot;
            } else if(rank[iRoot] > rank[jRoot]) {
                parent[jRoot] = iRoot;
            } else {
                parent[jRoot] = iRoot;
                rank[iRoot]++;
            }
        }
    }

    /* Depth first search
       Time complexity: O(N^2), Space complexity: O(N)  where N = number of vertices */
    public static int[] findRedundantConnection2(int[][] edges) {
        List<Integer>[] graph = new LinkedList[edges.length + 1];
        for(int i = 0; i <= edges.length; i++) {
            graph[i] = new LinkedList<>();
        }

        Set<Integer> visited = new HashSet<>();
        /* Check for edge (u, v) in edges without adding it to the graph if we are able to reach from u to v.
           If we are, then adding that edge is resulting in a cycle. Else, add that edge in the graph and check for the
           next edge. */
        for(int[] edge : edges) {
            visited.clear();
            if(dfs(edge[0], edge[1], graph, visited)) {
                return edge;
            } else {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
        }
        throw new IllegalArgumentException();
    }

    private static boolean dfs(int src, int dst, List<Integer>[] graph, Set<Integer> visited) {
        visited.add(src);
        if(src == dst) {
            return true;
        }
        for(int adj : graph[src]) {
            if(!visited.contains(adj)) {
                if(dfs(adj, dst, graph, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantConnection(new int[][] {{1,2}, {1,3}, {2,3}})));
        System.out.println(Arrays.toString(findRedundantConnection(new int[][] {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}})));
    }
}
