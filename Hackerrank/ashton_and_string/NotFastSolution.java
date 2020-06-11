package ashton_and_string;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//Ashton and String 

public class NotFastSolution {

	static char findChar(String s, int k) {
		Set<String> subs = findSubstrings(s);
		String res = subs.parallelStream()
				.reduce((s1, s2) -> s1 + s2)
				.get();
		return res.charAt(k - 1);
	}
	
	static Set<String> findSubstrings(String s) {
		Set<String> subs = new TreeSet<String>();
		getSubstrings(subs, s);
		return subs;
	}
	
    static Set<String> getSubstrings(Set<String> subs, String s) {
    	subs.add(s);
        String s1 = "";
        String s2 = "";
    	for (int i = s.length(); i >= 0; i--) {
    		s1 = s.substring(0, i);
    		s2 = s.substring(i, s.length());
    		if (!s.equals(s1) && !s1.isEmpty()) {
    			subs.add(s1);
    			if (!subs.contains(s2) && !s2.isEmpty()) {
    				getSubstrings(subs, s2);
    			}
    		}
    	}
    	
    	return subs;
    }
    
    static char ashtonString(String s, int k) {
		return findChar(s, k);
    }

    public static void main(String[] args) throws IOException {
    	System.out.println(findSubstrings("dbac"));
    	System.out.println(ashtonString("dbac", 5));
    }
    
}
