package com.breedish.test.leetcode.google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Solution {

    public int numUniqueEmails(String[] emails) {
        Set<String> uniq = new HashSet<>();
        for (String candidate : emails) {
            String[] parts = candidate.split("@");
            String local = parts[0];
            int aliasIdx = local.indexOf("+");
            if (aliasIdx != -1) local = local.substring(0, aliasIdx);
            local = local.replaceAll("\\.", "");
            uniq.add(local + "@" + parts[1]);
        }
        return uniq.size();
    }

    public String licenseKeyFormatting(String S, int K) {
        int chars = 0;
        for (int i = 0; i < S.length(); i++) if (S.charAt(i) != '-') chars++;

        StringBuilder res = new StringBuilder();

        int p = 0;
        int groupLen = chars % K;
        if (groupLen == 0) groupLen = K;

        while (p < S.length()) {
            char c = S.charAt(p);

            if (c != '-') {
                if (Character.isLowerCase(c)) c = Character.toUpperCase(c);
                res.append(c);
                groupLen--;

                if (groupLen <= 0) {
                    res.append("-");
                    groupLen = K;
                }
            }
            p++;
        }

        if (res.length() > 0 && res.charAt(res.length() - 1) == '-') res.setLength(res.length() - 1);
        return res.toString();
    }

    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num2.charAt(i) - '0') * (num1.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul +  pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int p : pos) {
            if (res.length() != 0 || p != 0) res.append(p);
        }
        return res.length() == 0 ? "0" : res.toString();
    }

    public long strToLong(String str) {
        if (str == null || str.isEmpty()) return 0;

        int idx = 0;

        while (str.charAt(idx) == ' ') idx++;

        int sign = +1;
        char startChar = str.charAt(idx);
        if (startChar == '-') sign = -1;
        if (startChar == '-' || startChar == '+') idx++;

        long result = 0;
        long maxValue = Long.MAX_VALUE / 10;
        long maxValueRemainder = Long.MAX_VALUE % 10;
        long maxNegValueRemainder = maxValueRemainder + 1;

        for (int i = idx; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                int digit = c - '0';
                if (sign == 1 && (result > maxValue || (result == maxValue && maxValueRemainder < digit))) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && (result > maxValue || (result == maxValue && maxNegValueRemainder < digit))) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + digit;
            } else break;
        }

        return sign * result;
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 0) return true;
        Boolean[] memo = new Boolean[nums.length];
        memo[nums.length - 1] = TRUE;
        return hasPath(0, nums, memo);
    }

    private boolean hasPath(int position, int[] arr, Boolean[] memo) {
        if (memo[position] != null) return memo[position];

        int jump = Math.min(arr.length, position + arr[position]);
        for (int i = position + 1; i <= jump; i++) {
            if (hasPath(i, arr, memo)) {
                memo[position] = TRUE;
                return true;
            }
        }

        memo[position] = FALSE;
        return false;
    }

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        Map<Character, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            dictionary.put(c, dictionary.getOrDefault(c, 0) + 1);
        }

        int[] res = {-1, 0, 0};
        int matched = 0;
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (dictionary.containsKey(c)) window.put(c, window.getOrDefault(c, 0) + 1);
            if (window.containsKey(c) && window.get(c).equals(dictionary.get(c))) matched++;

            while (l <= r && matched == dictionary.size()) {
                char lc = s.charAt(l);
                if (res[0] == -1 ||  (r -l + 1) < res[0]) {
                    res[0] = r - l + 1;
                    res[1] = l;
                    res[2] = r;
                }

                if (dictionary.containsKey(lc)) {
                    window.put(lc, window.get(lc) - 1);
                    if (window.get(lc) < dictionary.get(lc)) matched--;
                }

                l++;
            }
            r++;
        }
        return res[0] == -1 ? "" : s.substring(res[1], res[2] + 1);
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC"));//BANC
    }

}
