import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution
{
	private int[] twoSumOrdered(int[] numbers, int targetSum)
	{
		if (numbers == null || numbers.length < 2)
		{
			return null;
		}
		for (int i = 0, j = numbers.length - 1; i < j; )
		{
			int sum = numbers[i] + numbers[j];
			if (sum == targetSum)
			{
				return new int[]{i, j};
			}
			else if (sum > targetSum)
			{
				j--;
			}
			else if (sum < targetSum)
			{
				i++;
			}
		}
		return null;
	}

	private int[] twoSum(int[] numbers, int target)
	{
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < numbers.length; i++)
		{
			if (map.containsKey(target - numbers[i]))
			{
				result[1] = i;
				result[0] = map.get(target - numbers[i]);
				return result;
			}
			map.put(numbers[i], i);
		}
		return result;
	}

	public List<List<Integer>> threeSumSorted(int[] nums)
	{
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length < 3)
		{
			return results;
		}

//		if (nums.length == 3 && nums[0] == 0 && nums[1] == nums[0] && nums[2] == nums[1])
//		{
//			results.enqueue(Arrays.asList(0, 0, 0));
//		}
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++)
		{

			if (i == 0 || nums[i] > nums[i - 1])
			{
				int a = nums[i];
				for (int j = i + 1, z = nums.length - 1; j < z; )
				{
					final int tmpSum = a + nums[j] + nums[z];
					if (tmpSum == 0)
					{
						results.add(Arrays.asList(a, nums[j], nums[z]));
						j++;
						z--;
						while (j < z && nums[j] == nums[j - 1])
						{
							j++;
						}

						while (j < z && nums[z] == nums[z + 1])
						{
							z--;
						}
					}
					else if (tmpSum > 0)
					{
						z--;
					}
					else if (tmpSum < 0)
					{
						j++;
					}
				}
			}
		}

		return results;
	}

	public int singleNumber(int[] nums)
	{
		int num = 0;
		for (int i = 0; i < nums.length; i++)
		{
			num ^= nums[i];
		}
		return num;
	}

	public int myAtoi(String str)
	{
		if (str == null || str.isEmpty())
		{
			return 0;
		}

		int index = 0;

		while (str.charAt(index) == ' ')
			index++;

		int sign = 1;

		final char startChar = str.charAt(index);
		if (startChar == '-')
		{
			sign = -1;
			index++;
		}
		else if (startChar == '+')
		{
			index++;
		}

		int result = 0;
		int maxValue = Integer.MAX_VALUE / 10;
		int maxValueRem = 7;
		int maxNegValueRem = maxValueRem + 1;

		for (int i = index; i < str.length(); i++)
		{
			final char c = str.charAt(i);
			if (c >= '0' && c <= '9')
			{
				int digit = c - '0';
				if (sign == 1 && (result > maxValue || (result == maxValue && maxValueRem < digit)))
				{
					return Integer.MAX_VALUE;
				}
				else if (sign == -1 && (result > maxValue || (result == maxValue && maxNegValueRem < digit)))
				{
					return Integer.MIN_VALUE;
				}
				result = result * 10 + digit;
			}
			else
			{
				break;
			}
		}

		return sign * result;
	}

	public boolean isAnagram(String s, String t)
	{
		if (Objects.equals(s, t))
		{
			return true;
		}

		final int length = s.length();
		if (length != t.length())
		{
			return false;
		}
		char[] sa = s.toCharArray();
		char[] ta = t.toCharArray();
		int[] values = new int['z' + 1];
		for (int i = 0; i < length; i++)
		{
			values[sa[i]]++;
			values[ta[i]]--;
		}

		for (int value : values)
		{
			if (value != 0)
			{
				return false;
			}
		}

		return true;
//
//		final char[] sa = s.toCharArray();
//		Arrays.sort(sa);
//		final char[] ta = t.toCharArray();
//		Arrays.sort(ta);
//		return Arrays.equals(sa, ta);
	}


	public String reverseString(String s)
	{
		char[] sa = s.toCharArray();
		int limit = sa.length / 2;
		for (int i = 0; i < limit; i++)
		{
			final int last = sa.length - i - 1;
			char tmp = sa[last];
			sa[last] = sa[i];
			sa[i] = tmp;
		}
		return new String(sa);
	}


	public static void main(String[] args)
	{
//		Solution s = new Solution();
//		System.out.println(Arrays.toString(s.twoSum(new int[]{3, 2, 4}, 6)));

//		System.out.println(s.reverseString("hello"));
//		System.out.println(s.reverseString("my world is not enough"));
//		System.out.println(Arrays.toString(s.twoSumOrdered(new int[]{2, 3, 4, 6, 6, 7, 711, 15}, 13)));
//		System.out.println(Arrays.toString(s.twoSumOrdered(new int[]{2, 3, 4, 6, 6, 7, 711, 15}, 5)));
//		System.out.println(s.isAnagram("zlap", "kcqx"));
//		System.out.println(s.threeSumSorted(new int[]{-25, -10, -7, -3, 2, 4, 8, 10, -2, 8, -6, -2, -2, 4}));
//		System.out.println(s.threeSumSorted(new int[]{0, 0, 0}));
//		System.out.println(s.threeSumSorted(new int[]{0, 0, 0, 0, 0}));
//		System.out.println(s.myAtoi("-234"));
//		System.out.println(s.myAtoi("+1"));
//		System.out.println(s.myAtoi("0"));
//		System.out.println(s.myAtoi("-0"));
//		System.out.println(s.myAtoi("   -cv01233 "));
//		System.out.println(s.myAtoi("+01"));
//		System.out.println(s.myAtoi("15600"));
//		System.out.println(s.myAtoi("2,147,483,647"));
//		System.out.println(s.myAtoi("2,147,483,646"));
//		System.out.println(s.myAtoi("2,147,483,847"));
//		System.out.println(s.myAtoi("-2,147,483,647"));
//		System.out.println(s.myAtoi("-2,147,483,648"));
//		System.out.println(s.myAtoi("-2,147,483,649"));
//		System.out.println(s.myAtoi("-2,147,483,642"));
//		System.out.println(s.myAtoi("-2147483647"));
//		System.out.println(s.singleNumber(new int[]{1, 2, 1, 2, 3}));
//		System.out.println(s.singleNumber(new int[]{1, 2, 1, 2, -5}));

//		ExecutorService service = Executors.newFixedThreadPool(2);
//		Lock lock = new ReentrantLock();
//		Condition a = lock.newCondition();
//		Condition b = lock.newCondition();
//		service.submit(() ->
//		{
//			try
//			{
//				while (!Thread.currentThread().isInterrupted())
//				{
//					lock.lock();
//					System.out.println("1");
//					b.signalAll();
//					a.await();
//				}
//			}
//			catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//			finally
//			{
//				lock.unlock();
//			}
//		});
//
//		service.submit(() ->
//		{
//			try
//			{
//				while (!Thread.currentThread().isInterrupted())
//				{
//					lock.lock();
//					System.out.println("2");
//					a.signalAll();
//					b.await();
//				}
//			}
//			catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
//			finally
//			{
//				lock.unlock();
//			}
//		});
//
//		try
//		{
//			TimeUnit.SECONDS.sleep(30);
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		service.shutdown();

		System.out.println(flipBit(0, 1));
		System.out.println(Integer.toBinaryString((1 << 31) -1));
		System.out.println(Integer.toBinaryString(5));
		System.out.println(Integer.toBinaryString(~5));
		System.out.println(~5);
	}

	public static int flipBit(int value, int bitIndex) {
		return value ^ (1 << (bitIndex -1 ));
	}

	public boolean isValid(String s)
	{
		if (s == null || s.isEmpty())
		{
			return true;
		}

		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray())
		{
			if (c == '(')
			{
				stack.push(')');
			}
			else if (c == '{')
			{
				stack.push('}');
			}
			else if (c == '[')
			{
				stack.push(']');
			}
			else if (stack.isEmpty() || stack.pop() != c)
			{
				return false;
			}
		}
		return stack.isEmpty();
	}


}
