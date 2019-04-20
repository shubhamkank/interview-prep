package com.interview.leetcode;

import java.util.*;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for(int i = 0; i < numCourses; i++) {
            indegreeMap.put(i, 0);
        }

        for(int i = 0; i < prerequisites.length; i++) {
            graph.putIfAbsent(prerequisites[i][1], new HashSet<>());
            Set<Integer> set = graph.get(prerequisites[i][1]);
            if(!set.contains(prerequisites[i][0])) {
                set.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], set);
                indegreeMap.put(prerequisites[i][0], indegreeMap.get(prerequisites[i][0]) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int c : indegreeMap.keySet()) {
            if(indegreeMap.get(c) == 0) {
                queue.add(c);
            }
        }

        List<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            int u = queue.remove();
            result.add(u);

            if(graph.containsKey(u)) {
                for(int v : graph.get(u)) {
                    indegreeMap.put(v, indegreeMap.get(v) - 1);
                    if(indegreeMap.get(v) == 0) {
                        queue.add(v);
                    }
                }
            }
        }

        if(result.size() != indegreeMap.size()) {
            return false;
        }
        return true;
    }

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> visited = new HashMap<>();

        for(int i = 0; i < numCourses; i++) {
            visited.put(i, 0);
        }

        for(int i = 0; i < prerequisites.length; i++) {
            graph.putIfAbsent(prerequisites[i][1], new HashSet<>());
            Set<Integer> set = graph.get(prerequisites[i][1]);
            if(!set.contains(prerequisites[i][0])) {
                set.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], set);
            }
        }

        for(int u : visited.keySet()) {
            if(visited.get(u) == 0) {
                if(!dfsUtil(u, graph, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfsUtil(int u, Map<Integer, Set<Integer>> graph, Map<Integer, Integer> visited) {
        visited.put(u, 1);
        if(graph.containsKey(u)) {
            for(int v : graph.get(u)) {
                if(visited.get(v) == 1) {
                    return false;
                }
                if(visited.get(v) == 0) {
                    if(!dfsUtil(v, graph, visited)) {
                        return false;
                    }
                }
            }
        }
        visited.put(u, 2);
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canFinish2(2, new int[][]{{1, 0}}));
        System.out.println(canFinish2(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(canFinish2(4, new int[][]{{1, 0}, {2, 1}}));
        System.out.println(canFinish2(4, new int[][]{{1, 0}, {2, 1}, {2, 0}}));
        System.out.println(canFinish2(4, new int[][]{{1, 0}, {2, 1}, {0, 2}}));
    }
}
