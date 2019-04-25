import java.util.*;
import java.math.BigInteger;
public class _206 {
	
	public static int n;
	
	public static int[] digits;
	public static char[] c;
	
	public static int[] backwards;
	
	public static long count;
	public static long start;
	public static int last;
	public static int last3;
	
	public static long[] x;
	public static long[] y;
	public static int index = 0;
	
	public static int[] array;
	public static int[] array2;
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer>[] squareEnds = new ArrayList[1000];
	
	public static void main3(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		digits = new int[2*n-1];
		for(int i = 0; i < n; i++)
			digits[2*i] = in.nextInt();
		
		backwards = new int[digits.length];
		for(int i = 0; i < digits.length; i++)
			backwards[i] = digits[digits.length-i-1];
		
		in.close();
	//	System.out.println(Arrays.toString(backwards));
		start = System.currentTimeMillis();
		
		long result = find3();
		
		BigInteger R = BigInteger.valueOf(result);
		System.out.println();
		System.out.println(R.multiply(R));
		System.out.println(correct(R.multiply(R)));
		System.out.println(Arrays.toString(backwards));
		
		System.out.println(result);
		System.out.println(System.currentTimeMillis()-start + " milliseconds");
	}
	
	public static long find3() {
		Queue<String> q = new LinkedList<>();
		
		for(int i = 0; i < 10; i++)
			if(i*i % 10 == digits[digits.length-1])
				q.add(String.valueOf(i));
		
		while(!q.isEmpty())
		{
//			System.out.println("q = " + q);
			System.out.println(q.size());
			
			String g = q.poll();

			int place = g.length()/2 + 1;
			for(int i = 0; i < 100; i++)
			{
				String g2 = String.format("%02d", i) + g;
				long value = Long.parseLong(g2);
				
	//			System.out.println("g2 = " + g2);
				
				BigInteger v = BigInteger.valueOf(value);
				BigInteger s = v.multiply(v);
	//			System.out.println("s = " + s);
				
				int right = correct(s);
				if(right >= place+1)
				{
					if(right == n)
						return value;
					
	//				if(value % 1000 == 234)
	//				System.out.println("valueHERE = " + value + " : " + s + " : " + right);
	//				System.out.println("place = " + place);
	//				System.out.println("s = " + s);
					q.add(g2);
				}
				
			}
		}
		
		return 0;
	}
	
	public static int correct(BigInteger b) {
		StringBuilder sb = new StringBuilder().append(b.toString()).reverse();
		
		if(sb.length() > backwards.length)
			return 0;
		
		while(sb.length() < backwards.length)
			sb.append(0);
		
		String g = sb.toString();
		
//		System.out.println("g = " + g);
		for(int i = 0; i <= backwards.length / 2; i++)
			if(backwards[2*i] != (g.charAt(2*i) - '0'))
				return i;
		
		return (backwards.length+1)/2;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		
		digits = new int[2*n-1];
		for(int i = 0; i < n; i++)
			digits[2*i] = in.nextInt();
		
		in.close();
		
		start = System.currentTimeMillis();
		
		last = digits[digits.length-1];
		last3 = digits[digits.length-5] * 100 + digits[digits.length-3] * 10 + last;
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < digits.length; i++)
			sb.append(digits[i]);
		
		c = sb.toString().toCharArray();
		
		x = new long[1000000];
		y = new long[1000000];
		
		array = new int[digits.length];
		array2 = new int[digits.length];
		
		Arrays.fill(array2, 9);
		for(int i = 0; i < array.length; i += 2)
			array2[i] = array[i] = digits[i];
		
		getRanges(0);
		
		int[] temp = new int[100000];
		for(long i = 0; i < temp.length; i++)
			temp[(int) i] = (int) ((i*i) % temp.length);
		
		for(int i = 0; i < squareEnds.length; i++)
			squareEnds[i] = new ArrayList<>();
		
		for(int i = 0; i < temp.length; i++)
		{
			int num = (temp[i]/10000) * 100 + ((temp[i] % 1000) / 100) * 10 + temp[i] % 10;
			squareEnds[num].add(i);
		}
		
//		System.out.println("last3 = " + last3 + " size = " + squareEnds[last3].size());
		
//		for(int i = 0; i < 100; i++)
//			System.out.println(x[i] + " " + y[i]);
		
		System.out.println(System.currentTimeMillis() - start + " milliseconds");
//		start = System.currentTimeMillis();
		
		long result = find2();
				
		System.out.println(result);
		BigInteger b = BigInteger.valueOf(result);
		System.out.println(b.multiply(b));
//		System.out.println("count = " + count);
		System.out.println(System.currentTimeMillis() - start + " milliseconds");
		
