package com.interview.graphs;

import java.util.LinkedList;
import java.util.Stack;

public class DepthFirstSearch {

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
        public void DFS(int v) {
            boolean[] visited = new boolean[V];
            DFSUtil(v, visited);
        }

        /* For disconnected graph
           Time complexity: O(V + E), Space complexity: O(V) */
        public void DFSAll() {
            boolean[] visited = new boolean[V];

            for(int i = 0; i < V; i++) {
                if(!visited[i]) {
                    DFSUtil(i, visited);
                }
            }
        }

        /* Iterative implementation
           Time complexity: O(V + E), Space complexity: O(V) */
        public void DFSIterative(int v) {
            boolean[] visited = new boolean[V];

            Stack<Integer> stack = new Stack<>();
            stack.push(v);

            while(!stack.isEmpty()) {
                int x = stack.pop();
                visited[x] = true;
                System.out.print(x + " ");

                for(int w : adj[x]) {
                    if(!visited[w]) {
                        stack.push(w);
                    }
                }
            }
        }

        private void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");

            for(int w : adj[v]) {
                if(!visited[w]) {
                    DFSUtil(w, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(4);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(3, 3);
        System.out.println(g1);

        g1.DFS(2);
        System.out.print("\n");

        g1.DFSIterative(2);
        System.out.print("\n");

        g1.DFSAll();
    }
}
