
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

// Kata: Getting along with Integer Partitions (4 kyu)

public class IntPart {
 
//  From wikipedia https://en.wikipedia.org/wiki/Partition_(number_theory)
//
//  In number theory and combinatorics, a partition of a positive integer n, 
//  also called an integer partition, is a way of writing n as a sum of positive integers. 
//  Two sums that differ only in the order of their summands are considered the same partition.
//
//  For example, 4 can be partitioned in five distinct ways:
//
//  4, 3 + 1, 2 + 2, 2 + 1 + 1, 1 + 1 + 1 + 1.
//
//  We can write:
//
//  enum(4) -> [[4],[3,1],[2,2],[2,1,1],[1,1,1,1]] and
//
//  enum(5) -> [[5],[4,1],[3,2],[3,1,1],[2,2,1],[2,1,1,1],[1,1,1,1,1]].
//
//  The number of parts in a partition grows very fast. For n = 50 number of parts is 204226, 
//  for 80 it is 15,796,476 It would be too long to tests answers with arrays of such size. 
//  So our task is the following:
//
//  1 - n being given (n integer, 1 <= n <= 50) calculate enum(n) ie the partition of n. 
//  We will obtain something like that:
//  enum(n) -> [[n],[n-1,1],[n-2,2],...,[1,1,...,1]] 
//  (order of array and sub-arrays doesn't matter). 
//  This part is not tested.
//
//  2 - For each sub-array of enum(n) calculate its product. 
//  If n = 5 we'll obtain after removing duplicates and sorting:
//
//  prod(5) -> [1,2,3,4,5,6]
//
//  prod(8) -> [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 16, 18]
//
//  If n = 40 prod(n) has a length of 2699 hence the tests will not verify such arrays. 
//  Instead our task number 3 is:
//
//  3 - return the range, the average and the median of prod(n) in the following form 
//  (example for n = 5):
//
//  "Range: 5 Average: 3.50 Median: 3.50"
//
//  Range is an integer, Average and Median are float numbers rounded to two decimal places 
//  (".2f" in some languages).
//
//  #Notes: Range : difference between the highest and lowest values.
//
//  Mean or Average : To calculate mean, add together all of the numbers in a set 
//  and then divide the sum by the total count of numbers.
//
//  Median : The median is the number separating the higher half of a data sample 
//  from the lower half. (https://en.wikipedia.org/wiki/Median)
//
//  #Hints: Try to optimize your program to avoid timing out.
//
//  Memoization can be helpful but it is not mandatory for being successful.
    
    public static String part(long n) {
        StringBuilder result = new StringBuilder();
        long range = 0;
        double average = 0;
        double median = 0;
        
        // delete duplicates
        Map<Integer, List<Integer>> existElements = new TreeMap<Integer, List<Integer>>();
        List<Integer> listOfNumbers = enumProds(n, existElements).stream().distinct().collect(Collectors.toList());
        Collections.sort(listOfNumbers);
        
        for (Integer num: listOfNumbers) {
            average += num;
        }
        average /= listOfNumbers.size();
        range = listOfNumbers.get(listOfNumbers.size()-1) - listOfNumbers.get(0);
        
        if (listOfNumbers.size()%2 == 0) {
            median = (double)(listOfNumbers.get(listOfNumbers.size()/2-1) + listOfNumbers.get(listOfNumbers.size()/2))/2;
        }
        else {
            median = (double)listOfNumbers.get(listOfNumbers.size()/2);
        }
        
        result.append("Range: ");
        result.append(range);
        result.append(" Average: ");
        DecimalFormat f = new DecimalFormat("##.00");
        result.append(f.format(average));
        result.append(" Median: ");
        result.append(f.format(median));
        
        return result.toString();
    }
    
     // searches at the same time all enums and each list prod
    public static List<Integer> enumProds(long n, Map<Integer, List<Integer>> existElements) {
        
        List<Integer> addends = new ArrayList<Integer>();
        
        if (!existElements.containsKey((int)n)) {
            if (n == 1) {
                addends.add(1);
            }
            else if (n == 2) {
                addends.add(2);
                addends.add(1);
            }
            else {
                long num = n;
                num--;
                
                // add number itself
                addends.add((int) n);
                
                // add n-1 * 1
                addends.add((int) num);
                
                // add number by decrementing
                long newNum = num;
                while (num > 1) {
                    num--; 
                    List<Integer> listOfProds = enumProds(n - num, existElements);
                    for(Integer prevNumber: listOfProds) {
                        newNum = num;
                        newNum*=prevNumber;
                        addends.add((int) newNum);
                    }
                }
            }  
            addends = addends.stream().distinct().collect(Collectors.toList());
            existElements.put((int) n, addends);
        }
        else {
            addends.addAll(existElements.get((int)n));
        }
        return addends;     
    }
    
    public static void assertEquals(String str1, String str2) {
        if (str1.equals(str2))
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    
    public static void main(String args[]) throws Exception {
        // testing
        // test part
        System.out.println(part(2));
        System.out.println(part(3));
        System.out.println(part(4));
        System.out.println(part(5));
        
        // test on exist solutions
        assertEquals("Range: 1 Average: 1.50 Median: 1.50", IntPart.part(2));
        assertEquals("Range: 2 Average: 2.00 Median: 2.00", IntPart.part(3));
        assertEquals("Range: 3 Average: 2.50 Median: 2.50", IntPart.part(4));
        assertEquals("Range: 5 Average: 3.50 Median: 3.50", IntPart.part(5));
    }
}
