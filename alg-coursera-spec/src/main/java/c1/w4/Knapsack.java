package c1.w4;

import java.util.Scanner;

public class Knapsack {
    static int optimalWeight(int W, int[] weights) {

        int[][] items = new int[W + 1][weights.length + 1];
        for (int i = 0; i <= W; i++) {
            items[i][0] = 0;
        }
        for (int i = 0; i <= weights.length; i++) {
            items[0][i] = 0;
        }

        for (int i = 1; i <= W; i++) {
            for (int j = 1; j <= weights.length; j++) {
                if (weights[j - 1] > i) items[i][j] = items[i][j-1];
                else items[i][j] = Math.max(items[i][j-1], items[i-weights[j - 1]][j-1] + weights[j - 1]);
            }
        }

        return items[W][weights.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

