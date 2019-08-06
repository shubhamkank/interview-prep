package com.interview.leetcode;

import java.util.*;

public class EvaluateDivision {

    /* Depth-first search
       Time complexity: O(E + Q * (V + E))  Q - number of queries, V - number of vertices, E - number of edges
       O(E) to build the graph, O(Q * (V + E)) - DFS for each query
       Space complexity: O(V) */
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for(int i = 0; i < equations.length; i++) {
            map.putIfAbsent(equations[i][0], new HashMap<>());
            map.putIfAbsent(equations[i][1], new HashMap<>());
            map.get(equations[i][0]).put(equations[i][1], values[i]);
            map.get(equations[i][1]).put(equations[i][0], 1 / values[i]);
        }

        double[] result = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            result[i] = dfsUtil(queries[i][0], queries[i][1], map, new HashSet<>(), 1.0);
        }
        return result;
    }

    private static double dfsUtil(String s, String t, Map<String, Map<String, Double>> map, Set<String> visited, double r) {
        if(!map.containsKey(s) || !map.containsKey(t)) {
            return -1.0;
        }
        if(s.equals(t)) {
            return r;
        }

        visited.add(s);
        Map<String, Double> neighbours = map.get(s);
        for(String c : neighbours.keySet()) {
            if(!visited.contains(c)) {
                double result = dfsUtil(c, t, map, visited, r * neighbours.get(c));
                if (result != -1.0) {
                    return result;
                }
            }
        }
        return -1.0;
    }

    /* Union find: https://leetcode.com/problems/evaluate-division/discuss/147281/Java-Union-Find-solution-faster-than-99
       Time complexity: O(e + q) e - number of equations, q - number of queries
       Space complexity: O(V) - number of vertices in the graph
     */
    public static double[] calcEquation2(String[][] equations, double[] values, String[][] queries) {
        Map<String, String> parent = new HashMap<>();// node -> parent
        Map<String, Double> ratio = new HashMap<>();   // node -> node / parent

        for(int i = 0; i < equations.length; i++) {
            union(parent, ratio, equations[i][0], equations[i][1], values[i]);
        }

        double[] result = new double[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String s1 = queries[i][0];
            String s2 = queries[i][1];
            result[i] = -1.0;

            if(parent.containsKey(s1) && parent.containsKey(s2) &&
                    find(parent, ratio, s1).equals(find(parent, ratio, s2))) {
                result[i] = ratio.get(s1) / ratio.get(s2);
            }
        }
        return result;
    }

    private static void union(Map<String, String> parent, Map<String, Double> ratio, String s1, String s2, double val) {
        if(!parent.containsKey(s1)) {
            parent.put(s1, s1);
            ratio.put(s1, 1.0);
        }
        if(!parent.containsKey(s2)) {
            parent.put(s2, s2);
            ratio.put(s2, 1.0);
        }

        String p1 = find(parent, ratio, s1);
        String p2 = find(parent, ratio, s2);
        parent.put(p1, p2);
        ratio.put(p1, val * ratio.get(s2) / ratio.get(s1));
    }

    private static String find(Map<String, String> parent, Map<String, Double> ratio, String s) {
        if(s.equals(parent.get(s))) {
            return s;
        }
        String father = parent.get(s);
        String grandfather = find(parent, ratio, father);
        //path-compression
        parent.put(s, grandfather);
        ratio.put(s, ratio.get(s) * ratio.get(father));
        return grandfather;
    }

    public static void main(String[] args) {
        String[][] equations = { {"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        Arrays.stream(calcEquation2(equations, values, queries)).forEach(System.out::println);
    }
}
