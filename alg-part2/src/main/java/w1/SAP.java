package w1;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.Map;

public class SAP
{
    private final Digraph G;

    private final Map<Integer, BreadthFirstDirectedPaths> pathsCache;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph graph)
    {
        checkNotNull(graph);
        G = new Digraph(graph);
        pathsCache = new HashMap<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w)
    {
        validateVertices(v, w);
        return detectMinAncestorPath(v, w, getPaths(v), getPaths(w))[1];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w)
    {
        validateVertices(v, w);
        return detectMinAncestorPath(v, w, getPaths(v), getPaths(w))[0];
    }

    private BreadthFirstDirectedPaths getPaths(int v)
    {
        return pathsCache.computeIfAbsent(v, (k) -> new BreadthFirstDirectedPaths(G, k));
    }

    private int[] detectMinAncestorPath(int v, int w, BreadthFirstDirectedPaths vPaths, BreadthFirstDirectedPaths wPaths)
    {
        int ancestor = -1;
        int ancestorLength = -1;
        for (int i = 0; i < G.V(); i++)
        {
            if ((vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) || (i == v && wPaths.hasPathTo(v)) || (i == w && vPaths.hasPathTo(w)))
            {
                int length = vPaths.distTo(i) + wPaths.distTo(i);
                if (length < ancestorLength || ancestorLength == -1)
                {
                    ancestor = i;
                    ancestorLength = length;
                }
            }
        }
        return new int[]{ancestor, ancestorLength};
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        return searchMinPathAncestor(v, w)[1];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        return searchMinPathAncestor(v, w)[0];
    }

    private int[] searchMinPathAncestor(Iterable<Integer> v, Iterable<Integer> w)
    {
        checkNotNull(v);
        checkNotNull(w);
        validateVertices(v);
        validateVertices(w);

        BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(G, w);

        int[] result = new int[]{-1, -1};

        for (int vVertex : v)
        {
            for (int wVertex : w)
            {
                int[] ancestor = detectMinAncestorPath(vVertex, wVertex, vPaths, wPaths);
                if (result[1] < ancestor[1] || result[1] == -1)
                {
                    result = ancestor;
                }
            }
        }

        return result;
    }

    private void validateVertices(int... vertexes)
    {
        for (int v : vertexes)
        {
            if (v < 0 || v >= G.V())
            {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateVertices(Iterable<Integer> vertexes)
    {
        for (int v : vertexes)
        {
            if (v < 0 || v >= G.V())
            {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkNotNull(Object value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException();
        }
    }

    // do unit testing of this class
    public static void main(String[] args)
    {
        System.out.println("test");
    }
}
