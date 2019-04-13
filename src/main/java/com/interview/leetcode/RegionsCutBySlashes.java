package com.interview.leetcode;

public class RegionsCutBySlashes {

    public static int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] graph = new int[n * 3][n * 3];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i].charAt(j) == '/') {
                    graph[i * 3][j * 3 + 2] = 1;
                    graph[i * 3 + 1][j * 3 + 1] = 1;
                    graph[i * 3 + 2][j * 3] = 1;
                } else if(grid[i].charAt(j) == '\\') {
                    graph[i * 3][j * 3] = 1;
                    graph[i * 3 + 1][j * 3 + 1] = 1;
                    graph[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }

        int numRegions = 0;
        for(int i = 0; i < n * 3; i++) {
            for(int j = 0; j < n * 3; j++) {
                if(graph[i][j] == 0) {
                    numRegions++;
                    dfsUtil(graph, i, j);
                }
            }
        }
        return numRegions;
    }

    private static void dfsUtil(int[][] graph, int i, int j) {
        int n = graph.length;
        if(i < 0 || i >= n || j < 0 || j >= n || graph[i][j] == 1) {
            return;
        }
        graph[i][j] = 1;
        dfsUtil(graph, i, j - 1);
        dfsUtil(graph, i, j + 1);
        dfsUtil(graph, i - 1, j);
        dfsUtil(graph, i + 1, j);
    }

    public static int regionsBySlashes2(String[] grid) {
        int n = grid.length;
        int[][] graph = new int[n * 3][n * 3];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i].charAt(j) == '/') {
                    graph[i * 3][j * 3 + 2] = 1;
                    graph[i * 3 + 1][j * 3 + 1] = 1;
                    graph[i * 3 + 2][j * 3] = 1;
                } else if(grid[i].charAt(j) == '\\') {
                    graph[i * 3][j * 3] = 1;
                    graph[i * 3 + 1][j * 3 + 1] = 1;
                    graph[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }

        UnionFind uf = new UnionFind(graph);
        int m = graph.length;
        n = graph[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(graph[i][j] == 0) {
                    graph[i][j] = 1;
                    if(i - 1 >= 0 && graph[i - 1][j] == 0) {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if(i + 1 < m && graph[i + 1][j] == 0) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if(j - 1 >= 0 && graph[i][j - 1] == 0) {
                        uf.union(i * n + j, i * n + (j - 1));
                    }
                    if(j + 1 < n && graph[i][j + 1] == 0) {
                        uf.union(i * n + j, i * n + (j + 1));
                    }
                }
            }
        }
        return uf.getCount();
    }

    public static void main(String[] args) {

    }

}

class UnionFind {

    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        count = 0;
        parent = new int[m * n];
        rank = new int[m * n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    parent[i * n + j] = i * n + j;
                    count++;
                }
                rank[i * n + j] = 0;
            }
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

    public void getCount() {
        return count;
    }
}
