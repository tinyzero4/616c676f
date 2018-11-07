package w5;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int ALPHABET_SIZE = 256;

    public static void encode() {
        char[] alphabet = new char[ALPHABET_SIZE];
        for (char i = 0; i < ALPHABET_SIZE; i++) {
            alphabet[i] = i;
        }

        char input;
        while (!BinaryStdIn.isEmpty()) {
            input = BinaryStdIn.readChar();
            char inputIdx = alphabet[input];
            BinaryStdOut.write(inputIdx, 8);
            if (inputIdx > 0) {
                for (int i = 0; i < alphabet.length; i++) {
                    char idx = alphabet[i];
                    if (idx < inputIdx) alphabet[i] = ++idx;
                }
                alphabet[input] = 0;
            }
            BinaryStdOut.flush();
        }
    }

    public static void decode() {
        char[] alphabet = new char[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            alphabet[i] = (char) i;
        }

        int idx;
        while (!BinaryStdIn.isEmpty()) {
            idx = BinaryStdIn.readChar(8);
            char chr = alphabet[idx];
            BinaryStdOut.write(chr, 8);

            for (int i = idx; i > 0; i--) {
                char tmpChr = alphabet[i];
                alphabet[i] = alphabet[i - 1];
                alphabet[i - 1] = tmpChr;
            }
            BinaryStdOut.flush();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) return;
        if (args[0].equals("-")) encode();
        if (args[0].equals("+")) decode();
    }
}
