public class MergeSortBU<T extends Comparable<T>>
		implements SortAlgorithm<T>
{
	@Override
	public T[] sort(T[] items)
	{
		if (items == null || items.length == 0)
		{
			return items;
		}

		Comparable[] aux = new Comparable[items.length];
		int n = items.length;
		for (int sz = 1; sz < n; sz += sz)
		{
			for (int lo = 0; lo < n - sz; lo += sz + sz)
			{
				doMerge(items, (T[]) aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
			}
		}
		return items;
	}

	private void doMerge(T[] items, T[] aux, int lo, int mid, int hi)
	{
		for (int i = lo; i <= hi; i++)
		{
			aux[i] = items[i];
		}

		for (int i = lo, j = mid + 1, k = lo; k <= hi; k++)
		{
			if (i > mid) {
				items[k] = aux[j++];
			} else if (j > hi)
			{
				items[k] = aux[i++];
			} else if (less(aux[i], aux[j]))
			{
				items[k] = aux[i++];
			} else  {
				items[k] = aux[j++];
			}
		}
	}

	public static void main(String[] args)
	{
		MergeSortBU<Integer> sort = new MergeSortBU<>();
//		sort.show(sort.sort(new Integer[]{22, 33, 2, 66, 0, -1, 3, 5, 7, 3, 4, 6}));
		sort.show(sort.sort(new Integer[]{22, 33, 2, 66, 0}));
	}
}
