import java.util.ArrayList;
import java.util.Date;

public class RangeOfDates {
    public Date startDate;
    public Date endDate;

    public RangeOfDates(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BodyOfWater rangeReport(ArrayList<BodyOfWater> information, int startIndex) {
        int endIndex = startIndex;
        while (endIndex < information.size() && !information.get(endIndex).getDateRecorded().after(endDate)) {
            endIndex++;
        }
        endIndex--; // Correct off-by-one after loop

        int count = endIndex - startIndex + 1;

        double oxySum = sumOxygen(information, startIndex, endIndex);
        double tempSum = sumTemperature(information, startIndex, endIndex);
        double pHSum = sumPH(information, startIndex, endIndex);
        double ammoniaSum = sumAmmonia(information, startIndex, endIndex);
        double nitrateSum = sumNitrate(information, startIndex, endIndex);
        double nitriteSum = sumNitrite(information, startIndex, endIndex);
        double calciumSum = sumCalcium(information, startIndex, endIndex);
        double magnesiumSum = sumMagnesium(information, startIndex, endIndex);
        double iodineSum = sumIodine(information, startIndex, endIndex);
        double nitrogenSum = sumNitrogen(information, startIndex, endIndex);
        double phosphateSum = sumPhosphate(information, startIndex, endIndex);

        // Calculate averages
        double OxyAverage = roundToTwoDecimals(oxySum / count);
        double TemperatureAverage = roundToTwoDecimals(tempSum / count);
        double pHAverage = roundToTwoDecimals(pHSum / count);
        double AmmoniaAverage = roundToTwoDecimals(ammoniaSum / count);
        double nitrateAverage = roundToTwoDecimals(nitrateSum / count);
        double nitriteAverage = roundToTwoDecimals(nitriteSum / count);
        double calciumAverage = roundToTwoDecimals(calciumSum / count);
        double magnesiumAverage = roundToTwoDecimals(magnesiumSum / count);
        double iodineAverage = roundToTwoDecimals(iodineSum / count);
        double nitrogenAverage = roundToTwoDecimals(nitrogenSum / count);
        double phosphateAverage = roundToTwoDecimals(phosphateSum / count);

        // Return averaged BodyOfWater object using the endDate as reference
        return new BodyOfWater(endDate, TemperatureAverage, pHAverage, OxyAverage,
                nitrogenAverage, phosphateAverage, AmmoniaAverage, nitrateAverage,
                nitriteAverage, calciumAverage, magnesiumAverage, iodineAverage);
    }

    // Round to 2 decimal places
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // Recursive summation methods

    public double sumOxygen(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getOxygenLevel() + sumOxygen(list, startIndex + 1, endIndex);
    }

    public double sumTemperature(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getTemperature() + sumTemperature(list, startIndex + 1, endIndex);
    }

    public double sumPH(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getPH() + sumPH(list, startIndex + 1, endIndex);
    }

    public double sumAmmonia(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getAmmonia() + sumAmmonia(list, startIndex + 1, endIndex);
    }

    public double sumNitrate(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getNitrate() + sumNitrate(list, startIndex + 1, endIndex);
    }

    public double sumNitrite(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getNitrite() + sumNitrite(list, startIndex + 1, endIndex);
    }

    public double sumCalcium(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getCalcium() + sumCalcium(list, startIndex + 1, endIndex);
    }

    public double sumMagnesium(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getMagnesium() + sumMagnesium(list, startIndex + 1, endIndex);
    }

    public double sumIodine(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getIodine() + sumIodine(list, startIndex + 1, endIndex);
    }

    public double sumNitrogen(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getNitrogenLevels() + sumNitrogen(list, startIndex + 1, endIndex);
    }

    public double sumPhosphate(ArrayList<BodyOfWater> list, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        return list.get(startIndex).getPhosphorusLevels() + sumPhosphate(list, startIndex + 1, endIndex);
    }
}
