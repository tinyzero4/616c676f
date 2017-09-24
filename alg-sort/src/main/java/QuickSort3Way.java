import java.util.Arrays;

public class QuickSort3Way<T extends Comparable<T>>
    implements SortAlgorithm<T> {

    @Override
    public T[] sort(T[] items) {
        shuffle(items);
        doSort(items, 0, items.length - 1);
        return items;
    }

    private void doSort(T[] items, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        T v = items[lo];
        int lt = lo, i = lo + 1, gt = hi;
        while (i < gt) {
            int cmp = items[i].compareTo(v);
            if (cmp < 0) {
                exch(items, i++, lt++);
            }
            if (cmp > 0) {
                exch(items, i, gt--);
            }
            if (cmp == 0) {
                i++;
            }
        }

        doSort(items, lo, lt - 1);
        doSort(items, gt + 1, hi);
    }

    public static void main(String[] args) {
        QuickSort3Way<Integer> qs = new QuickSort3Way<>();
        System.out.println(Arrays.toString(qs.sort(new Integer[]{3, 7, 3, 3, 4, 2, 5, 9})));
    }
}
