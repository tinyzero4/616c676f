package w1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast
{
    private final WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet)
    {
        checkNotNull(wordnet);
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns)
    {
        checkNotNull(nouns);

        String outcastNoun = null;
        int outcastDistance = -1;

        for (int i = 0; i < nouns.length; i++)
        {
            int distance = 0;
            for (int j = 0; j < nouns.length; j++)
            {
                distance += wordnet.distance(nouns[i], nouns[j]);
            }

            if (distance > outcastDistance)
            {
                outcastNoun = nouns[i];
                outcastDistance = distance;
            }
        }
        return outcastNoun;
    }

    // see test client below
    public static void main(String[] args)
    {
        WordNet wordnet = new WordNet(
                WordNet.class.getResource("synsets.txt").getPath(),
                WordNet.class.getResource("hypernyms.txt").getPath()
        );
        Outcast outcast = new Outcast(wordnet);
        In in = new In(WordNet.class.getResource("outcast5.txt").getPath());
        String[] nouns = in.readAllStrings();
        StdOut.println(outcast.outcast(nouns));
    }

    private void checkNotNull(Object value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException();
        }
    }

}