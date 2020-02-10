package com.breedish.test.leetcode;

import com.breedish.test.leetcode.TreeSolution.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;

public class TreeSolutionTest {

    private TreeSolution solution = new TreeSolution();

    @Test
    public void testIsValid() {
        Assert.assertThat(solution.isValidBSTV1(new TreeNode(1)), is(true));
        TreeNode n1 = new TreeNode(2);
        n1.left = new TreeNode(1);
        n1.right = new TreeNode(3);
        Assert.assertThat(solution.isValidBSTV1(n1), is(true));
    }

    @Test
    public void testOrderTraversals() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        Stack<Integer> s = new Stack<Integer>();
        s.add(1);
        s.add(2);
        s.add(3);
        for (Integer integer : s) {
            System.out.println(integer);
        }

        var res = solution.zigzagLevelOrder(node);
    }


}
