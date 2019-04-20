package com.interview.leetcode;

import java.util.*;

public class ReconstructItinerary {

    /* Eulerian Path: https://en.wikipedia.org/wiki/Eulerian_path
       https://leetcode.com/problems/reconstruct-itinerary/discuss/78768/Short-Ruby-Python-Java-C%2B%2B
       https://leetcode.com/problems/reconstruct-itinerary/discuss/78766/Share-my-solution
       Time complexity: O(n + nlogn + n) (build graph O(n + nlogn), Hierholzer Algorithm O(n))
       Space complexity: O(n), where n is the total number of tickets
     */
    public static List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        LinkedList<String> path = new LinkedList<>();

        for(String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).add(ticket[1]);
        }

        dfsUtil("JFK", graph, path);
        return path;
    }

    private static void dfsUtil(String source, Map<String, PriorityQueue<String>> graph, LinkedList<String> path) {
        PriorityQueue<String> destinations = graph.get(source);
        while(destinations != null && !destinations.isEmpty()) {
            dfsUtil(destinations.poll(), graph, path);
        }
        path.addFirst(source);
    }

    public static void main(String[] args) {
        System.out.println(findItinerary(new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}}));
        System.out.println(findItinerary(new String[][]{{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}}));
    }
}
