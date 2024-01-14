package com.breedish.test.leetcode;

public class KMP {

    private String pat;
    private int[][] dfa;

    public KMP(String pat) {  // Build DFA from pattern.
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        int X = 0;

        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;

        for (int j = 1; j < M; j++) {

            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];
            }

            dfa[pat.charAt(j)][j] = j + 1;

            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {

        int i, j, N = txt.length(), M = pat.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;
        else return N;
    }

    public static void main(String[] args) {
        var p1 = new KMP("ABABAC");
        System.out.println(p1.search("CMAPABCABA"));
    }
}

