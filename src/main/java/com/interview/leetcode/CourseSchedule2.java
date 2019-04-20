package com.interview.leetcode;

import java.util.*;

public class CourseSchedule2 {

    /* Topological sort - Kahn's Algorithm (uses BFS)
       Time complexity: O(V + E), Space complexity: O(V + E) for the graph otherwise O(V) */
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
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

        int[] schedule = new int[numCourses];
        int i = 0;
        while(!queue.isEmpty()) {
            int u = queue.remove();
            schedule[i] = u;
            i++;

            for(int v : graph[u]) {
                indegree[v]--;
                if(indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if(i != numCourses) {
            schedule = new int[0];
        }
        Arrays.stream(schedule).forEach(x -> System.out.print(x + " "));
        System.out.println();
        return schedule;
    }

    public static void main(String[] args) {
        findOrder(2, new int[][]{{1, 0}});
        findOrder(2, new int[][]{{1, 0}, {0, 1}});
        findOrder(4, new int[][]{{1, 0}, {2, 1}});
        findOrder(4, new int[][]{{1, 0}, {2, 1}, {2, 0}});
        findOrder(4, new int[][]{{1, 0}, {2, 1}, {0, 2}});
    }
}
