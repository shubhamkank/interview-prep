package com.interview.leetcode;

import java.util.HashSet;
import java.util.Set;

public class CrackingTheSafe {

    //Time complexity: O(k^n), Space complexity: O(k^n)
    //https://leetcode.com/problems/cracking-the-safe/discuss/153039/DFS-with-Explanations
    public static String crackSafe(int n, int k) {
        StringBuilder pwd = new StringBuilder();
        for (int i = 0; i < n; i++) {
            pwd.append('0');
        }

        StringBuilder resultSb = new StringBuilder(pwd);
        int targetNum = (int) Math.pow(k, n);

        Set<String> visited = new HashSet<>();
        visited.add(pwd.toString());

        dfs(resultSb, visited, targetNum, n, k);
        return resultSb.toString();
    }

    private static boolean dfs(StringBuilder resultSb, Set<String> visited, int targetNum, int n, int k) {
        if(visited.size() == targetNum) {
            return true;
        }

        String lastDigits = resultSb.substring(resultSb.length() - n + 1);
        for(char ch = '0'; ch < '0' + k; ch++) {
            String newPwd = lastDigits + ch;
            if(!visited.contains(newPwd)) {
                visited.add(newPwd);
                resultSb.append(ch);
                if(dfs(resultSb, visited, targetNum, n, k)) {
                    return true;
                }
                visited.remove(newPwd);
                resultSb.deleteCharAt(resultSb.length() - 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(crackSafe(1, 2));
        System.out.println(crackSafe(2, 2));
        System.out.println(crackSafe(3, 2));
    }
}
