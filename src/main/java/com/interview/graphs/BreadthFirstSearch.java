package com.interview.graphs;

import java.util.LinkedList;

public class BreadthFirstSearch {

    static class Graph {

        private final int V;
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

        /* Time complexity: O(V + E), Space complexity: O(V) */
        public void BFS(int v) {
            boolean[] visited = new boolean[V];
            //Using LinkedList as queue as Queue is an abstract class
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(v);

            while(!queue.isEmpty()) {
                int x = queue.remove();
                visited[x] = true;
                System.out.print(x + " ");

                for(int w : adj[x]) {
                    if(!visited[w]) {
                        queue.add(w);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(g);

        g.BFS(2);
    }
}
