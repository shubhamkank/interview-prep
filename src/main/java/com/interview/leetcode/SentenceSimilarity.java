package com.interview.leetcode;

import java.util.*;

public class SentenceSimilarity {

    //Time complexity: O(n + p) where n is number of words & p is the number of pairs
    //Space complexity: O(p)
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) {
            return false;
        }

        Set<String> set = new HashSet<>();
        for(List<String> pair : pairs) {
            set.add(pair.get(0) + "#" + pair.get(1));
        }

        for(int i = 0; i < words1.length; i++) {
            if(!words1[i].equals(words2[i]) && !set.contains(words1[i] + "#" + words2[i]) &&
                    !set.contains(words2[i] + "#" + words1[i])) {
                return false;
            }
        }
        return true;
    }
}
