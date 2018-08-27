package c3.w5.connecting_points;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.IntStream.range;

public class ConnectingPoints
{
	private static BigDecimal minimumDistance(int[] x, int[] y)
	{
		int V = x.length;
		if (V <= 1)
		{
			return BigDecimal.ZERO;
		}

		PriorityQueue<Edge> queue = new PriorityQueue<>(comparing(Edge::getWeight));
		Queue<Edge> mst = new LinkedList<>();

		Point[] points = new Point[V];
		range(0, V).forEach(i -> points[i] = new Point(i, x[i], y[i]));
		stream(points).forEach(v -> stream(points).filter(w -> w.index > v.index).forEach(w -> queue.add(new Edge(v, w))));

		UF uf = new UF(V);
		while (!queue.isEmpty() || mst.size() < V - 1)
		{
			Edge e = queue.poll();
			int v = e.either();
			int w = e.other(v);
			if (!uf.connected(v, w))
			{
				uf.union(v, w);
				mst.add(e);
			}
		}

		return mst.stream().map(Edge::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(20, RoundingMode.DOWN);
	}

	private static BigDecimal minimumDistancePrimLazy(int[] x, int[] y)
	{
		int V = x.length;
		if (V <= 1)
		{
			return BigDecimal.ZERO;
		}

		Graph g = new Graph(V);

		Point[] points = new Point[V];
		range(0, V).forEach(i -> points[i] = new Point(i, x[i], y[i]));
		stream(points).forEach(v -> stream(points).filter(w -> w.index > v.index).forEach(w -> g.addEdge(new Edge(v, w))));

		LazyMST lazyMst = new LazyMST(g);
		return lazyMst.getWeight();
	}

	static class UF
	{

		int[] items;

		int[] size;

		int components;

		public UF(int n)
		{
			items = new int[n];
			size = new int[n];
			IntStream.range(0, n).forEach(i -> {
				items[i] = i;
				size[i] = 1;
			});
			components = n;
		}

		public void union(int p, int q)
		{
			int pRootIndex = find(p);
			int qRootIndex = find(q);

			if (pRootIndex == qRootIndex)
			{
				return;
			}

			if (size[pRootIndex] > size[qRootIndex])
			{
				items[pRootIndex] = qRootIndex;
				size[qRootIndex] += size[pRootIndex];
			}
			else
			{
				items[qRootIndex] = pRootIndex;
				size[pRootIndex] += size[qRootIndex];
			}
			components--;
		}

		public boolean connected(int p, int q)
		{
			return find(p) == find(q);
		}

		public int find(int p)
		{
			int root = p;
			while (root != items[root])
				root = items[root];
			while (p != root)
			{
				int t = items[p];
				items[p] = root;
				p = t;
			}

			return p;
		}

		public int count()
		{
			return components;
		}
	}

	static class LazyMST
	{
		boolean[] marked;
		PriorityQueue<Edge> pq;
		Deque<Edge> mst;
		BigDecimal weight;

		LazyMST(Graph g)
		{
			marked = new boolean[g.edges()];
			pq = new PriorityQueue<>(comparing(Edge::getWeight));
			mst = new LinkedList<>();
			weight = BigDecimal.ZERO.setScale(20, RoundingMode.DOWN);

			visit(g, 0);

			while (!pq.isEmpty())
			{
				Edge e = pq.poll();
				int v = e.either();
				int w = e.other(v);
				if (marked[v] && marked[w])
				{
					continue;
				}
				mst.add(e);

				weight = weight.add(e.weight);

				if (!marked[v])
				{
					visit(g, v);
				}
				if (!marked[w])
				{
					visit(g, w);
				}
			}
		}

		void visit(Graph g, int v)
		{
			marked[v] = true;
			g.adj(v).stream()
					.filter(e -> !marked[e.other(v)])
					.forEach(pq::add);
		}

		Deque<Edge> getMst()
		{
			return mst;
		}

		BigDecimal getWeight()
		{
			return weight.setScale(20, RoundingMode.DOWN);
		}
	}

	private static BigDecimal distance(Point p1, Point p2)
	{
		BigDecimal xPart = valueOf(p1.x).subtract(valueOf(p2.x)).pow(2);
		BigDecimal yPart = valueOf(p1.y).subtract(valueOf(p2.y)).pow(2);
		return new BigDecimal(Math.sqrt(xPart.add(yPart).doubleValue()));
	}

	static class Graph
	{
		int V;
		int E;
		List<Edge>[] adjList;

		Graph(int v)
		{
			this.V = v;
			adjList = new List[v];
			range(0, v).forEach(i -> adjList[i] = new LinkedList<>());
		}

		Graph addEdge(Edge e)
		{
			adjList[e.v.index].add(e);
			adjList[e.w.index].add(e);
			E++;
			return this;
		}

		public List<Edge> adj(int adjTo)
		{
			return adjList[adjTo];
		}

		public int edges()
		{
			return E;
		}
	}

	static class Edge
	{
		Point v;
		Point w;
		BigDecimal weight;

		Edge(Point v, Point w)
		{
			this.v = v;
			this.w = w;
			this.weight = distance(v, w);
		}

		int either()
		{
			return v.index;
		}

		int other(int v)
		{
			if (this.v.index == v)
			{
				return this.w.index;
			}
			if (this.w.index == v)
			{
				return this.v.index;
			}
			throw new IllegalStateException();
		}

		BigDecimal getWeight()
		{
			return weight;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
			{
				return true;
			}
			if (o == null || getClass() != o.getClass())
			{
				return false;
			}
			Edge edge = (Edge) o;
			return Objects.equals(v, edge.v) &&
					Objects.equals(w, edge.w) &&
					Objects.equals(weight, edge.weight);
		}

		@Override
		public int hashCode()
		{

			return Objects.hash(v, w, weight);
		}
	}

	static class Point
	{
		int x;
		int y;
		int index;

		Point(int index, int x, int y)
		{
			this.x = x;
			this.y = y;
			this.index = index;
		}

		@Override
		public boolean equals(Object o)
		{
			if (this == o)
			{
				return true;
			}
			if (o == null || getClass() != o.getClass())
			{
				return false;
			}
			Point point = (Point) o;
			return x == point.x &&
					y == point.y &&
					index == point.index;
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(x, y, index);
		}

		@Override
		public String toString()
		{
			return "Point{x=" + x + ", y=" + y + ", index=" + index + "}";
		}
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++)
		{
			x[i] = scanner.nextInt();
			y[i] = scanner.nextInt();
		}
		System.out.println(minimumDistance(x, y));
	}
}

