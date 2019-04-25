import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.InputMismatchException;
public class _016 {

	public static int[] ans;
	public static HashSet<Integer> set = new HashSet<>();
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int T = in.nextInt();
		int[] q = new int[T];
		for(int i = 0; i < T; i++)
		{
			q[i] = in.nextInt();
			set.add(q[i]);
		}
		
		initialize();
		for(int i = 0; i < T; i++)
			out.println(ans[q[i]]);
		out.close();
	}
	
	public static void initialize() {
		ans = new int[10001];
		BigInteger v = BigInteger.valueOf(2);
		for(int i = 1; i < ans.length; i++)
		{
			if(set.contains(i))
			{
				String g = v.toString();
				for(int j = 0; j < g.length(); j++)
					ans[i] += g.charAt(j)-'0';
			}
			v = v.multiply(BigInteger.valueOf(2));
		}
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
3
3
4
7

8
7
11
*/