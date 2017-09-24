import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> {

    private Comparable[] items;
    private int n;

    public PriorityQueue(int size) {
        items = new Comparable[size + 1];
    }

    public void enqueue(Comparable item) {
        items[++n] = item;
        swim(n);
    }

    public Comparable poll() {
        Comparable max = items[1];
        items[1] = items[n--];
        items[n + 1] = null;
        sink(1, n);
        return max;
    }

    public Comparable peek() {
        return items[n];
    }

    void swim(int i) {
        while (i > 1 && less(items, i / 2, i)) {
            exch(i, i / 2);
            i /= 2;
        }
    }

    void sink(int i, int n) {
        while (i * 2 <= n) {
            int j = i * 2;
            if (j < n && less(items, j, j + 1)) {
                j++;
            }
            if (!less(items, i, j)) {
                break;
            }
            exch(i, j);
            i = j;
        }
    }

    void exch(int i, int j) {
        Comparable tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    public static void heapSort(Comparable[] items) {
        int n = items.length;
        if (n == 1) {
            return;
        }
        for (int i = n / 2; i >= 1; i--) {
            sink(items, i, items.length);
        }
        while (n > 1) {
            exch(items, 1, n--);
            sink(items, 1, n);
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    public static void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n/2; k >= 1; k--)
            sink(pq, k, n);
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
        }
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }


    public static void main(String[] args) {
        Comparable[] items = new Comparable[] {1111, 555, 345, 220404, 12,3, 56, 0};
        heapSort(items);
        System.out.println(Arrays.toString(items));
    }

}
