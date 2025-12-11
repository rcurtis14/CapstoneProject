import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AquaticEcosystem {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        System.out.println("===== Hello Welcome to Water Quality Evaluator=====");
        System.out.println("Please Enter existing Username for BodyOfWater, or to Create a new profile input (new): ");
        String username = sc.nextLine();


        //Feature 3 Creating a new user profile for a new body of water in the system
        if (username.equalsIgnoreCase("new")) {
            System.out.println("Please enter a new username: ");
            username = sc.nextLine();

            // Code here is influenced by this article on how to create a new csv file in main. here it is linked: https://stackoverflow.com/questions/45890997/how-to-create-a-folder-to-further-create-csv-files-in
            String fileName = username + ".csv";
            Path sourcePath = Paths.get("src");
            Path filePath = sourcePath.resolve(fileName);

            File csvFile = new File(filePath.toString());

            try {
                // Create directories if they don't exist
                if (csvFile.getParentFile() != null) {
                    csvFile.getParentFile().mkdirs();
                }

                if (csvFile.createNewFile()) {
                    System.out.println("File Created Successfully: " + csvFile.getAbsolutePath());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the CSV file: " + e.getMessage());
            }

            // Initialize FileOutputStream with the actual file path
            try (FileOutputStream NewUserProfile = new FileOutputStream(csvFile, true); // Open file for writing (true for appending)
                 PrintWriter writer = new PrintWriter(NewUserProfile)) {

                System.out.println("Would You like to make a new entry to your profile? (y/n)");
                String entryCheck = null;
                String z = sc.nextLine();

                if (z.toLowerCase().equals("y")) {
                    entryCheck = "y";
                    System.out.println("To make a new entry there will be a given format of measurable water quality attributes, input these according to the provided format separated by commas. " +
                            "\nHere is the format for the new entry, if a certain characteristic has not been measured please enter 0 for its category.");
                }
                if (z.toLowerCase().equals("n")) {
                    entryCheck = "n";
                    System.exit(0);
                }

                // runs a while loop that runs until the user is done inputting new values
                String newLine = "";
                while (entryCheck.equals("y")) {
                    System.out.println("Date(MM/DD/YYYY), Temperature (°C), pH, Oxygen Level (mg/L), Nitrogen Level (mg/L), Phosphate Level (mg/L), Ammonia (mg/L), Nitrate (mg/L), Nitrite (mg/L), Calcium (mg/L), Magnesium (mg/L), Iodine (mg/L)");
                    newLine = sc.nextLine();
                    writer.println(newLine);

                    System.out.println("Would you like to make another entry? (y/n)");
                    String nextEntry = sc.nextLine();
                    if (!nextEntry.equalsIgnoreCase("y")) {
                        break;  // Exit loop
                    }
                }
                writer.flush();  // Ensure data is written to the file before closing
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }


        //Opens the file to be read
        FileInputStream myFile = null;

        int usernameCheck = 0;
        while (usernameCheck < 1) {
            try {
                myFile = new FileInputStream("src/" + username + ".csv");
            } // input file here
            catch (FileNotFoundException e) {
                System.out.println("File not found");
                System.out.println("There was an error with your username for your data! Please input correct username : ");
                username = sc.nextLine();
                continue;
            }
            usernameCheck++;
        }


        Scanner fileReader = new Scanner(myFile);
        Scanner console = new Scanner(System.in);
        ArrayList<BodyOfWater> waterBody = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        LinkedList stringDates = new LinkedList();


        //Creates an arraylist of type Bodyofwater with the inputted data from a data set
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] values = line.split(",");
            BodyOfWater water = new BodyOfWater(sdf.parse(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]),
                    Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]));
            waterBody.add(water);

        }

        Node dateList = null;
        fileReader.close();
        FileInputStream linkedlistfile = null;
        try {
            linkedlistfile = new FileInputStream("src/" + username + ".csv");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        Scanner dates = new Scanner(linkedlistfile);

        while (dates.hasNextLine()) {
            String line = dates.nextLine();
            String[] values = line.split(",");
            stringDates.sortedAppend(values[0]);
        }
        dates.close();
        dateList = stringDates.getHead();

        //Feature 1 - Sorts entry list of data by the date of entry
        DateComparator comparator = new DateComparator();
        sort(waterBody, waterBody.size(), comparator);
        System.out.println("=== ENTRY DATA FOR " + username + " ===");
        for (int i = 0; i < waterBody.size(); i++) {
            System.out.println(waterBody.get(i).toString());
        }

        //add feature
        int added = 0;
        try (FileOutputStream NewUserProfile = new FileOutputStream("src/" + username + ".csv", true); // Open file for writing (true for appending)
             PrintWriter writer = new PrintWriter(NewUserProfile)) {

            System.out.println("Would You like to make a new entry to your profile? (y/n)");
            String entryCheck = null;
            String z = sc.nextLine();

            if (z.toLowerCase().equals("y")) {
                entryCheck = "y";
                System.out.println("To make a new entry there will be a given format of measurable water quality attributes, input these according to the provided format separated by commas. " +
                        "\nHere is the format for the new entry, if a certain characteristic has not been measured please enter 0 for its category.");
                // runs a while loop that runs until the user is done inputting new values
                String newLine = "";
                while (entryCheck.equals("y")) {
                    System.out.println("Date(MM/DD/YYYY), Temperature (°C), pH, Oxygen Level (mg/L), Nitrogen Level (mg/L), Phosphate Level (mg/L), Ammonia (mg/L), Nitrate (mg/L), Nitrite (mg/L), Calcium (mg/L), Magnesium (mg/L), Iodine (mg/L)");
                    newLine = sc.nextLine();
                    writer.println(newLine);

                    String[] values = newLine.split(",");
                    BodyOfWater water = new BodyOfWater(sdf.parse(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Double.parseDouble(values[6]),
                            Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]));
                    waterBody.add(water);



                    dateList = null;
                    linkedlistfile = null;

                    String[] dateValues = newLine.split(",");
                    stringDates.sortedAppend(dateValues[0]);


                    dateList = stringDates.getHead();

                    sort(waterBody, waterBody.size(), comparator);
                    added++;

                    System.out.println("Would you like to make another entry? (y/n)");
                    String nextEntry = sc.nextLine();
                    if (!nextEntry.equalsIgnoreCase("y")) {
                        break;  // Exit loop
                    }
                    if (z.toLowerCase().equals("n")) {
                        entryCheck = "n";

                    }





                }
                writer.flush();  // Ensure data is written to the file before closing
            }} catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }











        // Feature 2 - allows user to either see their most recent report or asks the user for which date they would like the report made
        String answer = "";
        while (!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N"))) {
            if(added > 0){
                System.out.println("=== ENTRY DATA FOR " + username + " ===");
                for (int i = 0; i < waterBody.size(); i++) {
                    System.out.println(waterBody.get(i).toString());
                }
            }
            System.out.println("Would you like your most recent entry report? (Y/N)");
            answer = console.nextLine();
        }

        //initialize Writer for all functions
        FileOutputStream report = null;
        try {
            report = new FileOutputStream("src/Report.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Report File not found");
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(report);
        } catch (Exception e) {
            System.out.println(" Error writing to file. ");
        }







        //Most recent entry data
        if (answer.equalsIgnoreCase("Y")) {

            // go through date linked list to find right string
            String temp = stringDates.returnTail(dateList);
            String[] monthsAndDays = temp.split("/");

            //Temperature Evaluator
            int month = Integer.parseInt(monthsAndDays[0]);
            int day = Integer.parseInt(monthsAndDays[1]);
            Temperature newTemp = new Temperature(waterBody.get(waterBody.size() - 1).getTemperature());
            String temperatureEval = newTemp.EvaluateTemp(month, day);

            nutrientPollutionGroup nutrients = new nutrientPollutionGroup(waterBody.get(waterBody.size() - 1).getNitrogenLevels(), waterBody.get(waterBody.size() - 1).getPhosphorusLevels(), waterBody.get(waterBody.size() - 1).getAmmonia(), waterBody.get(waterBody.size() - 1).getNitrate(), waterBody.get(waterBody.size() - 1).getNitrite());

            writer.println("==HERE IS YOUR REPORT FOR " + temp + "==");
            //call of temperature feature
            writer.println(temperatureEval);
            //proceeds to call all rest of individual features
            BodyOfWater selected = waterBody.get(waterBody.size() - 1);
            //call for Oxygen feature
            writer.println(OxygenLevelEval(selected));
            //pH evaluation feature
            writer.println(phEval(selected));
            // Nutrient pollution
            writer.println(nutrients.nutrientPollutionEval(newTemp.getTemperature(), waterBody.get(waterBody.size() - 1).getPH()));
            //Calcium eval
            writer.println(calciumEval(selected));
            // Magnesium eval
            writer.println(magnesiumEval(selected));
            //Iodine Eval
            writer.println(iodineEval(selected));
            writer.flush();
            writer.close();
            System.out.println("Report document created successfully!");

        }






        //Specific date entry data
        else if (answer.equalsIgnoreCase("N")) {
                int user = 0;
                while(user == 0) {
                    System.out.println("Would you like an individual date report(1), or a report for a range of entries(2)?");
                    try{user = Integer.parseInt(console.nextLine());}
                    catch (Exception e ){
                        System.out.println("Please enter a valid user number");
                    }
                    if (user != 1 && user != 2) {
                        continue;
                    }
                    else{break;}
                }

                // 1 for individual report
                if (user == 2) {
                    String startDateString, endDateString;
                    Date startDate = null, endDate = null;
                    int indexOfStartDate = -1, indexOfEndDate = -1;

// Get Start Date
                    while (true) {
                        System.out.print("Enter Start Date of data for cumulative report (MM/dd/yyyy): ");
                        startDateString = console.nextLine();
                        try {
                            startDate = sdf.parse(startDateString);
                        } catch (ParseException e) {
                            System.out.println("Invalid format. Try again.");
                            continue;
                        }

                        // Find index of start date by comparing formatted dates
                        String formattedStart = sdf.format(startDate);
                        for (int i = 0; i < waterBody.size(); i++) {
                            if (sdf.format(waterBody.get(i).getDateRecorded()).equals(formattedStart)) {
                                indexOfStartDate = i;
                                break;
                            }
                        }

                        if (indexOfStartDate != -1) {
                            break;
                        }
                        System.out.println("Start date not found in records. Try again.");
                    }

// Get End Date
                    while (true) {
                        System.out.print("Enter End Date of data for cumulative report (MM/dd/yyyy): ");
                        endDateString = console.nextLine();
                        try {
                            endDate = sdf.parse(endDateString);
                        } catch (ParseException e) {
                            System.out.println("Invalid format. Try again.");
                            continue;
                        }

                        // Find index of end date by comparing formatted dates
                        String formattedEnd = sdf.format(endDate);
                        for (int i = 0; i < waterBody.size(); i++) {
                            if (sdf.format(waterBody.get(i).getDateRecorded()).equals(formattedEnd)) {
                                indexOfEndDate = i;
                                break;
                            }
                        }

                        if (indexOfEndDate != -1) {
                            break;
                        }
                        System.out.println("End date not found in records. Try again.");
                    }

                    if (indexOfStartDate > indexOfEndDate) {
                        System.out.println("Start date comes after end date. Please try again.");
                    } else {

                        RangeOfDates rangeOfDates = new RangeOfDates(startDate, endDate);
                        BodyOfWater rangeAverage = rangeOfDates.rangeReport(waterBody, indexOfStartDate);
                        Temperature newTemp = new Temperature(rangeAverage.getTemperature());
                        nutrientPollutionGroup nutrients = new nutrientPollutionGroup(rangeAverage.getNitrogenLevels(), rangeAverage.getPhosphorusLevels(), rangeAverage.getAmmonia(), rangeAverage.getNitrate(), rangeAverage.getNitrite());


                        String tempEval = newTemp.EvaluateTempAvg(rangeAverage);
                        writer.println("==HERE IS YOUR REPORT FOR " + startDateString + " to " + endDateString  + "==");
                        writer.println(tempEval);
                        writer.println(OxygenLevelEval(rangeAverage));
                        //pH evaluation feature
                        writer.println(phEval(rangeAverage));
                        //nutrient evaluation
                        writer.println(nutrients.nutrientPollutionEval(newTemp.getTemperature(),rangeAverage.getPH()));
                        //Calcium eval
                        writer.println(calciumEval(rangeAverage));
                        // Magnesium eval
                        writer.println(magnesiumEval(rangeAverage));
                        //Iodine Eval
                        writer.println(iodineEval(rangeAverage));
                        writer.flush();
                        writer.close();
                        System.out.println("Report document created successfully!");
                        System.exit(1);

                    }

            }

            String[] x = new String[1];
            int check = 1;
            int indexOfDate = 0;
            Date choice;
            while (check < 2) {
                System.out.println("For which date do you want your report: input date from table (MM/dd/yyyy)");
                x[0] = console.next();
                choice = sdf.parse(x[0]);
                for (int i = 0; i < waterBody.size(); i++) {
                    if (waterBody.get(i).getDateRecorded().equals(choice)) {
                        indexOfDate = i;
                        check++;
                    }
                }
            }

            // go through date linked list to find right string
            String temp = stringDates.remove_from_index(indexOfDate);
            String[] monthsAndDays = temp.split("/");
            //stringDates.insert_at_index(temp, indexOfDate);
            //Temperature Evaluator
            int month = Integer.parseInt(monthsAndDays[0]);
            int day = Integer.parseInt(monthsAndDays[1]);
            Temperature newTemp = new Temperature(waterBody.get(indexOfDate).getTemperature());
            nutrientPollutionGroup nutrients = new nutrientPollutionGroup(waterBody.get(indexOfDate).getNitrogenLevels(), waterBody.get(indexOfDate).getPhosphorusLevels(), waterBody.get(indexOfDate).getAmmonia(), waterBody.get(indexOfDate).getNitrate(), waterBody.get(indexOfDate).getNitrite());

            String temperatureEval = newTemp.EvaluateTemp(month, day);
            writer.println("==HERE IS YOUR REPORT FOR " + temp + "==");
            writer.println(temperatureEval);

            BodyOfWater selected = waterBody.get(indexOfDate);
            //proceeds to call all rest of individual features
            writer.println(OxygenLevelEval(selected));
            //pH evaluation feature
            writer.println(phEval(selected));
            //nutrient evaluation
            writer.println(nutrients.nutrientPollutionEval(newTemp.getTemperature(), waterBody.get(indexOfDate).getPH()));
            //Calcium eval
            writer.println(calciumEval(selected));
            // Magnesium eval
            writer.println(magnesiumEval(selected));
            //Iodine Eval
            writer.println(iodineEval(selected));
            writer.flush();
            writer.close();
            System.out.println("Report document created successfully!");


        }
    }

        //Makes use of bubble sorting to sort the arraylist based on the dates of the entries.
        public static void sort (ArrayList < BodyOfWater > Information,int n, DateComparator comparator){
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (comparator.compare(Information.get(j), Information.get(j + 1)) > 0) {
                        //call swap method
                        swap(Information, j, j + 1);
                    }
                }
            }
        }
        public static void swap (ArrayList < BodyOfWater > Information,int n, int j ){
            BodyOfWater temp = Information.get(n);
            Information.set(n, Information.get(j));
            Information.set(j, temp);

        }


    public static String OxygenLevelEval(BodyOfWater entry) {
        double oxygen = entry.getOxygenLevel();
        double temperature = entry.getTemperature();
            // data table of approximate temperature and 100% saturation level
            double[] SaturatedLevel = {14.6, 14.2, 13.8, 13.5, 13.1, 12.8, 12.5, 12.1, 11.8, 11.6, 11.3, 11.0, 10.8, 10.5, 10.3,
                    10.1, 9.9, 9.7, 9.6, 9.3, 9.1, 8.9, 8.7, 8.6, 8.4, 8.3, 8.1, 8.0, 7.8, 7.7, 7.6, 7.5};
            Math.round(temperature);
            int index = 32 - (int) temperature;
            double maxDissolvedOxygen = SaturatedLevel[index];
            double answer = (oxygen / maxDissolvedOxygen);
            double roundedAnswer = (Math.round(answer * 100.0) / 100.0);
            double percentDO = answer * 100;


            String evaluation = "";

            evaluation += "\n===== Oxygen Level Evaluation =====" + "\nDissolved Oxygen: " + Math.round(oxygen * 100.0) / 100.0 +
                    "                      Saturation Level of Dissolved Oxygen: %" + Math.round(percentDO * 100.0) / 100.0;
            if (oxygen < 4.0) {
                evaluation += "\nYour Oxygen Level of " + roundedAnswer + " mg/L is Concerning!" +
                        "\nWhen dissolved oxygen levels fall below 4.0 mg/L in an environment, it is harmful to aquatic life. Many fish and other organisms rely on oxygen levels higher than this, thus most all fish in this ecosystem are at significant risk of death. This level is particularly common in areas with high pollution or stagnant water, where oxygen depletion occurs more quickly.";
                return evaluation;
            }
            if (4.0 < oxygen && oxygen < 6.5) {
                evaluation += "\nYour Oxygen Level of " + roundedAnswer + " mg/L requires monitoring. " +
                        "\nOxygen levels between 4.0 and 6.5 mg/L are generally considered acceptable for most aquatic life. While some species may still experience stress at the lower end of this range, most fish and organisms can thrive. However, it's important to monitor oxygen levels closely, as prolonged periods near the lower end can still impact the health of sensitive species and disrupt the balance of the ecosystem.";
                return evaluation;
            }
            if (6.5 < oxygen && oxygen < 9.5) {
                evaluation += "\nYour Oxygen Level of " + roundedAnswer + " mg/L is generally good." +
                        "\nThe dissolved oxygen (DO) level ranging from 6.5 to 9.5 mg/L indicates a healthy aquatic environment. This range is typically sufficient to support a variety of aquatic life, including fish and invertebrates, as most species require DO levels within this range for proper respiration and metabolic function. However, levels closer to the upper end of this range are ideal, as oxygen-rich waters promote biodiversity and ecosystem stability.";
                return evaluation;
            }
            if (9.5 < oxygen && oxygen < 12.0) {
                evaluation += "\nYour Oxygen Level of " + roundedAnswer + " mg/L is considered ideal!" +
                        "\nA dissolved oxygen (DO) level between 9.5 and 12 mg/L suggests very well-oxygenated water, which is ideal for supporting a diverse and healthy aquatic ecosystem. At these levels, most aquatic organisms can thrive, with increased oxygen availability promoting higher metabolic rates and supporting more species. This range is often seen in fast-moving streams, colder waters, or areas with high photosynthetic activity. However, while it is beneficial for most aquatic life, excessive oxygen levels can occasionally stress certain species if they fluctuate rapidly.";
                return evaluation;
            }
            return evaluation;
        }
    public static String phEval(BodyOfWater entry) {
        double ph = entry.getPH();
            String evaluation = "\n===== pH EVALUATION =====";


            if (ph > 0 && ph <= 5) {
                evaluation += "\npH Range: 0 to 5 (Highly Acidic to Moderately Acidic)\n" +

                        "Characteristics: Strongly acidic, potentially dangerous for most aquatic life.\n" +
                        "Effects:\n" +
                        "   Aquatic Life: Most aquatic organisms, including fish, invertebrates, and plants, will not survive in this environment. The water may be inhospitable, leading to the death of sensitive species.\n" +

                        "   Ecosystem: Acidic conditions disrupt the water's natural chemical balance, harming ecosystems. Acid rain or pollution could contribute to these low pH levels.\n" +

                        "   Water Chemistry: Low pH can increase the solubility of toxic metals (e.g., aluminum), which can further harm aquatic life and degrade water quality.\n" +

                        "   Immediate Action: Immediate intervention is required to neutralize the acidity and restore a safer pH level, typically by adding alkaline substances (e.g., lime).\n" +

                        "   Toxicity Risk: Extremely high; no aquatic life can thrive, and the water is likely to cause significant damage to biological systems.\n";
                return evaluation;
            } else if (ph > 5 && ph < 6.5) {
                evaluation += "\npH Range 5 to 6.5 (Mildly Acidic)\n" +
                        "A pH between 5 and 6.5 indicates mildly acidic water, which can affect water quality and aquatic life." +

                        "   Water Chemistry: Mild acidity may increase the solubility of harmful metals and reduce nutrient availability for aquatic organisms."

                        + "\n  Impact on Aquatic Life: Species sensitive to pH, like fish and invertebrates, may experience stress, impacting growth and reproduction. Some species are more tolerant of these conditions."

                        + "\n  Environmental and Human Impacts: Prolonged acidification can harm ecosystems and biodiversity. The water may be safe for recreational use but could require treatment for drinking and may corrode infrastructure over time.";
                return evaluation;
            } else if (ph >= 6.5 && ph <= 8.5) {
                evaluation += "\npH Range 6.5 to 8.5 (IDEAL)"
                        + "A pH range of 6.5 to 8.5 is ideal for most aquatic life, supporting a healthy and stable environment for both freshwater and marine species.\n" +

                        "   Water Quality: Water within this pH range maintains optimal conditions for nutrient cycling, oxygen availability, and overall water chemistry, ensuring that essential minerals and nutrients are accessible to aquatic organisms.\n" +

                        "   Impact on Aquatic Life:\n" +

                        "   Fish and Invertebrates: Most species of fish, amphibians, and invertebrates thrive in this pH range. It supports proper metabolic function, growth, and reproduction.\n" +

                        "   Plant Life: Aquatic plants also grow well within this pH range, contributing to oxygen production and supporting the food chain.\n" +

                        "   Ecosystem Health: Stable pH within this range helps preserve biodiversity and supports a balanced ecosystem, fostering interactions between species and maintaining the food web.";
                return evaluation;
            } else if (ph > 8.5 && ph < 11) {
                evaluation += "\npH Range 8.5 to 11 (Alkaline) \n" + "A pH range of 8.5 to 11 is considered alkaline and may be suitable for certain aquatic environments but can be challenging for many species.\n" +

                        "   Water Quality: Water in this pH range tends to have higher concentrations of dissolved minerals and may affect the solubility of essential nutrients, making them less available to aquatic organisms. Excessively high pH can also lead to an imbalance in the overall water chemistry.\n" +

                        "   Impact on Aquatic Life:\n" +

                        "   Fish and Invertebrates: Many freshwater fish and invertebrates struggle to survive in this pH range. Species like trout and salmon are particularly sensitive to high pH levels. However, some species, like certain types of cichlids or fish in alkaline waters, may tolerate higher pH.\n" +

                        "   Plants: Aquatic plants may experience nutrient deficiencies as the high pH affects nutrient uptake, which could stunt growth and disrupt the ecosystem.\n" +

                        "   Ecosystem Health: At this pH, the balance of the ecosystem can be disrupted, leading to reduced biodiversity. Excessive alkalinity can harm the reproduction and survival of sensitive species, leading to population declines.\n";
                return evaluation;
            } else if (ph >= 11 && ph <= 14) {
                evaluation += "\npH Range 11 to 14 (Highly Alkaline)\n" + "A pH range of 11 to 14 is highly alkaline and generally unsuitable for most aquatic life.\n" +

                        "   Water Quality: At these extreme pH levels, the water becomes very caustic, which can severely alter the chemistry of the water. The solubility of many essential nutrients becomes significantly reduced, and harmful compounds like ammonia may become more toxic.\n" +

                        "   Impact on Aquatic Life:\n" +

                        "   Fish and Invertebrates: Most aquatic organisms, including fish and invertebrates, cannot survive in such high pH conditions. The extreme alkalinity can cause physical damage to tissues, impair breathing, and disrupt metabolic functions, often leading to death.\n" +

                        "   Plants: Aquatic plants would likely die or experience severe stress, as they cannot thrive in such high pH environments.\n" +

                        "   Ecosystem Health: The ecosystem would be unsustainable at these pH levels. Biodiversity would be severely impacted, and most life forms would not be able to survive, leading to a collapse of the ecosystem.";
                return evaluation;
            }
            return evaluation;
        }
    public static String calciumEval(BodyOfWater entry) {
        double calcium = entry.getCalcium();
            String evaluation = "\n===== CALCIUM EVALUATION =====";

            if (calcium < 0) {
                return evaluation + "\n Error: Invalid calcium concentration. Value must be ≥ 0.";
            }

            if (calcium <= 5) {
                evaluation += "\nCalcium Range: 0–5 mg/L (Very Soft Water)\n" +
                        "Characteristics: Water is extremely soft, low in minerals.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: May stress hard water species like snails and crustaceans.\n" +
                        "   - Ecosystem: Limited buffering capacity — pH may fluctuate more easily.\n" +
                        "   - Water Chemistry: Low calcium reduces ionic stability, possibly impacting shell formation and osmoregulation.\n";
            } else if (calcium <= 15) {
                evaluation += "\nCalcium Range: 6–15 mg/L (Soft Water)\n" +
                        "Characteristics: Still soft, but less extreme. Common in rain-fed or mountainous areas.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Generally safe for most fish, but not ideal for species needing harder water.\n" +
                        "   - Ecosystem: Stable in low-nutrient systems, but sensitive to acidification.\n";
            } else if (calcium <= 40) {
                evaluation += "\nCalcium Range: 16–40 mg/L (Moderately Hard Water)\n" +
                        "Characteristics: Balanced mineral content, typical of many freshwater systems.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Ideal for most species — supports shell development, nerve function, and osmoregulation.\n" +
                        "   - Water Chemistry: Good buffering capacity, helps maintain pH stability.\n";
            } else if (calcium <= 100) {
                evaluation += "\nCalcium Range: 41–100 mg/L (Hard Water)\n" +
                        "Characteristics: Elevated mineral content, common in groundwater-fed systems.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Suitable for hard water fish; may inhibit growth of some plants.\n" +
                        "   - Ecosystem: Supports biological processes, but may affect nutrient cycling.\n";
            } else {
                evaluation += "\nCalcium Range: >100 mg/L (Very Hard Water)\n" +
                        "Characteristics: Mineral-rich, often linked to limestone or urban areas.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Suitable for some fish, but may cause scaling and reduce plant growth.\n" +
                        "   - Water Chemistry: High calcium can reduce solubility of phosphate, affecting productivity.\n" +
                        "   - Ecosystem: May alter nutrient availability and biodiversity over time.\n";
            }

            return evaluation;
        }
        //magnesium evaluation
        public static String magnesiumEval(BodyOfWater entry) {
            double magnesium = entry.getMagnesium();  // in mg/L
            String evaluation = "\n===== MAGNESIUM EVALUATION =====";

            if (magnesium < 0) {
                return evaluation + "\n Error: Invalid magnesium concentration. Value must be ≥ 0.";
            }

            if (magnesium <= 5) {
                evaluation += "\nMagnesium Range: ≤ 5 mg/L (Very Low)\n" +
                        "Characteristics: Very soft water, often found in high rainfall or mountainous areas.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: May lead to mineral deficiency in species that rely on magnesium for metabolic and muscular functions.\n" +
                        "   - Ecosystem Impact: Low buffering capacity; water is more vulnerable to acidification.\n" +
                        "   - Biological Role: Magnesium is a cofactor in many enzymatic reactions, and deficiency may impact plant and invertebrate health.\n";
            } else if (magnesium <= 15) {
                evaluation += "\nMagnesium Range: 6–15 mg/L (Soft to Moderate)\n" +
                        "Characteristics: Common in many freshwater systems.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Generally safe and supports metabolic health.\n" +
                        "   - Ecosystem: Helps stabilize water chemistry, though levels may still be low for hard-water species like mollusks and snails.\n";
            } else if (magnesium <= 30) {
                evaluation += "\nMagnesium Range: 16–30 mg/L (Moderately Hard)\n" +
                        "Characteristics: Balanced level, often associated with healthy freshwater ecosystems.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Supports shell-building, nerve function, and osmoregulation.\n" +
                        "   - Ecosystem: Helps buffer pH fluctuations and improves habitat stability.\n";
            } else if (magnesium <= 60) {
                evaluation += "\nMagnesium Range: 31–60 mg/L (Hard Water)\n" +
                        "Characteristics: High mineral content, common in groundwater-fed or limestone-rich areas.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Generally well-tolerated by most species; preferred by some.\n" +
                        "   - Water Chemistry: May reduce solubility of other minerals (e.g., phosphates).\n" +
                        "   - Human Impact: Can contribute to scaling in pipes but is safe for drinking and recreation.\n";
            } else {
                evaluation += "\nMagnesium Range: >60 mg/L (Very Hard Water)\n" +
                        "Characteristics: Unusually high magnesium levels.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: May limit biodiversity; some freshwater species may struggle to adapt.\n" +
                        "   - Ecosystem: Could alter nutrient cycling and increase water hardness-related issues.\n" +
                        "   - Source: Often due to geological formations or human activity such as mining runoff.\n";
            }

            return evaluation;
        }


    public static String iodineEval(BodyOfWater entry) {
        double iodine = entry.getIodine(); // Assumes iodine is in mg/L
            String evaluation = "\n===== IODINE EVALUATION =====";

            if (iodine < 0) {
                return evaluation + "\n Error: Invalid iodine concentration. Value must be ≥ 0.";
            }

            if (iodine <= 0.0005) {
                evaluation += "\nIodine Range: ≤ 0.0005 mg/L (Deficient)\n" +
                        "Characteristics: Extremely low iodine concentration, typical of freshwater systems.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Iodine is an essential trace element for thyroid function in fish and amphibians.\n" +
                        "   - Deficiency Risk: May lead to developmental issues, hormone imbalances, and reduced reproduction in some species.\n" +
                        "   - Ecosystem Impact: Rarely toxic at this level, but prolonged deficiency may affect population stability of iodine-dependent species.\n";
            } else if (iodine <= 0.005) {
                evaluation += "\nIodine Range: 0.0006–0.005 mg/L (Sufficient for Most Ecosystems)\n" +
                        "Characteristics: Normal range for iodine in natural surface waters, particularly coastal or estuarine areas.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Supports healthy thyroid function, metabolism, and growth in aquatic animals.\n" +
                        "   - Ecosystem: Optimal for biodiversity and reproductive health in both freshwater and brackish systems.\n";
            } else if (iodine <= 0.05) {
                evaluation += "\nIodine Range: 0.005–0.05 mg/L (Elevated but Typically Safe)\n" +
                        "Characteristics: Higher-than-normal iodine concentration, may occur naturally near marine outflows or due to anthropogenic influence.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Generally tolerable, though prolonged exposure may affect iodine-sensitive organisms.\n" +
                        "   - Ecosystem Health: Monitor for accumulation in sediments and uptake in food chains.\n";
            } else if (iodine <= 0.1) {
                evaluation += "\nIodine Range: 0.05–0.1 mg/L (High)\n" +
                        "Characteristics: Potential contamination from industrial or medical sources.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: May cause metabolic disturbances in some species.\n" +
                        "   - Ecosystem: Risk of bioaccumulation and trophic magnification.\n" +
                        "   - Recommendation: Investigate sources and consider water treatment if consistent readings occur.\n";
            } else {
                evaluation += "\nIodine Range: > 0.1 mg/L (Very High)\n" +
                        "Characteristics: Unnatural iodine concentrations, often a result of industrial pollution or disinfection byproducts.\n" +
                        "Effects:\n" +
                        "   - Aquatic Life: Toxicity likely in iodine-sensitive organisms.\n" +
                        "   - Ecosystem: High risk of bioaccumulation and ecological disruption. Urgent investigation recommended.\n";
            }

            return evaluation;
        }

    }








