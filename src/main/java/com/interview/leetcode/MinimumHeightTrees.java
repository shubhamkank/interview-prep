package com.interview.leetcode;

import java.util.*;

public class MinimumHeightTrees {

    /* The idea is to eat up all the leaves at the same time, until one/two leaves are left
       https://leetcode.com/problems/minimum-height-trees/discuss/76055/Share-some-thoughts
       Time complexity: O(n), Space complexity: O(n)
       Note that for a tree we always have V = n, E = n-1 */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) {
            return Arrays.asList(1);
        }
        List<Integer> result = new ArrayList<>();

        List<Integer>[] graph = new LinkedList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] indegree = new int[n];
        Queue<Integer> leaves = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            indegree[i] = graph[i].size();
            if(indegree[i] == 1) {
                leaves.add(i);
            }
        }

        int count = n;
        while(count > 2) {
            int size = leaves.size();
            count -= size;

            for(int i = 0; i < size; i++) {
                int u = leaves.remove();
                for(int v : graph[u]) {
                    indegree[v]--;
                    if(indegree[v] == 1) {
                        leaves.add(v);
                    }
                }
            }
        }
        result.addAll(leaves);
        return result;
    }

    public static void main(String[] args) {

    }
}
