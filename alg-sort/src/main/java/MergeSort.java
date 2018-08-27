public class MergeSort<T extends Comparable<T>>
    implements SortAlgorithm<T> {
    @Override
    public T[] sort(T[] items) {
        Comparable[] aux = new Comparable[items.length];
        doSort(items, (T[]) aux, 0, items.length - 1);
        return items;
    }

    private void doSort(T[] items, T[] aux, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        if (hi - lo < 10) {
            for (int i = lo; i <= hi; i++) {
                for (int j = i; j > lo && less(items[j], items[j - 1]); j--) {
                    exch(items, j, j - 1);
                }
            }
            return;
        }

        int mid = lo + (hi - lo) / 2;
        doSort(items, aux, lo, mid);
        doSort(items, aux, mid + 1, hi);

        doMerge(items, aux, lo, mid, hi);
    }

    private void doMerge(T[] items, T[] aux, int lo, int mid, int hi) {
        if (less(items[mid], items[mid + 1])) {
            return;
        }

        for (int i = lo; i <= hi; i++) {
            aux[i] = items[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                items[k] = aux[j++];
            } else if (j > hi) {
                items[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                items[k] = aux[i++];
            } else {
                items[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> sort = new MergeSort<>();
//		sort.show(sort.sort(new Integer[]{22, 33, 2, 66, 0, -1, 3, 5, 7, 3, 4, 6}));
        sort.show(sort.sort(new Integer[]{1, 5, 2, 34, 1, 2, 39, 10}));
        sort.show(sort.sort(new Integer[]{5, 4, 3, 2, 1}));
//		fib(100);
    }

    public static void fib(int n) {
        int[] fib = new int[n];
        int i = 0;
        fib[i++] = 0;
        fib[i++] = 1;
        while (i < n) {
            fib[i] = fib[i - 1] + fib[i - 2];
            System.out.println(fib[i]);
            i++;
        }
    }

    public static void fib2(int n) {
        int[] fib = new int[n];
        int i = 0;
        fib[i++] = 0;
        fib[i++] = 1;
        while (i < n) {
            fib[i] = fib[i - 1] + fib[i - 2];
            System.out.println(fib[i]);
            i++;
        }
    }
}
