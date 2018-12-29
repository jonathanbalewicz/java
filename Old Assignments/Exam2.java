
import java.io.*;

public class Exam2 {
 public static void main(String[] args) {
  Sentence[] sentences = new Sentence[1000];
  int size = 0;

  size = readFile(sentences);
  
  System.out.println("\nEnd of processing.");
 }
 public static int readFile(Sentence[] sentences) {
  BufferedReader input;
  String[] words = new String[1000];
  String line;
  int size = 0;
  String lastChar;
  int letters = 0;
  int charValue;

  try {
   input = new BufferedReader(new FileReader("aa2.txt"));
   line = input.readLine();
   sentences[size] = new Sentence();
   while (line != null) { // loop through lines

    words = line.split("\\s+");
    for (int j=0; j < words.length; j++) {// loop through words in the line

     if (words[j].length() != 0) {

      for (int k = 0; k< words[j].length(); k++) {//loop through characters
       charValue = (int)words[j].toString().toLowerCase().charAt(k);

       if (charValue >= 97 && charValue <= 122) { // test if character is a letter
        letters++;
       }
      }
      sentences[size].add(" "+words[j], letters);

      lastChar = words[j].substring(words[j].length()-1);
      if (lastChar.equals(".") || lastChar.equals("?") || lastChar.equals("!")) {
       // if this is the last word in the sentence
       size++;
       sentences[size] = new Sentence();
      }
     }
     letters = 0;
    }
    line = input.readLine();
   }
   input.close();

  } catch (IOException ioe) {
   System.out.println(ioe.getMessage());
  }
  return size;
 }
 
 public static void printSentences(Sentence[] sentences, int size, int start, int end) {
  for (int i = start; i <= end; i++) {
   System.out.println("("+(1+i)+")"+sentences[i]);
  }
 }
 
}
class Sentence {
 private String text;
 private int wordCount;
 private int letterCount;

 public Sentence() {
  this.text = "";
  this.wordCount = 0;
  this.letterCount = 0;
 }
 public String toString() {
  return text;
 }
}
