package com.interview.graphs;

public class FloydWarshall {

    private int dist[][];

    /* Time complexity: O(V^3), Space complexity: O(V^2)
       Dynamic Programming - Bottom up approach
       d(i, j, k) - shortest path weight from vertex i to vertex j for all intermediate vertices in the set {1, 2, .., k}
       d(i, j, k) = w(i, j) if k = 0
       d(i, j, k) = min{ d(i, j, k - 1), d(i, k, k - 1) + d(k, j, k - 1) } if k = 0
       Assumes no negative weight cycles although if they exist we can find it
      */
    public FloydWarshall(int[][] graph) {
        int V = graph.length;
        dist = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for(int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int INF = Integer.MAX_VALUE;
        int graph[][] = { 
                {0,   5,  INF, 10}, 
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };
        
        FloydWarshall floydWarshall = new FloydWarshall(graph);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(floydWarshall.dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
