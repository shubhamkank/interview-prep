package com.interview.leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class SearchAutocompleteSystem {

    public static void main(String[] args) {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sentences, times);
        System.out.println(autocompleteSystem.input('i'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));

        AutocompleteSystem2 autocompleteSystem2 = new AutocompleteSystem2(sentences, times);
        System.out.println(autocompleteSystem2.input('i'));
        System.out.println(autocompleteSystem2.input(' '));
        System.out.println(autocompleteSystem2.input('a'));
        System.out.println(autocompleteSystem2.input('#'));

        String[] sentences2 = {"abc","abbc","a"};
        int[] times2 = {3,3,3};

        AutocompleteSystem2 autocompleteSystem3 = new AutocompleteSystem2(sentences2, times2);
        System.out.println(autocompleteSystem3.input('b'));
        System.out.println(autocompleteSystem3.input('c'));
        System.out.println(autocompleteSystem3.input('#'));
        System.out.println(autocompleteSystem3.input('b'));
        System.out.println(autocompleteSystem3.input('c'));
        System.out.println(autocompleteSystem3.input('#'));
        System.out.println(autocompleteSystem3.input('a'));
        System.out.println(autocompleteSystem3.input('b'));
        System.out.println(autocompleteSystem3.input('c'));
        System.out.println(autocompleteSystem3.input('#'));
        System.out.println(autocompleteSystem3.input('a'));
        System.out.println(autocompleteSystem3.input('b'));
        System.out.println(autocompleteSystem3.input('c'));
        System.out.println(autocompleteSystem3.input('#'));

    }

    static class AutocompleteSystem {

        Map<String, Integer> sentenceCountMap;
        String currentSentence;

        // Time complexity: O(kl) where k is average length of each sentence (calculating hashcode of a sentence will
        // take O(k) time) and l is the total number of sentences
        public AutocompleteSystem(String[] sentences, int[] times) {
            sentenceCountMap = new HashMap<>();
            currentSentence = "";

            for (int i = 0; i < sentences.length; i++) {
                sentenceCountMap.put(sentences[i], times[i]);
            }
        }

        // Time complexity: O(n + m log m) where n is total sentences in the map till now and m is the matched search
        // result which is sorted
        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if (c == '#') {
                sentenceCountMap.put(currentSentence, sentenceCountMap.getOrDefault(currentSentence, 0) + 1);
                currentSentence = "";
            } else {
                List<SearchResult> searchResults = new ArrayList<>();
                currentSentence += c;
                for (String sentence : sentenceCountMap.keySet()) {
                    if (sentence.startsWith(currentSentence)) {
                        searchResults.add(new SearchResult(sentence, sentenceCountMap.get(sentence)));
                    }
                }
                searchResults.sort((r1, r2) -> r1.times == r2.times ? r1.sentence.compareTo(r2.sentence) : r2.times - r1.times);
                result = searchResults.stream().limit(3).map(r -> r.sentence)
                        .collect(Collectors.toList());
            }
            return result;
        }
    }

    static class SearchResult {
        String sentence;
        int times;

        public SearchResult(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }

    // Approach 2: In order to reduce the 'n' value in the previous approach we can use two-level hashmap or an array
    // of hashmaps - Map<String, Integer>[] arr -> length of array = 26 -> sentences starting with 'a' stored in 0th
    // index, sentences starting with 'b' stored in 1st index and so on. The time complexity of input method will
    // become O(s + m log m) where s << n

    // Approach 3: Using Trie
    static class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> histories;

        public TrieNode() {
            children = new HashMap<>();
            histories = new HashMap<>();
        }
    }

    static class AutocompleteSystem2 {

        TrieNode root, cur;
        StringBuilder prefixSB;

        public AutocompleteSystem2(String[] sentences, int[] times) {
            root = new TrieNode();
            cur = root;

            for (int i = 0; i < sentences.length; i++) {
                insert(sentences[i], times[i]);
            }
            prefixSB = new StringBuilder();
        }

        private void insert(String sentence, int time) {
            TrieNode cur = root;
            for(char ch : sentence.toCharArray()) {
                if(!cur.children.containsKey(ch)) {
                    cur.children.put(ch, new TrieNode());
                }
                cur = cur.children.get(ch);
                cur.histories.put(sentence, cur.histories.getOrDefault(sentence, 0) + time);
            }
        }

        public List<String> input(char c) {
            if(c == '#') {
                String prefix = prefixSB.toString();
                prefixSB = new StringBuilder();
                insert(prefix, 1);
                cur = root;
                return new ArrayList<>();
            } else {
                prefixSB.append(c);
                cur = search(c);
                //Null when not found in trie and therefore further characters for given query till # will have null cur pointer
                if(cur == null) {
                    return new ArrayList<>();
                }
                return findTopK(cur.histories, 3);
            }
        }

        private List<String> findTopK(Map<String, Integer> histories, int k) {
            //Min pq to find top k frequent elements: https://www.programcreek.com/2014/05/leetcode-top-k-frequent-elements-java/
            PriorityQueue<SearchResult> pq = new PriorityQueue<>(
                    (r1, r2) -> r1.times == r2.times ? r2.sentence.compareTo(r1.sentence) : r1.times - r2.times);

            for(String sentence : histories.keySet()) {
                pq.add(new SearchResult(sentence, histories.get(sentence)));
                if(pq.size() > k) {
                    pq.remove();
                }
            }

            List<String> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                result.add(pq.remove().sentence);
            }

            Collections.reverse(result);
            return result;
        }

        private TrieNode search(char c) {
            if(cur == null) {
                return null;
            }
            cur = cur.children.get(c);
            return cur;
        }
    }
}
