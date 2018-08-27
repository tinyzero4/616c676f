package c3.w3.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.Integer.*;

public class BFS
{
	private static int distance(ArrayList<Integer>[] adj, int s, int t)
	{
		int[] distance = new int[adj.length];
		Arrays.fill(distance, MAX_VALUE);

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(s);
		distance[s] = 0;

		while (!queue.isEmpty())
		{
			Integer v = queue.poll();
			if (v.equals(t))
			{
				return distance[v];
			}
			adj[v].stream().filter(cv -> distance[cv] == MAX_VALUE).forEach(cv -> {
				queue.offer(cv);
				distance[cv] = distance[v] + 1;
			});

		}
		return -1;
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++)
		{
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++)
		{
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		System.out.println(distance(adj, x, y));
	}
}

