package com.interview.graphs;

import java.util.Stack;

public class BellmanFordSP {

    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean negativeCycle;

    /* Time complexity: O(VE) */
    public BellmanFordSP(EdgeWeightedDigraph graph, int s) {
        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0;

        for(int i = 1; i < graph.V() - 1; i++) {
            for(DirectedEdge edge : graph.edges()) {
                int u = edge.from();
                int v = edge.to();
                double weight = edge.weight();

                if(distTo[v] > distTo[u] + weight) {
                    distTo[v] = distTo[u] + weight;
                    edgeTo[v] = edge;
                }
            }
        }

        for(DirectedEdge edge : graph.edges()) {
            int u = edge.from();
            int v = edge.to();
            double weight = edge.weight();

            if(distTo[v] > distTo[u] + weight) {
                negativeCycle = true;
                break;
            }
        }
    }

    public boolean hasNegativeCycle() {
        return negativeCycle;
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public double distTo(int v) {
        validateVertex(v);
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(8);
        graph.addEdge(new DirectedEdge(4, 5,  0.35));
        graph.addEdge(new DirectedEdge(5, 4,  0.35));
        graph.addEdge(new DirectedEdge(4, 7,  0.37));
        graph.addEdge(new DirectedEdge(5, 7,  0.28));
        graph.addEdge(new DirectedEdge(7, 5,  0.28));
        graph.addEdge(new DirectedEdge(5, 1,  0.32));
        graph.addEdge(new DirectedEdge(0, 4,  0.38));
        graph.addEdge(new DirectedEdge(0, 2,  0.26));
        graph.addEdge(new DirectedEdge(7, 3,  0.39));
        graph.addEdge(new DirectedEdge(1, 3,  0.29));
        graph.addEdge(new DirectedEdge(2, 7,  0.34));
        graph.addEdge(new DirectedEdge(6, 2, -1.20));
        graph.addEdge(new DirectedEdge(3, 6,  0.52));
        graph.addEdge(new DirectedEdge(6, 0, -1.40));
        graph.addEdge(new DirectedEdge(6, 4, -1.25));

        int s = 0;
        BellmanFordSP sp = new BellmanFordSP(graph, s);

        if (!sp.hasNegativeCycle()) {
            for (int v = 0; v < graph.V(); v++) {
                if (sp.hasPathTo(v)) {
                    System.out.print(s + " to " + v + " (" + sp.distTo(v) + ")  ");
                    for (DirectedEdge e : sp.pathTo(v)) {
                        System.out.print(e + "   ");
                    }
                    System.out.println();
                }
                else {
                    System.out.print(s + " to " + v + " no path");
                }
            }
        }
    }
}
