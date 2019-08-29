package com.breedish.test.leetcode;

import com.breedish.test.leetcode.LinkedListsSolution.ListNode;
import com.breedish.test.leetcode.LinkedListsSolution.Node;
import org.junit.Test;

public class LinkedListSolutionTest {

    private LinkedListsSolution solution = new LinkedListsSolution();

    @Test
    public void testAddSumV1() {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(7);
        l3.next = new ListNode(0);
        l3.next.next = new ListNode(8);

        solution.addTwoNumbers(l1, l2);

        ListNode n = new ListNode(1);
        n.next = new ListNode(8);
        solution.addTwoNumbers(n, new ListNode(0));
    }

    @Test
    public void testAddSumV2() {
        //[9]
        ListNode node = new ListNode(1);
        node.next = new ListNode(9);
        node.next.next = new ListNode(9);
        node.next.next.next = new ListNode(9);
        node.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next.next.next.next = new ListNode(9);
        node.next.next.next.next.next.next.next.next.next = new ListNode(9);
        //[1,9,9,9,9,9,9,9,9,9]
        //[0,0,0,0,0,0,0,0,0,0,1]

        solution.addTwoNumbers(new ListNode(9), node);
    }

    @Test
    public void testMergeSort() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(4);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);

        solution.mergeTwoLists(n1, n2);
    }

    @Test
    public void reverseKGroupTest() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        solution.reverseKGroup(node, 3);
    }

    @Test
    public void testDeepCopyList() {
        Node n1 = new Node(1, null, null);
        Node n2 = new Node(2, null, null);

        n1.next = n2;
        n1.random = n2;
        n2.random = n2;

        solution.copyRandomList(n1);
    }

    @Test
    public void testReverseList() {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        solution.reverseListV2(node);
    }

    @Test
    public void testMergeKLists() {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(20);
        node1.next.next = new ListNode(30);
        node1.next.next.next = new ListNode(40);
        node1.next.next.next.next = new ListNode(50);

        ListNode node2 = new ListNode(11);
        node2.next = new ListNode(12);
        node2.next.next = new ListNode(13);
        node2.next.next.next = new ListNode(14);
        node2.next.next.next.next = new ListNode(55);

        ListNode node3 = new ListNode(21);
        node3.next = new ListNode(22);
        node3.next.next = new ListNode(23);
        node3.next.next.next = new ListNode(24);
        node3.next.next.next.next = new ListNode(25);

        solution.mergeKListsV3(new ListNode[]{node1, node2, node3});

    }
}
