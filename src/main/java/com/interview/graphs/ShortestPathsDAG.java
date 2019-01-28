package com.interview.graphs;

import java.util.LinkedList;
import java.util.Stack;

public class ShortestPathsDAG {

    static class Graph {
        private final int V;
        private LinkedList<Edge>[] adj;

        public Graph(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int u, int v, int weight) {
            Edge e = new Edge(u, v, weight);
            adj[u].add(e);
        }

        public int V() {
            return V;
        }

        public LinkedList<Edge> adj(int v) {
            return adj[v];
        }
    }

    static class Edge {
        private final int u;
        private final int v;
        private final double weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    public static void shortestPaths(Graph g, int s) {
        boolean[] visited = new boolean[g.V()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < g.V(); i++) {
            if(!visited[i]) {
                topologicalSortUtil(g, i, visited, stack);
            }
        }

        double[] distTo = new double[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

        while(!stack.isEmpty()) {
            int u = stack.pop();
            for(Edge e : g.adj(u)) {
                int v = e.v;
                if(distTo[v] > distTo[u] + e.weight) {
                    distTo[v] = distTo[u] + e.weight;
                }
            }
        }

        for (double dist : distTo) {
            System.out.print(dist + " ");
        }
    }

    private static void topologicalSortUtil(Graph g, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for(Edge e : g.adj(v)) {
            int w = e.v;
            if(!visited[w]) {
                topologicalSortUtil(g, w, visited, stack);
            }
        }
        stack.push(v);
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1, 5);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 2);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, -2);

        shortestPaths(g, 1);
    }
}
