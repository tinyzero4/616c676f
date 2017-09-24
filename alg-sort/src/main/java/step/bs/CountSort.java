package step.bs;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CountSort {

    static int[] countSort(int[] items, int itemsCount) {
        int[] itemsCounts = new int[itemsCount];
        int[] result = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            itemsCounts[items[i]] += 1;
        }
        for (int i = 1; i < itemsCount; i++) {
            itemsCounts[i] += itemsCounts[i - 1];
        }
        for (int i = items.length - 1; i >= 0; i--) {
            int item = items[i];
            result[itemsCounts[item] - 1] = item;
            itemsCounts[item]--;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int itemsCount = Integer.parseInt(scanner.nextLine());
        int[] items = Arrays.stream(scanner.nextLine().split(" ")).limit(itemsCount).mapToInt(Integer::parseInt).toArray();
        System.out.println(String.join(" ", IntStream.of(countSort(items, 11)).mapToObj(Integer::toString).toArray(String[]::new)));
    }
}
