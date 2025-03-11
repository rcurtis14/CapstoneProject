import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.*;

public class AquaticEcosystem {
    public static void main(String[] args) throws ParseException {

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
        ArrayList<BodyOfWater> waterBody = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] values = line.split(",");
            BodyOfWater water = new BodyOfWater(sdf.parse(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]),
                    Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]));
            waterBody.add(water);

        }
        //Feature 1 - Sorts entry list of data by the date of entry
        DateComparator comparator = new DateComparator();
        sort(waterBody, waterBody.size(), comparator);

        for (int i = 0; i < waterBody.size(); i++) {
        System.out.println(waterBody.get(i).toString());
        }

        // Feature 2 - allows user to either see their most recent report or asks the user for which date they would like the report made
        String answer = "";
        while (!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))) {
        System.out.println("Would you like your most recent entry report? (Y/N)");
        answer = console.nextLine();
        }
        if (answer.equalsIgnoreCase("Y")) {
            //proceeds to call all rest of individual features
            OxygenLevelEval(waterBody, waterBody.size() -1);
        }
        else if (answer.equalsIgnoreCase("N")) {
            String[] x = new String[1];
            int check = 1;
            int indexOfDate = 0;
            while (check < 2) {
                System.out.println("For which date do you want your report: input date from table (MM/dd/yyyy)");
                x[0] = console.next();
                Date choice = sdf.parse(x[0]);
                for (int i = 0; i < waterBody.size(); i++) {
                    if (waterBody.get(i).getDateRecorded().equals(choice)) {
                        indexOfDate = i;
                        check ++;
                    }
                }

            }

            //Oxygen Level Evaluator
            OxygenLevelEval(waterBody, indexOfDate);

        }







    }

    //Makes use of bubble sorting to sort the arraylist based on the dates of the entries.
    public static void sort (ArrayList<BodyOfWater> Information, int n, DateComparator comparator) {
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - 1 - i; j++){
                if (comparator.compare(Information.get(j), Information.get(j+1)) > 0) {
                    //call swap method
                    swap(Information, j, j+1);
                }
            }
        }
    }
    public static void swap(ArrayList<BodyOfWater> Information, int n, int j ){
        BodyOfWater temp = Information.get(n);
        Information.set(n, Information.get(j));
        Information.set(j, temp);

    }

   public static void OxygenLevelEval (ArrayList<BodyOfWater> Information, int s ){
       BodyOfWater Entry = Information.get(s);
        double oxygen = Entry.getOxygenLevel();
        double temperature = Entry.getTemperature();
        // data table of approximate temperature and 100% saturation level
        double [] SaturatedLevel = {14.6, 14.2, 13.8, 13.5, 13.1, 12.8, 12.5, 12.1, 11.8, 11.6, 11.3, 11.0, 10.8, 10.5, 10.3,
        10.1, 9.9, 9.7, 9.6, 9.3, 9.1, 8.9, 8.7, 8.6, 8.4, 8.3,8.1,8.0, 7.8, 7.7, 7.6, 7.5};
        Math.round(temperature);
        int index = 32 - (int)temperature;
        double maxDissolvedOxygen = SaturatedLevel[index];
        double answer = (oxygen / maxDissolvedOxygen);
        double roundedAnswer = (Math.round(oxygen *100.0)/100.0);
        double percentDO = answer *100;


       FileOutputStream myFile = null;
       try{ myFile = new FileOutputStream("src/Report.txt");}
       catch (FileNotFoundException e) {
           System.out.println("Report File not found");
       }
        PrintWriter writer = null;
              try{ writer =  new PrintWriter(myFile);}
              catch(Exception e){
                  System.out.println(" Error writing to file. ");
              }

        writer.printf("\nHere is your report for Oxygen Level in the provided water sample: " + "\nDissolved Oxygen: %.2f"
                + "                      Saturation Level of Dissolved Oxygen: %.2f%%", oxygen, percentDO);
        if (oxygen < 4.0){
            writer.println("\nYour Oxygen Level of " + roundedAnswer + " mg/L is Concerning!" +
                    "\nWhen dissolved oxygen levels fall below 4.0 mg/L in an environment, it is harmful to aquatic life. Many fish and other organisms rely on oxygen levels higher than this, thus most all fish in this ecosystem are at significant risk of death. This level is particularly common in areas with high pollution or stagnant water, where oxygen depletion occurs more quickly.");
        }
        if (4.0 < oxygen && oxygen< 6.5){
           writer.println("\nYour Oxygen Level of " + roundedAnswer + " mg/L requires monitoring. " +
                   "\nOxygen levels between 4.0 and 6.5 mg/L are generally considered acceptable for most aquatic life. While some species may still experience stress at the lower end of this range, most fish and organisms can thrive. However, it's important to monitor oxygen levels closely, as prolonged periods near the lower end can still impact the health of sensitive species and disrupt the balance of the ecosystem.");
        }
        if (6.5 < oxygen && oxygen< 9.5 ){
            writer.println("\nYour Oxygen Level of " + roundedAnswer + " mg/L is generally good." +
                    "\nThe dissolved oxygen (DO) level ranging from 6.5 to 9.5 mg/L indicates a healthy aquatic environment. This range is typically sufficient to support a variety of aquatic life, including fish and invertebrates, as most species require DO levels within this range for proper respiration and metabolic function. However, levels closer to the upper end of this range are ideal, as oxygen-rich waters promote biodiversity and ecosystem stability."
                    );
        }
        if (9.5 < oxygen && oxygen< 12.0 ){
            writer.println("\nYour Oxygen Level of " + roundedAnswer + " mg/L is considered ideal!" +
                    "\nA dissolved oxygen (DO) level between 9.5 and 12 mg/L suggests very well-oxygenated water, which is ideal for supporting a diverse and healthy aquatic ecosystem. At these levels, most aquatic organisms can thrive, with increased oxygen availability promoting higher metabolic rates and supporting more species. This range is often seen in fast-moving streams, colder waters, or areas with high photosynthetic activity. However, while it is beneficial for most aquatic life, excessive oxygen levels can occasionally stress certain species if they fluctuate rapidly.");
        }
        writer.flush();
        writer.close();
   }


}






