package c1.w1;

import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long remainder = n % findPeriodLength(m);
        if (remainder == 0) {
            return 0;
        }

        long previous = 0;
        long current  = 1;

        for (long i = 1; i < remainder; i++) {
            long tmp_previous = previous;
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
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}

