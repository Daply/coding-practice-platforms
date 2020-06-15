
public class Dioph {

    public static String solEquaStr(long n) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (long div1 = 1; div1 <= Math.floor(Math.sqrt(n)); div1++) {
            if (n % div1 == 0) {
                long div2 = n / div1;
                if ((div1 + div2) % 2 == 0 &&
                        Math.abs(div1 - div2) % 4 == 0) {
                    long x = (div1 + div2)/2;
                    long y = Math.abs(div1 - div2)/4;
                    result.append("[" + x + ", "+ y + "], ");
                }
            }
        }
        if (result.length() > 2)
            result.delete(result.length()-2, result.length());
        result.append("]");
        return result.toString();
    }

}