package c1.w1;

import java.util.*;

public class FibonacciSumLastDigit {

    private static long getFibonacciSumNaive(long n) {
        if (n <= 1) {
            return n;
        }

        int m = 10;
        long remainder = n % findPeriodLength(m);
        if (remainder == 0) {
            return 9;
        }

        long previous = 0;
        long current = 1;

        for (int i = 1; i < remainder; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current == 0 ? 9 : (current - 1) % m;

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
        long n = scanner.nextLong();
        if (n <= 1) {
            System.out.println(n);
        } else {
            long s = getFibonacciSumNaive(n + 2);
            System.out.println(s);
        }
    }
}

