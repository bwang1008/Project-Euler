import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class _024 {
	
	public static long[] factorial;

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		factorial = new long[14];
		factorial[0] = 1;
		for(int i = 1; i < 14; i++)
			factorial[i] = factorial[i-1] * i;
		
		int T = in.nextInt();
		while(T --> 0) {
			long N = in.nextLong();
			StringBuilder sb = new StringBuilder();
			ArrayList<Character> list = new ArrayList<>();
			for(int i = 0; i < 13; i++)
				list.add((char) (i + 97));
			
			String g = recurse(N-1, sb, list);
			out.println(g);
		}
		
		out.close();
	}
	
	public static String recurse(long N, StringBuilder sb, ArrayList<Character> list) {
		if(list.size() == 0) return sb.toString();
		
		int index = 0;
		long size = factorial[list.size() - 1];
		while(!(index * size <= N && N < (index+1) * size)) index++;
		
		sb.append(list.remove(index));
		N -= size * index;
		recurse(N, sb, list);
		
		return sb.toString();
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
1
2
Sample Output

abcdefghijklm
abcdefghijkml
*/