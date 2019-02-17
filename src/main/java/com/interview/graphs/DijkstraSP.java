package com.interview.graphs;

import java.util.*;

public class DijkstraSP {

    private double[] dist;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;

    /* Time complexity: O(ElogV), Space complexity: O(V)
       Applicable for non-negative weights*/
    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        dist = new double[graph.V()];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(graph.V(), new Node());

        for (int i = 0; i < graph.V(); i++) {
            dist[i] = Double.POSITIVE_INFINITY;
        }
        dist[s] = 0;

        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            int u = pq.remove().node;
            settled.add(u);

            for(DirectedEdge e : graph.adj(u)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int u = e.from();
        int v = e.to();

        if(!settled.contains(v)) {
            if(dist[v] > dist[u] + e.weight()) {
                dist[v] = dist[u] + e.weight();
            }
            pq.add(new Node(v, dist[v]));
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
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(9);
        graph.addEdge(new DirectedEdge(0, 1, 4));
        graph.addEdge(new DirectedEdge(0, 7, 8));

        graph.addEdge(new DirectedEdge(1, 0, 4));
        graph.addEdge(new DirectedEdge(1, 2, 8));
        graph.addEdge(new DirectedEdge(1, 7, 11));

        graph.addEdge(new DirectedEdge(2, 1, 8));
        graph.addEdge(new DirectedEdge(2, 3, 7));
        graph.addEdge(new DirectedEdge(2, 5, 4));
        graph.addEdge(new DirectedEdge(2, 8, 2));

        graph.addEdge(new DirectedEdge(3, 2, 7));
        graph.addEdge(new DirectedEdge(3, 4, 9));
        graph.addEdge(new DirectedEdge(3, 5, 14));

        graph.addEdge(new DirectedEdge(4, 3, 9));
        graph.addEdge(new DirectedEdge(4, 5, 10));

        graph.addEdge(new DirectedEdge(5, 2, 4));
        graph.addEdge(new DirectedEdge(5, 3, 14));
        graph.addEdge(new DirectedEdge(5, 4, 10));
        graph.addEdge(new DirectedEdge(5, 6, 2));

        graph.addEdge(new DirectedEdge(6, 5, 2));
        graph.addEdge(new DirectedEdge(6, 7, 1));
        graph.addEdge(new DirectedEdge(6, 8, 6));

        graph.addEdge(new DirectedEdge(7, 0, 8));
        graph.addEdge(new DirectedEdge(7, 1, 11));
        graph.addEdge(new DirectedEdge(7, 6, 1));
        graph.addEdge(new DirectedEdge(7, 8, 7));

        graph.addEdge(new DirectedEdge(8, 2, 2));
        graph.addEdge(new DirectedEdge(8, 6, 6));
        graph.addEdge(new DirectedEdge(8, 7, 7));

        DijkstraSP dijkstraSP = new DijkstraSP(graph, 0);
        double[] dist = dijkstraSP.dist;
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + " " + dist[i]);
        }
    }

}
