package com.interview.leetcode;

public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    //Runtime: O(max(m,n)) Space: O(max(m,n))
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
        ListNode head = null;
        ListNode tail = null;

        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + (carry ? 1 : 0);
            int digit = sum % 10;
            carry = sum >= 10;

            if(head == null) {
                head = new ListNode(digit);
                tail = head;
            } else {
                tail.next = new ListNode(digit);
                tail = tail.next;
            }

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry) {
            tail.next = new ListNode(1);
        }

        return head;
    }

    public static void main(String [] args) {

        ListNode l1 = new ListNode(0);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);
        while(result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
