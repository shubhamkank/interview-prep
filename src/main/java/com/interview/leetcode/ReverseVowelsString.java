package com.interview.leetcode;

import java.util.*;

public class ReverseVowelsString {

    static Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static String reverseVowels(String s) {
        char[] ar = s.toCharArray();
        int l = 0;
        int n = s.length();
        int r = n - 1;

        while(l < r) {
            while(l < n && !isVowel(ar[l])) {
                l++;
            }

            while(r >= 0 && !isVowel(ar[r])) {
                r--;
            }

            if(l < n && r >= 0 && l < r) {
                char temp = ar[l];
                ar[l] = ar[r];
                ar[r] = temp;
                l++;
                r--;
            }
        }
        return new String(ar);
    }

    public String removeVowels2(String S) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < S.length(); i++) {
            if(!set.contains(S.charAt(i))) {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }

    private static boolean isVowel(char c) {
        return set.contains(c);
    }

    public static void main(String[] args) {

    }
}
