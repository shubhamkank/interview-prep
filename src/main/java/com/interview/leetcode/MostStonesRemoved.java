package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MostStonesRemoved {

    /* Union-find approach
       Use indexes instead of considering points
       Time complexity: O(n), Space complexity: O(n) */
    public static int removeStones(int[][] stones) {
        if(stones == null || stones.length < 2) {
            return 0;
        }
        UnionFind uf = new UnionFind();
        for(int i = 0; i < stones.length; i++) {
            uf.union(stones[i][0], ~stones[i][1]);  // ~i = -i - 1
        }
        return stones.length - uf.getCount();
    }

    static class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> rank;
        int count;

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
            count = 0;
        }

        public int find(int i) {
            if(!parent.containsKey(i)) {
                parent.put(i, i);
                rank.put(i, 0);
                count++;
            }
            if(parent.get(i) != i) {
                parent.put(i, find(parent.get(i)));
            }
            return parent.get(i);
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot == jRoot) {
                return;
            }

            if(rank.get(iRoot) < rank.get(jRoot)) {
                parent.put(iRoot, jRoot);
            } else if(rank.get(jRoot) < rank.get(iRoot)) {
                parent.put(jRoot, iRoot);
            } else {
                parent.put(iRoot, jRoot);
                rank.put(jRoot, rank.get(jRoot) + 1);
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    /* Depth-first search
       Time complexity: O(n), Space complexity: O(n) */
    public static int removeStones2(int[][] stones) {
        if(stones == null || stones.length < 2) {
            return 0;
        }

        boolean[] visited = new boolean[stones.length];
        int numOfIslands = 0;

        for (int i = 0; i < stones.length; i++) {
            if(!visited[i]) {
                dfsUtil(stones, i, visited);
                numOfIslands++;
            }
        }
        return stones.length - numOfIslands;
    }

    private static void dfsUtil(int[][] stones, int idx, boolean[] visited) {
        visited[idx] = true;
        for (int i = 0; i < stones.length; i++) {
            if(visited[i]) {
                continue;
            }
            if(stones[i][0] == stones[idx][0] || stones[i][1] == stones[idx][1]) {
                dfsUtil(stones, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(removeStones2(stones));
    }
}
