package com.breedish.test.leetcode;

import com.breedish.test.leetcode.TreeSolution.TreeNode;
import org.junit.Test;

public class TreeSolutionTest {

    private TreeSolution solution = new TreeSolution();

    @Test
    public void testIsValid() {
        solution.isValidBST(new TreeNode(1));
    }
}
