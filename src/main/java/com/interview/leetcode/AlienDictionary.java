package com.interview.leetcode;

import java.util.*;

public class AlienDictionary {

    /* Topological sort - Kahn's Algorithm (uses BFS)
       https://leetcode.com/problems/alien-dictionary/discuss/70119/Java-AC-solution-using-BFS
       https://en.wikipedia.org/wiki/Topological_sorting
       Time complexity: O(n), Space complexity: O(n) n - number of characters in the dictionary
       Since each words[i] and words[i+1] pair can only generate 1 edge at most.
       E is words.length edges at most, which is <=N */
    public static String alienOrder(String[] words) {
        Map<Character, Integer> indegreeMap = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();

        for(String word : words) {
            for(char c : word.toCharArray()) {
                indegreeMap.put(c, 0);
            }
        }

        //sorted dictionary so consider only adjacent words (other comparisons can be implicitly derived)
        for(int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int minLength = Math.min(word1.length(), word2.length());
            for(int j = 0; j < minLength; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if(c1 != c2) {
                    graph.putIfAbsent(c1, new HashSet<>());
                    Set<Character> set = graph.get(c1);
                    if(!set.contains(c2)) {
                        set.add(c2);
                        graph.put(c1, set);
                        indegreeMap.put(c2, indegreeMap.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        LinkedList<Character> queue = new LinkedList<>();

        for(char c : indegreeMap.keySet()) {
            if(indegreeMap.get(c) == 0) {
                queue.add(c);
            }
        }

        while(!queue.isEmpty()) {
            char curChar = queue.remove();
            sb.append(curChar);

            if(graph.containsKey(curChar)) {
                for(char nextChar : graph.get(curChar)) {
                    indegreeMap.put(nextChar, indegreeMap.get(nextChar) - 1);
                    if(indegreeMap.get(nextChar) == 0) {
                        queue.add(nextChar);
                    }
                }
            }
        }

        if(sb.length() != indegreeMap.size()) {
            return "";
        }
        return sb.toString();
    }

    /* Topological sort - using DFS
       https://leetcode.com/problems/alien-dictionary/discuss/70115/3ms-Clean-Java-Solution-(DFS)
       https://www.youtube.com/watch?v=rKQaZuoUR4M (Detect cycle in directed graph)
       Time complexity: O(n), Space complexity: O(n) n - number of characters in the dictionary */
    public static String alienOrder2(String[] words) {
        Map<Character, Integer> visited = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();

        for(String word : words) {
            for(char c : word.toCharArray()) {
                visited.put(c, 0);  //0 == not visited
            }
        }

        for(int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int minLength = Math.min(word1.length(), word2.length());
            for(int j = 0; j < minLength; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                if(c1 != c2) {
                    graph.putIfAbsent(c1, new HashSet<>());
                    Set<Character> set = graph.get(c1);
                    if(!set.contains(c2)) {
                        set.add(c2);
                        graph.put(c1, set);
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : visited.keySet()) {
            if(visited.get(c) == 0) {
                if(!dfs(c, graph, visited, sb)) {
                    return "";
                }
            }
        }
        return sb.reverse().toString();
    }

    private static boolean dfs(char curChar, Map<Character, Set<Character>> graph, Map<Character, Integer> visited, StringBuilder sb) {
        visited.put(curChar, 1);  //1 == visting
        if(graph.containsKey(curChar)) {
            for (char nextChar : graph.get(curChar)) {
                if (visited.get(nextChar) == 1) {
                    return false;
                }
                if (visited.get(nextChar) == 0) {
                    if (!dfs(nextChar, graph, visited, sb)) {
                        return false;
                    }
                }
            }
        }
        visited.put(curChar, 2);    //2 == visited
        sb.append(curChar);
        return true;
    }


    public static void main(String[] args) {
        System.out.println(alienOrder2(new String[] {"z", "z"}));
        System.out.println(alienOrder2(new String[] {"a", "z", "x", "z"}));
        System.out.println(alienOrder2(new String[] {"abcde", "az"}));

        System.out.println(alienOrder2(new String[] {"za", "zb", "ca", "cb"}));
    }
}
