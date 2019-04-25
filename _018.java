import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
public class _018 {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
		{
			int N = in.nextInt();
			int[][] a = new int[N][N];
			int[][] b = new int[N][N];
			
			for(int i = 0; i < N; i++)
				for(int j = 0; j < i+1; j++)
					a[i][j] = in.nextInt();
			
			b[0][0] = a[0][0];
			for(int i = 1; i < N; i++)
			{
				b[i][0] = b[i-1][0] + a[i][0];
				b[i][i] = b[i-1][i-1] + a[i][i];
			}
			
			for(int i = 2; i < N; i++)
				for(int j = 1; j < i; j++)
					b[i][j] = a[i][j] + Math.max(b[i-1][j-1], b[i-1][j]);
			
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++)
				max = Math.max(b[N-1][i], max);
			
			System.out.println(max);
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
1
4
3
7 4
2 4 6
8 5 9 3

23
*/