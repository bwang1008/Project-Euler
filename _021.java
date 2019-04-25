import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
public class _021 {

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<ArrayList<Integer>> divisors = new ArrayList<>(100001);
		for(int i = 0; i < 100001; i++)
			divisors.add(new ArrayList<>());
		
		for(int i = 1; i < divisors.size(); i++)
			for(int j = i; j < divisors.size(); j += i)
				divisors.get(j).add(i);
		
		int[] sum = new int[100001];
		for(int i = 1; i < sum.length; i++) {
			for(int j : divisors.get(i))
				sum[i] += j;
			sum[i] -= i;
		}
		
		boolean[] isAmicable = new boolean[100001];
		for(int i = 1; i < sum.length; i++)
			if(sum[i] < sum.length && sum[i] != i && sum[sum[i]] == i)
				isAmicable[i] = isAmicable[sum[i]] = true;
		
		int[] sum2 = new int[100002];
		for(int i = 1; i < sum.length; i++)
			sum2[i] = sum2[i-1] + ((isAmicable[i]) ? i : 0);
		
		int T = in.nextInt();
		while(T --> 0) {
			int N = in.nextInt();
			out.println(sum2[N-1]);
		}
		
		out.close();
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
1
300
Sample Output

504
*/