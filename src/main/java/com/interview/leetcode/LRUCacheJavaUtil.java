package com.interview.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class LRUCacheJavaUtil extends LinkedHashMap<Integer, Integer> {

    Predicate<Integer> removeEldestEntry;

    public LRUCacheJavaUtil(int capacity) {
        super(capacity, 0.75f, true);
        this.removeEldestEntry = (size) -> size > capacity;
    }

    public int get(int key) {
        return getOrDefault(key, -1);
    }
    public void set(int key, int value) {
        put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return removeEldestEntry.test(size());
    }
}
