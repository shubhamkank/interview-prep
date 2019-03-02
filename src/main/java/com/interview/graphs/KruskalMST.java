package com.interview.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST {

    private PriorityQueue<Edge> pq;
    private List<Edge> mst;
    private double weight;

    /* Greedy Algorithm
       Time complexity: O(ElogV), Space complexity: O(V) */
    public KruskalMST(EdgeWeightedGraph graph) {
        pq = new PriorityQueue<>();
        mst = new ArrayList<>();

        for(Edge e : graph.edges()) {
            pq.add(e);
        }

        UnionFind uf = new UnionFind(graph.V());
        while (!pq.isEmpty()) {
            Edge e = pq.remove();
            int u = e.either();
            int v = e.other(u);

            if(!uf.connected(u, v)) {
                uf.union(u, v);
                weight += e.weight();
                mst.add(e);
            }
        }
    }

    public double weight() {
        return weight;
    }

    public List<Edge> edges() {
        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(8);
        graph.addEdge(new Edge(4, 5, 0.35));
        graph.addEdge(new Edge(4, 7, 0.37));
        graph.addEdge(new Edge(5, 7, 0.28));
        graph.addEdge(new Edge(0, 7, 0.16));
        graph.addEdge(new Edge(1, 5, 0.32));
        graph.addEdge(new Edge(0, 4, 0.38));
        graph.addEdge(new Edge(2, 3, 0.17));
        graph.addEdge(new Edge(1, 7, 0.19));
        graph.addEdge(new Edge(0, 2, 0.26));
        graph.addEdge(new Edge(1, 2, 0.36));
        graph.addEdge(new Edge(1, 3, 0.29));
        graph.addEdge(new Edge(2, 7, 0.34));
        graph.addEdge(new Edge(6, 2, 0.40));
        graph.addEdge(new Edge(3, 6, 0.52));
        graph.addEdge(new Edge(6, 0, 0.58));
        graph.addEdge(new Edge(6, 4, 0.93));

        KruskalMST kruskalMST = new KruskalMST(graph);
        System.out.println(kruskalMST.weight());
        System.out.println(kruskalMST.edges());
    }
}
