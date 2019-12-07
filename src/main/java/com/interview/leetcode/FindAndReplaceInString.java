package com.interview.leetcode;

import java.util.Arrays;

public class FindAndReplaceInString {

    //Time complexity: O(
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        int N = S.length();
        int[] match = new int[N];
        Arrays.fill(match, -1);

        for (int i = 0; i < indexes.length; i++) {
            int idx = indexes[i];
            if(S.substring(idx, idx + sources[i].length()).equals(sources[i])) {
                match[idx] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < N) {
            if(match[i] >= 0) {
                sb.append(targets[match[i]]);
                i += sources[match[i]].length();
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(findReplaceString("abcd", new int[] {0, 2}, new String[] {"a", "cd"}, new String[]{"eee", "ffff"}));
    }
}
