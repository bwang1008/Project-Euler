import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
public class _017 {
	
	public static String[] place = {" Trillion "," Billion "," Million "," Thousand ",""};
	public static String[] tens = {"","Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	public static String[] teens = {"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen","Twenty"};
	public static String[] ones = {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};

	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
			out.println(ans(in.nextLong()));
		
		out.close();
	}
	
	public static String ans(long N) {
		if(N == 0) return "Zero";
		int[] a = new int[5];
		for(int i = 4; i >= 0; i--)
		{
			a[i] = (int) (N % 1000);
			N /= 1000;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 5; i++)
		{
			if(a[i] >= 100)
				sb.append(ones[a[i]/100] + " Hundred ");
			
			int b = a[i] % 100;
			if(b < 10) sb.append(ones[b]);
			else if(b < 20) sb.append(teens[b % 10]);
			else sb.append(tens[b / 10] + ((b % 10 > 0) ? " " + ones[b % 10] : ""));
			if(a[i] > 0) sb.append(place[i]);
		}
		
		return sb.toString().trim();
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
10
17

Ten
Seventeen
*/