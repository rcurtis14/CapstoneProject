import java.util.Comparator;
import java.util.Date;
public class BodyOfWater {
//Date	Temperature (°C)	pH	Oxygen Level (mg/L)	Nitrogen Level (mg/L)	Phosphate Level (mg/L)	Ammonia (mg/L)	Nitrate (mg/L)	Nitrite (mg/L)	Calcium (mg/L)	Magnesium (mg/L)	Iodine (mg/L)
    private double temperature;
    private double pH;
    private double ammonia;
    private double nitrate;
    private double nitrite;
    private double calcium;
    private double magnesium;
    private double iodine;
    private double oxygenLevel;
    private Date dateRecorded;
    private double nitrogenLevel;
    private double phosphateLevel;

    //Default Constructor
    public BodyOfWater() {
        temperature = 0.0;
        pH = 0.0;
        ammonia = 0.0;
        nitrate = 0.0;
        nitrite = 0.0;
        calcium = 0.0;
        magnesium = 0.0;
        iodine = 0.0;
        oxygenLevel = 0.0;
        dateRecorded = null;
        nitrogenLevel = 0.0;
        phosphateLevel = 0.0;

    }

    //Parameterized Constructor
    //Date	Temperature (°C)	pH	Oxygen Level (mg/L)	Nitrogen Level (mg/L)	Phosphate Level (mg/L)	Ammonia (mg/L)	Nitrate (mg/L)	Nitrite (mg/L)	Calcium (mg/L)	Magnesium (mg/L)	Iodine (mg/L)
    public BodyOfWater (Date dateRecorded,double temperature, double pH, double oxygenLevel, double nitrogenLevel, double phosphateLevel, double ammonia, double nitrate, double nitrite, double calcium, double magnesium, double iodine) {
        this.temperature = temperature;
        this.pH = pH;
        this.ammonia = ammonia;
        this.nitrate = nitrate;
        this.nitrite = nitrite;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.iodine = iodine;
        this.oxygenLevel = oxygenLevel;
        this.dateRecorded = dateRecorded;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphateLevel = phosphateLevel;

    }
    //Copy Constructor
    public BodyOfWater (BodyOfWater bodyOfWater){
        this.temperature = bodyOfWater.temperature;
        this.pH = bodyOfWater.pH;
        this.ammonia = bodyOfWater.ammonia;
        this.nitrate = bodyOfWater.nitrate;
        this.nitrite = bodyOfWater.nitrite;
        this.calcium = bodyOfWater.calcium;
        this.magnesium = bodyOfWater.magnesium;
        this.iodine = bodyOfWater.iodine;
        this.oxygenLevel = bodyOfWater.oxygenLevel;
        this.dateRecorded = bodyOfWater.dateRecorded;
        this.nitrogenLevel = bodyOfWater.nitrogenLevel;
        this.phosphateLevel = bodyOfWater.phosphateLevel;
    }



    //getter and setter methods
    public void setTemperature (double temperature) {
        this.temperature = temperature;
    }
    public double getTemperature (){
        return this.temperature;
    }
    public void setPH (double pH) {
        this.pH = pH;
    }
    public double getPH () {
        return this.pH;
    }
    public void setAmmonia (double ammonia) {
        this.ammonia = ammonia;
    }
    public double getAmmonia () {
        return this.ammonia;
    }
    public void setNitrate (double nitrate) {
        this.nitrate = nitrate;
    }
    public double getNitrate () {
        return this.nitrate;
    }
    public void setNitrite (double nitrite) {
        this.nitrite = nitrite;
    }
    public double getNitrite () {
        return this.nitrite;
    }
    public void setCalcium (double calcium) {
        this.calcium = calcium;
    }
    public double getCalcium () {
        return this.calcium;
    }
    public void setMagnesium (double magnesium) {
        this.magnesium = magnesium;
    }
    public double getMagnesium () {
        return this.magnesium;
    }
    public void setIodine (double iodine) {
        this.iodine = iodine;
    }
    public double getIodine () {
        return this.iodine;
    }
    public void setOxygenLevel (double oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }
    public double getOxygenLevel () {
        return this.oxygenLevel;
    }
    public void setDateRecorded (Date dateRecorded) {
        this.dateRecorded = dateRecorded;
    }
    public Date getDateRecorded () {
        return this.dateRecorded;
    }
    public void setNitrogenLevel (double nitrogenLevel) {
        this.nitrogenLevel = nitrogenLevel;
    }
    public double getNitrogenLevels () {
        return this.nitrogenLevel;
    }
    public void setPhosphateLevel (double phosphorusLevel) {
        this.phosphateLevel = phosphorusLevel;
    }
    public double getPhosphorusLevels () {
        return this.phosphateLevel;
    }
    @Override
    public String toString () {
        return "Date Recorded: " + dateRecorded;
    }
}
//Comparator Class for date Sorting
class DateComparator implements Comparator<BodyOfWater> {

    @Override
    public int compare(BodyOfWater o1, BodyOfWater o2) {
        return o1.getDateRecorded().compareTo(o2.getDateRecorded()) ;
    }
}

