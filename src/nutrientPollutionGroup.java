public class nutrientPollutionGroup {
    //These are often evaluated together when assessing pollution, especially from agricultural runoff or wastewater.
    //Nitrogen Level (mg/L)
    //Phosphate Level (mg/L)
    //Ammonia (mg/L)
    //Nitrate (mg/L)
    //Nitrite (mg/L)
    public double nitrogen;
    public double phosphate;
    public double ammonia;
    public double nitrate;
    public double nitrite;



    //default
    public nutrientPollutionGroup (){
        nitrogen = 0;
        phosphate = 0;
        ammonia = 0;
        nitrate = 0;
        nitrite = 0;
    }
    //parameterized
    public nutrientPollutionGroup (double nitrogen, double phosphate, double ammonia, double nitrate, double nitrite){
        this.nitrogen = nitrogen;
        this.phosphate = phosphate;
        this.ammonia = ammonia;
        this.nitrate = nitrate;
        this.nitrite = nitrite;
    }

    public String nutrientPollutionEval (double temperature, double pH){
        //This is the class that will ultimately return the evaluation string
        // all of these methods will return a number 0-4 based on the risk associated with the parameters level along with the temperature
        int phosphateRisk = PhosphateEval(); //phosphate less affected by temperature so i didn't pass it in
        int ammoniaRisk = ammoniaEval(temperature, pH);
        int nitrateRisk = NitrateEval(nitrate, temperature);
        int nitriteRisk = NitriteEval(nitrite, temperature);
        int nitrogenRisk = NitrogenEval(nitrogen, temperature);



        String evaluation = "\n=====NUTRIENT POLLUTION GROUP==========\n" +
                "Phosphate, nitrate, nitrite, ammonia, and nitrogen are essential nutrients that support plant and algal growth in aquatic ecosystems. In balanced amounts, they sustain healthy food webs. " +
                "\nHowever, excessive levels can trigger algal blooms, reduce oxygen levels, and disrupt aquatic life — a process known as eutrophication.\n";
        // Phosphate
        if (phosphateRisk == -1) {
            evaluation += "Error: Invalid phosphate level. Please enter a non-negative value.\n";
        }
        else if (phosphateRisk == 0) {
            evaluation += "Phosphate levels are safe ✅ — minimal risk to the ecosystem.\n";
        } else if (phosphateRisk == 1) {
            evaluation +="Phosphate levels slightly elevated ⚠️ — monitor for potential algae growth.\n";
        } else if (phosphateRisk == 2) {
            evaluation +="Phosphate levels are elevated 🟠 — increased risk of eutrophication.\n";
        } else if (phosphateRisk == 3) {
            evaluation +="High phosphate levels 🔴 — water is polluted, expect algal blooms.\n";
        } else if (phosphateRisk == 4) {
            evaluation +="Critical phosphate pollution ⚫ — severe threat to aquatic life.\n";
        }

        // Ammonia
        if (ammoniaRisk == -1) {
            evaluation += "Error: Invalid ammonia level. Please enter a non-negative value.\n";
        }
        else if (ammoniaRisk == 0) {
            evaluation +="Ammonia levels are safe ✅ — no immediate toxicity concerns.\n";
        } else if (ammoniaRisk == 1) {
            evaluation += "Ammonia levels slightly elevated ⚠️ — could stress sensitive species.\n";
        } else if (ammoniaRisk == 2) {
            evaluation +="Moderate ammonia pollution 🟠 — risky for long-term aquatic health.\n";
        } else if (ammoniaRisk == 3) {
            evaluation +="High ammonia levels 🔴 — potentially toxic to fish and invertebrates.\n";
        } else if (ammoniaRisk == 4) {
            evaluation +="Severe ammonia pollution ⚫ — immediate danger to aquatic life.\n";
        }

        // Nitrate
        if (nitrateRisk == -1) {
            evaluation += "Error: Invalid nitrate level. Please enter a non-negative value.\n";
        }
        else if (nitrateRisk == 0) {
            evaluation +="Nitrate levels are within safe limits ✅ — no immediate risk.\n";
        } else if (nitrateRisk == 1) {
            evaluation +="Nitrate levels are slightly elevated ⚠️ — may contribute to nutrient loading.\n";
        } else if (nitrateRisk == 2) {
            evaluation +="Nitrate levels are elevated 🟠 — potential to fuel algal blooms.\n";
        } else if (nitrateRisk == 3) {
            evaluation +="High nitrate levels 🔴 — could significantly impact water quality.\n";
        } else if (nitrateRisk == 4) {
            evaluation +="Very high nitrate levels ⚫ — serious environmental concern.\n";
        }

        // Nitrite
        if (nitriteRisk == -1) {
            evaluation += "Error: Invalid nitrite level. Please enter a non-negative value.\n";
        }
        else if (nitriteRisk == 0) {
            evaluation +="Nitrite levels are safe ✅ — no immediate health risk.\n";
        } else if (nitriteRisk == 1) {
            evaluation +="Nitrite levels slightly elevated ⚠️ — potential stress on aquatic organisms.\n";
        } else if (nitriteRisk == 2) {
            evaluation +="Moderate nitrite levels 🟠 — may disrupt oxygen transport in fish.\n";
        } else if (nitriteRisk == 3) {
            evaluation +="High nitrite levels 🔴 — dangerous for aquatic life, especially fish.\n";
        } else if (nitriteRisk == 4) {
            evaluation +="Critical nitrite pollution ⚫ — severe toxicity, urgent action needed.\n";
        }

        // Nitrogen
        if (nitrogenRisk == -1) {
            evaluation += "Error: Invalid nitrogen level. Please enter a non-negative value.\n";
        }
        else if (nitrogenRisk == 0) {
            evaluation +="Total nitrogen levels are acceptable ✅ — ecosystem remains balanced.\n";
        } else if (nitrogenRisk == 1) {
            evaluation +="Nitrogen slightly elevated ⚠️ — keep monitoring.\n";
        } else if (nitrogenRisk == 2) {
            evaluation +="Moderate nitrogen presence 🟠 — may promote unwanted biological activity.\n";
        } else if (nitrogenRisk == 3) {
            evaluation +="High nitrogen levels 🔴 — increased risk of eutrophication.\n";
        } else if (nitrogenRisk == 4) {
            evaluation +="Very high nitrogen ⚫ — likely to degrade ecosystem health severely.\n";
        }

        return evaluation;

    }

    //nitrite Eval
    public int NitriteEval(double nitrite, double temperature) {
        if (nitrite < 0 || temperature < 0) { // Error case
            return -1;  // Invalid nitrite or temperature level
        }

        // Base risk level based on nitrite concentration
        int baseRisk;
        if (nitrite <= 0.01) { // Safe
            baseRisk = 0;
        }
        else if (nitrite <= 0.03) { // Low risk
            baseRisk = 0;
        }
        else if (nitrite <= 0.1) { // Moderate risk
            baseRisk = 1;
        }
        else if (nitrite <= 0.3) { // Elevated risk
            baseRisk = 2;
        }
        else if (nitrite <= 0.5) { // High risk
            baseRisk = 3;
        }
        else if (nitrite > 0.5) { // Very high risk
            baseRisk = 4;
        } else {
            return -1;  // Invalid nitrite level (shouldn't occur)
        }

        // Adjust base risk based on temperature
        if (temperature < 10) {
            // Lower biological activity at low temperatures, reduced nitrite impact
            baseRisk -= 1; // Reduce risk level by 1
        } else if (temperature > 20) {
            // Faster biochemical activity at higher temperatures, increased nitrite risk
            baseRisk += 1; // Increase risk level by 1
        }

        // Ensure risk is within the valid range (0 to 4)
        if (baseRisk < 0) {
            baseRisk = 0;
        } else if (baseRisk > 4) {
            baseRisk = 4;
        }

        return baseRisk;
    }
    //nitrate evaluation
    public int NitrateEval(double nitrate, double temperature) {
        if (nitrate < 0 || temperature < 0) { // Error case
            return -1;  // Invalid input
        }

        // Base risk level based on nitrate concentration
        int baseRisk;
        if (nitrate <= 1) {         // Safe
            baseRisk = 0;
        } else if (nitrate <= 3) {  // Low risk
            baseRisk = 0;
        } else if (nitrate <= 10) { // Moderate risk
            baseRisk = 1;
        } else if (nitrate <= 20) { // Elevated risk
            baseRisk = 2;
        } else if (nitrate <= 40) { // High risk
            baseRisk = 3;
        } else {   // Very high risk
            baseRisk = 4;
        }

        // Adjust based on temperature
        if (temperature < 10) {
            baseRisk -= 1; // Low temps → slower biological activity → lower impact
        } else if (temperature > 20) {
            baseRisk += 1; // High temps → faster eutrophication → increased impact
        }

        //  risk level to valid range (0–4)
        if (baseRisk < 0) {
            baseRisk = 0;
        } else if (baseRisk > 4) {
            baseRisk = 4;
        }

        return baseRisk;
    }


    //ammonia eval
    public int ammoniaEval(double temperature, double pH){
        double a = calculateUnIonizedNH3(ammonia, temperature, pH); //unionizedAmmoniaLevel
        if (a < 0 ){
            return -1;
        }
        else if (a <= .01 ){
            return 0;
        }
        else if (a <= .02 ){
            return 0;
        }
        else if (a <= .05 ){ //Moderate risk begin caution messaging
            return 1;
        }
        else if (a <= .1 ){ //moderately high risk
            return 2;
        }
        else if (a <= .2 ){ //high risk
            return 3;
        }
        else { //very high risk
            return 4;
        }

    }
    public static double calculateUnIonizedNH3(double totalAmmonia, double temp, double pH) {
        // Estimate pKa based on temperature
        double pKa = 0.09018 + (2729.92 / (273.15 + temp));

        // Calculate the fraction of un-ionized NH3
        double nh3Fraction = 1 / (1 + Math.pow(10, (pKa - pH)));

        // Calculate un-ionized NH3 (mg/L)
        double unIonizedNH3 = totalAmmonia * nh3Fraction;

        return unIonizedNH3;
    }


    //phosphate evaluation
    public int PhosphateEval (){
        if (phosphate < 0){ //error
            return -1;
        }
        else if (phosphate <= .01){
            return 0;
        }
        else if (phosphate <= .03){
            return 0;
        }
        else if (phosphate <= .1){ //caution level
            return 1;
        }
        else if (phosphate <= .3){ //elevated risk
            return 2;
        }
        else if (phosphate <= .5){ //high risk Polluted!
            return 3;
        }
        else if (phosphate > .5){ //very polluted
            return 4;
        }
        return -1;
    }



    public int NitrogenEval(double nitrogen, double temperature) {
            if (nitrogen < 0 || temperature < 0) { // Error case
                return -1;  // Invalid nitrogen or temperature level
            }

            // Base risk level based on nitrogen concentration (same as before)
            int baseRisk;
            if (nitrogen <= 1) { // Safe
                baseRisk = 0;
            }
            else if (nitrogen <= 3) { // Low risk
                baseRisk = 0;
            }
            else if (nitrogen <= 10) { // Moderate risk
                baseRisk = 1;
            }
            else if (nitrogen <= 20) { // Elevated risk
                baseRisk = 2;
            }
            else if (nitrogen <= 50) { // High risk
                baseRisk = 3;
            }
            else if (nitrogen > 50) { // Very high risk
                baseRisk = 4;
            } else {
                return -1;  // Invalid nitrogen level (shouldn't occur)
            }

            // Adjust base risk based on temperature
            if (temperature < 10) {
                // Lower biological activity at low temperatures, lower nitrogen impact
                baseRisk -= 1; // Reduce risk level by 1
            } else if (temperature > 20) {
                // Faster biochemical activity at higher temperatures, increased risk
                baseRisk += 1; // Increase risk level by 1
            }

            // Ensure risk is within the valid range (0 to 4)
            if (baseRisk < 0) {
                baseRisk = 0;
            } else if (baseRisk > 4) {
                baseRisk = 4;
            }

            return baseRisk;
        }
    }


