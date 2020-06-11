package minimum_loss;

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
	Map<Long, Integer> map = new TreeMap<Long, Integer>();

    // Complete the minimumLoss function below.
    int minimumLoss(long[] price) {
    	int prevIndex = -1;
    	long diff = -1;
    	long min = -1;
    	
    	for (Entry<Long, Integer> e: map.entrySet()) {
    		if (prevIndex != -1) {
	    		diff = price[e.getValue()] - price[prevIndex];
	    		if ((min == -1  || diff < min) &&
	    			    diff > 0 && e.getValue() < prevIndex) {
	    				min = diff;
	    			}
    		}
    		prevIndex = e.getValue();
    	}
    	
    	return (int)min;
    }

    public int solve(ArrayList<String> readLines) {
    	map = new TreeMap<Long, Integer>();
        int n = Integer.parseInt(readLines.get(0));
        String[] priceItems = readLines.get(1).split(" ");
        n = priceItems.length;
        long[] price = new long[n];
        for (int i = 0; i < n; i++) {
            long priceItem = Long.parseLong(priceItems[i]);
            price[i] = priceItem;
            map.put(price[i], i);
        }
        
        int result = minimumLoss(price);
        return result;
    }

}
