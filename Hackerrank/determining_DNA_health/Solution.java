package determining_DNA_health;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] genes = new String[n];

        String[] genesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String genesItem = genesItems[i];
            genes[i] = genesItem;
        }

        int[] health = new int[n];

        String[] healthItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int healthItem = Integer.parseInt(healthItems[i]);
            health[i] = healthItem;
        }

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int min = -1;
        int max = -1;
        for (int sItr = 0; sItr < s; sItr++) {
            String[] firstLastd = scanner.nextLine().split(" ");

            int first = Integer.parseInt(firstLastd[0]);

            int last = Integer.parseInt(firstLastd[1]);

            String d = firstLastd[2];
            
            int totalHealth = 0;
            int fromIndex = 0;
            for (int i = first; i <= last; i++) {
            	fromIndex = 0;
            	while (fromIndex != -1) {
            		fromIndex = d.indexOf(genes[i], fromIndex);
            		if (fromIndex != -1) {
            			fromIndex++;
            			totalHealth += health[i];
            		}
            	}
            }
            if (totalHealth < min || min == -1) {
            	min = totalHealth;
            }
            if (totalHealth > max || max == -1) {
            	max = totalHealth;
            }
        }
        
        System.out.println(min + " " + max);
        scanner.close();
    }
}
