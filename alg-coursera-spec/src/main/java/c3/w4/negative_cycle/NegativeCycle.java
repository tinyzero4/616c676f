package c3.w4.negative_cycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NegativeCycle
{
	private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost)
	{
		int[] distances = new int[adj.length];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[0] = 0;

		Set<Integer> relaxed = new HashSet<>();
		for (int i = 0; i <= adj.length; i++)
		{
			relaxed = new HashSet<>();
			for (int j = 0; j < adj.length; j++)
			{
				relaxed.addAll(relaxEdges(distances[j], adj[j], cost[j], distances));
//				printDistances(i, j, distances);
			}

		}
		return relaxed.isEmpty() ? 0 : 1;
	}

	private static void printDistances(int pass, int iteration, int[] distances)
	{
		for (int i = 0; i < distances.length; i++)
		{
			System.out.println("[" + pass +"] / [" + iteration + "]" + Arrays.toString(distances));
		}
	}


	private static Set<Integer> relaxEdges(int distance, ArrayList<Integer> edges, ArrayList<Integer> cost, int[] distances)
	{
		Set<Integer> relaxed = new HashSet<>();
		if (distance == Integer.MAX_VALUE)
		{
			distance = 0;
		}
		for (int i = 0; i < edges.size(); i++)
		{
			int u = edges.get(i);
			int newDistance = distance + cost.get(i);
			if (distances[u] > newDistance)
			{
				distances[u] = newDistance;
				relaxed.add(u);
			}
		}

		return relaxed;
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++)
		{
			adj[i] = new ArrayList<Integer>();
			cost[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++)
		{
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1].add(w);
		}
		System.out.println(negativeCycle(adj, cost));
	}
}

