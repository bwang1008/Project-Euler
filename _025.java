import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
public class _025 {
	
	public static int[] len = new int[23930];
	public static double[] log = new double[len.length];
	public static int[] index = new int[5001];

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		double a = 1;
		double b = 1;
		log[1] = log[2] = 1;
		for(int i = 3; i <= 20; i++)
		{
			double next = a+b;
			a = b;
			b = next;
			log[i] = 1 + Math.log10(b);
		}
		
		double d = Math.log10((1+Math.sqrt(5))/2);
		for(int i = 21; i < log.length; i++)
			log[i] = log[i-1] + d;
		
		for(int i = 1; i < len.length; i++)
			len[i] = (int) Math.floor(log[i]);
		
		int j = 2;
		for(int i = 2; i < len.length && j < index.length; i++)
			if(len[i] != len[i-1])
				index[j++] = i;
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
			out.println(index[in.nextInt()]);
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
3
4

12
17
*/