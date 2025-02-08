import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

public class AquaticEcosystem {
    public static void main(String[] args) {

        //Opens the file to be read\
        FileInputStream myFile = null;
        // sample tank is data that I created through randomizing a reasonable range of data for each of the water parameters
        // I then imported this here
        try {
            myFile = new FileInputStream("src/sample tank - Sheet1.csv");
        } // input file here
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner fileReader = new Scanner(myFile);
        Scanner console = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        //Hopefully will eventually display differnt names of data files that the user will be able to input the name of
        //and get the data for that body of water.
        System.out.println("Please select file: " + myFile);
        String userSelectedFile = console.nextLine();
        int lineCount = 0;
        while (fileReader.hasNextLine()) {
            lineCount++;
        }


        int temperatureData = 0;
        // This while loop will go through all lines and for each different specification I plan to have the specifications data
        // added to its own array so that it can be easily accessed. I am working on learning more about the Date function
        // So as to call data from specific dates that will already be in order in the other arrays, so that when the user inputs a date
        // the program outputs the data of that test as well as details about what that test said about the water quality. 
        while (fileReader.hasNextLine()) {
            String waterInfo = fileReader.nextLine();

            String[] allData = waterInfo.split(",");
            String data = allData.toString();

            //parses for date data which will determine based on the inputted date what other data is passed through the methods
            Date[] dateArray = new Date[lineCount];
            for (int i = 0; i < lineCount; i++) {
                try{dateArray[i] = dateFormat.parse(data);}
                catch(ParseException e){}

                }





            //parses for temperature data

            try{
                  temperatureData = Integer.parseInt(allData[1] + ",");}
            catch (NumberFormatException e) {
                System.out.println("No inputted temperature at this date.");
            }
        }
        System.out.println("What date will you be fetching information from: ");

        System.out.println("What type of fish are being housed in this body of water: ");
        String fishType = console.nextLine();
        temperatureGauge(temperatureData, fishType);
    }

    //temperature method that when called will compare the temperature to the recommended temperature for fish of different
    //water quality specifications such as freshwater tropical and marine
    public static double temperatureGauge(double temp, String fishType) {

        // Method to check if the temperature is healthy for a specific type of fish tank
        // Define temperature ranges based on the fish type
        double minTemp = 0;
        double maxTemp = 0;

        // Set temperature ranges based on fish type
        if (fishType.equalsIgnoreCase("freshwater")) {
            minTemp = 22;
            maxTemp = 28;
        } else if (fishType.equalsIgnoreCase("marine")) {
            minTemp = 24;
            maxTemp = 29;
        } else if (fishType.equalsIgnoreCase("tropical")) {
            minTemp = 24;
            maxTemp = 28;
        }

        // Check if the temperature is within the healthy range
        if (temp < minTemp) {
            System.out.println("Warning: Temperature is too low! It should be between " + minTemp + "°C and " + maxTemp + "°C for " + fishType + " fish.");
        } else if (temp > maxTemp) {
            System.out.println("Warning: Temperature is too high! It should be between " + minTemp + "°C and " + maxTemp + "°C for " + fishType + " fish.");
        } else {
            System.out.println("The temperature is healthy for " + fishType + " fish.");
        }
        return temp;
    }

    //planning to possibly make more object features that allow me to call a function which checks all of this instead of having it
    // in the main file. Not sure at this point if this is the route I want to take.

}






