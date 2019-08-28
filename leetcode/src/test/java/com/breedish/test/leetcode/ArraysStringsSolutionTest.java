package com.breedish.test.leetcode;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArraysStringsSolutionTest {

    private ArraysStringsSolution solution = new ArraysStringsSolution();

    @Test
    public void testLongestUnrepeatedSubstringLength() {
        assertThat(solution.lengthOfLongestSubstring("abcabcbb"), is(3));
    }

    @Test
    public void testAtoi() {
        assertThat(solution.myAtoi(" "), is(0));
        assertThat(solution.myAtoi("42"), is(42));
        assertThat(solution.myAtoi("   -42"), is(-42));
        assertThat(solution.myAtoi("4193 with words"), is(4193));
        assertThat(solution.myAtoi("-91283472332"), is(-2147483648));
    }

    @Test
    public void testMaxArea() {
        assertThat(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}), is(49));
    }

    @Test
    public void testThreeSum() {
//        assertThat(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}), is(List.of(List.of(-1, 0, 1), List.of(-1, -1, 2))));
        assertThat(solution.threeSum(new int[]{1, 1, -2}), is(List.of(List.of(-2, 1, 1))));
    }

    @Test
    public void testThreeSumClosest() {
        assertThat(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1), is(2));
    }

    @Test
    public void testStrStr() {
//        assertThat(solution.strStr("hello", "ll"), is(2));
//        assertThat(solution.strStrV2("hello", "ll"), is(2));
//        assertThat(solution.strStrV2("a", "a"), is(0));
//        assertThat(solution.strStrV3("hello", "ll"), is(2));
        assertThat(
                solution.strStrV3(
                        "abbbaaaaaaabbababbbbabababbbbbbbaaaaaaabbaaabbaababbbbababababaabbbbbbaaaaababbbbaaabababbbaaaabbbaabbbbbbabababbabaaaaabaabaaababbbaaabaababbaaabaaababbabbbbababaaaaaaababaabaabbaabbbaaabaaaaaa",
                        "aabaaaabababbbabababbbaabaabaaaaabaabbbaabbbbba"
                ),
                is(-1)
        );
    }

    @Test
    public void testRotate() {
        int[][] m = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solution.rotate(m);
        assertThat(m, is(new int[][]{{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}}));

        m = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.rotate(m);
        assertThat(m, is(new int[][]{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}));
    }

    @Test
    public void testCompareVersion() {
        assertThat(solution.compareVersion("0.1", "1.0.1"), is(-1));
        assertThat(solution.compareVersion("7.5.2.4", "7.5.3"), is(-1));
        assertThat(solution.compareVersion("7.55.2.4", "7.5.3"), is(1));
        assertThat(solution.compareVersion("7.55.2.4", "7.55.2.4"), is(0));
    }

    @Test
    public void testProduct() {
        assertThat(solution.productExceptSelf(new int[]{1, 2, 3, 4}), is(new int[]{24, 12, 8, 6}));
    }

    @Test
    public void firstUniqChar() {
        assertThat(solution.firstUniqChar("loveleetcode"), is(2));
        assertThat(solution.firstUniqChar(""), is(-1));
        assertThat(solution.firstUniqChar("ababab"), is(-1));
    }

    @Test
    public void testIsValid() {
        assertThat(solution.isValid("((()))"), is(true));
    }

    @Test
    public void testTrap() {
        assertThat(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), is(6));
    }
}
