import edu.princeton.cs.algs4.StdOut;

public class ShellSort<T extends Comparable<T>>
		implements SortAlgorithm<T>
{
	@Override
	public T[] sort(T[] items)
	{
		int n = items.length;
		int h = 1;
		while (h < n/3)
		{
			h = 3 * h + 1;
		}
		while (h >= 1)
		{
			for (int i = h; i < n; i ++ )
			{
				for (int j = i; j >= h && less(items[j], items[j - h]); j -= h)
				{
					exch(items, j, j - h);
				}
			}
			h = h / 3;
		}
		StdOut.println(isSorted(items));
		return items;
	}

	public static void main(String[] args)
	{
		ShellSort<Integer> sort = new ShellSort<>();
		sort.show(sort.sort(new Integer[] {5,2222,33,1, 7, 41, 12}));
	}
}