		System.out.println("\n");
		long c = 2341234L;
		long c2 = c*c;
		System.out.println(c2);
		c = 12341234L;
		c2 = c*c;
		System.out.println(c2);
		
	}
	
	public static void getRanges(int level) {
		for(int i = 0; i < 10; i++)
		{
			array2[2*level+1] = array[2*level+1] = i;
			if(level < (n-9)/2)
			{
				getRanges(level+1);
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				for(int j = 0; j < array.length; j++)
				{
					sb.append(array[j]);
					sb2.append(array2[j]);
				}
				
				x[index] = sqrt(new BigInteger(sb.toString())).longValue();
				y[index] = sqrt(new BigInteger(sb2.toString())).longValue();

				index++;
			}
		}
	}
	
	public static long find2() {
		System.out.println("index = " + index);
		System.out.println("j: " + squareEnds[last3].size());
		System.out.println("Difference? :" + (y[0] - x[0]));
		for(int i = index-1; i >= 0; i--)
		{
			long a = x[i];
			long b = y[i];
			
			for(int j : squareEnds[last3])
			{
				a = (a/100000) * 100000 + j;
				
				for(long k = a; k <= b; k += 100000)
				{	
					BigInteger K = BigInteger.valueOf(k);
					BigInteger s = K.multiply(K);
					
					if(equals(s.toString().toCharArray()))
					{
						System.out.println("i = " + i);
						return k;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static long find() {
		for(int i = 0; i < index; i++)
		{
			long a = x[i];
			long b = y[i];
			
			int r = (int) (a % 10);
			while(r*r % 10 != last)
			{
				a++;
				r++;
			}
			
			for(long j = a; j <= b; j += 10)
			{
				BigInteger J = BigInteger.valueOf(j);
				BigInteger s = J.multiply(J);
				
				if(equals(s.toString().toCharArray()))
					return j;
			}
			
			if(last % 5 == 0) continue;
			
			a++;
			r++;
			while(r*r % 10 != last)
			{
				a++;
				r++;
			}
			
			for(long j = a; j <= b; j += 10)
			{
				BigInteger J = BigInteger.valueOf(j);
				BigInteger s = J.multiply(J);
				
				if(equals(s.toString().toCharArray()))
					return j;
			}
			
		}
		
		return 0;
	}
	
	public static long getValues(int level) {
		for(int i = 0; i < 10; i++)
		{
			digits[2*level + 1] = i;
			if(level < (n-5)/2)
			{
				long result = getValues(level+1);
				if(result != 0)
				{
					return result;
				}
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < digits.length; j++)
					sb.append(digits[j]);
				
				BigInteger low = sqrt(new BigInteger(sb.toString()));
				
				int[] digits2 = new int[digits.length];
				Arrays.fill(digits2, 9);
				for(int j = 0; j <= 2*level+1 ; j++)
					digits2[j] = digits[j];
				for(int j = 2*level+2; j < digits2.length; j += 2)
					digits2[j] = digits[j];
				
				sb = new StringBuilder();
				for(int j = 0; j < digits2.length; j++)
					sb.append(digits2[j]);
				
				BigInteger high = sqrt(new BigInteger(sb.toString()));
				
				int last = low.mod(BigInteger.TEN).intValue();
				while((last*last) % 10 != digits[digits.length-1])
				{
					low = low.add(BigInteger.ONE);
					last = (last+1) % 10;
				}
				
				if(low.compareTo(high) > 0) continue;
				
//				count += (high.longValue()-low.longValue());
				count++;
				
				for(long j = low.longValue(); j <= high.longValue(); j += 10)
				{
					BigInteger s = BigInteger.valueOf(j).multiply(BigInteger.valueOf(j));
					if(equals(s.toString().toCharArray()))
					{
						return j;
					}
				}
				
				if(digits[digits.length-1] % 5 == 0) continue;
				
				low = low.add(BigInteger.ONE);
				last = (last+1) % 10;
				while((last*last) % 10 != digits[digits.length-1])
				{
					low = low.add(BigInteger.ONE);
					last = (last+1) % 10;
				}
				
				for(long j = low.longValue(); j <= high.longValue(); j += 10)
				{
					BigInteger s = BigInteger.valueOf(j).multiply(BigInteger.valueOf(j));
					if(equals(s.toString().toCharArray()))
					{
						return j;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static BigInteger sqrt(BigInteger b) {
		double d = Math.sqrt(b.doubleValue());
		return BigInteger.valueOf((long) d);
	}
	
	public static boolean equals(char[] a) {
		if(a[a.length-3] != c[c.length-3]) return false;
		
		for(int i = a.length-5; i >= 0; i -= 2)
			if(a[i] != c[i])
				return false;
		
		return true;
	}

}
/*
4
1 2 3 4

1312

8
1 2 0 0 6 4 7 6

12341234

15
1 4 8 7 1 0 6 7 9 6 4 2 4 4 0

106999426075380

15
5 0 4 5 2 2 9 7 4 9 5 5 6 7 6

223817012831234

14
9 7 3 3 9 0 5 1 5 5 5 6 7 6

30458715496644

14
9 9 9 9 9 9 9 9 2 0 8 3 4 9

31622776601683

15
9 7 3 3 9 0 5 0 0 7 3 6 4 6 9

315870128312343

15
9 3 9 0 6 7 6 3 7 0 3 8 0 5 0

315182329180050

15
9 3 9 0 6 0 0 1 0 9 2 0 4 0 5

315182329184555

15
9 9 9 9 9 9 9 4 9 9 5 7 8 5 9

316227766016837
*/