import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class tree_height {
    static public void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new tree_height().run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "1", 1 << 26).start();
    }

    private void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements()) {
                tok = new StringTokenizer(in.readLine());
            }
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];
        Node[] nodes;
        Node root;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
                nodes[i] = new Node(i);
            }

            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1) root = nodes[i];
                else nodes[parent[i]].add(nodes[i]);
            }
        }

        int computeHeight() {
            root.level = 1;

            Queue<Node> tasks = new LinkedList<>();
            tasks.add(root);

            int height = 1;
            while (!tasks.isEmpty()) {
                Node node = tasks.poll();
                height = Math.max(height, node.level);
                tasks.addAll(node.children.stream().map(n -> {
                    n.level = node.level + 1;
                    return n;
                }).collect(Collectors.toList()));
            }
            return height;
        }
    }

    static class Node {
        int value;
        int level;
        List<Node> children = new ArrayList<>();

        public Node(int value) {
            this.value = value;
        }

        void add(Node c) {
            children.add(c);
        }
    }
}
