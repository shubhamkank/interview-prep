package com.interview.leetcode;

public class BackspaceStringCompare {

    //Time complexity: O(m + n), Space complexity: O(m + n)
    public static boolean backspaceCompare(String S, String T) {
        return getReducedString(S).equals(getReducedString(T));
    }

    private static String getReducedString(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == '#') {
                count++;
            } else {
                if(count > 0) {
                    count--;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    //Time complexity: O(m + n), Space complexity: O(1)
    public static boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int count1 = 0, count2 = 0;

        while(i >= 0 || j >= 0) {
            while (i >= 0 && (S.charAt(i) == '#' || count1 > 0)) {
                if(S.charAt(i) == '#') {
                    count1++;
                } else {
                    count1--;
                }
                i--;
            }

            while (j >= 0 && (T.charAt(j) == '#' || count2 > 0)) {
                if(T.charAt(j) == '#') {
                    count2++;
                } else {
                    count2--;
                }
                j--;
            }

            if(i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                i--;
                j--;
            } else {
                return i == -1 && j == -1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceCompare2("bbbextm", "bbb#extm"));
    }
}
