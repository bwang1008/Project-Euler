import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
public class _022 {

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
		
		boolean[] isAbundant = new boolean[100001];
		isAbundant[0] = true;
		
		int count = 0;
		for(int i = 0; i < sum.length; i++) {
			if(sum[i] > i) {
				isAbundant[i] = true;
				count++;
			}
		}
		
		int[] abund = new int[count+1];
		count = 0;
		for(int i = 0; i < sum.length; i++)
			if(isAbundant[i])
				abund[count++] = i;
		
		Arrays.fill(isAbundant, false);
		
		isAbundant[0] = true;
		for(int i = 0; i < abund.length; i++) {
			for(int j = i; j < abund.length; j++) {
				int a = abund[i];
				int b = abund[j];
				if(a + b >= 28123) break;
				isAbundant[a+b] = true;
				
			}
		}
		
		int T = in.nextInt();
		while(T --> 0) {
			int N = in.nextInt();
			out.println(ans(isAbundant, N) ? "YES" : "NO");
		}
		
		out.close();
	}
	
	static boolean ans(boolean[] isAbundant, int N) {
		if(N >= 28123)
			return true;
		return isAbundant[N];
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
24
49
Sample Output

YES
NO
*/