import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
public class _019 {
	
	public static int[] adjust = {4, 0, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2};
	public static int[] adLeap = {3, 6, 0, 3, 5, 1, 3, 6, 2, 4, 0, 2};
	
	public static void main(String[] args) {
		FastScanner in = new FastScanner(System.in);
		
		int T = in.nextInt();
		for(int t = 0; t < T; t++)
		{
			long y1 = in.nextLong();
			int m1 = in.nextInt();
			int d1 = in.nextInt();
			long y2 = in.nextLong();
			int m2 = in.nextInt();
			in.nextInt();
			
			int count = 0;
			if(d1 > 1) m1++;
			while(y1 < y2)
			{
				for(int i = m1; i <= 12; i++)
					if(doomsday(i,1,y1) == 0) 
						count++;
				
				m1 = 1;
				y1++;
			}
			for(int i = m1; i <= m2; i++)
				if(doomsday(i,1,y2) == 0)
					count++;
			
			System.out.println(count);
		}

	}
	
	public static int doomsday(int month, int day, long year) {
		if(month <= 0 || month > 12 || day < 0 || day > 31 || year <= 0) return -1;
		
		boolean b = isLeap(year);
		
		if(month == 2 && !b && day > 28) return -1;
		if(month == 2 && b && day > 29) return -1;
		if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) return -1;
		
		int ddG = (int) (2 + year % 7 + (year / 4) % 7 - (year / 100) % 7 + (year / 400) % 7) % 7;
		int ddJ = (int) (year % 7 + (year / 4) % 7) % 7;
		int dd = 0;
		if(year == 1582)
		{
			if(month == 10)
			{
				if(5 <= day && day <= 14) return -1;
				else dd = (day < 5) ? ddJ : ddG;
			}
			else dd = (month < 10) ? ddJ : ddG;
		}
		else dd = (year < 1582) ? ddJ : ddG;
		
		return (dd + (b ? adLeap[month-1] : adjust[month-1]) + day) % 7;
	}
	
	public static boolean isLeap(long year) {
		if(year % 400 == 0) return true;
		if(year % 100 == 0) return false;
		return year % 4 == 0;
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
1900 1 1
1910 1 1
2000 1 1
2020 1 1

18
35
*/