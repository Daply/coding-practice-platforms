package ashton_and_string;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

// Ashton and String 

public class Solution {
	
	static char findSubstrings(String s, int k) {
		Set<String> subs = new TreeSet<String>();
		char c = getSubstrings(subs, s, k, 0, ' ');
		return c;
	}
	
    static char getSubstrings(Set<String> subs, String s, int k, int lengthOfTotal, char res) {
    	subs.add(s);
    	lengthOfTotal += s.length();
    	if (lengthOfTotal >= k) {
    		res = s.charAt(lengthOfTotal%k);
    		return res;
    	}
        String s1 = "";
        String s2 = "";
    	for (int i = s.length(); i >= 0; i--) {
    		s1 = s.substring(0, i);
    		s2 = s.substring(i, s.length());
    		if (!s.equals(s1) && !s1.isEmpty()) {
    			subs.add(s1);
    			lengthOfTotal += s1.length();
    	    	if (lengthOfTotal >= k) {
    	    		res = s.charAt(lengthOfTotal%k);
    	    		return res;
    	    	}
    			if (!subs.contains(s2) && !s2.isEmpty()) {
    				res = getSubstrings(subs, s2, k, lengthOfTotal, res);
    			}
    		}
    	}
    	
    	return res;
    }
    
    static char ashtonString(String s, int k) {
		return findSubstrings(s, k);
    }

    public static void main(String[] args) throws IOException {
    	// a, ac, b, ba, bac, c, d, db, dba, dbac
    	System.out.println(findSubstrings("dbac", 5));
    	System.out.println(ashtonString("dbac", 8));
    }
    
}
