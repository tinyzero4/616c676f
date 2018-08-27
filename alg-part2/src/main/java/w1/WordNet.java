package w1;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class WordNet
{
    private final Digraph G;

    private final Map<String, Set<Integer>> synsetsByNoun;

    private final Map<Integer, SynSet> synsetById;

    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms)
    {
        checkNotNull(synsets);
        checkNotNull(hypernyms);

        synsetsByNoun = new HashMap<>();
        synsetById = new HashMap<>();

        readSynsets(new In(synsets));
        G = readHypernyms(new In(hypernyms), this.synsetById.size());

        if (new DirectedCycle(G).hasCycle())
        {
            throw new IllegalArgumentException();
        }
        validateSeveralRoots();

        sap = new SAP(G);
    }

    private void validateSeveralRoots()
    {
        int rootCount = 0;
        for (int i = 0; i < G.V(); i++)
        {
            if (G.outdegree(i) == 0 && G.indegree(i) > 0)
            {
                rootCount++;
            }

            if (rootCount > 1)
            {
                throw new IllegalArgumentException();
            }
        }
    }

    private SynSet lookupById(int id)
    {
        return synsetById.getOrDefault(id, new SynSet());
    }

    private Set<Integer> lookupIdsByNoun(String noun)
    {
        return synsetsByNoun.getOrDefault(noun, new HashSet<>());
    }

    private void readSynsets(In in)
    {
        String line;
        for (line = in.readLine(); line != null; line = in.readLine())
        {
            String[] def = line.split(",");
            for (String noun : def[1].split(" "))
            {
                SynSet set = new SynSet();
                set.id = Integer.parseInt(def[0]);
                set.name = noun;
                synsetsByNoun.computeIfAbsent(set.name, (k) -> new HashSet<>()).add(set.id);
                synsetById.put(set.id, set);
            }
        }
    }

    private Digraph readHypernyms(In in, int size)
    {
        Digraph digraph = new Digraph(size);
        String line;
        for (line = in.readLine(); line != null; line = in.readLine())
        {
            String[] sets = line.split(",");
            int v = Integer.parseInt(sets[0]);
            for (int i = 1; i < sets.length; i++)
            {
                int w = Integer.parseInt(sets[i]);
                digraph.addEdge(v, w);
            }
        }
        return digraph;
    }

    private static class SynSet
    {
        int id;
        String name;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns()
    {
        return new HashSet<>(synsetsByNoun.keySet());
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        checkNotNull(word);
        return synsetsByNoun.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        if (!synsetsByNoun.containsKey(nounA) || !synsetsByNoun.containsKey(nounB))
        {
            throw new IllegalArgumentException();
        }

        if (nounA.equals(nounB))
        {
            return 0;
        }

        return sap.length(lookupIdsByNoun(nounA), lookupIdsByNoun(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!synsetsByNoun.containsKey(nounA) || !synsetsByNoun.containsKey(nounB))
        {
            throw new IllegalArgumentException();
        }
        return lookupById(sap.ancestor(lookupIdsByNoun(nounA), lookupIdsByNoun(nounB))).name;
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
        WordNet wordNet = new WordNet(
                WordNet.class.getResource("synsets6.txt").getPath(),
                WordNet.class.getResource("hypernyms6InvalidTwoRoots.txt").getPath()
        );
        System.out.println(wordNet.distance("change", "thing"));
    }
}
