package com.interview.leetcode;

public class RangeSumQueryMutable {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int [] {1, 3, 5});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));


        NumArray2 numArray2 = new NumArray2(new int [] {1, 3, 5});
        System.out.println(numArray2.sumRange(0, 2));
        numArray2.update(1, 2);
        System.out.println(numArray2.sumRange(0, 2));

        NumArray3 numArray3 = new NumArray3(new int [] {1, 3, 5});
        System.out.println(numArray3.sumRange(0, 2));
        numArray3.update(1, 2);
        System.out.println(numArray3.sumRange(0, 2));
    }

    //BIT - Binary Indexed Tree
    //Space complexity: O(n)
    static class NumArray {

        int[] tree;
        int[] arr;
        int n;

        //Time complexity: O(n * logn)
        public NumArray(int[] nums) {
            if(nums.length == 0) {
                return;
            }

            n = nums.length;
            tree = new int[n + 1];
            arr = new int[n];

            for (int i = 0; i < n; i++) {
                update(i, nums[i]);
            }
        }

        //Time complexity: O(logn)
        public void update(int i, int val) {
            int diff = val - arr[i];
            arr[i] = val;
            i++;
            while(i <= n) {
                tree[i] += diff;
                i += i & (-i);
            }
        }

        //Time complexity: O(logn)
        public int sumRange(int i, int j) {
            return sum(j + 1) - sum(i);
        }

        //Time complexity: O(logn)
        public int sum(int i) {
            int s = 0;
            while(i > 0) {
                s += tree[i];
                i -= i & (-i);
            }
            return s;
        }
    }

    //Segment Tree
    static class NumArray2 {

        TreeNode root;

        public NumArray2(int[] nums) {
            if(nums.length == 0) {
                return;
            }

            root = constructSegmentTree(nums, 0, nums.length - 1);
        }

        //Time complexity: O(n)
        private TreeNode constructSegmentTree(int[] nums, int start, int end) {
            if(start > end) {
                return null;
            }

            TreeNode node = new TreeNode(start, end);

            if(start == end) {
                node.sum = nums[start];
                return node;
            }

            int mid = (start + end) / 2;

            node.left = constructSegmentTree(nums, start, mid);
            node.right = constructSegmentTree(nums, mid + 1, end);

            node.sum += node.left != null ? node.left.sum : 0;
            node.sum += node.right != null ? node.right.sum : 0;

            return node;
        }

        //Time complexity: O(logn)
        public void update(int i, int val) {
            update(root, i, val);
        }

        private void update(TreeNode root, int i, int val) {
            if(root.start == root.end && root.start == i) {
                root.sum = val;
                return;
            }

            int mid = (root.start + root.end) / 2;

            if(i <= mid) {
                update(root.left, i, val);
            } else {
                update(root.right, i, val);
            }

            root.sum = root.left.sum + root.right.sum;
        }

        //Time complexity: O(logn)
        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }

        private int sumRange(TreeNode root, int i, int j) {
            if(root.start == i && root.end == j) {
                return root.sum;
            }

            int mid = (root.start + root.end) / 2;

            if(j <= mid) {
                return sumRange(root.left, i, j);
            } else if(i >= mid + 1) {
                return sumRange(root.right, i, j);
            } else {
                return sumRange(root.left, i, mid) + sumRange(root.right, mid + 1, j);
            }
        }
    }

    static class TreeNode {
        TreeNode left, right;
        int start, end;
        int sum;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.sum = 0;
            this.left = null;
            this.right = null;
        }
    }

    static class NumArray3 {

        int[] tree;
        int n;

        public NumArray3(int[] nums) {
            if(nums.length == 0) {
                return;
            }

            n = nums.length;
            int height = (int) Math.ceil(Math.log(n) / Math.log(2));
            tree = new int[(int)(Math.pow(2, height + 1) - 1)]; //2n - 1 where n is size of array and power of 2
            contructSegmentTree(nums, 0, n - 1, 0);
        }

        private void contructSegmentTree(int[] nums, int low, int high, int pos) {
            if(low == high) {
                tree[pos] = nums[low];
                return;
            }

            int mid = (low + high) / 2;
            contructSegmentTree(nums, low, mid, 2 * pos + 1);
            contructSegmentTree(nums, mid + 1, high, 2 * pos + 2);
            tree[pos] = tree[2 * pos + 1] + tree[2 * pos + 2];
        }

        public void update(int i, int val) {
            update(0, n - 1, i, val, 0);
        }

        private void update(int low, int high, int index, int val, int pos) {
            if(low == high && low == index) {
                tree[pos] = val;
                return;
            }

            int mid = (low + high) / 2;
            if(index <= mid) {
                update(low, mid, index, val, 2 * pos + 1);
            } else {
                update(mid + 1, high, index, val, 2 * pos + 2);
            }
            tree[pos] = tree[2 * pos + 1] + tree[2 * pos + 2];
        }

        public int sumRange(int i, int j) {
            return sumRange(0, n - 1, i, j, 0);
        }

        private int sumRange(int low, int high, int i, int j, int pos) {
            if(i <= low && j >= high) {
                return tree[pos];
            }

            if(i > high || j < low) {
                return 0;
            }

            int mid = (low + high) / 2;
            return sumRange(low, mid, i, j, 2 * pos + 1) + sumRange(mid + 1, high, i, j, 2 * pos + 2);
        }
    }

    //Segment Tree Implementation 4: https://leetcode.com/problems/range-sum-query-mutable/discuss/75763/7ms-Java-solution-using-bottom-up-segment-tree
}
