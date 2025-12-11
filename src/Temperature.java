public class Temperature {
    public double temperature;


    public Temperature (){
        this.temperature = 0.0;

    }
    public Temperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    //create a temperature evaluator method based on the month passed in
    public String EvaluateTemp (int month, int day){
        String evaluation = "=======Temperature Evaluation==========\n";
        //Evaluates for fall
        if(month == 9 && day >= 22 || month == 10 || month == 11 ){
            evaluation += "According to your date inputted you are in the season of fall or autumn, with a recorded temperature of " + Math.round(temperature * 100.0) / 100.0 + "\n";
            // Low temperature
            if (temperature >= 4.0 && temperature < 6.0 ) {
                evaluation += "\nIt appears that your body of water's temperature is lower than typical for the Fall/autumn." + "A lower-than-normal water temperature in a body of water can disrupt local ecosystems. " +
                        "Cold temperatures can stress aquatic species, especially those reliant on stable thermal conditions, affecting growth, reproduction, and survival. \nProlonged cooling may alter species composition and food webs, potentially leading to shifts in biodiversity."
                + "\n It is recommended that the temperature be monitored closely from this point so as to ensure temperature does not prematurely fall to low levels. ";
                return evaluation;
            }

            // good temperature
            else if (temperature >= 6 && temperature <= 15){
                evaluation += "\nIt appears that your body of water's temperature is in the ideal range for the Fall/autumn."+ "A water temperature within the normal range for a body of water supports healthy ecosystems. " +
                        "\nSeasonal cooling allows species adapted to cooler temperatures, such as certain fish, to thrive and engage in natural behaviors like migration and breeding. This stability helps maintain biodiversity and supports balanced food webs, promoting overall ecosystem health.";
                return evaluation;

            }

            //higher temperature
            else if (temperature > 15 && temperature < 23){
                evaluation += "\nIt appears your body of water's temperature is at an above average level. "+ "Warmer temperatures may disrupt the natural behavior of species, potentially delaying migration or breeding cycles. It can also lead to reduced oxygen levels, impacting fish and other organisms. " +
                        "\nProlonged warmth could promote harmful algal blooms, further threatening ecosystem health and biodiversity." + " It is recommended that the temperature be monitored from this point on to ensure temperature does not remain at high levels. ";
                return evaluation;
            }
            //Temperature falls into either of the extrema
            else {
                evaluation += "\nIt appears that your body of water falls into an extreme temperature range beyond the bounds of the fall season." + "An extreme temperature, either very cool or very warm, for this time of year in a body of water can have significant ecological impacts. " +
                        "\nExtremely cool temperatures may force cold-sensitive species to migrate or reduce their activity, disrupting food webs and breeding cycles. Conversely, extremely warm temperatures can deplete oxygen levels, stress aquatic life, and promote harmful algal blooms, which can lead to fish kills and loss of biodiversity. " +
                        "\nBoth extremes can destabilize ecosystems and require close monitoring to assess long-term effects. Close monitoring is encouraged. ";
                return evaluation;
            }
        }
        //Evaluates for winter
        else if (month == 12 || month == 1 || month == 2 || month == 3 && day <= 20 ){
            evaluation += "According to your date inputted you are in the season of winter, with a recorded temperature of " + + Math.round(temperature * 100.0) / 100.0 + "\n";
            //low temperature
            if (temperature >= 0.0 && temperature <= 2.0 ){
                evaluation += "\nIt appears that your body of water's temperature is lower than typical for the winter." +
                        "\nCold temperatures may reduce metabolic rates of species, limiting feeding and growth. Prolonged low temperatures can also lead to lower oxygen levels, stressing fish and other aquatic organisms. " +
                        "\nIn extreme cases, it can cause ice formation, restricting habitat availability and disrupting species' ability to move or find food. Such conditions can negatively impact biodiversity and overall ecosystem health. " +
                        "\n Closer monitoring is suggested.";
                return evaluation;
            }
            //good temperature
            else if ( temperature > 2.0 && temperature <= 9.0 ){
                evaluation += "\nIt appears that your body of water's temperature is in the ideal range for the Winter."+ "A water temperature within the normal range for a body of water supports healthy ecosystems. " +
                        "\nSpecies adapted to colder conditions can continue their seasonal behaviors, such as slow feeding and reduced metabolic rates. This temperature range helps maintain oxygen levels and supports " +
                        "\nthe natural food web, promoting ecological balance. Overall, normal winter temperatures allow ecosystems to function efficiently and sustainably.";
                return evaluation;
            }
            //higher water temperature
            else if (temperature >= 9.1 && temperature <= 13.0 ){
                evaluation += "Your body of water appears to have a higher temperature than typical for the winter. " +
                        "\nA higher-than-normal water temperature during winter in a body of water can disrupt seasonal cycles in aquatic ecosystems. Warmer temperatures may cause species to become more active earlier than usual, potentially affecting breeding and migration patterns. " +
                        "\nIt can also lead to lower oxygen levels, stressing aquatic life. Additionally, higher temperatures may encourage the growth of harmful algae blooms, which can degrade water quality and harm species dependent on the ecosystem." +
                        "\n Prolonged warmth can destabilize the ecosystem and threaten biodiversity. Closer monitoring is suggested. ";
                return evaluation;
            }
            else{
                evaluation += "Your body of water falls outside of the normal range of high and low temperatures for the winter. " +
                        "A prolonged extreme high or low water temperature during winter in a U.S. body of water can severely disrupt ecosystems. Extremely cold temperatures can reduce oxygen levels, stress or even kill fish, and limit species' ability to find food. " +
                        "\nConversely, prolonged warmth can lead to reduced oxygen availability, trigger harmful algal blooms, and cause premature behavioral changes in species, such as early breeding or migration. " +
                        "\nBoth extremes can destabilize food webs, reduce biodiversity, and harm the overall health of the ecosystem. Close monitoring encouraged.";
                return evaluation;
            }
        }
        //evaluates for spring
        else if (month == 3 && day >= 21 || month == 4 || month == 5 || month == 6 && day <= 20 ){
            evaluation += "According to your date inputted you are in the season of spring, with a recorded temperature of " + Math.round(temperature * 100.0) / 100.0 + "\n";
            //low temp
            if (temperature >= 2.0 && temperature <= 5.0 ){
                evaluation += "\nIt appears that your body of water's temperature is lower than typical for the spring." +
                        "\nA lower-than-normal water temperature during the spring season in a body of water can delay the growth and development of aquatic species. Cold temperatures may slow metabolic rates, affecting feeding, reproduction, and migration patterns. " +
                        "\nThis could disrupt seasonal cycles and food webs, potentially leading to a decrease in species' survival rates. " +
                        "Additionally, prolonged cold can reduce the availability of oxygen in the water, further stressing aquatic life and impacting overall ecosystem health. Closer monitoring is suggested.";

                return evaluation;
            }
            else if ( temperature > 5.0 && temperature <= 11.0 ){
                evaluation += "Your body of water falls into a good temperature range, typical for the spring. " +
                "\nA normal water temperature during the spring season in a U.S. body of water supports the natural rhythm of ecosystems. It allows species to resume feeding, reproduction, and migration cycles at the appropriate time, promoting healthy growth and development. " +
                        "\nStable temperatures help maintain oxygen levels, supporting biodiversity and balanced food webs. Normal spring temperatures contribute to a thriving ecosystem and the successful renewal of aquatic life.";
                return evaluation;
            }
            else if ( temperature > 11.1 && temperature < 16.0 ){
                evaluation += "The temperature of your body of water is higher than typical for this time of year. " + "\n A higher-than-normal water temperature during the spring season in a body of water can disrupt natural aquatic cycles. " +
                        "\nWarmer temperatures may cause species to become active earlier than usual, potentially leading to mismatches in food availability or altered breeding patterns. It can also reduce oxygen levels, stressing aquatic organisms and promoting harmful algal blooms. " +
                        "\nThese changes can destabilize food webs, reduce biodiversity, and impact the overall health of the ecosystem.\n" + "Closer monitoring is suggested.";
                        ;
                return evaluation;
            }
            else{
                evaluation += "The temperature of your body of water is outside the normal range of high and low temperatures for the spring season. " +
                        "\nExtremely low temperatures can delay species' growth, reproduction, and migration, potentially leading to higher mortality rates and a disruption of food webs. " +
                        "\nOn the other hand, extreme warmth can reduce oxygen levels, promote harmful algal blooms, and cause species to become prematurely active, resulting in mismatches in food availability and reproductive timing. " +
                        "\nBoth extremes can destabilize ecosystems, harm biodiversity, and hinder the natural seasonal processes. Close monitoring encouraged.";
                return evaluation;
            }
        }
        //evaluates for summer
        else if (month == 6 && day >= 20 || month == 7 || month == 8 || month == 9 && day <= 20 ){
            evaluation += "According to your date inputted you are in the season of summer, with a recorded temperature of " + Math.round(temperature * 100.0) / 100.0 + "\n";
            //low temp
            if (temperature >= 14 && temperature <= 18){
                evaluation += "The temperature of your body of water is lower than normal for this time of year." +
                        "\nLower water temperatures in a body of water during the summer can disrupt the ecosystem by slowing down the growth and reproduction of species that thrive in warmer conditions. " +
                        "\nFish and other organisms may become less active, affecting their feeding and survival rates. Cold-water species may become more dominant, potentially displacing warmer-water species. Additionally, cooler temperatures can reduce oxygen levels, impacting aquatic life. " +
                        "\nThese changes can lead to imbalances in the food web and overall ecosystem health. Increased monitoring is suggested. ";
                return evaluation;
            }
            //normal temp
            else if (temperature > 18 && temperature <= 22){
                evaluation += "Your body of water falls into a good temperature range, typical for the summer. " +
                "\nNormal water temperatures during the summer help maintain a balanced ecosystem. Species that are adapted to warmer conditions thrive, with active feeding, growth, and reproduction. " +
                        "\nStable temperatures support a healthy food web, allowing fish, plants, and microorganisms to coexist efficiently. " +
                        "\nOxygen levels are typically sufficient for aquatic life, supporting biodiversity. Overall, normal summer temperatures contribute to a stable and productive aquatic ecosystem.";
                return evaluation;
            }
            else if (temperature >= 15 && temperature <= 21){
                evaluation += "The temperature of your body of water is higher than normal for this time of year. " +
                        "\nHigher-than-normal water temperatures during the summer can strain an ecosystem. Warmer waters can reduce oxygen levels, making it harder for fish and other aquatic life to survive. " +
                        "\nSpecies that are sensitive to heat may experience stress, slower growth, or increased mortality, while heat-tolerant species may thrive, potentially disrupting the balance. " +
                        "\nThese temperature shifts can also lead to algal blooms, which deplete oxygen and further harm the ecosystem. Overall, higher temperatures can destabilize the aquatic environment and reduce biodiversity. Increased monitoring is suggested";
                return evaluation;
            }
            else{
                evaluation += "Extreme water temperatures during summer can destabilize ecosystems. Very cold waters slow metabolism and reduce survival rates, favoring cold-water species and decreasing biodiversity. " +
                        "In contrast, high temperatures can deplete oxygen, stress aquatic life, and cause algal blooms, leading to the loss of heat-sensitive species and disrupting the ecosystem balance. Both extremes harm biodiversity and overall ecosystem health."
                + "\nClose monitoring is encouraged.";
                return evaluation;
            }
        }
        return null;
    }

    public String EvaluateTempAvg(BodyOfWater entry) {
        double temperature = entry.getTemperature();
        String evaluation = "\n======= Temperature Evaluation =======\n";
        evaluation += "Recorded Temperature: " + Math.round(temperature * 100.0) / 100.0 + "°C\n";

        if (temperature < 2.0) {
            evaluation += "\nTemperature is extremely low.\n" +
                    "Such cold water can severely stress aquatic species, slow down metabolism, reduce oxygen availability, and potentially lead to ice formation. " +
                    "These conditions limit food accessibility, restrict movement, and can result in mortality or migration of temperature-sensitive species.\n" +
                    " Close monitoring is strongly advised.";
        } else if (temperature >= 2.0 && temperature < 6.0) {
            evaluation += "\nTemperature is lower than typical.\n" +
                    "Cool water temperatures may slow growth and reproduction in aquatic life, impacting species' behavior and survival. " +
                    "Lower temperatures also affect dissolved oxygen levels, which can stress organisms over time.\n" +
                    " Monitoring recommended.";
        } else if (temperature >= 6.0 && temperature <= 15.0) {
            evaluation += "\nTemperature is in an optimal range.\n" +
                    "This range supports healthy aquatic ecosystems, maintaining metabolic rates, oxygen levels, and natural seasonal behavior. " +
                    "Species can thrive with proper food chains and reproduction cycles.\n" +
                    " No immediate concerns.";
        } else if (temperature > 15.0 && temperature <= 22.0) {
            evaluation += "\nTemperature is moderately high.\n" +
                    "Higher temperatures may begin to stress some aquatic organisms, lower oxygen levels, and alter natural behaviors such as migration or spawning. " +
                    "Warmer water can also favor harmful algae growth.\n" +
                    " Monitoring suggested.";
        } else {
            evaluation += "\nTemperature is extremely high.\n" +
                    "Very warm water reduces dissolved oxygen and may result in fish kills, algal blooms, and other ecological disturbances. " +
                    "Sensitive species may suffer or be replaced by heat-tolerant ones, altering ecosystem dynamics.\n" +
                    " Immediate attention required.";
        }

        return evaluation;
    }

}
