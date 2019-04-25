import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
public class _206C {

	public static int[] digits;
	
	public static int index = 0;
	public static long[] x;
	public static long[] y;
	
	public static int[] array;
	public static int[] array2;
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		
		int N = in.nextInt();
		digits = new int[2*N-1];
		for(int i = 0; i < N; i++)
			digits[2*i] = in.nextInt();
		
		x = new long[1000000];
		y = new long[1000000];
		
		array = new int[2*N-1];
		array2 = new int[2*N-1];
		
		Arrays.fill(array2, 9);
		
		for(int i = 0; i < array.length; i += 2)
			array2[i] = array[i] = digits[i];
		
		ArrayList<Integer> list = new ArrayList<>();
		for(long i = 0; i < 100000; i++)
		{
			int s = (int) (i*i % 100000);
			if(s % 10 == digits[digits.length-1] && (s % 1000)/100 == digits[digits.length-3] && (s/10000) == digits[digits.length-5])
				list.add((int) i);
		}
		
		System.out.println("list.size() = " + list.size());
		
		getRanges(0);
		System.out.println("getRanges done");
		
		for(int i = 0; i < index; i++)
		{
			long j = x[i] / 100000;
			long j2 = y[i] / 100000;
			for(long k = j; k <= j2; k++)
			{
				for(int m : list)
				{
					long temp = k*100000 + m;
					BigInteger t = BigInteger.valueOf(temp);
					if(equals(t.multiply(t)))
					{
						System.out.println(temp);
						System.exit(0);
					}
				}
			}
		}
	}
	
	public static void getRanges(int level) {
		for(int i = 0; i < 10; i++)
		{
			array2[2*level+1] = array[2*level+1] = i;
			if(level < 4)
				getRanges(level+1);
			else {
				StringBuilder sb = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				
				for(int j : array)
					sb.append(j);
				
				for(int j : array2)
					sb2.append(j);
				
				x[index] = (long) Math.sqrt(Double.parseDouble(sb.toString()));
				y[index] = (long) Math.sqrt(Double.parseDouble(sb2.toString()));
				
				index++;
			}
		}
	}
	
	public static boolean equals(BigInteger b) {
		String g = b.toString();
		int[] d2 = new int[g.length()];
		for(int i = 0; i < d2.length; i += 2)
		{
			d2[i] = g.charAt(i)-'0';
			if(d2[i] != digits[i]) return false;
		}
		return true;
	}
	
	/**
	 * Source: Matt Fontaine
	 */
	public static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		int read() {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		boolean isEndline(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public String nextLine() {
			int c = read();
			while (isEndline(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndline(c));
			return res.toString();
		}
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