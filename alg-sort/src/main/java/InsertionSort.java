import edu.princeton.cs.algs4.StdOut;

public class InsertionSort<T extends Comparable<T>>
		implements SortAlgorithm<T>
{
	@Override
	public T[] sort(T[] items)
	{
		int n = items.length;
		for (int i = 0; i < n; i++)
		{
			for (int j = i; j > 0 && less(items[j], items[j - 1]); j--)
			{
				exch(items, j, j - 1);
			}
		}
		StdOut.println(isSorted(items));
		return items;
	}

	public static void main(String[] args)
	{
		InsertionSort<Integer> sort = new InsertionSort<>();
		sort.show(sort.sort(new Integer[] {5,2222,33,1, 7, 41, 12}));
	}
}
