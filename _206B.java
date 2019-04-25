import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
public class _206B {
	
	public static int[] digits;
	
	public static int index = 0;
	public static long[] x;
	public static long[] y;
	
	public static int[] array;
	public static int[] array2;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int N = Integer.parseInt(in.readLine());
		
		digits = new int[2*N-1];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++)
			digits[2*i] = Integer.parseInt(st.nextToken());
		
		x = new long[1000000];
		y = new long[1000000];
		
		array = new int[2*N-1];
		array2 = new int[2*N-1];
		
		Arrays.fill(array2, 9);
		
		for(int i = 0; i < array.length; i += 2)
			array2[i] = array[i] = digits[i];
		
		System.out.println(Arrays.toString(array));
		
		getRanges(0);
		
		System.out.println("index = " + index);
		for(int i = 0; i < 100; i++)
		{
			System.out.println(x[i] + " : " + y[i]);
			System.out.println("HERE: " + x[i]*x[i] + " : " + y[i] * y[i]);
		}
		
		in.close();
		out.close();
	}
	
	public static void getRanges(int level) {
		for(int i = 0; i < 10; i++)
		{
			array2[2*level+1] = array[2*level+1] = i;
			if(level < 2)
				getRanges(level+1);
			else {
				StringBuilder sb = new StringBuilder();
				StringBuilder sb2 = new StringBuilder();
				
				for(int j : array)
					sb.append(j);
				
				for(int j : array2)
					sb2.append(j);
				
				x[index] = sqrt(new BigInteger(sb.toString()));
				y[index] = sqrt(new BigInteger(sb2.toString()));
				
				index++;
			}
		}
	}
	
	public static long sqrt(BigInteger b) {
		double d = b.doubleValue();
		return (long) Math.sqrt(d);
	}

}
/*
4
1 2 3 4

1312

8
1 2 0 0 6 4 7 6

12341234

15
1 4 8 7 1 0 6 7 9 6 4 2 4 4 0

106999426075380

15
5 0 4 5 2 2 9 7 4 9 5 5 6 7 6

223817012831234

14
9 7 3 3 9 0 5 1 5 5 5 6 7 6

30458715496644

14
9 9 9 9 9 9 9 9 2 0 8 3 4 9

31622776601683

15
9 7 3 3 9 0 5 0 0 7 3 6 4 6 9

315870128312343

15
9 3 9 0 6 7 6 3 7 0 3 8 0 5 0

315182329180050

15
9 3 9 0 6 0 0 1 0 9 2 0 4 0 5

315182329184555

15
9 9 9 9 9 9 9 4 9 9 5 7 8 5 9

316227766016837
*/