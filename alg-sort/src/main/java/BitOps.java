import static java.lang.Long.toBinaryString;
import static java.lang.String.format;

public class BitOps
{
	public static void main(String[] args)
	{
		System.out.println(counterGame(1560834904));
		System.out.println(counterGame(1768820483));
		System.out.println(counterGame(1533726144));
		System.out.println(counterGame(1620434450));
		System.out.println(counterGame(1463674015));
//		System.out.println(Integer.toBinaryString(53));
//		System.out.println(Integer.toBinaryString(53>>1));
//		System.out.println(Integer.toBinaryString(53>>2));
//		System.out.println(Integer.toBinaryString(53>>4));
//		System.out.println(Integer.toBinaryString(53>>8));
//		System.out.println(Integer.toBinaryString(53>>16));
	}

	static long prevPower(long x)
	{
		x = x | (x >> 1);
		x = x | (x >> 2);
		x = x | (x >> 4);
		x = x | (x >> 8);
		x = x | (x >> 16);
		x = x | (x >> 32);
		return x - (x >> 1);
	}

	static String counterGame(long n)
	{
		// Complete this function
		boolean lWins = true;
		while (n > 1)
		{
			if ((n & (n - 1)) == 0)
			{
				n /= 2;
			}
			else
			{
				n -= prevPower(n);

			}
			if (n > 1)
			{
				lWins = !lWins;
			}
		}

		return lWins ? "Loise" : "Richard";
	}

	static int countOnesC(int num)
	{
		int ret = 0;
		int cur = num;
		while (cur != 0)
		{
			cur &= cur - 1;
			ret++;
		}
		return ret;
	}

	private int getMode(int value, int bit)
	{
		return value & bit;
	}
}
