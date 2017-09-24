package c1.w2;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        int[] coins = new int[]{10, 5, 1};

        int coinsCount = 0;
        for (int coin : coins) {
            while (coin <= m) {
                coinsCount++;
                m -= coin;
            }
        }
        return coinsCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

