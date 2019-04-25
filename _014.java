import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
public class _014 {

	public static int[] len = new int[5000001];
	public static int[] max = new int[5000001];
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int T = in.nextInt();
		initialize();
		
		
		for(int i = 0; i < T; i++)
			out.println(max[in.nextInt()]);
		out.close();
	}
	
	public static void initialize() {
		len[1] = max[1] = 1;
		for(int i = 2; i < len.length; i++)
			count(i);
		int index = 1;
		for(int i = 2; i < max.length; i++)
		{
			if(len[i] >= len[index])
				index = i;
			max[i] = index;
		}
	}
	
	public static int count(long N) {
		boolean b = N < len.length;
		if(b && len[(int) N] > 0) return len[(int) N];
		int value = ((N & 1) == 0) ? 1 + count(N/2) : 2 + count((3*N+1)/2);
		if(b) len[(int) N] = value;
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
3
10 
15
20

9
9
19
*/