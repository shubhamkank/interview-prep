package com.interview.leetcode;

public class BTreeLongestConsecutiveSeq {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int max;

    //Top-down approach
    //Time complexity: O(n), Space complexity: O(n)
    public int longestConsecutive(TreeNode root) {
        max = 0;
        dfs2(root, 0, root != null ? root.val : 0);
        return max;
    }

    //Bottom-up approach
    //Time complexity: O(n), Space complexity: O(n) - skewed binary tree - all nodes on one side
    public int longestConsecutive2(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int l = dfs(root.left);
        int r = dfs(root.right);

        int leftPath = 1, rightPath = 1;
        if(root.left != null && root.left.val == root.val + 1) {
            leftPath += l;
        }
        if(root.right != null && root.right.val == root.val + 1) {
            rightPath += r;
        }

        int path = Math.max(leftPath, rightPath);
        max = Math.max(max, path);
        return path;
    }

    private void dfs2(TreeNode root, int curPath, int target) {
        if(root == null) {
            return;
        }

        if(root.val == target) {
            curPath++;
        } else {
            curPath = 1;
        }

        max = Math.max(max, curPath);
        dfs2(root.left, curPath, root.val + 1);
        dfs2(root.right, curPath, root.val + 1);
    }

    private int dfs3(TreeNode root, int curPath, int target) {
        if(root == null) {
            return curPath;
        }

        if(root.val == target) {
            curPath++;
        } else {
            curPath = 1;
        }

        //Similar to top down approach
        int left = dfs3(root.left, curPath, root.val + 1);
        int right = dfs3(root.right, curPath, root.val + 1);
        return Math.max(Math.max(left, right), curPath);
    }
}
