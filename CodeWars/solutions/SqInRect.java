package code_wars;
import java.util.ArrayList;
import java.util.List;

// Kata: Rectangle into Squares (5 kyu)

public class SqInRect {
  
//    Description:
//
//    The drawing below gives an idea of how to cut a given "true" rectangle into squares 
//    ("true" rectangle meaning that the two dimensions are different).
//    
//    Can you translate this drawing into an algorithm?
//
//    You will be given two dimensions
//
//    a positive integer length (parameter named lng)
//    a positive integer width (parameter named wdth)
//
//    You will return an array or a string (depending on the language; 
//    Shell bash and Fortran return a string) with the size of each of the squares.
//
//    sqInRect(5, 3) should return [3, 2, 1, 1]
//    sqInRect(3, 5) should return [3, 2, 1, 1]
                      
                      
    
    public static List<Integer> sqInRect(int lng, int wdth) {
        List<Integer> result = null;
        if (lng != wdth) {
            result = new ArrayList<Integer>();
            int dif = 0;
            result.add(Math.min(lng, wdth));
            while (lng != 0  && wdth != 0) {
                dif = Math.max(lng, wdth) - Math.min(lng, wdth);
                lng = Math.min(lng, wdth);
                wdth = dif;
                if (dif != 0)
                    result.add(Math.min(lng, wdth));
            }
        }
        
        return result;
    }
}
