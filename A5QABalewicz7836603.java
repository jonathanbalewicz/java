/**
 * A5QABalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 5, Part A
 * @author       Jonathan Balewicz, 7836603
 * @version      November 30, 2018
 *
 * PURPOSE: Print a triangle of characters made from a given string.
 */

public class A5QABalewicz7836603 {
    public static void main(String[] args) {
        trianglePrint("disestablishmentarianism");
        trianglePrint("+-! *# =~ ~= #* !-+");
    }
    
/* Prints the triangle string.
 * 
 * Returns nothing.
 */
public static void trianglePrint(String s) {
    System.out.println(triangleStringInit(s));
}

/* Helper method for triangleString() method
 * 
 * Takes the string.
 * 
 * Returns the triangle string.
 */
public static String triangleStringInit(String s) {
    
    return triangleString(s, s.length());
}

/* Takes the string and string length.
 * 
 * Returns the triangle string after recursive calls.
 */
public static String triangleString(String s, int charCount) {
    String subString1;
    String subString2;
    int len = s.length();
    if (charCount % 2 == 0) {
        subString1 = s.substring(0, (int)((double)(len+1)/2.0)-1);
        subString2 = s.substring((int)((double)(len)/2.0) + 1, len);
    } else {
        subString1 = s.substring(0, (int)((double)(len)/2.0));
        subString2 = s.substring((int)((double)(len)/2.0) + 2, len);
    }
    if (charCount > 2)
        s = triangleString(" "+subString1+subString2+" ", charCount-2)+ "\n" + s;
    return s;
    
}
}