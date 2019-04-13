package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class CouplesHoldingHands {

    /* Greedy Approach
       1. We aren't doing any redundant swaps - on every swap atleast one couple (or at max two) is united
       Time complexity: O(n ^ 2), Space complexity: O(1) */
    public static int minSwapsCouples(int[] row) {
        int result = 0;
        for(int i = 0; i < row.length; i += 2) {
            int x = row[i];
            if(row[i + 1] == (x ^ 1)) {
                continue;
            }

            for(int j = i + 2; j < row.length; j++) {
                if(row[j] == (x ^ 1)) {
                    int temp = row[j];
                    row[j] = row[i + 1];
                    row[i + 1] = temp;
                    result++;
                    break;
                }
            }
        }
        return result;
    }

    /* Greedy Approach (Optimised by using hash map)
       Time complexity: O(n), Space complexity: O(n) */
    public static int minSwapsCouples2(int[] row) {
        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < row.length; i += 2) {
            map.put(row[i], i);
        }

        for(int i = 0; i < row.length; i += 2) {
            int x = row[i];
            int y = x ^ 1;

            if(row[i + 1] != y) {
                int neighbour = row[i + 1];
                row[i + 1] = y;     //not necessary
                row[map.get(y)] = neighbour;
                map.put(neighbour, map.get(y));
                map.put(y, i + 1);  //not necessary
                result++;
            }
        }
        return result;
    }

    /* Union find approach
       https://leetcode.com/problems/couples-holding-hands/discuss/117520/Java-union-find-easy-to-understand-5-ms
       https://leetcode.com/problems/couples-holding-hands/discuss/113369/Formal-proof-of-the-optimality-of-greedy-algorithm
       https://en.wikipedia.org/wiki/Cyclic_permutation
       Time complexity: O(n), Space complexity: O(n) */
    public static int minSwapsCouples3(int[] row) {
        int n = row.length / 2;

        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            int x = row[2 * i] / 2;
            int y = row[2 * i + 1] / 2;
            uf.union(x, y);
        }
        return n - uf.getCount();
    }

    static class UnionFind {

        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            count = n;
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
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {

    }
}
