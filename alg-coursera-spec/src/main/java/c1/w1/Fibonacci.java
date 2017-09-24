package c1.w1;

import java.util.Scanner;

public class Fibonacci {
    private static long calc_fib(int n) {
        if (n <= 1)
            return n;

        int a = 0;
        int b = 1;
        int i = 2;
        while (i <= n) {
            int tmp = a + b;
            a = b;
            b = tmp;
            i++;
        }
        return b;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fib(n));
    }
}
