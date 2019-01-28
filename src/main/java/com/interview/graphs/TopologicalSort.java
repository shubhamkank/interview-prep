package com.interview.graphs;

import java.util.Stack;

public class TopologicalSort {

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
