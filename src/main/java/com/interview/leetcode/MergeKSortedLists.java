package com.interview.leetcode;

import java.util.*;

public class MergeKSortedLists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    //Brute Force - Extra Space
    // Time complexity: O(nlogn) - n is the total number of nodes, Space complexity: O(n)
    public ListNode mergeKLists(ListNode[] lists) {
        ArrayList<Integer> nums = new ArrayList<>();
        for(ListNode node : lists) {
            while (node != null) {
                nums.add(node.val);
                node = node.next;
            }
        }

        Collections.sort(nums);
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for(int num : nums) {
            temp.next = new ListNode(num);
            temp = temp.next;
        }
        return head.next;
    }

    // Compare one by one
    // Time complexity: O(kn) - n is the total number of nodes, Space complexity: O(n)
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode temp = head;

        while(true) {
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            boolean terminate = false;
            for(int i = 0; i < lists.length; i++) {
                if(lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIdx = i;
                    terminate = true;
                }
            }

            if(!terminate) {
                break;
            }
            temp.next = new ListNode(min);
            temp = temp.next;
            lists[minIdx] = lists[minIdx].next;
        }
        return head.next;
    }

    // Using Priority Queue
    // Time complexity: O(nlogk) - n is the total number of nodes, Space complexity: O(n) - new list, O(k) - queue
    public ListNode mergeKLists3(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));
        for(ListNode node : lists) {
            if(node != null) {
                queue.add(node);
            }
        }

        ListNode head = new ListNode(0);
        ListNode temp = head;
        while (!queue.isEmpty()) {
            ListNode node = queue.remove();
            temp.next = new ListNode(node.val);
            temp = temp.next;
            node = node.next;
            if(node != null) {
                queue.add(node);
            }
        }
        return head.next;
    }

    //Merge one by one
    // Time complexity: O(kn) - n is the total number of nodes, Space complexity: O(1)
    public ListNode mergeKLists4(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = lists[0];
        for(int i = 1; i < lists.length; i++) {
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(Integer.MIN_VALUE);
        ListNode temp = l;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if(l1 != null) {
            temp.next = l1;
        }
        if(l2 != null) {
            temp.next = l2;
        }

        return l.next;
    }

    //Divide and conquer
    // Time complexity: O(nlogk) - n is the total number of nodes, Space complexity: O(1)
    public ListNode mergeKLists5(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        return mergeKListsRecursive(lists, 0, lists.length-1);
    }

    private ListNode mergeKListsRecursive(ListNode [] lists, int start, int end) {
        if(start == end) {
            return lists[start];
        }

        int mid = (start + end)/2;
        ListNode l1 = mergeKListsRecursive(lists, start, mid);
        ListNode l2 = mergeKListsRecursive(lists, mid+1, end);
        return mergeTwoListsRecursive(l1, l2);
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        MergeKSortedLists mergeKSortedLists = new MergeKSortedLists();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(0);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(10);

        ListNode result = mergeKSortedLists.mergeKLists5(new ListNode[] {l1, l2, l3});
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
