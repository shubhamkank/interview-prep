package com.interview.leetcode;

public class LongestUnivaluePath {

    int result;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    //Time complexity: O(N), Space complexity: O(H) - height of tree
    // Note here path cannot have repeating nodes: example - [4, 4, null, 4, 4]
    public int longestUnivaluePath(TreeNode root) {
        result = 0;
        helper(root);
        return result;
    }

    public int helper(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftLen = helper(node.left);
        int rightLen = helper(node.right);

        int leftPath = 0, rightPath = 0;
        if(node.left != null && node.val == node.left.val) {
            leftPath = leftLen + 1;
        }
        if(node.right != null && node.val == node.right.val) {
            rightPath = rightLen + 1;
        }

        result = Math.max(result, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}
