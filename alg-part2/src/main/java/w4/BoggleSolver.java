package w4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BoggleSolver {

    private final TrieST<Integer> dictionary;

    // Initializes the data structure using the given array of strings as the words.
    // (You can assume each word in the words contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] words) {
        dictionary = new TrieST<>();
        for (int i = 0; i < words.length; i++) dictionary.put(words[i], i);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        Set<String> words = new HashSet<>();

        Node[][] nodes = preProcessBoard(board);
        boolean[][] marked = new boolean[board.rows()][board.cols()];

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                Node root = nodes[i][j];
                dfs(root, marked, "" + charToString(root.ch), words);

                for (boolean[] row : marked) {
                    Arrays.fill(row, false);
                }

            }
        }
        return words;
    }

    private static final class Node {
        int row;
        int col;
        List<Node> adj;
        char ch;

        Node(int row, int col, char ch) {
            this.row = row;
            this.col = col;
            this.ch = ch;
        }
    }

    private void dfs(Node node, boolean[][] marked, String word, Set<String> words) {
        if (word.length() >= 3) {
            if (!dictionary.keysWithPrefix(word).iterator().hasNext()) {
                return;
            }
            if (dictionary.contains(word)) words.add(word);
        }

        marked[node.row][node.col] = true;
        for (Node v : node.adj) {
            if (!marked[v.row][v.col]) {
                dfs(v, marked, word + charToString(v.ch), words);
            }
        }
        marked[node.row][node.col] = false;
    }

    private String charToString(char c) {
        return 'Q' == c ? "QU" : String.valueOf(c);
    }

    private Node[][] preProcessBoard(BoggleBoard board) {
        Node[][] nodes = new Node[board.rows()][board.cols()];

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                nodes[i][j] = new Node(i, j, board.getLetter(i, j));
            }
        }

        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                List<Node> adj = new ArrayList<>();
                if (i > 0) adj.add(nodes[i - 1][j]);
                if (i < board.rows() - 1) adj.add(nodes[i + 1][j]);
                if (j > 0) adj.add(nodes[i][j - 1]);
                if (j < board.cols() - 1) adj.add(nodes[i][j + 1]);

                if (i > 0 && j > 0) adj.add(nodes[i - 1][j - 1]);
                if (i > 0 && j < board.cols() - 1) adj.add(nodes[i - 1][j + 1]);
                if (i < board.rows() - 1 && j > 0) adj.add(nodes[i + 1][j - 1]);
                if (i < board.rows() - 1 && j < board.cols() - 1) adj.add(nodes[i + 1][j + 1]);

                nodes[i][j].adj = adj;
            }
        }


        return nodes;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        return 0;
    }

    private static class TrieST<Value> {

        private static final int R = 26;

        private static class Node {
            private Object val;
            private Node[] next = new Node[R];
        }

        private Node root;
        private int n;

        public Value get(String key) {
            Node x = get(root, key, 0);
            if (x == null) return null;
            return (Value) x.val;
        }

        private Node get(Node x, String key, int d) {
            if (x == null) return null;
            if (d == key.length()) return x;
            char c = key.charAt(d);
            return get(x.next[c - 65], key, d + 1);
        }

        public void put(String key, Value val) {
            if (val == null) delete(key);
            else root = put(root, key, val, 0);
        }

        private Node put(Node x, String key, Value val, int d) {
            if (x == null) x = new Node();
            if (d == key.length()) {
                if (x.val == null) n++;
                x.val = val;
                return x;
            }
            char c = key.charAt(d);
            x.next[c - 65] = put(x.next[c - 65], key, val, d + 1);
            return x;
        }

        public void delete(String key) {
            root = delete(root, key, 0);
        }

        private Node delete(Node x, String key, int d) {
            if (x == null) return null;
            if (d == key.length()) {
                if (x.val != null) n--;
                x.val = null;
            }
            else {
                char c = key.charAt(d);
                x.next[c - 65] = delete(x.next[c - 65], key, d+1);
            }

            // remove subtrie rooted at x if it is completely empty
            if (x.val != null) return x;
            for (int c = 0; c < R; c++)
                if (x.next[c - 65] != null)
                    return x;
            return null;
        }

        public boolean contains(String key) {
            return get(key) != null;
        }

        public int size() {
            return n;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public Iterable<String> keys() {
            return keysWithPrefix("");
        }

        public Iterable<String> keysWithPrefix(String prefix) {
            Queue<String> results = new Queue<>();
            Node x = get(root, prefix, 0);
            collect(x, new StringBuilder(prefix), results);
            return results;
        }

        private void collect(Node x, StringBuilder prefix, Queue<String> results) {
            if (x == null) return;
            if (x.val != null) results.enqueue(prefix.toString());
            for (char c = 0; c < R; c++) {
                prefix.append(c);
                collect(x.next[c], prefix, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(BoggleSolver.class.getResource("dic.txt").getPath());
        BoggleSolver solver = new BoggleSolver(in.readAllLines());

        char[][] b = new char[][]{
                {'S', 'N', 'R', 'T'},
                {'O', 'I', 'E', 'L'},
                {'E', 'Q', 'T', 'T'},
                {'R', 'S', 'A', 'T'}
        };
        BoggleBoard board = new BoggleBoard(b);
        Iterable<String> validWords = solver.getAllValidWords(board);
        validWords.forEach(System.out::println);
    }


}