import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution
{

	// Complete the rotLeft function below.
	static int[] rotLeft(int[] a, int d)
	{
		if (d <= 0)
		{
			return a;
		}
		int shift = d % a.length;
		if (shift == 0)
		{
			return a;
		}
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++)
		{
			int index = shift <= i ? i - shift : a.length - shift + i;
			b[index] = a[i];
		}
		return b;
	}

	private static void swap(int i, int j, int[] a)
	{
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args)
			throws IOException
	{
		System.out.println(Arrays.toString(rotLeft(new int[]{1, 2, 3, 4, 5}, 4)));
	}


}
