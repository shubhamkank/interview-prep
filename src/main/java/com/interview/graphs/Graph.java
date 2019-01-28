package com.interview.graphs;

import java.util.LinkedList;

public class Graph {

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
