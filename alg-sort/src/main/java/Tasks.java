public class Tasks
{
	public static void checkForNonDuplicateItems(int[] items)
	{

		int result = 0;
		for (int item : items)
		{
			result ^= item;
		}

		System.out.println(result);
	}


	public static void main(String[] args)
	{
//		checkForNonDuplicateItems(new int[]{1, 2, 3, 3, 2, 1, 4, 9, 10, 8, 3, 10});
	}
}
