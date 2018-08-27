package w1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths
{
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        edgeTo[v] = -1;

        while (!stack.isEmpty())
        {
            int w = stack.pop();
            marked[w] = true;
            G.adj(w).forEach((av) -> {
                if (!marked[av])
                {
                    stack.push(av);
                    edgeTo[av] = w;
                }
            });
        }
    }

    public boolean hasPathTo(int v)
    {
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v)
    {
        validateVertex(v);
        Stack<Integer> path = new Stack<>();
        if (marked[v])
        {
            for (int w = v; edgeTo[w] != -1; w = edgeTo[w])
            {
                path.push(w);
            }
            path.push(s);
        }
        return path;
    }

    private void validateVertex(int v)
    {
        int length = this.marked.length;
        if (v < 0 || v >= length)
        {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (length - 1));
        }
    }

    public static void main(String[] args)
    {
        Graph G = new Graph(new In(DepthFirstPaths.class.getResource("digraph1.txt").getPath()));
        int s = 7;
        DepthFirstPaths dfs = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++)
        {
            if (dfs.hasPathTo(v))
            {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v))
                {
                    if (x == s)
                    {
                        StdOut.print(x);
                    }
                    else
                    {
                        StdOut.print("-" + x);
                    }
                }
                StdOut.println();
            }
            else
            {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}
