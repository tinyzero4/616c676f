package c1.w4;

import java.util.Scanner;

import static java.lang.Math.min;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int[][] cost = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            cost[i][0] = i;
        }
        for (int j = 0; j <= t.length(); j++) {
            cost[0][j] = j;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                cost[i][j] = min(cost[i - 1][j - 1] + (s.charAt(i-1) != t.charAt(j-1) ? 1 : 0), min(cost[i][j - 1] + 1, cost[i - 1][j] + 1));
            }
        }

        return cost[s.length()][t.length()];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
