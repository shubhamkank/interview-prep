package com.interview.leetcode;

import java.util.HashMap;

public class LRUCache {

    private int capacity;
    private HashMap<Integer, DNode> map;
    private int size;
    private DNode head;
    private DNode tail;

    static class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;

        public DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        size = 0;
        head = new DNode(0, 0);
        tail = new DNode(0, 0);
        head.next = tail;
        head.prev = null;
        tail.next = null;
        tail.prev = head;
    }

    private void deleteNode(DNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(DNode node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        if(map.containsKey(key)) {
            DNode node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            return result;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            DNode node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            DNode node = new DNode(key, value);
            map.put(key, node);
            if(size < capacity) {
                size++;
                addToHead(node);
            } else {
                map.remove(tail.prev.key);
                deleteNode(tail.prev);
                addToHead(node);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
