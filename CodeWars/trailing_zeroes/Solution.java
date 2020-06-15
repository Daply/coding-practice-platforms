
// Kata: Number of trailing zeros of N! (5 kyu)

public class Solution {

//    Write a program that will calculate the number of trailing 
//    zeros in a factorial of a given number.
//
//    N! = 1 * 2 * 3 * ... * N
//
//    Be careful 1000! has 2568 digits...
//
//    For more info, see: http://mathworld.wolfram.com/Factorial.html
//    Examples
//
//    zeros(6) = 1
//    # 6! = 1 * 2 * 3 * 4 * 5 * 6 = 720 --> 1 trailing zero
//
//    zeros(12) = 2
//    # 12! = 479001600 --> 2 trailing zeros
//
//    Hint: You're not meant to calculate the factorial. 
//    Find another way to find the number of zeros.

    
    public static int zeros(int n) {
        int res = n/5;
        if (res != 0)
            res += zeros(res);
        
        return res;
    }
    
    public static void assertThat(long zeroes1, long zeroes2) {
        if (zeroes1 == zeroes2)
            System.out.println("Test passed");
        else 
            System.out.println("Test failed");
    }
    
    public static void main(String[] args) {
        assertThat(Solution.zeros(0), 0); 
        assertThat(Solution.zeros(6), 1); 
        assertThat(Solution.zeros(14), 2); 
        assertThat(Solution.zeros(25), 6);
        assertThat(Solution.zeros(531), 131);
    }
}
