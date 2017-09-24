package step.bs;

import java.util.Scanner;

import static java.util.Arrays.stream;

public class Main
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);

		String[] itemsDef = scanner.nextLine().split(" ");
		Integer[] items = stream(itemsDef, 1, itemsDef.length)
				.map(Integer::parseInt)
				.toArray(Integer[]::new);

		stream(scanner.nextLine().split(" "))
				.skip(1)
				.map(Integer::parseInt)
				.map(i -> findIndex(i, items))
				.forEach(System.out::println);
	}

	static int findIndex(int item, Integer[] items)
	{
		int lo = 0;
		int hi = items.length - 1;

		while (lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if (item < items[mid])
			{
				hi = mid - 1;
			}
			else
			{
				if (item <= items[mid])
				{
					return mid + 1;
				}
				lo = mid + 1;
			}
		}
		return -1;
	}
}
