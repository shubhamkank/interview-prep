package com.interview.graphs;

import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSort {

    static class Graph {
        private final int V;
        private int E;
        private LinkedList<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.adj = new LinkedList[V];
            for(int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int u, int v) {
            validateVertex(u);
            validateVertex(v);
            adj[u].add(v);
            E++;
        }

        public LinkedList<Integer> adj(int v) {
            return adj[v];
        }

        private void validateVertex(int v) {
            if(v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            for (int u = 0; u < V; u++) {
                s.append(u + ": ");
                for (int v : adj[u]) {
                    s.append(v + " ");
                }
                s.append("\n");
            }
            return s.toString();
        }

        public int V() {
            return V;
        }
    }

    /* Time complexity: O(V + E), Space complexity: O(V)
       Applicable for Directed Acyclic Graphs (DAGs) */
    public static void topologicalSort(Graph g) {
        boolean[] visited = new boolean[g.V()];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < g.V(); i++) {
            if(!visited[i]) {
                topologicalSortUtil(g, i, visited, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void topologicalSortUtil(Graph g, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for(int w : g.adj(v)) {
            if(!visited[w]) {
                topologicalSortUtil(g, w, visited, stack);
            }
        }
        stack.push(v);
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        topologicalSort(g);
    }
}
