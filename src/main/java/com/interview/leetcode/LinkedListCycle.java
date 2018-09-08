package com.interview.leetcode;

import java.util.HashMap;

public class LinkedListCycle {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Time complexity: O(n) - n is the total number of nodes, Space complexity: O(n)
    public boolean hasCycle(ListNode head) {
        HashMap<ListNode, Integer> map = new HashMap<>();
        ListNode temp = head;
        while(temp != null) {
            if(map.containsKey(temp)) {
                return true;
            } else {
                map.put(temp, 0);
            }
            temp = temp.next;
        }
        return false;
    }

    // Time complexity: O(n + k) ~ O(n) - n is the total number of nodes and k is cyclic length k, Space complexity: O(1)
    public boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if(fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String [] args) {
        LinkedListCycle linkedListCycle = new LinkedListCycle();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l3;

        System.out.println(linkedListCycle.hasCycle(l1));

        l5.next = null;

        System.out.println(linkedListCycle.hasCycle(l1));
    }
}
