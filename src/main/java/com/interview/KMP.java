package com.interview;

public class KMP {

    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private static int[] computePiTable(char[] pattern) {
        //lps stands for longest proper prefix which is also suffix
        int[] lps = new int[pattern.length];
        int j = 0;

        for (int i = 1; i < pattern.length;) {
            if(pattern[j] == pattern[i]) {
                lps[i] = j + 1;
                j++;
                i++;
            } else {
                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static boolean KMP(char[] text, char[] pattern) {
        int[] lps = computePiTable(pattern);
        int i = 0;
        int j = 0;

        while (i < text.length && j < pattern.length) {
            if(text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if(j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        if(j == pattern.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(KMP("abcxabcdabcdabcy".toCharArray(), "abcdabcy".toCharArray()));
    }
}
