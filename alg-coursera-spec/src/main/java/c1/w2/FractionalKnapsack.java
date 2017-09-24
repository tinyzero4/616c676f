package c1.w2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class FractionalKnapsack {

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here
        Item[] items = new Item[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i] = new Item(values[i], weights[i]);
        }
        Arrays.sort(items, Comparator.comparingDouble(Item::getDensity).reversed());

        for (Item item : items) {
            int w = Math.min(item.weight, capacity);
            value += w * item.density;
            capacity -= w;
        }
        return value;
    }

    private static class Item {
        int value;
        int weight;
        double density;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            density = 1.0 * value / weight;
        }

        double getDensity() {
            return density;
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }

        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
