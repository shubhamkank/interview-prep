package com.interview.leetcode;

import java.util.*;

public class UniqueWordAbbrev {

    static class ValidWordAbbr {

        Map<String, Set<String>> map;

        public ValidWordAbbr(String[] dictionary) {
            map = new HashMap<>();
            for (String word: dictionary) {
                String wordAbbr = getWordAbbr(word);
                map.putIfAbsent(wordAbbr, new HashSet<>());
                map.get(wordAbbr).add(word);
            }
        }

        public boolean isUnique(String word) {
            String wordAbbr = getWordAbbr(word);
            if (map.containsKey(wordAbbr)) {
                Set<String> set = map.get(wordAbbr);
                return set.contains(word) && set.size() == 1;
            } else {
                return true;
            }
        }

        private static String getWordAbbr(String word) {
            int wordLen = word.length();
            if (wordLen <= 2) {
                return word;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(word.charAt(0));
            sb.append(String.valueOf(wordLen - 2));
            sb.append(word.charAt(wordLen -1));
            return sb.toString();
        }
    }

    static class ValidWordAbbr2 {

        Map<String, String> map;

        public ValidWordAbbr2(String[] dictionary) {
            map = new HashMap<>();
            for (String word: dictionary) {
                String wordAbbr = getWordAbbr(word);
                if(map.containsKey(wordAbbr)) {
                    if(!map.get(wordAbbr).equals(word)) {
                        map.put(wordAbbr, "");
                    }
                } else {
                    map.put(wordAbbr, word);
                }
            }
        }

        public boolean isUnique(String word) {
            String wordAbbr = getWordAbbr(word);
            return !map.containsKey(wordAbbr) || map.get(wordAbbr).equals(word);
        }

        private static String getWordAbbr(String word) {
            int wordLen = word.length();
            if (wordLen <= 2) {
                return word;
            }

            StringBuilder sb = new StringBuilder();
            sb.append(word.charAt(0));
            sb.append(String.valueOf(wordLen - 2));
            sb.append(word.charAt(wordLen -1));
            return sb.toString();
        }
    }


}
