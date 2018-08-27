package c3.w5.clustering;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.DOWN;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.IntStream.range;

public class Clustering
{
	private static BigDecimal clustering(int[] x, int[] y, int k)
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
		while (uf.components > k)
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

		Edge e = queue.poll();
		while (uf.connected(e.v.index, e.w.index))
		{
			e = queue.poll();
		}

		return e.weight.setScale(20, DOWN);
	}

	private static BigDecimal distance(Point p1, Point p2)
	{
		BigDecimal xPart = valueOf(p1.x).subtract(valueOf(p2.x)).pow(2);
		BigDecimal yPart = valueOf(p1.y).subtract(valueOf(p2.y)).pow(2);
		return new BigDecimal(Math.sqrt(xPart.add(yPart).doubleValue()));
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
		int k = scanner.nextInt();
		System.out.println(clustering(x, y, k));
	}


}

