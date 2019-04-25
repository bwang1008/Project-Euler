import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
public class _010 {
	
	public static int L = 1000001;
	public static int[] primes;
	public static long[] sum;
	public static int[] index;

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean[] comp = new boolean[L];
		for(int i = 2; i <= Math.sqrt(L); i++)
			for(int j = i*i; j < comp.length; j += i)
				comp[j] = true;
		
		int count = 0;
		for(int i = 2; i < L; i++)
			if(!comp[i]) count++;
		
		primes = new int[count];
		count = 0;
		for(int i = 2; i < L; i++)
			if(!comp[i]) primes[count++] = i;
			
		HashSet<Integer> set = new HashSet<>();
		for(int i : primes)
			set.add(i);
		
		index = new int[L];
		for(int i = 3; i < L; i++)
			index[i] = set.contains(i) ? index[i-1] + 1 : index[i-1];
		
		sum = new long[primes.length];
		sum[0] = 2;
		for(int i = 1; i < sum.length; i++)
			sum[i] = sum[i-1] + primes[i];
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
			out.println(sum[index[in.nextInt()]]);
		
		out.println(sum[sum.length-1]);
		out.close();
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
2
5
10

10
17
*/