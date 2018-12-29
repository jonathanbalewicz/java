/**
 * A4QABalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 4, Part A
 * @author       Jonathan Balewicz, 7836603
 * @version      November 20, 2018
 *
 * PURPOSE: 
 */
import java.io.*;
import java.util.ArrayList;

public class A4QABalewicz7836603 {
  public static void main(String[] agrs) {
    processFile("a4a.txt");

    System.out.println("End of Processing");
  }
  public static void processFile(String file) {
    BufferedReader input;
    String[] words = new String[4];
    String line;
    int quantity;
    String size;
    double price;
    String flavour;
    ArrayList<Order> orderList = new ArrayList<Order>();
      
    try {
      input = new BufferedReader(new FileReader(file));
      line = input.readLine();
      while (line != null) { // loop through lines
        words = line.split(",");

        if (words[0].equals("Coffee")) {
          quantity = Integer.parseInt(words[1]);
          size = words[2];
          orderList.add(new Coffee(quantity, size));
        } else if (words[0].equals("Donut")) {
          quantity = Integer.parseInt(words[1]);
          price = Double.parseDouble(words[2]);
          flavour = words[3];
          orderList.add(new Donut(quantity, price, flavour));
        }
        line = input.readLine();
      }
      input.close();
      print(orderList);
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    
  }
  public static void print(ArrayList<Order> orderList) {
      Order order;
      int coffeeCount = 0;
      int donutCount = 0;
      double totalPrice = 0.0;

      
      for (int i = 0; i < orderList.size(); i++) {
          order = orderList.get(i);
          if (order.orderType().equals("donut")) {
          donutCount += order.getQuantity();
          } else {
          coffeeCount += order.getQuantity();
          }
          totalPrice += order.totalPrice();
          System.out.printf(order + ", total price = $%.2f\n", order.totalPrice());
          
    }
      System.out.println("\nTotal coffees ordered: " + coffeeCount + " Total donuts ordered: " + donutCount);
      System.out.printf("\nTotal price of all orders $%.2f\n", totalPrice);
  }
}
class Order {
  private int quantity;

  public Order(int quantity) {
      this.quantity = quantity;
  }
  public double totalPrice() {
      return 0.0;
  }
  public String orderType() {
      return " ";
  }
  public int getQuantity() {
      return quantity;
  }
  public String toString() {
        return "quantity = " + quantity;
    }
}
class Coffee extends Order {
    private String size;
    
    public Coffee(int quantity, String size) {
        super(quantity);
        this.size = size;
    }
    
    
    public double totalPrice() {
        int quantity = super.getQuantity();
        double totalPrice = 0;
        
        if (size.equals("small"))
            totalPrice = 1.39*quantity;
        else if (size.equals("medium"))
            totalPrice = 1.69*quantity;
        else if (size.equals("large"))
            totalPrice = 1.99*quantity;
        return totalPrice;
  }
    public String orderType() {
      return "coffee";
  }
    public String toString() {
        return "Coffee " + super.toString() + ", size = " + size;
    }
    
    
}
class Donut extends Order {
    private double price;
    private String flavour;
    
    public Donut(int quantity, double price, String flavour) {
        super(quantity);
        this.price = price;
        this.flavour = flavour;
    }
    public double totalPrice() {
        int quantity = super.getQuantity();
        double totalPrice = price*quantity;
        
        if (quantity < 6) {
            totalPrice += totalPrice*0.07;
        }
        return totalPrice;
  }
    public String orderType() {
      return "donut";
  }
    
    public String toString() {
        return "Donut " + super.toString() + ", price = $" + String.format("%.2f", price) + ", flavour = " + flavour;
    }
    
    
    
}
