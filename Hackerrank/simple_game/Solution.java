package simple_game;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

//Simple Game



public class Solution {

    /*
     * Complete the simpleGame function below.
     */
    static int simpleGame(int n, int m, int k) {
		return k;
        /*
         * Write your code here.
         */

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nmk = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int n = Integer.parseInt(nmk[0]);

        int m = Integer.parseInt(nmk[1]);

        int k = Integer.parseInt(nmk[2]);

        int result = simpleGame(n, m, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
