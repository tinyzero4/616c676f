package com.breedish.test.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeSolution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBSTV1(TreeNode root) {
        return isValidNode(root, null, null);
    }

    private boolean isValidNode(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;

        return isValidNode(node.left, min, node.val) && isValidNode(node.right, node.val, max);
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        List<Integer> level = new ArrayList<>();
        List<TreeNode> nextLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            level.add(node.val);

            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);

            if (queue.isEmpty()) {
                levels.add(level);
                queue.addAll(nextLevel);

                level = new ArrayList<>();
                nextLevel.clear();
            }
        }

        return levels;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;
        traverse(root, levels, 0);
        return levels;
    }

    private void traverse(TreeNode node, List<List<Integer>> levels, int lvl) {
        if (node == null) return;
        if (levels.size() <= lvl) levels.add(new LinkedList<>());

        List<Integer> level = levels.get(lvl);
        if (lvl % 2 == 0) {
            level.add(node.val);
        } else {
            level.add(0, node.val);
        }

        traverse(node.left, levels, lvl + 1);
        traverse(node.right, levels, lvl + 1);
    }

}
