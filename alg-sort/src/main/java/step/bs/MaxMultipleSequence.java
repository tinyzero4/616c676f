package step.bs;

import java.util.Arrays;
import java.util.Scanner;

public class MaxMultipleSequence {

    private static int findMaxMultipleSequence(int[] items) {
        int[] result = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            result[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if (items[j] != 0 && items[i] % items[j] == 0 && result[i] < result[j] + 1) {
                    result[i] = result[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] > max) max = result[i];
        }
        return max;
    }

    public static void main(String[] args) {
//        System.out.println(findMaxMultipleSequence(new int[]{3, 6, 7, 12}));
        Scanner scanner = new Scanner(System.in);
        int limit = Integer.parseInt(scanner.nextLine());
        System.out.println(findMaxMultipleSequence(Arrays.stream(scanner.nextLine().split(" ")).limit(limit).mapToInt(Integer::parseInt).toArray()));

    }

}
