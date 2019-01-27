package com.interview.graphs;

import java.util.LinkedList;

public class AdjListGraph {

    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public AdjListGraph(int V) {
        this.V = V;
        this.adj = new LinkedList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v) {
        validateVertex(u);
        validateVertex(v);
        E++;
        adj[u].add(v);
        adj[v].add(u);
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int u = 0; u < V; u++) {
            s.append(u + ": ");
            for (int v : adj[u]) {
                s.append(v + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        AdjListGraph graph = new AdjListGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        System.out.println(graph);
    }
}
