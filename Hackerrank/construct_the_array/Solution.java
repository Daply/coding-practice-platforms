package construct_the_array;

import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the countArray function below.
    static long countArray(int n, int k, int x) {
        // Return the number of ways to fill in the array.

    	int [] a = new int[n];
    	a[0] = 1;
    	a[n-1] = x;
    	
    	for (int i = 1; i < n - 1; i++) {
    		a[i] = a[i-1] + a[i+1];
    	}
    	return 0;
    }

    public long solve(ArrayList<String> readLines) {
        String[] nkx = readLines.get(0).split(" ");

        int n = Integer.parseInt(nkx[0]);

        int k = Integer.parseInt(nkx[1]);

        int x = Integer.parseInt(nkx[2]);
        
        long result = countArray(n, k, x);
        
        return result;
    }
}
