/**
 * A3QABalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 3, Part A
 * @author       Jonathan Balewicz, 7836603
 * @version      October 29, 2018
 *
 * PURPOSE: Process a text file containing actions
 * of buying and adding products to a shopping list,
 * to create a shopping list, and list of items purchsed.
 * Prints the shopping list and purchase list when
 * requested by the text file.
 */
import java.io.*;
import java.util.ArrayList;

public class A3QABalewicz7836603 {
  public static void main(String[] args) {

    processFile("a3a.txt");

    System.out.println("\nEnd of processing.");
  }

  /* Creates the shopping list and purchase list for the file,
   * printing the lists as requested by the file.
   * 
   * Takes the file name.
   * 
   * Returns nothing.
   */
  public static void processFile(String file) {
    BufferedReader input;
    String[] words = new String[3];
    String line;
    Product item;
    int index;
    int amount;
    ArrayList<Product> shoppingList = new ArrayList<Product>();
    ArrayList<Product> purchaseList = new ArrayList<Product>();

    try {
      input = new BufferedReader(new FileReader(file));
      line = input.readLine();
      while (line != null) { // loop through lines
        words = line.split(",");

        if (words[0].equals("add")) {
          amount = Integer.parseInt(words[1]);
          index = search(shoppingList, words[2]);
          if (index != -1) { // add to the quanity of the item in the shopping list
            shoppingList.get(index).add(amount);
          } else { // create a new item in shopping list
            item = new Product(words[2], amount);
            shoppingList.add(item);
          }
        } else if (words[0].equals("buy")) {
          amount = Integer.parseInt(words[1]);
          index = search(purchaseList, words[2]);
          if (index != -1) { // add to purchase quantity
            purchaseList.get(index).add(amount);
            decrementShopping(shoppingList, words[2], amount);
          } else { // add new item to purchase list
            item = new Product(words[2], amount);
            purchaseList.add(item);
            decrementShopping(shoppingList, words[2], amount);
          }
        } else if (words[0].equals("list")) { // print lists
          System.out.println("\n==============");
          System.out.println("Shopping List:");
          printLists(shoppingList);
          System.out.println("\nPurchase List:");
          printLists(purchaseList);
        }
        line = input.readLine();
      }
      input.close();

    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
  }

  /* Decrement the shopping list quanitity of a product.
   * Remove the product off the shopping list if there are no
   * more of this item on the list.
   * 
   * Takes the shopping list, the product name,
   * and the amount of the product.
   * 
   * Returns nothing.
   */
  public static void decrementShopping(ArrayList<Product> list, String product, int amount) {
    int index = search(list, product);
    if (index != -1) {
      list.get(index).add((-1) * amount); // decrement the quantity
      if (list.get(index).getQuantity() < 1) {
        list.remove(index); // remove from list
      }
    }


    /* Search for a product in an ArrayList.
     * 
     * Takes the ArrayList and the product you are looking for.
     * 
     * Returns the index of the item in the ArrayList.
     */
  }
  public static int search(ArrayList < Product > list, String product) {
    int match = -1;
    for (int i = 0; i < list.size(); i++) {
      if (product.equals(list.get(i).getName())) {
        match = i;
      }
    }
    return match;
  }

  /* Print the ArrayList.
   * 
   * Takes the ArrayList.
   * 
   * Return nothing.
   */
  public static void printLists(ArrayList < Product > list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }
}

class Product {
  private String name;
  private int quantity;

  public Product(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }
  public void add(int amount) {
    quantity += amount;
  }
  public int getQuantity() {
    return quantity;
  }
  public String getName() {
    return name;
  }
  public String toString() {
    return quantity + " - " + name;
  }
}