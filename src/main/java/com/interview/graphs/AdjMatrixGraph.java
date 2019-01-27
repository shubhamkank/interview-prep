package com.interview.graphs;

public class AdjMatrixGraph {

    private final int V;
    private int E;
    private boolean[][] adj;

    public AdjMatrixGraph(int V) {
        this.V = V;
        this.adj = new boolean[V][V];
    }

    public void addEdge(int u, int v) {
        if(!adj[u][v]) {
            E++;
        }
        adj[u][v] = true;
        adj[v][u] = true;
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
            for(int v = 0; v < V; v++) {
                if(adj[u][v]) {
                    s.append(v + " ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        AdjMatrixGraph graph = new AdjMatrixGraph(5);
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
