/**
 * A2QABalewicz7836603
 *
 * COMP 1020 SECTION D01
 * INSTRUCTOR    Heather Matheson
 * ASSIGNMENT    Assignment 2, Part A
 * @author       Jonathan Balewicz, 7836603
 * @version      October 11, 2018
 *
 * PURPOSE: Read a text file with a record for each gold medal won an event,
 *  and output the number of medal that each country won and the number of 
 *  medals won in each event type to a the console and to a2q1out.txt .
 */
import java.io.*;

public class A2QABalewicz7836603 {
	public static void main(String[] args) {

		Even[] events = new Even[500]; // events by country
		int size = 0; // size of the above array

		Even[] eventsByEventType = new Even[500];
		int size2 = 0; // size of the above array

		size = generateEventsCountry(events); // generate events by country
		size2 = generateEventsType(eventsByEventType); // generate events by event type

		// print and output to file
		printEvents(events, eventsByEventType, size, size2);

		System.out.println("\nEnd of processing.");
	}

	public static int generateEventsCountry(Even[] events)
	{
		int size = 0; // size of the above array

		BufferedReader input;
		String country, eventType;
		Even countryFound;

		try {
			input = new BufferedReader(new FileReader("a2a.txt"));

			country = input.readLine();
			while (country != null) {
				eventType = input.readLine();
				input.readLine(); // skip over specific event
				countryFound = findCountry(events, country, size);
				if (countryFound == null) {
					// country not seen, add country to array
					events[size] = new Even(country, eventType);
					size++;
				} else {
					// add medal to country
					countryFound.addMedal();
				}
				country = input.readLine();
			}
			input.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} 
		return size;
	}
	public static int generateEventsType(Even[] eventsByEventType)
	{
		int size = 0; // size of the above array

		BufferedReader input;
		String country, eventType;
		Even eventTypeFound;

		try {
			input = new BufferedReader(new FileReader("a2a.txt"));
			country = input.readLine();
			while (country != null) {
				eventType = input.readLine();
				input.readLine(); // skip over specific event
				eventTypeFound = findEventType(eventsByEventType, eventType, size);
				if (eventTypeFound == null) {
					// event type not already seen, add event type to the array
					eventsByEventType[size] = new Even(country, eventType);
					size++;
				} else { // add medal to event type
					eventTypeFound.addMedal();
				}
				country = input.readLine();
			}
			input.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} 
		return size;
	}

	public static Even findCountry(Even[] events, String country, int size) {// returns the country if it exists, otherwise null
		Even result = null;
		int pos;
		pos = 0;
		while (pos < size && result == null) { // loop through countries looking for a match
			if (events[pos] != null && events[pos].sameCountry(country)) {
				result = events[pos];
			} else {
				pos++;
			}
		}
		return result;
	}

	public static Even findEventType(Even[] events, String eventType, int size) {
		Even result = null;
		int pos;
		pos = 0;
		while (pos < size && result == null) {

			if (events[pos] != null && events[pos].sameEventType(eventType)) {
				result = events[pos];
			} else {
				pos++;
			}
		}
		return result;
	}
	public static void printEvents (Even[] events, Even[] eventsByEventType, int size, int size2) {
		try {
			String line;
			PrintWriter output;
			System.out.println("Count of gold medallists by country:");
			output = new PrintWriter(new FileWriter("a1q2out.txt"));
			output.println("Count of gold medallists by country:");
			for (int i = 0; i < size; i++) {
				line = events[i].stringByCountry();
				output.println(line);
				System.out.println(line);
			}
			System.out.println("\nCount of gold medallists by event type:");
			output.println();
			output.println("Count of gold medallists by event type:");
			for (int j = 0; j < size2; j++) {
				line = eventsByEventType[j].stringByEventType();
				output.println(line);
				System.out.println(line);
			}
			output.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} 

	}
}

class Even { // named Even to prevent class conflicts
	private String country;
	private String eventType;
	private int medalCount; 

	public Even(String country, String eventType) {
		this.country = country;
		this.eventType = eventType;
		this.medalCount = 1;
	}

	public void addMedal() {
		medalCount++;
	}

	public boolean sameCountry(String secondCountry) {
		return country.equals(secondCountry);
	}
	public boolean sameEventType(String secondEventType) {
		return eventType.equals(secondEventType);
	}
	public String stringByCountry() {
		return country + " - " + medalCount;
	}
	public String stringByEventType() {
		return eventType + " - " + medalCount;
	}
}
