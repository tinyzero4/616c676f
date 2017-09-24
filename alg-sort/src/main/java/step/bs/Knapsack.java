package step.bs;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {

    public static int findMaxFillWithRepetition(int w, int[] costs) {
        int[] weights = new int[w];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < costs.length; j++) {
                if (costs[j] <= i) {
                    weights[i] = Math.max(weights[i], weights[i - costs[j]] + costs[j]);
                }
            }
        }

        return weights[w - 1];
    }

    public static int findMaxFillWithoutRepetition(int w, int[] costs) {
        int[][] restrictions = new int[w + 1][costs.length + 1];

        for (int i = 1; i <= costs.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (costs[i - 1] <= j) {
                    try {
                        restrictions[j][i] = Math.max(restrictions[j][i - 1], restrictions[j - costs[i - 1]][i - 1] + costs[i - 1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    restrictions[j][i] = restrictions[j][i - 1];
                }
            }
        }
        return restrictions[w][costs.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] def = scanner.nextLine().split(" ");
        int w = Integer.parseInt(def[0]);
        int limit = Integer.parseInt(def[1]);
        System.out.println(findMaxFillWithRepetition(w, Arrays.stream(scanner.nextLine().split(" ")).limit(limit).mapToInt(Integer::parseInt).toArray()));

//        System.out.println(findMaxFillWithoutRepetition(10, new int[]{1, 4, 8}));
    }
}
