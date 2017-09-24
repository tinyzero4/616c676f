package c1.w4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

public class PrimitiveCalculator {

    private static List<Integer> optimal_sequence(int n) {
        int[] counts = new int[n + 1];
        counts[0] = 0;
        counts[1] = 1;

        for (int i = 2; i < counts.length; i++) {
            int div3 = i % 3 == 0 ? i / 3 : -1;
            int div2 = i % 2 == 0 ? i / 2 : -1;
            int min1 = i - 1;

            if (div3 != -1 && div2 == -1) counts[i] = min(counts[min1], counts[div3]) + 1;
            else if (div3 == -1 && div2 != -1) counts[i] = min(counts[min1], counts[div2]) + 1;
            else if (div3 == -1) counts[i] = counts[min1] + 1;
            else counts[i] = min(counts[div3], min(counts[min1], counts[div2])) + 1;
        }

        Integer[] sequence = new Integer[counts[n]];
        sequence[sequence.length - 1] = n;

        int index = n;

        for (int i = sequence.length - 2; i >= 0; i--) {
            int div3 = index % 3 == 0 ? counts[index / 3] : 0;
            int div2 = index % 2 == 0 ? counts[index / 2] : 0;

            if (i + 1 == div3) sequence[i] = index / 3;
            else if (i + 1 == div2) sequence[i] = index / 2;
            else sequence[i] = index - 1;

            index = sequence[i];
        }

        return Arrays.asList(sequence);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

