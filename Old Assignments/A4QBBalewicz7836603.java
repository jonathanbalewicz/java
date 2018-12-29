/**
 * A4QBBalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 4, Part B
 * @author       Jonathan Balewicz, 7836603
 * @version      November 22, 2018
 *
 * PURPOSE: Read a text file of coffee, donut, pop, and sandwich orders. Print information about the orders.
 */
import java.io.*;
import java.util.ArrayList;

public class A4QBBalewicz7836603 {
    public static void main(String[] agrs) {
        OrderList orderList = new OrderList();

        processFile(orderList, "a4b.txt");

        System.out.println("Orders:");
        System.out.println(orderList);

        orderList.sort();

        System.out.println("Sorted Orders:");
        System.out.println(orderList);
        System.out.println(orderList.totals());

        System.out.println("End of Processing");
    }
    /* Reads the file, puts the information in the OrderList.
     * 
     * Takes the file name and OrderList.
     * 
     * Returns nothing.
     */
    public static void processFile(OrderList orderList, String file) {
        BufferedReader input;
        String[] words = new String[5];
        String line;
        int quantity;
        String size;
        double price;
        String flavour;
        String brand;
        String filling;
        String bread;

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
                } else if (words[0].equals("Pop")) {
                    quantity = Integer.parseInt(words[1]);
                    size = words[2];
                    brand = words[3];
                    orderList.add(new Pop(quantity, size, brand));
                } else if (words[0].equals("Sandwich")) {
                    quantity = Integer.parseInt(words[1]);
                    price = Double.parseDouble(words[2]);
                    filling = words[3];
                    bread = words[4];
                    orderList.add(new Sandwich(quantity, price, filling, bread));
                }
                line = input.readLine();
            }
            input.close();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

}
class OrderList {
    private ArrayList < Order > orderList;

    public OrderList() {
        orderList = new ArrayList < Order > ();
    }
    public void add(Order order) {
        orderList.add(order);
    }
    //Sorts the orders using selection sort
    public void sort() {
        Order temp;
        double lowest;
        int lowestIndex;
        double price;

        for (int i = 0; i < orderList.size(); i++) {
            lowest = orderList.get(i).totalPrice();
            lowestIndex = i;
            for (int j = i + 1; j < orderList.size(); j++) {
                price = orderList.get(j).totalPrice();
                if (price < lowest) {
                    lowest = price;
                    lowestIndex = j;
                }
            }
            temp = orderList.get(i);
            orderList.set(i, orderList.get(lowestIndex));
            orderList.set(lowestIndex, temp);
        }
    }
    // Returns a string of information about the total prices and quantities of the orders
    public String totals() {
        Order order;
        int coffeeCount = 0;
        int donutCount = 0;
        int popCount = 0;
        int sandwichCount = 0;
        double totalPrice = 0.0;
        String string = "";
        int quantity;

        for (int i = 0; i < orderList.size(); i++) {
            order = orderList.get(i);
            totalPrice += order.totalPrice();
            quantity = order.getQuantity();
            if (order.orderType().equals("donut")) {
                donutCount += quantity;
            } else if (order.orderType().equals("coffee")) {
                coffeeCount += quantity;
            } else if (order.orderType().equals("pop")) {
                popCount += quantity;
            } else if (order.orderType().equals("sandwich")) {
                sandwichCount += quantity;
            }
        }

        string += "---------------------------------------";
        string += "\nTotal coffees ordered: " + coffeeCount + "\nTotal donuts ordered: " + donutCount;
        string += "\nTotal pops ordered: " + coffeeCount + "\nTotal sandwichs ordered: " + sandwichCount;
        string += String.format("\n\nTotal price of all orders $%.2f\n", totalPrice);
        string += "---------------------------------------";
        return string;
    }
    public String toString() {
        Order order;
        double totalPrice = 0.0;
        String string = "";
        int quantity;

        for (int i = 0; i < orderList.size(); i++) {
            order = orderList.get(i);
            string += String.format(order + ", total price = $%.2f\n", order.totalPrice());
        }
        return string;
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
class Drink extends Order {
    private String size;

    public Drink(int quantity, String size) {
        super(quantity);
        this.size = size;
    }
    public String getSize() {
        return size;
    }
    public int getQuantity() {
        return super.getQuantity();
    }
    public double totalPrice() {
        return 0.0;
    }
    public String orderType() {
        return "drink";
    }

    public String toString() {
        return super.toString() + ", size = " + size;
    }
}
class Food extends Order {
    private double price;

    public Food(int quantity, double price) {
        super(quantity);
        this.price = price;
    }
    public int getQuantity() {
        return super.getQuantity();
    }
    public double totalPrice() {
        int quantity = super.getQuantity();
        double totalPrice = price * quantity;

        return totalPrice;
    }
    public String orderType() {
        return "food";
    }

    public String toString() {
        return super.toString() + ", price = $" + String.format("%.2f", price);
    }
}
class Coffee extends Drink {

    public Coffee(int quantity, String size) {
        super(quantity, size);
    }

    public double totalPrice() {
        int quantity = super.getQuantity();
        double totalPrice = 0;

        if (getSize().equals("small"))
            totalPrice = 1.39 * quantity;
        else if (getSize().equals("medium"))
            totalPrice = 1.69 * quantity;
        else if (getSize().equals("large"))
            totalPrice = 1.99 * quantity;
        return totalPrice;
    }
    public String orderType() {
        return "coffee";
    }
    public String toString() {
        return "Coffee " + super.toString();
    }


}
class Donut extends Food {
    private String flavour;

    public Donut(int quantity, double price, String flavour) {
        super(quantity, price);
        this.flavour = flavour;
    }
    public double totalPrice() {
        double totalPrice = super.totalPrice();

        if (getQuantity() < 6) {
            totalPrice += totalPrice * 0.07;
        }
        return totalPrice;
    }
    public String orderType() {
        return "donut";
    }

    public String toString() {
        return "Donut " + super.toString() + ", flavour = " + flavour;
    }
}
class Pop extends Drink {
    private String brand;

    public Pop(int quantity, String size, String brand) {
        super(quantity, size);
        this.brand = brand;
    }
    public double totalPrice() {
        int quantity = super.getQuantity();
        double totalPrice = 0;

        if (getSize().equals("small"))
            totalPrice = 1.79 * quantity;
        else if (getSize().equals("medium"))
            totalPrice = 2.09 * quantity;
        else if (getSize().equals("large"))
            totalPrice = 2.49 * quantity;
        return totalPrice;
    }

    public String orderType() {
        return "pop";
    }

    public String toString() {
        return "Pop " + super.toString() + ", brand = " + brand;
    }
}
class Sandwich extends Food {
    private String filling;
    private String bread;

    public Sandwich(int quantity, double price, String filling, String bread) {
        super(quantity, price);
        this.filling = filling;
        this.bread = bread;
    }
    public double totalPrice() {
        double totalPrice = super.totalPrice();
        totalPrice += totalPrice * 0.07;
        return totalPrice;
    }
    public String orderType() {
        return "sandwich";
    }

    public String toString() {
        return "Sandwich " + super.toString() + ", filling = " + filling + ", bread = " + bread;
    }
}