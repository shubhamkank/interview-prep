package com.interview.leetcode;

import java.util.*;

public class NumOfConnectedComponents {

    public static int countComponents(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, graph, visited);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int u, List<Integer>[] graph, boolean[] visited) {
        visited[u] = true;
        for(int v : graph[u]) {
            if(!visited[v]) {
                dfs(v, graph, visited);
            }
        }
    }

    public static int countComponents2(int n, int[][] edges) {
        List<Integer>[] graph = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while(!queue.isEmpty()) {
                    int u = queue.remove();
                    for(int v : graph[u]) {
                        if(!visited[v]) {
                            visited[v] = true;
                            queue.add(v);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public static int countComponents3(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }

        return uf.getCount();
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
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
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        System.out.println(countComponents3(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}));
        System.out.println(countComponents3(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
    }
}
