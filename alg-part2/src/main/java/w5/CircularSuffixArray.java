package w5;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {

    private final String s;

    private Integer[] suffixes;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) throw new IllegalArgumentException();
        this.s = s;

        suffixes = new Integer[s.length()];

        for (int i = 0; i < s.length(); i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, new Comparator<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                for (int i = 0; i < s.length(); i++) {
                    int leftIndex = left + i;
                    int rightIndex = right + i;
                    if (leftIndex >= s.length()) leftIndex -= s.length();
                    if (rightIndex >= s.length()) rightIndex -= s.length();

                    if (s.charAt(leftIndex) != s.charAt(rightIndex)) return (s.charAt(leftIndex) > s.charAt(rightIndex) ? 1 : -1);
                }
                return 0;
            }
        });

    }

    // length of s
    public int length() {
        return s.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length() - 1) throw new IllegalArgumentException();
        return suffixes[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
//        String text = BinaryStdIn.readString();
        String text = args[0];

        CircularSuffixArray suffixArray = new CircularSuffixArray(text);

        StdOut.println(suffixArray.length());

        StdOut.println(suffixArray.index(11));
    }
}
