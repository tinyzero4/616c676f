package com.breedish.test.leetcode.slidingwindow;


import com.breedish.test.leetcode.google.Solution;

import java.util.HashMap;

class MinWindowSubstringSolution {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) return "";

        var dictionary = new HashMap<Character, Integer>();
        var window = new HashMap<Character, Integer>();
        var matched = 0;

        for (int i = 0; i < t.length(); i++) {
            var c = t.charAt(i);
            dictionary.put(c, dictionary.getOrDefault(c, 0) + 1);
        }

        int[] stats = new int[]{-1, 0, 0};
        int l = 0, r = 0;

        while (r < s.length()) {
            var c = s.charAt(r);
            if (dictionary.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
            }
            if (window.get(c).equals(dictionary.get(c))) matched++;

            while (l <= r && matched == dictionary.size()) {
                char lc = s.charAt(l);

                if (stats[0] == -1 || (r - l) < stats[0]) {
                    stats[0] = r - l;
                    stats[1] = l;
                    stats[2] = r;
                }

                if (dictionary.containsKey(s.charAt(l))) {
                    window.put(lc, window.get(lc) - 1);
                    if (window.get(lc) < dictionary.get(lc)) matched--;
                }

                l++;
            }
            r++;
        }
        return stats[0] == -1 ? "" : s.substring(stats[0], stats[1]);


    }

    public static void main(String[] args) {
        var res = new Solution().minWindow(
                "ADOBECODEBANC",
                "ABC"
        );
        System.out.println(res);
    }
}


