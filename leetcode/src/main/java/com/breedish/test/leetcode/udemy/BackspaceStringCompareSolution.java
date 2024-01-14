package com.breedish.test.leetcode.udemy;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompareSolution {

    public boolean backspaceCompare(String s, String t) {
        var sp = s.length() - 1;
        var tp = t.length() - 1;

        while (sp >= 0 || tp >= 0) {
            if (sp >= 0 && s.charAt(sp) == '#') {
                var skip = 2;
                while (skip > 0) {
                    sp--;
                    if (sp >= 0 && s.charAt(sp) == '#') skip += 2;
                    skip--;
                }
            }

            if (tp >= 0 && t.charAt(tp) == '#') {
                var skip = 2;
                while (skip > 0) {
                    tp--;
                    if (tp >= 0 && t.charAt(tp) == '#') skip += 2;
                    skip--;
                }
            }

            if (sp < 0 && tp < 0) return true;
            if (sp >= 0 && tp < 0) return false;
            if (sp < 0) return false;
            if (s.charAt(sp) != t.charAt(tp)) return false;
            sp--;
            tp--;
        }
        return true;
    }

    public static void main(String[] args) {
        var solution = new BackspaceStringCompareSolution();
        System.out.println(solution.backspaceCompare("va#z", "aa##zb#"));
        System.out.println(solution.backspaceCompare("a#z", "aa##zb#"));
        System.out.println(solution.backspaceCompare("a#a", "a"));
        System.out.println(solution.backspaceCompare("ab#c#d#e#", "abcb###"));
        System.out.println(solution.backspaceCompare("bxj##tw", "bxo#j##tw"));
    }

}
