import java.util.Scanner;

public class Main
{
	private Integer[] heap;

	private int n;

	private Main(int size)
	{
		heap = new Integer[size];
	}

	public void add(int item)
	{
		heap[++n] = item;
		swim(n);
	}

	public Integer pop()
	{
		int min = heap[1];
		heap[1] = heap[n--];
		sink(1);
		heap[n + 1] = null;
		return min;
	}

	private void exch(int i, int j)
	{
		int item = heap[i];
		heap[i] = heap[j];
		heap[j] = item;
	}

	private void sink(int i)
	{
		while (2 * i <= n)
		{
			int j = 2 * i;
			if (j < n && gt(j + 1, j))
			{
				j = j + 1;
			}

			if (!gt(j, i))
			{
				break;
			}

			exch(i, j);
			i = j;
		}
	}

	private void swim(int i)
	{
		while (i > 1 && gt(i, i / 2))
		{
			exch(i, i / 2);
			i /= 2;
		}
	}

	private boolean less(int i, int j)
	{
		return heap[i].compareTo(heap[j]) < 0;
	}

	private boolean gt(int i, int j)
	{
		return heap[i].compareTo(heap[j]) > 0;
	}

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int size = Integer.parseInt(scanner.nextLine());
		Main heap = new Main(size);
		for (int i = 0; i < size; i++)
		{
			String[] op = scanner.nextLine().split(" ");
			String command = op[0];
			switch (command)
			{
				case "Insert":
					heap.add(Integer.parseInt(op[1]));
					break;
				case "ExtractMax":
					System.out.println(heap.pop());
					break;
				default:
					System.out.println("unknown op");
			}
		}
	}
}
