package com.interview.leetcode;

public class AddBoldTagInString {


    //Time Complexity: O(N * sum(w)) where N is length of s and sum(w) is sum of length of words in dict
    //Space complexity: O(N)
    public static String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];

        for(String word : dict) {
            int start = 0;
            while(start >= 0) {
                start = s.indexOf(word, start);
                if(start < 0) {
                    break;
                }

                int end = start + word.length();
                for(int i = start; i < end; i++) {
                    bold[i] = true;
                }
                //Just start from next index, instead of iterating through entire string
                start++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            if(bold[i] && (i == 0 || !bold[i - 1])) {
                sb.append("<b>");
            }

            sb.append(s.charAt(i));

            if(bold[i] && (i == s.length() - 1 || !bold[i + 1])) {
                sb.append("</b>");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBoldTag("abcxyz123", new String[] {"abc","123"}));
        System.out.println(addBoldTag("aaabbcc", new String[] {"aaa","aab","bc"}));

        System.out.println(-1 % 26) ;
    }
}
