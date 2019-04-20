package com.interview.leetcode;

import java.util.*;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        int[] indegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.add(i);
            }
        }

        int result = 0;
        while(!queue.isEmpty()) {
            int u = queue.remove();
            result++;

            for(int v : graph[u]) {
                indegree[v]--;
                if(indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if(result != numCourses) {
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
