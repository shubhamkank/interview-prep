package com.interview.leetcode;

public class RedundantConnection2 {

    /* Union Find
       Time complexity: O(n), Space complexity: O(n)
       https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C%2B%2BJava-Union-Find-with-explanation-O(n)
       https://leetcode.com/problems/redundant-connection-ii/discuss/108058/one-pass-disjoint-set-solution-with-explain

     */
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];

        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};

        /* Check if there is a node with two parents. If yes, then there are two potential candidates edges with
        destination node having indegree 2. */
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if(parent[v] == 0) {
                parent[v] = u;
            } else {
                can1 = new int[] {parent[v], v};
                can2 = new int[] {u, v};
                edge[1] = 0; //Remove the later edge
            }
        }

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for(int[] edge : edges) {
            if(edge[1] == 0) {
                continue;
            }
            int u = edge[0];
            int v = edge[1];
            int uRoot = findRoot(parent, u);

            //Condition when cycle exists
            if(uRoot == v) {
                //If no node exists with indegree = 2 (Case 2)
                if(can1[0] == -1) {
                    return edge;
                } else {
                    //If a node exists with indegree = 2 and cycle exists too (Case 3)
                    return can1;
                }
            }
            parent[v] = u;
        }
        //Case 1
        return can2;
    }

    private static int findRoot(int[] parent, int i) {
        if(i != parent[i]) {
            //Path compression
            parent[i] = findRoot(parent, parent[i]);
        }
        return parent[i];
    }

    public static void main(String[] args) {

    }
}
