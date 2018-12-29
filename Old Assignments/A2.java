import java.io.*;

public class A2 {
   public static void main(String[] args) {
      BufferedReader input;
      String country, eventType, event;

      Evet[] events = new Evet[500]; // events by country
      int size = 0; // size of the above array
      
      Evet[] eventsByEventType = new Evet[500];
      int size2 = 0; // size of the above array


      try {
         input = new BufferedReader(new FileReader("a2a.txt"));

         country = input.readLine();
         while (country != null) {
            eventType = input.readLine();
            event = input.readLine();
                  // movie that was not previously listed
                  System.out.println(country);
                  System.out.println(eventType);
                  System.out.println(event);
                  events[size] = new Evet(country, eventType);
                  size++;
               
            country = input.readLine();
         }

         input.close();
      } catch (IOException ioe) {
         System.out.println(ioe.getMessage());
      }

      System.out.println("\nEnd of processing.");
   }
}

class Evet {
   private String country;
   private String eventType;
   private int medalCount;
   

   public Evet(String country, String eventType) {
   this.country = country;
      this.eventType = eventType;
      this.medalCount = 1;
   }
}
