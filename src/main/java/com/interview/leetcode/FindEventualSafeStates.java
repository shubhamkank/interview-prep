package com.interview.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {

    enum State {
        SAFE,
        UNSAFE
    }

    /* Depth first search
       Time complexity: O(V + E), Space complexity: O(V) */
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> safeNodes = new ArrayList<>();
        State[] states = new State[graph.length];

        for(int i = 0; i < graph.length; i++) {
            if(isSafe(i, graph, states)) {
                safeNodes.add(i);
            }
        }
        return safeNodes;
    }

    private static boolean isSafe(int u, int[][] graph, State[] states) {
        if(states[u] != null) {
            return states[u] == State.SAFE;
        }

        states[u] = State.UNSAFE;
        for(int v : graph[u]) {
            if(!isSafe(v, graph, states)) {
                return false;
            }
        }
        states[u] = State.SAFE;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }
}
