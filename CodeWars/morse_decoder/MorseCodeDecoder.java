
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

//Kata: Decode the Morse code, for real (2 kyu)

// FINISHED 1 and 2 parts, 3 NOT FINISHED
public class MorseCodeDecoder {
    
    public static Map<String, String> MorseCode = null;
    
    public static void createAlphabet() {
        MorseCode = new HashMap<String, String>();
        MorseCode.put(".-", "A");
        MorseCode.put("-...", "B");
        MorseCode.put("-.-.", "C");
        MorseCode.put("-..", "D");
        MorseCode.put(".", "E");
        MorseCode.put("..-.", "F");
        MorseCode.put("--.", "G");
        MorseCode.put("....", "H");
        MorseCode.put("..", "I");
        MorseCode.put(".---", "J");
        MorseCode.put("-.-" ,"K");
        MorseCode.put(".-..", "L");
        MorseCode.put("--", "M");
        MorseCode.put("-.", "N");
        MorseCode.put("---", "O");
        MorseCode.put(".--.", "P");
        MorseCode.put("--.-", "Q");
        MorseCode.put(".-.", "R");
        MorseCode.put("...", "S");
        MorseCode.put("-", "T");
        MorseCode.put("..-", "U");
        MorseCode.put("...-", "V");
        MorseCode.put(".--", "W");
        MorseCode.put("-..-", "X");
        MorseCode.put("-.--", "Y");
        MorseCode.put("--..", "Z");
        
        MorseCode.put(".----", "1");
        MorseCode.put("..---", "2");
        MorseCode.put("...--", "3");
        MorseCode.put("....-", "4");
        MorseCode.put(".....", "5");
        MorseCode.put("-....", "6");
        MorseCode.put("--...", "7");
        MorseCode.put("---..", "8");
        MorseCode.put("----.", "9");
        MorseCode.put("-----", "0");
    }
    
    public static String decodeBitsAdvanced(String bits) {
        StringBuilder result = new StringBuilder();
        String[] bitsLetters = bits.trim().split("0+");
        int size = bitsLetters.length;
        int summ = Arrays.stream(bitsLetters)
        .filter(line -> !line.equals(""))
        .map(String::length)
        .reduce((x, y) -> x + y).get();
        int medianOfOnes = summ / size;
        int medianOfZeroes = 0;
        // check if only one word in time units
        int onesNumber = Arrays.stream(bitsLetters)
                .filter(line -> !line.equals(""))
                .collect(Collectors.toList()).size();

        if (!bits.matches("1+")) {
            summ = Arrays.stream(bitsLetters)
                    .filter(line -> !line.equals(""))
                    .map(String::length)
                    .reduce((x, y) -> x + y).get();
            medianOfZeroes = summ / size;
        }
        Deque<String> tokens = tokenize(bits);      
        while (!tokens.isEmpty()) {
            String token = tokens.pop();
        }
        return result.toString();
    }
    
    public static String decodeBits(String bits) { 
        StringBuilder result = new StringBuilder();
        String[] bitsLetters = bits.trim().split("0+");
        // check if only one word in time units
        int onesNumber = Arrays.stream(bitsLetters)
                .filter(line -> !line.equals(""))
                .collect(Collectors.toList()).size();
        int unitOnesLen = Arrays.stream(bitsLetters)
        .filter(line -> !line.equals(""))
        .map(String::length)
        .min(Integer::compare)
        .get();
        int unitZeroesLen = 0;
        int unit = unitOnesLen;
        if (!bits.matches("1+")) {
            unitZeroesLen = Arrays.stream(bits.trim().split("1+"))
            .filter(line -> !line.equals(""))
            .map(String::length)
            .min(Integer::compare)
            .get();
            unit = Math.min(unitOnesLen, unitZeroesLen);
        }
        Deque<String> tokens = tokenize(bits);      
        while (!tokens.isEmpty()) {
            String token = tokens.pop();
            result.append(decodeLetterBit(token, unit, onesNumber));
        }
        return result.toString();
    }

    public static String decodeLetterBit(String letterBits, int unit, int onesNumber) { 
        if (onesNumber == 1) {
            if (letterBits.charAt(0) == '1')
                return ".";
        }
        else {
            if (letterBits.charAt(0) == '1' && letterBits.length() == unit)
                return ".";
            if (letterBits.charAt(0) == '1' && letterBits.length() == unit*3)
                return "-";
            if (letterBits.charAt(0) == '0' && letterBits.length() == unit*3)
                return " ";
            if (letterBits.charAt(0) == '0' && letterBits.length() == unit*7)
                return "  ";
        }
        return "";
    }
    
    private static Deque<String> tokenize(String input) {
        Deque<String> tokens = new LinkedList<>();
        Pattern pattern = Pattern.compile("0+|1+");
        Matcher m = pattern.matcher(input);
        while (m.find()) {
          tokens.add(m.group());
        }
        return tokens;
      }
    
    public static String decodeMorse(String morseCode) {
        // split by words
        return Arrays.stream(morseCode.trim().split("\\s{2,}"))
                .map(MorseCodeDecoder::getLetters)
                .collect(Collectors.joining(" "));
    }
    
    public static String getLetters(String word) {
        // split by letters
        return Arrays.stream(word.split("\\s"))
                .map(MorseCodeDecoder::getDecodedLetter)
                .collect(Collectors.joining());
    }
    public static String getDecodedLetter(String letter) {
        // split by letters
        return MorseCode.get(letter);
    }
    
    public static void main(String args[]) throws Exception {
        createAlphabet();
        
        // SIMPLE DECODE BITS TESTING
        // .... . -.--   .--- ..- -.. .  (HEY JUDE)
        // "1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011"
        System.out.println("TEST 1: HEY JUDE");
        System.out.println(decodeMorse(decodeBits("1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011")));
        System.out.println("TEST 2");
        // 01110
        System.out.println(decodeMorse(decodeBits("01110")));
        System.out.println("TEST 3");
//        1
//        101
//        10001
//        10111
//        1110111
        System.out.println(decodeMorse(decodeBits("1")));
        System.out.println(decodeMorse(decodeBits("101")));
        System.out.println(decodeMorse(decodeBits("10001")));
        System.out.println(decodeMorse(decodeBits("10111")));
        System.out.println(decodeMorse(decodeBits("1110111")));
        System.out.println("TEST 4");
//        111
//        1111111
//        110011
//        111000111
//        111110000011111
//        11111100111111
        System.out.println(decodeBits("111"));
        System.out.println(decodeMorse(decodeBits("111111")));
        System.out.println(decodeMorse(decodeBits("110011")));
        System.out.println(decodeMorse(decodeBits("111000111")));
        System.out.println(decodeMorse(decodeBits("111110000011111")));
        System.out.println(decodeMorse(decodeBits("11111100111111")));
        
        // ADVANCED DECODE BITS TESTING
        // (HEY JUDE) .... . -.--   .--- ..- -.. .
        // 0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000
        System.out.println(decodeMorse(decodeBitsAdvanced("0000000011011010011100000110000001111110100111110011111100000000000111011111111011111011111000000101100011111100000111110011101100000100000")));
    }
}
