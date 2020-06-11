package non_divisible_subset;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.regex.*;

// Non-Divisible Subset
// Medium
// 20

// Solved

public class Solution {

    // Complete the nonDivisibleSubset function below.
    public int nonDivisibleSubset(int k, int[] S) {
        int [] left = new int[k];
        for (int i = 0; i < S.length; i++) {
        	left[S[i]%k] += 1;
        }
        int max = 0;
        for (int i = 1; i < k/2 + 1; i++) {
        	if (i != k - i) {
        		max += Math.max(left[i], left[k-i]);
        	}
        }
        if (left[0] > 0) 
        	max++;
        if (k % 2 == 0)
        	max++;
        
        return max;
    }
    
    public int solve(ArrayList<String> readLines) {
        String[] nk = readLines.get(0).split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] S = new int[n];

        String[] SItems = readLines.get(1).split(" ");

        for (int i = 0; i < n; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }
        
        int result = nonDivisibleSubset(k, S);
        return result;
    }

}
