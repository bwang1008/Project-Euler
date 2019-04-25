import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
public class _009 {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int T = in.nextInt();
		for(int i = 0; i < T; i++)
			out.println(ans(in.nextInt()));
		
		out.close();
	}
	
	public static long ans(int N) {
		long value = -1;
		
		for(int i = 1; i <= Math.sqrt(N); i++)
		{
			if(N % i != 0) continue;
			value = Math.max(ans2(N/i) * i * i * i, value);
			value = Math.max(ans2(i) * (N/i) * (N/i) * (N/i), value);
		}
		
		return value;
	}
	
	public static long ans2(int N) {
		long value = -1;
		
		for(long p = 1; p < N/2; p++)
		{
			if(N % (2*p) != 0) continue;
			long q = (N / (2*p)) - p;
			if(p > q && q > 0)
				value = Math.max((p*p*p*p-q*q*q*q) * 2*p*q, value);
		}
		
		return value;
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
12
4

60
-1

4
12
4
30
36

60
-1
780
1620
*/