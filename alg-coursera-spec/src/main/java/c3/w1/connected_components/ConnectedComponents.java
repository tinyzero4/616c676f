package c3.w1.connected_components;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents
{
	private static int numberOfComponents(ArrayList<Integer>[] adj)
	{
		boolean marked[] = new boolean[adj.length];
		int count = 0;
		for (int i = 0; i < adj.length; i++)
		{
			if (!marked[i])
			{
				dfs(adj, marked, i);
				count++;
			}
		}
		return count;
	}

	private static void dfs(ArrayList<Integer>[] adj, boolean marked[], int v)
	{
		marked[v] = true;
		for (Integer cv : adj[v])
		{
			if (!marked[cv])
			{
				dfs(adj, marked, cv);
			}
		}
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
			adj[y - 1].add(x - 1);
		}
		System.out.println(numberOfComponents(adj));
	}
}

