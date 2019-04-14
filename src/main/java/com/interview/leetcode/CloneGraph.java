package com.interview.leetcode;

import java.util.*;

public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /* Breadth-First Search
       Time complexity: O(V + E), Space complexity: O(V + E) (with result) or O(V) (without considering result) */
    public static Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        //Can use Map<Node, Node>: origin node -> cloned node mapping
        Map<Integer, Node> map = new HashMap<>();

        Node clonedNode = new Node(node.val, new ArrayList<>());
        map.put(clonedNode.val, clonedNode);

        while (!queue.isEmpty()) {
            Node origNode = queue.remove();

            for(Node origNeighbour : origNode.neighbors) {
                if(!map.containsKey(origNeighbour.val)) {
                    Node clonedNeighbour = new Node(origNeighbour.val, new ArrayList<>());
                    map.put(clonedNeighbour.val, clonedNeighbour);
                    queue.add(origNeighbour);
                }
                map.get(origNode.val).neighbors.add(map.get(origNeighbour.val));
            }
        }
        return clonedNode;
    }

    /* Depth-First Search (can contain nodes with duplicate labels)
       Time complexity: O(V + E), Space complexity: O(V + E) (with result) or O(V) (without considering result) */
    public static Node cloneGraph2(Node node) {
        if(node == null) {
            return null;
        }
        return clone(node, new HashMap<>());
    }

    private static Node clone(Node node, Map<Node, Node> map) {
        Node clonedNode = new Node(node.val, new ArrayList<>());
        map.put(node, clonedNode);

        for(Node origNeighbour : node.neighbors) {
            if(map.containsKey(origNeighbour)) {
                clonedNode.neighbors.add(map.get(origNeighbour));
            } else {
                clonedNode.neighbors.add(clone(origNeighbour, map));
            }
        }
        return clonedNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        System.out.println(cloneGraph(node1));
    }
}
