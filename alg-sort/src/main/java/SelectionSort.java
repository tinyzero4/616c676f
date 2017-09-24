import edu.princeton.cs.algs4.StdOut;

public class SelectionSort<T extends Comparable<T>> implements SortAlgorithm<T>
{
	@Override
	public T[] sort(T[] items)
	{
		int n = items.length;
		for (int i = 0; i < n; i++)
		{
			int min = i;
			for (int j = i + 1; j < n; j++)
			{
				if (less(items[j], items[min]))
				{
					min = j;
				}
			}
			exch(items, i, min);
		}

		StdOut.println(isSorted(items));
		return items;
	}

	public static void main(String[] args)
	{
		SelectionSort<Integer> sort = new SelectionSort<>();
		sort.show(sort.sort(new Integer[] {5,2222,33,1, 7, 41, 12}));
	}
}
