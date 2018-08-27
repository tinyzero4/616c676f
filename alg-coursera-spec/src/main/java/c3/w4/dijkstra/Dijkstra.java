package c3.w4.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Dijkstra
{
	private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t)
	{
		int[] distances = new int[adj.length];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[s] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
		IntStream.range(0, adj.length).forEach(i -> queue.add(new Node(i, i == s ? 0 : Integer.MAX_VALUE)));
		while (!queue.isEmpty())
		{
			Node v = queue.poll();
			relaxEdges(distances[v.v], adj[v.v], cost[v.v], queue, distances);
		}

		int dist = distances[t];
		return dist == Integer.MAX_VALUE ? -1 : dist;
	}

	private static void relaxEdges(int distance, ArrayList<Integer> edges, ArrayList<Integer> cost, PriorityQueue<Node> queue, int[] distances)
	{
		for (int i = 0; i < edges.size(); i++)
		{
			int u = edges.get(i);
			int newDistance = distance + cost.get(i);
			if (distances[u] > distance && distances[u] > newDistance)
			{
				distances[u] = newDistance;
				queue.removeIf(n -> n.v == u);
				queue.add(new Node(u, newDistance));
			}
		}
	}

	static class Node
	{
		int v;
		int cost;

		public Node(int v, int cost)
		{
			this.v = v;
			this.cost = cost;
		}

		public int getCost()
		{
			return cost;
		}
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
			adj[i] = new ArrayList<>();
			cost[i] = new ArrayList<>();
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
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, cost, x, y));
	}
}

