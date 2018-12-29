/*
 * 
 This program can handle negative duration values by producing an error.
 It will process any other integer duration values properly, 
  as long as there is only one or zero numbers on both sides of the : with no other symbols.
 It handles whitespace on either side of both the hour and minute durations.
 
 */

import java.io.*;

public class Exercise3 {
	public static void main(String[] args) {
		BufferedReader input;
		String line;
		String[] tokens;
		int temperature;
		String numeric, direction = "";
		int speed;
		int precipitation = 0;
		String unit = "";
		int separation;
		String[] percDuration; // [hours, minutes]
		int hours = 0;
		int minutes = 0;

		Reading[] readings = new Reading[100];
		int size = 0;

		try {
			input = new BufferedReader(new FileReader("readings.txt"));

			line = input.readLine();
			while (line != null) {
				hours = 0;
				minutes = 0;
				tokens = line.split(",");

				temperature = Integer.parseInt(tokens[0].trim());

				tokens[1] = tokens[1].trim();
				separation = firstNonNumericPosition(tokens[1]);
				if (separation == 0 || (separation < 0 && Integer.parseInt(tokens[1]) != 0)) {
					speed = -1;
				} else {
					if (separation < 0) {
						speed = 0;
						direction = "";
					} else {
						numeric = tokens[1].substring(0, separation);
						speed = Integer.parseInt(numeric.trim());
						direction = tokens[1].substring(separation).trim();
					}


					if (tokens.length > 2) {
						if (tokens.length > 3) {
							percDuration = tokens[3].split(":");
							if (!percDuration[0].trim().equals("")) {
								hours = Integer.parseInt(percDuration[0].trim());
							}
							if (percDuration.length == 2 && !percDuration[1].trim().equals("")) {
								minutes = Integer.parseInt(percDuration[1].trim());
							}
							
							}
						tokens[2] = tokens[2].trim();
						separation = firstNonNumericPosition(tokens[2]);
						
						if (separation <= 0) {
							precipitation = -1;
						} else {
							numeric = tokens[2].substring(0, separation);
							precipitation = Integer.parseInt(numeric.trim());
							unit = tokens[2].substring(separation).trim();
						}
					} else {
						precipitation = 0;
						unit = ""; 
					}
				}

				if (speed < 0 || precipitation < 0 || hours < 0 || minutes < 0) {
					System.out.println("Error in input: " + line);
				} else {
					readings[size] = new Reading(temperature, speed, direction, 
						precipitation, unit.equalsIgnoreCase("cm"), hours, minutes);
					size++;
				}

				line = input.readLine();
			}

			input.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

		for (int i = 0; i < size; i++) {
			System.out.println(readings[i]);
		}

		System.out.println("\nEnd of processing.");
	}

	public static int firstNonNumericPosition(String str) {
		int pos = -1;
		int i = 0;

		while (pos < 0 && i < str.length()) {
			if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				pos = i;
			} else {
				i++;
			}
		}

		return pos;
	}
}

class Reading {
	private int temperature;      // in degrees C
	private int windSpeed;        // in km/h
	private String windDirection; // e.g. N,SW,ESE
	private int precipitation;    // in mm
	private boolean isSnow;       // true if precipitation fell as snow
	private int hours; 			  // duration of percipitation
	private int minutes;
	
	public Reading(int temperature, int windSpeed, String windDirection,
	               int precipitation, boolean isSnow, int hours, int minutes) {
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.precipitation = precipitation;
		this.isSnow = isSnow;
		this.hours = hours;
		this.minutes = minutes;
	}
	
	/*
	public Reading(int temperature, int windSpeed, String windDirection) {
   	this.temperature = temperature;
   	this.windSpeed = windSpeed;
   	this.windDirection = windDirection;
   	this.precipitation = 0;
   	this.isSnow = false;
   }

   public Reading(int temperature, int precipitation, boolean isSnow) {
   	this.temperature = temperature;
   	this.windSpeed = 0;
   	this.windDirection = "";
   	this.precipitation = precipitation;
   	this.isSnow = isSnow;
   }
   */
   
	public int getTemperature() {
		return temperature;
	}
	
	public int getPrecipitation() {
		return precipitation;
	}
	
	public String getWindDirection() {
		return windDirection;
	}
	
	public String toString() {
		String result;
		result = "temp: " + temperature + "C wind: " + windSpeed + windDirection +
		         " precipitation: " + precipitation;
		if (isSnow) {
			result += "cm snow";
		} else {
			result += "mm rain";
		}
		result += " percipitation duration " + hours + ":" + minutes;
		return result;
	}
}
