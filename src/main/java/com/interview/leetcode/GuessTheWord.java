package com.interview.leetcode;

import java.util.*;

interface Master {
      int guess(String word);
}

public class GuessTheWord {

    //Time complexity: O(n) but doesn't work on all the test case under 10 guesses
    public void findSecretWord(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) {
            String guess = wordlist[new Random().nextInt(wordlist.length)];
            int x = master.guess(guess);
            if(x == 6) {
                return;
            }
            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist) {
                if (match(guess, w) == x) {
                    wordlist2.add(w);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()];
        }
    }

    /* Time complexity: O(n^2)
       The probability of two words with 0 match is (25/26)^6 = 80%. That is to say, for a candidate word, we have 80%
       chance to see 0 match with the secret word. In this case, we had 80% chance to eliminate the candidate word and
       its "family" words which have at least 1 match. Additionally, in order to delete a max part of words, we select
       a candidate who has a big "family" (fewest 0 match with other words) */
    public void findSecretWord2(String[] wordlist, Master master) {
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> countMap = new HashMap<>();
            for(String w1 : wordlist) {
                for(String w2 : wordlist) {
                    if(match(w1, w2) == 0) {
                        countMap.put(w1, countMap.getOrDefault(w1, 0) + 1);
                    }
                }
            }
            String minWord = "";
            int minCount = Integer.MAX_VALUE;
            for(String w : wordlist) {
                if(countMap.getOrDefault(w, 0) < minCount) {
                    minWord = w;
                    minCount = countMap.getOrDefault(w, 0);
                }
            }

            int x = master.guess(minWord);
            if(x == 6) {
                return;
            }

            List<String> wordlist2 = new ArrayList<>();
            for (String w : wordlist) {
                if (match(minWord, w) == x) {
                    wordlist2.add(w);
                }
            }
            wordlist = wordlist2.toArray(new String[wordlist2.size()];
        }
    }2

    public int match(String w1, String w2) {
        int matches = 0;
        for (int i = 0; i < w1.length(); i++) {
            if(w1.charAt(i) == w2.charAt(i)) {
                matches++;
            }
        }
        return matches;
    }
}
