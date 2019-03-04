package com.interview.leetcode;

import java.util.LinkedList;

public class FriendCircles {

    static class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
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
            } else if(rank[jRoot] < rank[iRoot]) {
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

    /* Union Find
       Time complexity: O(n^2), Space complexity: O(n) */
    public static int findCircleNum(int[][] M) {
        if(M == null || M.length == 0) {
            return 0;
        }

        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(M[i][j] == 1 && i != j) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    /* Depth First Search
       Time complexity: O(n^2), Space complexity: O(n) */
    public static int findCircleNum2(int[][] M) {
        if(M == null || M.length == 0) {
            return 0;
        }

        int n = M.length;
        boolean[] visited = new boolean[n];
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                DFSUtil(M, i, visited);
                count++;
            }
        }
        return count;
    }

    public static void DFSUtil(int[][] M, int i, boolean[] visited) {
        int n = M.length;
        visited[i] = true;
        for(int j = 0; j < n; j++) {
            if(!visited[j] && M[i][j] == 1) {
                DFSUtil(M, j, visited);
            }
        }
    }

    /* Breadth First Search
       Time complexity: O(n^2), Space complexity: O(n) */
    public static int findCircleNum3(int[][] M) {
        if(M == null || M.length == 0) {
            return 0;
        }

        int n = M.length;
        boolean[] visited = new boolean[n];
        int count = 0;
        LinkedList<Integer> queue = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                queue.add(i);
                while(!queue.isEmpty()) {
                    int u = queue.remove();
                    visited[u] = true;
                    for(int v = 0; v < n; v++) {
                        if(!visited[v] && M[u][v] == 1) {
                            queue.add(v);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] M1 = {{1, 1, 0},
                     {1, 1, 0},
                     {0, 0, 1}};

        System.out.println(findCircleNum3(M1));

        int[][] M2 = {{1, 1, 0},
                      {1, 1, 1},
                      {0, 1, 1}};
        System.out.println(findCircleNum3(M2));
    }
}
