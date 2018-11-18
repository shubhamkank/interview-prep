package com.interview.leetcode;

public class RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        if(p.isEmpty()) {
            return s.isEmpty();
        }

        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if(p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean [][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        //p = a* or a*b*c* compared with empty text
        for(int i = 1; i <= p.length(); i++) {
            if(p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 1; j <= p.length(); j++) {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if(p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if((s.charAt(i - 1) == p.charAt(j - 2)) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        System.out.println(isMatch2("aa", "a"));
        System.out.println(isMatch2("aa", "a*"));
        System.out.println(isMatch2("ab", ".*"));
        System.out.println(isMatch2("aab", "c*a*b"));
        System.out.println(isMatch2("mississippi", "mis*is*p*."));
    }
}
