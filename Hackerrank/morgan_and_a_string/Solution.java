package morgan_and_a_string;

import java.io.*;
import java.util.*;

public class Solution {

    static char getMinChar(char a, char b) {
    	return (char)Math.min((int)a, (int)b);
    }
    
    static String morganAndString1(String a, String b) {
    	String ab = a + b;
    	int n = ab.length();
    	String doubleAb = ab + ab;
    	ArrayList<String> list = new ArrayList<String>();
    	String arr[] = new String[n]; 
    	for (int i = 0; i < n; i++) {
    		//list.add(doubleAb.substring(i, n));
    		arr[i] = doubleAb.substring(i, n);
    	}
    	Arrays.sort(arr);
    	return arr[0];
    }
    
    // This functionr return lexicographically  
    // minimum rotation of str 
    static String minLexRotation(String str)  
    { 
        // Find length of given String 
        int n = str.length(); 
  
        // Create an array of strings  
        // to store all rotations 
        String arr[] = new String[n]; 
  
        // Create a concatenation of  
        // String with itself 
        String concat = str + str; 
  
        // One by one store all rotations  
        // of str in array. A rotation is  
        // obtained by getting a substring of concat 
        for (int i = 0; i < n; i++) 
        { 
            arr[i] = concat.substring(i, i + n); 
        } 
  
        // Sort all rotations 
        Arrays.sort(arr); 
  
        // Return the first rotation  
        // from the sorted array 
        return arr[0]; 
    } 
    
    static String morganAndString(String a, String b) {
    	StringBuffer resultString = new StringBuffer();	
    	int indexJack = 0;
    	int indexDaniel = 0;
    	boolean end = false;
    	char minChar = ' ';
    	while (!end) {
    		if (indexJack >= a.length() && 
    				indexDaniel >= b.length()) {
    			end = true;
    		}
    		else if (indexJack >= a.length()) {
    			char letterD = b.charAt(indexDaniel);
    			resultString.append(letterD);
				indexDaniel++;
    		}
    		else if (indexDaniel >= b.length()) {
    			char letterJ = a.charAt(indexJack);
    			resultString.append(letterJ);
				indexJack++;
    		}
    		else {
	    		char letterJ = a.charAt(indexJack);
	    		char letterD = b.charAt(indexDaniel);
	    		minChar = getMinChar(letterJ, letterD);
	    		if (minChar == letterJ) {
					resultString.append(letterJ);
					indexJack++;
				}
				else if (minChar == letterD) {
					resultString.append(letterD);
					indexDaniel++;
				}
    		}
    	}
    	//resultString = new StringBuffer(minLexRotation(resultString.toString()));
    	return resultString.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = morganAndString(a, b);

            System.out.println(result);
//            bufferedWriter.write(result);
//            bufferedWriter.newLine();
        }

//        bufferedWriter.close();

        scanner.close();
//    	2
//    	JACK
//    	DANIEL
//    	ABACABA
//    	ABACABA
    	
//    	System.out.println(minLexRotation("GEEKSFORGEEKS")); 
//        System.out.println(minLexRotation("GEEKSQUIZ")); 
//        System.out.println(minLexRotation("BCABDADAB")); 
//        
//        System.out.println(minLexRotation("JACKDANIEL")); 
//        System.out.println(minLexRotation("ABACABAABACABA"));
    }
}

