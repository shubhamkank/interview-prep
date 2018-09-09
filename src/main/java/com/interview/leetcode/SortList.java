package com.interview.leetcode;

public class SortList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Top to bottom approach
    // Time complexity: O(nlogn) - n is the total number of nodes, Space complexity: O(logn) - logn recursive calls
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // Bottom-up approach
    // Time complexity: O(nlogn) - n is the total number of nodes, Space complexity: O(1)
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int n = 0;

        while (head != null) {
            head = head.next;
            n++;
        }

        //Merge lists of size 1-1 each to form sorted list of size 2 then 2-2 then 4-4 and so on
        // logn times
        for(int step = 1; step < n; step++) {
            ListNode tail = dummy;
            ListNode current = dummy.next;
            // Total - O(n)
            while (current != null) {
                ListNode left = current;
                ListNode right = split(left, step);
                current = split(right, step);
                tail = merge(left, right, tail);
            }
        }
        return dummy.next;
    }

    // Split list with first half containing n elements and return the head of the second half
    private ListNode split(ListNode head, int n) {
        if(head == null) {
            return null;
        }

        for(int i = 1; head.next != null && i < n; i++) {
            head = head.next;
        }

        ListNode right = head.next;
        head.next = null;
        return right;
    }

    //Merge two lists with head left and right, append to the previous node and return the tail of the merged list
    private ListNode merge(ListNode left, ListNode right, ListNode previous) {
        ListNode current = previous;

        while (left != null && right != null) {
            if(left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if(left != null) {
            current.next = left;
        }
        else if(right != null) {
            current.next = right;
        }

        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public static void main(String [] args) {
        SortList sortList = new SortList();

        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(10);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(20);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode result = sortList.sortList2(l1);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
