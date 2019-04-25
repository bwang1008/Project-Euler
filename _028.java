import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
public class _028 {
	
	static int p = 1_000_000_007;

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		long inv = modInv(3, p);
		System.out.println("inv = " + inv);
		
		//4c^2-4c+1, 4c^2-10c+7, 4c^2-8c+5, 4c^2-6c+3 -> each term 16c^2-28c+16, then -3 -> 1/3 (16x^3 - 18x^2 + 14x - 9), x = (N+1)/2
		
		int T = in.nextInt();
		while(T --> 0) {
			long N = (1 + in.nextLong()) >> 1;
			N %= p;
			long[] pow = new long[4];
			pow[0] = 1L;
			for(int i = 1; i < 4; i++)
				pow[i] = pow[i-1] * N % p;
			
			System.out.println(Arrays.toString(pow));
			
			long prod = (16 * pow[3] - 18 * pow[2] + 14 * pow[1] + p - 9) % p;
			prod = (inv * prod) % p;
			out.println(prod);
		}
		
		out.close();
	}
	
	public static long modInv(int x, int p) {
		int[] a = {x, 1, 0};
		int[] b = {p, 0, 1};
		while(b[0] != 0) {
			int[] c = new int[3];
			int q = a[0] / b[0];
			c[0] = a[0] - q * b[0];
			c[1] = a[1] - q * b[1];
			c[2] = a[2] - q * b[2];
			a = b; b = c;
		}
		
		return a[1];
	}
	
	/**
	 * Source: Matt Fontaine
	 */
	static class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int chars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
		}

		int read() {
			if (chars == -1)
				throw new InputMismatchException();
			if (curChar >= chars) {
				curChar = 0;
				try {
					chars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (chars <= 0)
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
3
5

25
101
*/
