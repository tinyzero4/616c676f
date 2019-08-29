package com.breedish.test.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

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

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node node = head;

        while (node != null) {
            Node tmp = new Node(node.val, null, null);
            Node next = node.next;
            tmp.next = node.next;
            node.next = tmp;
            node = next;
        }

        Node res = head.next;

        node = head;
        while (node != null) {
            Node next = node.next;
            if (node.random != null) node.next.random = node.random.next;
            node = next.next;
        }

        node = head;
        while (node != null) {
            Node next = node.next;
            if (next != null) node.next = node.next.next;
            node = next;
        }

        return res;
    }

    public ListNode reverseListV1(ListNode head) {
        ListNode rev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = rev;
            rev = head;
            head = next;
        }

        return rev;
    }

    public ListNode reverseListV2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = reverseListV2(head.next);
        head.next.next = head;
        head.next = null;

        return p;
    }

    public ListNode mergeKListsV1(ListNode[] lists) {
        ListNode resHead = new ListNode(0);
        ListNode res = resHead;

        while (true) {
            int minIdx = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < min) {
                    min = lists[i].val;
                    minIdx = i;
                }
            }

            if (minIdx == -1) break;

            res.next = lists[minIdx];
            lists[minIdx] = lists[minIdx].next;
            res.next.next = null;
            res = res.next;
        }
        return resHead.next;
    }

    public ListNode mergeKListsV2(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));

        for (int i = 0; i < lists.length; i++) if (lists[i] != null) pq.add(lists[i]);
        ListNode resHead = new ListNode(0);
        ListNode res = resHead;

        while (!pq.isEmpty()) {
            ListNode min = pq.poll();
            if (min.next != null) pq.add(min.next);
            min.next = null;
            res.next = min;
            res = res.next;
        }
        return resHead.next;
    }

    public ListNode mergeKListsV3(ListNode[] lists) {
        if (lists.length == 0) return null;

        int listsCount = lists.length;
        while (listsCount != 1) {
            for (int i = 0; i < listsCount / 2; i++) {
                lists[i] = mergeTwoLists(lists[i * 2], lists[i * 2 + 1]);
            }

            if (listsCount % 2 == 1) lists[listsCount/2] = lists[listsCount - 1];
            listsCount = (listsCount + 1) / 2;
        }

        return lists[0];
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

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


}
