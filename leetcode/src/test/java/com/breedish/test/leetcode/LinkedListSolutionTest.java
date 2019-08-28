package com.breedish.test.leetcode;

import com.breedish.test.leetcode.LinkedListsSolution.ListNode;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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

        solution.addTwoNumbers(new ListNode(9),  node);
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
}
