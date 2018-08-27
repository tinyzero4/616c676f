import edu.princeton.cs.algs4.StdRandom;

public class QuickSort<T extends Comparable<T>>
    implements SortAlgorithm<T> {
    @Override
    public T[] sort(T[] items) {
        if (items != null && items.length > 1) {
            StdRandom.shuffle(items);
            doSort(items, 0, items.length - 1);
        }
        return items;
    }

    private void doSort(T[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int k = partition(items, lo, hi);
        doSort(items, lo, k - 1);
        doSort(items, k + 1, hi);
    }

    private int partition(T[] items, int lo, int hi) {
        T pivot = items[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(items[++i], pivot)) {
                if (i == hi) {
                    break;
                }
            }

            while (less(pivot, items[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(items, i, j);
        }
        exch(items, lo, j);
        return j;
    }

    public T findKthElement(T[] items, int k) {
        shuffle(items);

        int lo = 0;
        int hi = items.length - 1;
        while (lo < hi) {
            int pivot = partition(items, lo, hi);
            if (pivot == k)  return items[pivot];
            else if (pivot < k) lo = pivot + 1;
            else if (pivot > k) hi = pivot -1;
        }

        return items[k];
    }

    public static void main(String[] args) {
        QuickSort<Integer> sort = new QuickSort<>();
        final Integer[] items = {22, 33, 2, 66, 0, -1, 3, 5, 7, 3, 4, 6};
        sort.show(sort.sort(items));
        System.out.println(sort.findKthElement(items, 2));
//        sort.show(sort.sort(new Integer[]{3, 7, 4, 2, 5, 9}));
    }

}
