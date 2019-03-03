package com.interview.graphs;

import java.util.*;

/* https://www.geeksforgeeks.org/strongly-connected-components/
   Time complexity: O(V + E), Space complexity: O(V)
   1. Use DFS on the graph to find vertices in decreasing order their finishing time
   2. Reverse the graph
   3. Run DFS on the reversed graph using the vertices from the order in the 1st step */
public class KosarajuSCC {

    private List<Set<Integer>> result;

    public KosarajuSCC(Digraph graph) {

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            if(!visited[i]) {
                DFSUtil(graph, i, visited, stack);
            }
        }

        Digraph reverseGraph = graph.reverse();

        Arrays.fill(visited, false);
        result = new ArrayList<>();
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if(!visited[v]) {
                Set<Integer> set = new HashSet<>();
                DFSUtilForReverseGraph(reverseGraph, v, visited, set);
                result.add(set);
            }
        }
    }

    private void DFSUtilForReverseGraph(Digraph reverseGraph, int v, boolean[] visited, Set<Integer> set) {
        visited[v] = true;
        set.add(v);
        for(int w : reverseGraph.adj(v)) {
            if(!visited[w]) {
                DFSUtilForReverseGraph(reverseGraph, w, visited, set);
            }
        }
    }

    private void DFSUtil(Digraph graph, int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for(int w : graph.adj(v)) {
            if(!visited[w]) {
                DFSUtil(graph, w, visited, stack);
            }
        }
        stack.push(v);
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        KosarajuSCC kosarajuSCC = new KosarajuSCC(g);
        System.out.println(kosarajuSCC.result);

        Digraph g2 = new Digraph(13);
        g2.addEdge(4 , 2);
        g2.addEdge(2 , 3);
        g2.addEdge(3 , 2);
        g2.addEdge(6 , 0);
        g2.addEdge(0 , 1);
        g2.addEdge(2 , 0);
        g2.addEdge(11, 12);
        g2.addEdge(12,  9);
        g2.addEdge(9 ,10);
        g2.addEdge(9 ,11);
        g2.addEdge(7 , 9);
        g2.addEdge(10, 12);
        g2.addEdge(11,  4);
        g2.addEdge(4 , 3);
        g2.addEdge(3 , 5);
        g2.addEdge(6 , 8);
        g2.addEdge(8 , 6);
        g2.addEdge(5 , 4);
        g2.addEdge(0 , 5);
        g2.addEdge(6 , 4);
        g2.addEdge(6 , 9);
        g2.addEdge(7 , 6);

        KosarajuSCC kosarajuSCC2 = new KosarajuSCC(g2);
        System.out.println(kosarajuSCC2.result);
    }
}
