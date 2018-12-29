/**
 * A3QBBalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 3, Part B
 * @author       Jonathan Balewicz, 7836603
 * @version      November 1, 2018
 *
 * PURPOSE: Read files with coordinates. Plot the coordinates as
 * characters. Plot the least squares regression line on the same plot.
 */
import java.io.*;
import java.util.ArrayList;

public class A3QBBalewicz7836603 {
  public static void main(String[] args) {
    char[][] plot1 = new char[42][21];
    char[][] plot2 = new char[42][21];

    plot1 = processFile("a3plot1.txt");
    plot2 = processFile("a3plot2.txt");

    System.out.println("Plot 1:");
    print(plot1);

    System.out.println("\n\nPlot 2:");
    print(plot2);

    System.out.println("\nEnd of processing.");
  }

  // Process file, and return array with the plot
  public static char[][] processFile(String file) {
    BufferedReader input;
    String line;
    int x;
    int y;
    double sumX = 0; // doubles to perform arithmetic
    double sumY = 0;
    double sumXY = 0; // sum of all (x_i)*(y_i)
    double sumXX = 0; // sum of all (x_i)^2
    double pointCount = 0;
    double xBar; // average of x coords
    double yBar;
    double m; // slope
    ArrayList < Integer > coords = new ArrayList < Integer > (); // x coords are stored in even indices, y in odd
    char[][] plot = new char[42][21];

    try {
      input = new BufferedReader(new FileReader(file));
      line = input.readLine();

      while (line != null) { // loop through lines
        try { // catch incomplete coords
          try { // catch non coords
            x = Integer.parseInt(line.split(" ")[0]);
            y = Integer.parseInt(line.split(" ")[1]);
            if (x >= 0 && x < 41 && y > 0 && y < 21) { // don't use out of range coords
              sumX += x;
              sumY += y;
              sumXY += x * y;
              sumXX += x * x;
              coords.add(x);
              coords.add(y);

              pointCount += 1;
            }
          } catch (NumberFormatException nfe) {}
        } catch (ArrayIndexOutOfBoundsException aie) {}
        line = input.readLine();
      }
      input.close();

    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
    }
    xBar = sumX / pointCount;
    yBar = sumY / pointCount;
    m = (sumXY - (pointCount * xBar * yBar)) / (sumXX - ((pointCount * xBar * xBar)));
    plot = plot(coords, xBar, yBar, m);
    return plot;
  }

  // Takes the plot coords, and line information. Returns the array with the plot.
  public static char[][] plot(ArrayList < Integer > coords, double xBar, double yBar, double m) {
    char[][] plot = new char[42][21];
    int xCoord;
    int yCoord;

    //make borders and fill with spaces
    plot[0][0] = '+';
    for (int y = 1; y < 21; y++) {
      plot[0][y] = '/';
    }
    for (int x = 1; x < 42; x++) {
      plot[x][0] = '-';
    }
    for (int y = 20; y > 0; y--) {
      for (int x = 1; x < 42; x++) {
        plot[x][y] = ' ';
      }
    }

    for (xCoord = 1; xCoord < 42; xCoord++) { //plot line, loop through x coords
      yCoord = (int)(yBar + m * (xCoord - 1 - xBar));
      if (yCoord < 21 && yCoord > 0) {
        plot[xCoord][yCoord] = '-';
      }
    }
    for (int i = 0; i < (coords.size() / 2); i++) { //plot points, loop through coords ArrayList
      xCoord = coords.get(i * 2) + 1;
      yCoord = coords.get(i * 2 + 1);
      if (plot[xCoord][yCoord] == ' ')
        plot[xCoord][yCoord] = 'X';
      else
        plot[xCoord][yCoord] = '*';
    }
    return plot;
  }

  // Takes an array of characters and plots them.
  public static void print(char[][] plot) {
    for (int y = 20; y >= 0; y--) {
      System.out.println();
      for (int x = 0; x < 42; x++) {
        System.out.print(plot[x][y]);
      }
    }
  }
}