package com.interview.leetcode;

public class ReverseLinkedList {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    //Time complexity: O(n), Space complexity: O(1)
    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = previous;
            previous = current;
            current = nextTemp;
        }
        return previous;
    }

    //Time complexity: O(n), Space complexity: O(n)
    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(5);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode result = reverseList2(l1);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
