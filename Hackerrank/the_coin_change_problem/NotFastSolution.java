package the_coin_change_problem;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NotFastSolution {

	static Set<Long> coins = new HashSet<Long>();
	static long count = 0;
	static long[] saved = null;
	
    // Complete the getWays function below.
    static void waysCount(long n, long[] c) {

    	long coin = 0;
    	for (int i = 0; i < c.length; i++) {
    		coin = n - c[i];
    		if (coins.contains(coin) && saved[(int) coin] != 1) {
    			count++;
    			saved[(int) coin] = 1;
    		}
    		if (coin > 0)
    			waysCount(coin, c);
    	}
    }
	
    // Complete the getWays function below.
    static long getWays(long n, long[] c) {
    	waysCount(n, c);
    	count++;
    	return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        long[] c = new long[m];

        String[] cItems = scanner.nextLine().split(" ");
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long max = 0;
        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cItems[i]);
            if (cItem > max) {
            	max = cItem;
            }
            c[i] = cItem;
            coins.add(cItem);
        }
        saved = new long[(int) max + 1];
        
        long ways = getWays(n, c);
        System.out.println(ways);
//        bufferedWriter.write((int) ways);
//        bufferedWriter.write(String.valueOf(ways));
//        bufferedWriter.newLine();
//        bufferedWriter.close();

        scanner.close();
    }
}
