import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
public class _012 {
	
	public static int L = 41042;
	public static int[] factors;
	public static int[] triangle;
	public static int[] first;
	public static int[] primes;
	public static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		initialize();
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
		{
			int q = in.nextInt();
			for(long i = 1; i < triangle.length; i++)
			{
				if(triangle[(int) i] > q)
				{
					out.println(i*(i+1)/2);
					break;
				}
			}
		}
		
		out.close();
	}
	
	public static void initialize() {
		factors = new int[L];
		boolean[] composite = new boolean[L];
		for(int i = 2; i <= Math.sqrt(L); i++)
			for(int j = i*i; j < composite.length; j += i)
				composite[j] = true;
		
		int count = 1;
		for(int i = 3; i < composite.length; i += 2)
			if(!composite[i])
				count++;
		
		primes = new int[count];
		count = 1;
		primes[0] = 2;
		for(int i = 3; i < composite.length; i += 2)
			if(!composite[i])
				primes[count++] = i;
		
		for(int i : primes)
			set.add(i);
		factors[1] = 1;
		for(int i = 2; i < factors.length; i++)
			numFactors(i);
		
		triangle = new int[L];
		triangle[1] = 1;
		for(int i = 2; i < triangle.length; i++)
		{
			if((i & 1) == 0)
				triangle[i] = factors[i/2] * factors[i+1];
			else
				triangle[i] = factors[i] * factors[(i+1)/2];
		}
	}
	
	public static int numFactors(int N) {
		if(factors[N] > 0) return factors[N];
		if(set.contains(N))
			return factors[N] = 2;
		
		int M = N;
		int value = 1;
		for(int i = 0; i <= Math.sqrt(N) && N > 1; i++)
		{
			int count = 1;
			while(N % primes[i] == 0)
			{
				N /= primes[i];
				count++;
			}
			value *= count;
		}
		if(N > 1)
			 value *= 2;
		return factors[M] = value;
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
1
2
3
4

3
6
6
28
*/