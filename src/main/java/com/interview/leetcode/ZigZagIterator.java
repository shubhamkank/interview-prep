package com.interview.leetcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ZigZagIterator {

    LinkedList<Iterator> list;

    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<>();
        if (!v1.isEmpty()) {
            list.add(v1.iterator());
        }
        if (!v2.isEmpty()) {
            list.add(v2.iterator());
        }
    }

    public int next() {
        Iterator iterator = list.remove();
        int result = (Integer) iterator.next();
        if (iterator.hasNext()) {
            list.add(iterator);
        }
        return result;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }

    static class ZigzagIterator {

        private int size;
        private int cursor1, cursor2;
        private List<Integer> v1;
        private List<Integer> v2;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            size = v1.size() + v2.size();
            cursor1 = 0;
            cursor2 = 0;
            this.v1 = v1;
            this.v2 = v2;
        }

        public int next() {
            int val;

            if ((cursor1 + cursor2) % 2 == 0) {

                if (cursor1 < v1.size()) {
                    val = v1.get(cursor1);
                    cursor1++;
                } else {
                    val = v2.get(cursor2);
                    cursor2++;
                }
            } else {
                if (cursor2 < v2.size()) {
                    val = v2.get(cursor2);
                    cursor2++;
                } else {
                    val = v1.get(cursor1);
                    cursor1++;
                }
            }
            return val;
        }

        public boolean hasNext() {
            return cursor1 + cursor2 < size;
        }
    }
}
