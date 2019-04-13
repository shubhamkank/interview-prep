package com.interview.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslands2 {

    /* Union Find Data Structure
       Time complexity: O(p * alpha(m * n)), Space complexity: O(m * n) */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        if(positions == null || positions.length == 0) {
            return Arrays.asList(0);
        }

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        List<Integer> result = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        for(int i = 0; i < positions.length; i++) {
            int id = positions[i][0] * n + positions[i][1];
            uf.setParent(id);

            for(int[] d : dir) {
                int x = positions[i][0] + d[0];
                int y = positions[i][1] + d[1];
                int nbId = x * n + y;

                if(x >= 0 && x < m && y >= 0 && y < n && uf.isValid(nbId)) {
                    uf.union(id, nbId);
                }
            }
            result.add(uf.getCount());
        }
        return result;
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = 0;

            for(int i = 0; i < n; i++) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void setParent(int i) {
            parent[i] = i;
            count++;
        }

        public boolean isValid(int i) {
            return parent[i] >= 0;
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
        System.out.println(numIslands2(3, 3, new int[][] {{0,0}, {0,1}, {1,2}, {2,1}}));
    }
}
