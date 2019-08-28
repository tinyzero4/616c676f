package com.breedish.test.leetcode;

public class LinkedListsSolution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        int add = 0;
        while (l1 != null || l2 != null) {
            int v1 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }

            int v2 = 0;
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }

            int s = v1 + v2 + add;
            add = s / 10;
            node.val = s % 10;
            if (l1 != null || l2 != null) {
                node.next = new ListNode(0);
                node = node.next;
            }
        }
        if (add > 0) node.next = new ListNode(add);
        return head;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode node = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        if (l1 != null) node.next = l1;
        else node.next = l2;

        return head.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null || head.next == null) return head;

        ListNode res = new ListNode(0);
        ListNode resTail = res;
        ListNode from = head;

        int i = 0;
        while (head != null) {
            head = head.next;
            i++;

            if (i % k == 0) {
                resTail.next = rev(from, head);
                resTail = from;
                from = head;
            }

        }

        if (i % k != 0) {
            resTail.next = from;
        }
        return res.next;
    }

    private ListNode rev(ListNode from, ListNode to) {
        ListNode rev = null;

        while (from != to) {
            ListNode cur = from;
            ListNode next = from.next;
            cur.next = rev;
            from = next;
            rev = cur;
        }
        return rev;
    }
}
