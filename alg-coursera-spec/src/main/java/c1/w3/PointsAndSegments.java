package c1.w3;

import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i = 0; i < points.length; i++) {
            int point = points[i];
            int maxCount = findLeftMostIndex(starts, point) + 1;
            if (maxCount > 0) {
                int endCount = findRightLeastIndex(ends, point) + 1;
                cnt[i] = endCount > maxCount ? maxCount : maxCount - endCount;
            }
        }

        return cnt;
    }

    private static int findLeftMostIndex(int[] starts, int point) {
        int l = 0;
        int h = starts.length - 1;

        if (starts[l] > point) {
            return -1;
        }

        if (starts[starts.length - 1] <= point) {
            return starts.length - 1;
        }

        while (l < h) {
            int mid = (l + h) >>> 1;
            int midPoint = starts[mid];
            if (midPoint <= point) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }

        return h - 1;
    }

    private static int findRightLeastIndex(int ends[], int point) {
        int l = 0;
        int h = ends.length - 1;

        if (ends[l] >= point) {
            return -1;
        }

        if (ends[ends.length - 1] < point) {
            return ends.length - 1;
        }

        while (l < h) {
            int mid = (l + h) >>> 1;
            int midPoint = ends[mid];
            if (midPoint >= point) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }

        return h - 1;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
//        System.out.println(findLeftMostIndex(new int[]{1, 2, 33, 36, 49, 51}, 1));
//        System.out.println(findRightLeastIndex(new int[]{1, 2, 33, 36, 49, 51}, 36));
//        System.out.println(Arrays.toString(fastCountSegments(new int[]{0, 7}, new int[]{5, 10}, new int[]{1, 6, 11})));
//        System.out.println(Arrays.toString(fastCountSegments(new int[]{0, -3, 7}, new int[]{5, 2, 10}, new int[]{1, 6})));
    }
}

