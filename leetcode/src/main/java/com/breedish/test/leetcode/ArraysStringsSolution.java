package com.breedish.test.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static java.lang.Character.isSpaceChar;

public class ArraysStringsSolution {

    public int[] twoSum(int[] nums, int target) {
        int[] numsInOrder = Arrays.copyOf(nums, nums.length);
        int[] mapping = new int[nums.length];

        Arrays.sort(numsInOrder);

        for (int i = 0; i < nums.length; i++) {
            mapping[Arrays.binarySearch(numsInOrder, nums[i])] = i;
        }

        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int idx = Arrays.binarySearch(numsInOrder, target - nums[i]);
            if (idx >= 0 && mapping[idx] != i) {
                res[0] = mapping[idx];
                res[1] = i;
            }
        }
        return res;
    }

    public int[] twoSum_v2(int[] nums, int target) {
        Map<Integer, Integer> mapping = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (mapping.containsKey(complement) && mapping.get(complement) != i) {
                return new int[]{i, mapping.get(complement)};
            } else {
                mapping.put(nums[i], i);
            }
        }

        throw new IllegalArgumentException();
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> mapping = new HashMap<>(128);
        int max = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (mapping.containsKey(c)) i = Math.max(i, mapping.get(c) + 1);
            mapping.put(c, j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public int myAtoi(String str) {
        int res = 0;

        if (str == null || str.isEmpty()) return res;

        int idx = 0;
        while (idx < str.length() && isSpaceChar(str.charAt(idx))) idx++;

        if (idx == str.length()) return res;

        int sign = 1;
        char signChar = str.charAt(idx);
        if (signChar == '-' || signChar == '+') {
            if (signChar == '-') sign = -1;
            idx++;
        }

        if (idx == str.length()) return res;
        int maxAllowedRes = Integer.MAX_VALUE / 10;
        int maxAllowedRemain = 7;
        int maxAllowedRemainNegative = maxAllowedRemain + 1;


        for (int i = idx; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) break;
            int digit = c - '0';

            if (sign > 0 && ((res > maxAllowedRes) || (res == maxAllowedRes && digit > maxAllowedRemain)))
                return Integer.MAX_VALUE;
            if (sign < 0 && ((res > maxAllowedRes) || (res == maxAllowedRes && digit > maxAllowedRemainNegative)))
                return Integer.MIN_VALUE;

            res = res * 10 + digit;
        }

        return sign * res;
    }

    public int maxArea(int[] height) {
        int maxarea = 0;
        if (height.length <= 1) return maxarea;

        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }

    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i == 0 || nums[i] != nums[i - 1]) {
                int sum = -nums[i];
                int lo = i + 1;
                int hi = nums.length - 1;

                while (lo < hi) {
                    int _sum = nums[lo] + nums[hi];
                    if (sum > 0 && nums[lo] > sum) break;

                    if (_sum == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (_sum < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public int threeSumClosest(int[] nums, int target) {
        int closest = target < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == target) return target;
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
                if (sum > target) hi--;
                else lo++;
            }
        }

        return closest;
    }

    /**
     * Brute force
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int i = 0;
        int j = 0;
        int n = haystack.length();
        int m = needle.length();
        for (; i < n && j < m; i++) {
            if (haystack.charAt(i) == needle.charAt(j)) j++;
            else {
                i -= j;
                j = 0;
            }
        }

        if (j == m) return i - m;
        else return -1;
    }

    /**
     * Moore
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrV2(String haystack, String needle) {
        int res = -1;

        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return res;

        int[] backup = new int[256];
        Arrays.fill(backup, -1);
        for (int i = 0; i < needle.length(); i++) backup[needle.charAt(i)] = i;

        int skip;
        for (int i = 0; i <= haystack.length() - needle.length(); i += skip) {
            skip = 0;
            for (int j = needle.length() - 1; j >= 0; j--) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    skip = j - backup[haystack.charAt(i + j)];
                    if (skip < 1) skip = 1;
                }
            }
            if (skip == 0) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * Rabin Karp
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStrV3(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        if (haystack.isEmpty()) return -1;
        if (needle.length() > haystack.length()) return -1;

        int R = 256;
        int Q = 997;
        int M = needle.length();
        int needleHash = hash(needle, M, R, Q);

        int RM = 1;
        for (int i = 1; i < M; i++) {
            RM = (RM * R) % Q;
        }

        int haystackHash = hash(haystack, M, R, Q);
        if (needleHash == haystackHash && check(haystack, needle, 0)) return 0;

        for (int i = M; i < haystack.length(); i++) {
            haystackHash = (haystackHash + Q - RM * haystack.charAt(i - M) % Q) % Q;
            haystackHash = (haystackHash * R + haystack.charAt(i)) % Q;
            if (haystackHash == needleHash && check(haystack, needle, i - M + 1)) return i - M + 1;
        }


        return -1;
    }

    private boolean check(String haystack, String needle, int j) {
        for (int i = 0; i < needle.length(); i++) {
            if (needle.charAt(i) != haystack.charAt(j + i)) return false;
        }
        return true;
    }

    private int hash(String msg, int M, int R, int Q) {
        int h = 0;
        for (int i = 0; i < M; i++) {
            h = (h * R + msg.charAt(i)) % Q;
        }
        return h;

    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j < length - i - 1; j++) {
                int p1 = matrix[i][j];//0,0
                int p2 = matrix[j][length - 1 - i];//0,3
                int p3 = matrix[length - 1 - i][length - 1 - j];//3,3
                int p4 = matrix[length - 1 - j][i];//3,0

                matrix[i][j] = p4;
                matrix[j][length - 1 - i] = p1;
                matrix[length - 1 - i][length - 1 - j] = p2;
                matrix[length - 1 - j][i] = p3;
            }
        }
    }

    public void print(int[][] matrix) {
        for (int[] r : matrix) {
            for (int i = 0; i < r.length; i++) {
                System.out.print(r[i]);
                if (i != r.length - 1) System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    @SuppressWarnings("all")
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        int n = Math.max(v1.length, v2.length);
        for (int i = 0; i < n; i++) {
            int k = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int j = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (k > j) return 1;
            else if (k < j) return -1;
            else continue;
        }
        return 0;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];
        res[0] = 1;

        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right = right * nums[i];
        }

        return res;
    }

    public int missingNumber(int[] nums) {
        int all = nums.length * (nums.length + 1) / 2;
        int sum = 0;
        for (int num : nums) sum += num;
        return all - sum;
    }

    public int missingNumberV2(int[] nums) {
        int res = 0, i = 0;
        for (; i < nums.length; i++) {
            res = res ^ i ^ nums[i];
        }
        return res ^ i;
    }

    public int firstUniqChar(String s) {
        int n = s.length();
        if (n == 0) return -1;

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        }

        return -1;
    }

    public boolean isValid(String s) {
        if (s.isEmpty()) return true;

        Stack<Character> chain = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                chain.push(')');
            } else if (c == '{') {
                chain.push('}');
            } else if (c == '[') {
                chain.push(']');
            } else if (chain.isEmpty() || c != chain.pop()) {
                return false;
            }

         }

        return chain.isEmpty();
    }

    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftHeight = new int[n];
        leftHeight[0] = height[0];
        for (int i = 1; i < n; i++) leftHeight[i] = Math.max(leftHeight[i - 1], height[i]);

        int[] rightHeight = new int[n];
        rightHeight[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) rightHeight[i] = Math.max(rightHeight[i + 1], height[i]);

        int total = 0;
        for (int i = 0; i < n; i++) total += Math.min(leftHeight[i], rightHeight[i]) - height[i];

        return total;
    }

}
