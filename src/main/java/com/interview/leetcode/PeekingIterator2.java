package com.interview.leetcode;

import java.util.*;

public class PeekingIterator2 implements Iterator<Integer> {

    Integer peek;  // use to visit and store the next element
    Iterator<Integer> it;

    public PeekingIterator2(Iterator<Integer> iterator) {
        it = iterator;
        peek = it.hasNext() ? it.next() : null;
    }

    public Integer peek() {
        return peek;
    }

    @Override
    public Integer next() {
        if (peek == null) {
            throw new NoSuchElementException();
        }
        Integer ret = peek;
        peek = it.hasNext() ? it.next() : null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return peek != null;
    }

}
