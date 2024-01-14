package com.breedish.test.leetcode.slidingwindow;

import java.util.Stack;

public class TrappingRainWaterSolution {

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0 || n == 1) return 0;

        var bounds = new Stack<Integer>();
        bounds.push(0);

        var total = 0;
        for (int i = 1; i < n; i++) {
            while (!bounds.isEmpty() && height[i] > height[bounds.peek()]) {
                var top = height[bounds.pop()];
                if (bounds.isEmpty()) break;
                var h = Math.min(height[i], height[bounds.peek()]) - top;
                var w = i - bounds.peek() - 1;
                total += h * w;
            }
            bounds.add(i);
        }
        return total;
    }

    public static void main(String[] args) {
//        System.out.println(new TrappingRainWaterSolution().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(new TrappingRainWaterSolution().trap(new int[]{4, 2, 0, 3, 2, 5}));
    }
}

