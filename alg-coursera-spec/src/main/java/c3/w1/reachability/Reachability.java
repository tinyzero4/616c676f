package c3.w1.reachability;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reachability
{
	private static int reach(ArrayList<Integer>[] adj, int x, int y)
	{
		return checkReachability(x, y, adj, new HashSet<>()) ? 1 : 0;
	}

	private static boolean checkReachability(int v, int y, ArrayList<Integer>[] adj, Set<Integer> processed)
	{
		for (Integer connectedVertex : adj[v])
		{
			if (connectedVertex.equals(y))
			{
				return true;
			}
			if (!processed.contains(connectedVertex))
			{
				processed.add(connectedVertex);
				if (checkReachability(connectedVertex, y, adj, processed)) return true;
			}
		}
		return false;
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
		System.out.println(reach(adj, x, y));
	}
}

