package com.interview.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class CountCompleteTreeNodes {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //Time complexity: O(n)
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;

        while(!queue.isEmpty()) {
            TreeNode node = queue.remove();
            count++;
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

    //Time complexity: O(n), Space complexity: O(logn)
    public int countNodes2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes2(root.left) + countNodes2(root.right);
    }

    //Time complexity: O(logn * logn), Space complexity: O(logn)
    public int countNodes3(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int leftHeight = leftHeight(root);
        int rightHeight = rightHeight(root);

        if(leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }
        return 1 + countNodes3(root.left) + countNodes3(root.right);
    }

    public static int leftHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            root = root.left;
            height++;
        }
        return height;
    }

    public static int rightHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            root = root.right;
            height++;
        }
        return height;
    }

    // Time complexity: O(logn * logn), Space complexity: O(logn)
    // https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
    public int countNodes4(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int height = height(root);
        if(height < 0) {
            return 0;
        }

        int rightHeight = height(root.right);
        if(rightHeight == height - 1) {
            return (1 << height) + countNodes4(root.right);
        } else {
            return (1 << (height - 1)) + countNodes4(root.left);
        }
    }

    // https://leetcode.com/problems/count-complete-tree-nodes/solution/
    // Time complexity: O(logn * logn), Space complexity: O(1)
    public int countNodes5(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int height = height(root);

        // Last level nodes are enumerated from 0 to 2^h - 1 (left -> right).
        // Perform binary search to check how many nodes exist.
        int left = 0, right = (1 << height) - 1;
        int pivot;

        while(left <= right) {
            pivot = left + (right - left) / 2;
            if(exists(pivot, height, root)) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        // The tree contains 2^d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (1 << height) - 1 + left;
    }

    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Return True if last level node idx exists.
    // Binary search with O(h) complexity.
    private static boolean exists(int idx, int height, TreeNode node) {
        int left = 0, right = (1 << height) - 1;
        int pivot;

        for(int i = 0; i < height; i++) {
            pivot = left + (right - left) / 2;
            if(idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public static void main(String[] args) {

    }
}
