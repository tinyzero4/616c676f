package c1.w1;

import java.util.Scanner;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1) {
            return n;
        }

        int m = 10;
        long remainder = n % findPeriodLength(m);
        if (remainder == 0) {
            return 0;
        }

        int previous = 0;
        int current = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
        }

        return current;
    }

    private static long findPeriodLength(long m) {
        long a = 0;
        long b = 1;
        long i = 0;
        for (; i < m * m ;i++) {
            long c = (a + b) % m;
            a = b;
            b = c;
            if (a == 0 && b == 1) {
                return i + 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);
    }
}

