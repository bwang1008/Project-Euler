import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
public class _020 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		HashMap<Integer, Long> map = new HashMap<>(1001);
		
		map.put(0, 1L);
		
		int len = 5000;
		long[] num = new long[len];
		long[] prod = new long[len];
		
		num[len-1] = 1;
		for(int i = 1; i <= 1000; i++) {
			int times = i;
			int shift = 0;
			StringBuilder sb = new StringBuilder();
			while(times > 0) {
				int digit = times % 10;
				for(int j = len-1; j-shift-1 >= 0; j--) {
					long product = num[j] * digit;
					prod[j-shift] += product;
					prod[j-shift-1] += prod[j-shift] / 10;
					prod[j-shift] %= 10;
				}
				times /= 10;
				shift++;
			}
			
			for(int j = len-1; j >= 0; j--)
				sb.append(prod[j]);
			
			long sum = 0;
			for(int j = 0; j < len; j++)
				sum += prod[j];
			map.put(i, sum);
			num = prod;
			prod = new long[len];
		}
		
		int T = in.nextInt();
		while(T --> 0) {
			int N = in.nextInt();
			out.println(map.get(N));
		}
		
		in.close();
		out.close();
	}

}
/*
2
3
6
Sample Output

6
9
*/