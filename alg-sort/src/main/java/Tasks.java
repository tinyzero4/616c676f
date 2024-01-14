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

	public static String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length ==0) return "";

		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() -1);
				if (prefix.isEmpty()) return "";
			}
		}
		return prefix;
	}


	public static void main(String[] args)
	{
//		checkForNonDuplicateItems(new int[]{1, 2, 3, 3, 2, 1, 4, 9, 10, 8, 3, 10});
		System.out.println(longestCommonPrefix(new String[] {"flower","flow","flight"}));
	}
}
