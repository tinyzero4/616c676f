package c1.w3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {

        int major = a[0];
        int majorCount = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == major) {
                majorCount++;
            } else {
                majorCount--;
            }

            if (majorCount <= 0) {
                major = a[i];
                majorCount = 0;
            }
        }

        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == major) {
                count++;
                if (count > a.length / 2) {
                    return a[i];
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
//        int[] a = new int[] {2, 3, 9, 2, 2};
//        System.out.println(getMajorityElement(a, 0, a.length));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

