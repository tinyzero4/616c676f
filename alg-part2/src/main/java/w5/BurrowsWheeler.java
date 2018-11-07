package w5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform() {
        String text = BinaryStdIn.readString();
        CircularSuffixArray suffixArray = new CircularSuffixArray(text);

        for (int i = 0; i < suffixArray.length(); i++) {
            if (suffixArray.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < suffixArray.length(); i++) {
            int idx = suffixArray.index(i);
            BinaryStdOut.write(text.charAt(idx == 0 ?  text.length() - 1: idx - 1), 8);
        }

        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform() {
        //TODO
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args.length == 0) return;
        if (args[0].equals("-")) transform();
        if (args[0].equals("+")) inverseTransform();
    }
}