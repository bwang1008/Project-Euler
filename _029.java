import java.util.HashSet;
import java.util.Scanner;
public class _029 {
    
    public static boolean[][] set;
    public static int[] uniqueRows;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        initialize(N);
        
        long sum = 0;
        boolean[] visited = new boolean[N+1];
        
        for(int i = 2; i*i <= visited.length; i++) {	//past sqrt(visited.length), floor(log) = 1, so no more repeated elements...
            if(visited[i]) continue;
            for(int j = i; j < visited.length; j *= i)	//once done with powers of 2, 2,4,8,16...all done
                visited[j] = true;
            sum += count(i, N);
        }
        
        long N2 = N-1;
        System.out.println(N2*N2 - sum);	//Mostly unique elements, subtract repeated elements
        in.close();
    }
    
    //Whole point is to do it in chunks: do it in powers of 2 first, then 3, then 5,6,7,10,...
    //Example: for powers of 2, 2^12 = 8^4 --> (1)(12) = (3)(4)
    public static void initialize(int N) {
        int log = (int) Math.floor(Math.log(N + 0.001)/Math.log(2));	//Check: 17^6 = 24137569, but log(24137569)/log(6) = 5.9999999...
        set = new boolean[log][N+1];
        
        int R = set.length;
        int C = set[0].length;
        
        uniqueRows = new int[R];	//store total repeated numbers up to row i
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < R; i++) {
            for(int j = 2; j < C; j++)
                set.add((i+1) * j); //1*12 = 3*4
            uniqueRows[i] = (i+1) * (C-2) - set.size(); // = total numbers - number of unique numbers
        }
    }
    
    public static int count(int base, int N) {
        int log = (int) Math.floor(Math.log(N + 0.001) / Math.log(base));
        return uniqueRows[log-1];		//Technically, the base doesn't matter 2^12 = (2^3)^4 === 3^12 = (3^3)^4, so equivalent values just different logs
    }

}
/*
5

15
*/