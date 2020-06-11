package time_conversion;

import java.io.*;

// Time Conversion
// Easy
// 15

// Solved

public class Solution {

    static String timeConversion(String s) {
    	StringBuffer sb = new StringBuffer(s);
    	int hour = Integer.parseInt(sb.substring(0, 2));
    	boolean midnight = sb.substring(s.length() - 2, s.length()).equals("AM") ? true : false;
    	
    	if (midnight) {
    		if (hour == 12) {
    			sb.replace(0, 2, "00");
    		}
    	}
    	else {
    		if (hour != 12) {
    			sb.replace(0, 2, String.valueOf(hour+12));
    		}
    	}
    	return sb.substring(0, s.length()-2);
    }
    
    static void testCases() {
    	boolean error = false;
    	if (timeConversion("12:00:01AM").equals("00:00:01")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("11:00:01PM").equals("23:00:01")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("11:00:01PM").equals("23:00:01")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("11:00:01AM").equals("11:00:01")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("12:05:45PM").equals("12:05:45")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("01:05:45PM").equals("13:05:45")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (timeConversion("11:59:59PM").equals("23:59:59")) {
    		System.out.println("Test passed");
    	}
    	else {
    		System.out.println("Test failed");
    		error = true;
    	}
    	if (!error) {
    		System.out.println("All tests passed");
    	}
    }

    public static void main(String[] args) throws IOException {
    	testCases();
    }
}

