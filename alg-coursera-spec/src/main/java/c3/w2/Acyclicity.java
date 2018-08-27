package c3.w2;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.*;

public class Acyclicity
{

	private static int acyclic(ArrayList<Integer>[] adj)
	{
		boolean[] onStack = new boolean[adj.length];
		boolean[] marked = new boolean[adj.length];

		Context context = new Context();
		for (int i = 0; i < adj.length; i++)
		{
			if (!marked[i])
			{
				dfs(adj, onStack, marked, context, i);
			}
		}
		return context.cycle ? 1 : 0;
	}

	static class Context {
		boolean cycle;
	}

	private static void dfs(ArrayList<Integer>[] adj, boolean[] onStack, boolean[] marked, Context context, int v)
	{


		onStack[v] = true;
		marked[v] = true;

		for (int cv : adj[v])
		{
			if (!marked[cv])
			{
				dfs(adj, onStack, marked, context, cv);
			}
			else if (onStack[cv])
			{
				context.cycle = TRUE;
			}
		}
		onStack[v] = false;
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
		System.out.println(acyclic(adj));
	}
}

