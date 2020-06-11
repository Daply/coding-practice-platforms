package code_wars;

// Kata: Sum of Digits / Digital Root (6 kyu)

public class DRoot {
    
//    In this kata, you must create a digital root function.
//
//    A digital root is the recursive sum of all the digits 
//    in a number. Given n, take the sum of the digits of n. 
//    If that value has two digits, continue reducing in this 
//    way until a single-digit number is produced. 
//    This is only applicable to the natural numbers.
//
//    Here's how it works:
//
//    digital_root(16)
//    => 1 + 6
//    => 7
//
//    digital_root(942)
//    => 9 + 4 + 2
//    => 15 ...
//    => 1 + 5
//    => 6
//
//    digital_root(132189)
//    => 1 + 3 + 2 + 1 + 8 + 9
//    => 24 ...
//    => 2 + 4
//    => 6
    
    public static int digital_root(int n) {
        int res = n%9;
        if (res == 0 && n != 0) {
            res = 9;
        }
        return res;
    }
    
    public static void main(String args[]) throws Exception {
        // testing
        System.out.println(digital_root(56812));
        
    }
}
