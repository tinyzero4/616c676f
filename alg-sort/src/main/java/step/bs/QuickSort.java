package step.bs;

import java.util.Random;

public class QuickSort {

    static Random r = new Random();

    static void shuffle(int[] items) {
        for (int i = 0; i < items.length; i++) {
            exch(i, i + r.nextInt(items.length - i), items);
        }
    }

    static void exch(int i, int j, int[] items) {
        if (i == j) {
            return;
        }
        int temp = items[j];
        items[j] = items[i];
        items[i] = temp;
    }

    static int[] qSort(int[] items) {
        if (items.length > 1) {
//            shuffle(items);
//            doSort(items, 0, items.length - 1);
            doSort3Way(items, 0, items.length - 1);
        }
        return items;
    }

    static void doSort(int[] items, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int p = partition(items, lo, hi);
        doSort(items, lo, p - 1);
        doSort(items, p + 1, hi);
    }

    static void doSort3Way(int[] items, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int v = items[lo];
        int i = lo + 1, lt = lo, gt = hi;
        while (i <= gt) {
            if (items[i] < v) {
                exch(i++, lt++, items);
            } else if (items[i] > v) {
                exch(i, gt--, items);
            } else if (items[i] == v) {
                i++;
            }
        }

        doSort3Way(items, lo, lt - 1);
        doSort3Way(items, gt + 1, hi);

    }

    //return index
    static int partition(int[] items, int lo, int hi) {
        exch(lo, lo + r.nextInt(hi - lo), items);
        int pivot = items[lo];

        int j = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (less(items[i], pivot)) {
                exch(i, ++j, items);
            }
        }

        exch(lo, j, items);
        return j;
    }

    static boolean less(int i, int j) {
        return i < j;
    }

    static int binarySearchL(int[] items, int item) {
        int lo = 0;
        int n = items.length;
        int hi = n - 1;

        int mid = -1;
        while (lo <= hi) {
            mid = (lo + hi) >>> 1;
            if (items[mid] >= item) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (items[mid] < item) {
            return mid;
        }

        while (mid >= 0 && items[mid] >= item) {
            mid--;
        }
        return mid < 0 ? -1 : mid;
    }

    static int binarySearchG(int[] items, int item) {
        int lo = 0;
        int n = items.length;
        int hi = n - 1;

        int mid = -1;
        while (lo <= hi) {
            mid = (lo + hi) >>> 1;
            if (items[mid] <= item) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (items[mid] > item) {
            return mid;
        }
        while (mid < n && items[mid] <= item) {
            mid++;
        }
        return mid >= n ? -1 : mid;
    }

    static void solve(int[] s, int[] e, int[] p) {
        int[] ss = qSort(s);
        int[] es = qSort(e);

        for (int i = 0; i < p.length; i++) {
            int s_count = binarySearchL(ss, p[i] + 1) + 1;
            int e_count = binarySearchL(es, p[i]) + 1;
            System.out.println(s_count - e_count);
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(qSort(new int[]{7, 0})));
//        System.out.println(binarySearchG(new int[]{5, 10}, 11));

        solve(new int[]{6, 0, 1, 2, 3, 3, 3}, new int[]{6, 3, 3, 3, 4, 5, 6}, new int[]{1, 2, 3, 4, 5, 6});
//        solve(new int[]{0, 7}, new int[]{5, 10}, new int[]{1, 6, 11});

//        Scanner s = new Scanner(System.in);
//        String[] def = s.nextLine().split(" ");
//        int lineCount = Integer.parseInt(def[0]);
//        int pointCount = Integer.parseInt(def[1]);
//        int[] ss = new int[lineCount];
//        int[] es = new int[lineCount];
//        int[] p = new int[pointCount];
//        for (int i = 0; i < lineCount; i++) {
//            String[] lineDef = s.nextLine().split(" ");
//            ss[i] = Integer.parseInt(lineDef[0]);
//            es[i] = Integer.parseInt(lineDef[1]);
//        }
//        String[] pDefs = s.nextLine().split(" ");
//        for (int i = 0; i < pDefs.length; i++) {
//            p[i] = Integer.parseInt(pDefs[i]);
//        }
//        solve(ss, es, p);
    }
}
