
// Kata: Stop gninnipS My sdroW! (6 kyu)

public class SpinWords {

//    Write a function that takes in a string of one or more words, 
//    and returns the same string, but with all five or more letter 
//    words reversed (Just like the name of this Kata). 
//    Strings passed in will consist of only letters and spaces. 
//    Spaces will be included only when more than one word is present.
//
//    Examples:
//
//    spinWords( "Hey fellow warriors" ) => returns "Hey wollef sroirraw"
//    spinWords( "This is a test") => returns "This is a test"
//    spinWords( "This is another test" )=> returns "This is rehtona test"

    
    
  public String spinWords(String sentence) {
      StringBuilder reversedWord = new StringBuilder();
      for (int i = sentence.length()-1; i >= 0; i--) {
          if (sentence.charAt(i) != ' ') {
              reversedWord.append(sentence.charAt(i));
          }
          else {
              if (reversedWord.length() >= 5) {
                  sentence = sentence.substring(0, i+1) + reversedWord.toString() +
                          sentence.substring(reversedWord.length() + i+1, sentence.length());
              }
              reversedWord = new StringBuilder();
          }
      }
      if (reversedWord.length() >= 5) {
          sentence = reversedWord.toString() + sentence.substring(reversedWord.length(), sentence.length());
      }
      
    return sentence;
  }  
  
  public static void main(String args[]) throws Exception {
      // testing
      SpinWords sw = new SpinWords();
      System.out.println(sw.spinWords("Stupid Hey fellow warriors"));
  }
}