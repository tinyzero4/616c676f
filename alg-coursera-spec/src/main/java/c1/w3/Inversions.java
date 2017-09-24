package c1.w3;

import java.util.Scanner;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] aux, int l, int h) {
        long inversions = 0;

        if (l >= h) {
            return inversions;
        }

        int mid = (l + h) >>> 1;

        inversions += getNumberOfInversions(a, aux, l, mid);
        inversions += getNumberOfInversions(a, aux, mid + 1, h);

        return inversions + merge(a, aux, l, h, mid);
    }

    private static long merge(int[] a, int[] aux, int l, int h, int mid) {
        long inversions = 0;
        if (a[mid] <= a[mid + 1]) {
            return inversions;
        }

        for (int i = l; i <= h; i++) {
            aux[i] = a[i];
        }

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= h; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > h) {
                a[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
                inversions += (mid - i +1);
            }

        }
        return inversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
//        int a[] = new int[]{2, 3, 9, 2, 9};
        int[] b = new int[a.length];

        System.out.println(getNumberOfInversions(a, b, 0, a.length - 1));
    }
}

