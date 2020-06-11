package the_maximum_subarray;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.regex.*;

// Minimum loss
// Medium
// 35

// Solved

public class Solution {


    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] arr) {

    	int [] sums = new int[arr.length+1];
    	boolean maxSubArrNotSet = true;
    	int maxSubArrSum = 0;
    	
    	for (int i = 1; i <= arr.length; i++) {
    		if (sums[i-1] + arr[i-1] > arr[i-1])
    			sums[i] = sums[i-1] + arr[i-1];
    		else
    			sums[i] = arr[i-1];
    		if (sums[i] > maxSubArrSum || maxSubArrNotSet) {
    			maxSubArrNotSet = false;
    			maxSubArrSum = sums[i];
    		}
    	}
    	
    	int [] result = new int[2];
    	result[0] = maxSubArrSum;
    	return result;
    }

    public String solve(ArrayList<String> readLines) {
    	StringBuffer result = new StringBuffer();
        int t = Integer.parseInt(readLines.get(0));
        
        for (int tItr = 0; tItr < t; tItr++) {
            int n = Integer.parseInt(readLines.get(tItr+1));

            int[] arr = new int[n];

            String[] arrItems = readLines.get(tItr+2).split(" ");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] res = maxSubarray(arr);
            for (int i = 0; i < 2; i++) {
            	result.append(res[i] + " ");
            }
            
        }
        return result.toString();
    }

}
