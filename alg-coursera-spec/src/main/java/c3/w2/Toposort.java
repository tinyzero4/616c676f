package c3.w2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Toposort
{
	private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj)
	{
		boolean used[] = new boolean[adj.length];
		LinkedList<Integer> order = new LinkedList<>();
		for (int i = 0; i < adj.length; i++)
		{
			if (!used[i])
			{
				dfs(adj, used, order, i);
			}
		}
		return new ArrayList<>(order);
	}

	private static void dfs(ArrayList<Integer>[] adj, boolean[] used, LinkedList<Integer> order, int v)
	{
		used[v] = true;
		for (int cv : adj[v])
		{
			if (!used[cv])
			{
				dfs(adj, used, order, cv);
			}
		}
		order.addFirst(v);
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++)
		{
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++)
		{
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
		}
		ArrayList<Integer> order = toposort(adj);
		for (int x : order)
		{
			System.out.print((x + 1) + " ");
		}
	}
}

