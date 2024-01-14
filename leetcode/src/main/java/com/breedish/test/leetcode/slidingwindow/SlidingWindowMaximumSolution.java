package com.breedish.test.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import static java.lang.String.format;

public class SlidingWindowMaximumSolution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int l = 0, r = 0;

        var pq = new Stack<Integer>();
        var max = Integer.MIN_VALUE;
        pq.add(max);

        while (r < k) {
            max += nums[r++];
        }


        var res = new ArrayList<Integer>();
        res.add(max);

//        while (r < nums.length) {
//            sum = sum - nums[l] + nums[r];
//            max = Math.max(max, sum);
//            res.add(max);
//            r++;
//            l++;
//        }
//        [3,3,3,5,14,16]
//        [3,3,5,5,6,7]
        return res.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new SlidingWindowMaximumSolution().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        var s = String.format("[video];[1:v][video]scale2ref=w=main_w*%2$3.2f/sar:h=main_h*%2$3.2f[overlay][video];[video][overlay]overlay=0:0", 123, 456.00);
        System.out.println(s);
        var s1 = format("[0:v]scale=w=-2:h=%1$d,format=yuv420p", 12222);
        System.out.println(s1);
    }
}
