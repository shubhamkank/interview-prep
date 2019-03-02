package com.interview.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimMST {

    private int[] edgeTo;
    private double[] dist;
    private boolean[] visited;
    private PriorityQueue<Node> pq;

    /* Greedy Algorithm
       Connected, Undirected weighted graph
       Time complexity: O(ElogV) - binary heap, O(E + VlogV) - fibonacci heap
       Space complexity: O(V) */
    public PrimMST(EdgeWeightedGraph graph, int s) {
        edgeTo = new int[graph.V()];
        dist = new double[graph.V()];
        visited = new boolean[graph.V()];
        pq = new PriorityQueue<>(graph.V(), new Node());

        for (int i = 0; i < graph.V(); i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }
        dist[s] = 0.0;
        edgeTo[s] = -1;

        pq.add(new Node(s, dist[s]));

        while (!pq.isEmpty()) {
            Node u = pq.remove();
            visited[u.node] = true;

            for(Edge e : graph.adj(u.node)) {
                prim(u.node, e.other(u.node), e.weight());
            }
        }

    }

    private void prim(int u, int v, double weight) {
        if(!visited[v]) {
            if(weight < dist[v]) {
                dist[v] = weight;
                edgeTo[v] = u;
                pq.add(new Node(v, dist[v]));
            }
        }
    }

    static class Node implements Comparator<Node> {
        int node;
        double cost;

        public Node() {
        }

        public Node(int node, double cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node n1, Node n2) {
            if(n1.cost < n2.cost) {
                return -1;
            }
            if(n1.cost > n2.cost) {
                return 1;
            }
            return 0;
        }
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

        PrimMST primMST = new PrimMST(graph, 0);

        int[] edgeTo = primMST.edgeTo;
        double[] dist = primMST.dist;

        System.out.println(Arrays.stream(dist).sum());

        for (int i = 0; i < 8; i++) {
            System.out.println(i + " - " + edgeTo[i] + "\t" + dist[i]);
        }
    }
}
