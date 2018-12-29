/**
 * A2QBBalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 2, Part B
 * @author       Jonathan Balewicz, 7836603
 * @version      October 11, 2018
 *
 * PURPOSE: Organize text file by sentences, print a sample of the sentences,
 * 	and show statistics about the text.
 */
import java.io.*;

public class A2QBBalewicz7836603 {
	public static void main(String[] args) {
		Sentence[] sentences = new Sentence[1000];
		int size = 0;

		size = readFile(sentences);
		
		System.out.println("The first five sentences:");
		printSentences(sentences, size, 0, 4);
		
		System.out.println("\nThe last five sentences:");
		printSentences(sentences, size, size-5, size-1);
		
		printStats(sentences, size);
		
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
			input = new BufferedReader(new FileReader("a2b.txt"));
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
	public static void printStats(Sentence[] sentences, int size) {
		int letters = 0;
		int wordCount = 0;
		double ari;
		double MULTIPLIER_LW = 4.71;
		double MULTIPLIER_WS = 0.5;
		double CONSTANT = 21.43;

		for (int i=0; i < size; i++) {// loop through sentences
			letters += sentences[i].getLetterCount();
			wordCount += sentences[i].getWordCount();
		}

		ari = MULTIPLIER_LW * ((double)letters/(double)wordCount);
		ari += MULTIPLIER_WS * ((double)wordCount/(double)size) - CONSTANT;
		ari = Math.round(ari * 10)/10.0;
		
		System.out.println("\nSummary statistics:");
		System.out.println("Letters: "+letters);
		System.out.println("Words: "+wordCount);
		System.out.println("Sentences: "+size);
		System.out.println("Readability: "+ari);

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
	public void add(String word, int letterCount) {
		text += word;
		this.letterCount += letterCount;
		if (letterCount != 0) {//only increment wordCount if the word contains a letter
			wordCount++;
		}
	}
	public int getWordCount() {
		return wordCount;
	}
	public int getLetterCount() {
		return letterCount;
	}
	public String toString() {
		return text;
	}
}

