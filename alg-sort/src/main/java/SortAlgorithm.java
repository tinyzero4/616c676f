import edu.princeton.cs.algs4.StdOut;

import java.util.Random;

public interface SortAlgorithm<T extends Comparable<T>> {
    T[] sort(T[] items);

    default void shuffle(T[] items) {
        Random r = new Random();
        for (int i = 1; i < items.length; i++) {
            int j = i + r.nextInt(items.length - i);
            exch(items, i, j);
        }
    }

    default boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    default void exch(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    default void show(T[] a) {
        for (T ai : a) {
            StdOut.print(ai + " ");
        }

        StdOut.println();
    }

    default boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
