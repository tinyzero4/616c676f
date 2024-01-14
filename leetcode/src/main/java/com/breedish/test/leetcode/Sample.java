package com.breedish.test.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Sample {

    public static void main(String[] args) {
        Deque<Integer> deq = new ArrayDeque<>();

        deq.add(1);
        printDeq(deq);
        deq.add(2);
        printDeq(deq);
        deq.add(3);
        printDeq(deq);

    }

    private static void printDeq(Deque<Integer> deq) {
        System.out.println(deq);
    }
}
