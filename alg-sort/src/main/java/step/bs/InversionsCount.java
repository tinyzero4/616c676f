package step.bs;

import static java.util.Arrays.copyOf;

public class InversionsCount
{
	public long mergeSort(int[] arr)
	{
		if (arr.length == 1)
		{
			return 0;
		}

		return doSort(copyOf(arr, arr.length), arr, 0, arr.length - 1);
	}

	private long doSort(int[] aux, int[] arr, int lo, int hi)
	{
		long inversions = 0;
		if (lo >= hi)
		{
			return inversions;
		}

		int mid = lo + (hi - lo) / 2;
		inversions += doSort(aux, arr, lo, mid);
		inversions += doSort(aux, arr, mid + 1, hi);

		return inversions + doMerge(aux, arr, lo, mid, hi);
	}

	private long doMerge(int[] aux, int[] arr, int lo, int mid, int hi)
	{
		long inversions = 0;
		if (arr[mid] <= arr[mid + 1]) return inversions;

		for (int i = lo; i <= hi; i++)
			aux[i] = arr[i];

		int i = lo;
		int j = mid + 1;
		for (int k = lo; k<=hi;k++)
		{
			if (i > mid) arr[k] = aux[j++];
			else if (j > hi) arr[k] = aux[i++];
			else if (aux[i] > aux[j]) {arr[k] = aux[j++];inversions += (mid - i + 1);}
			else arr[k] = aux[i++];
		}
		return inversions;
	}

	public static void main(String[] args)
	{
//		Scanner in = new Scanner(System.in);
//		int n = Integer.parseInt(in.nextLine());
//		int[] values = Arrays.stream(in.nextLine().split(" ")).limit(n)
//				.map(Integer::valueOf).mapToInt(Integer::intValue).toArray();
//		System.out.println(new InversionsCount().mergeSort(values));
		System.out.println(new InversionsCount().mergeSort(new int[] {5, 4, 3, 2, 1}));
		System.out.println(new InversionsCount().mergeSort(new int[] {2, 3, 9, 2, 9}));
	}
}
