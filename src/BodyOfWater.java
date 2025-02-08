import java.util.Date;
public class BodyOfWater {

    private double temperature;
    private double pH;
    private double alkalinity;
    private double ammonia;
    private double nitrate;
    private double nitrite;
    private double calcium;
    private double magnesium;
    private double iodine;
    private double strontium;
    private double oxygenLevel;
    private Date dateRecorded;
    private double nitrogenLevel;
    private double phosphateLevel;


    //Constructor
    public BodyOfWater (double temperature, double pH, double alkalinity, double oxygenLevel, Date dateRecorded, double nitrogenLevel, double phosphateLevel, double ammonia,
                        double nitrate, double nitrite, double calcium, double magnesium, double iodine, double strontium ) {
        this.temperature = temperature;
        this.pH = pH;
        this.alkalinity = alkalinity;
        this.ammonia = ammonia;
        this.nitrate = nitrate;
        this.nitrite = nitrite;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.iodine = iodine;
        this.strontium = strontium;
        this.oxygenLevel = oxygenLevel;
        this.dateRecorded = dateRecorded;
        this.nitrogenLevel = nitrogenLevel;
        this.phosphateLevel = phosphateLevel;

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
    public void setStrontium (double strontium) {
        this.strontium = strontium;
    }
    public double getStrontium () {
        return this.strontium;
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
    public void setAlkalinity (double alkalinity) {
        this.alkalinity = alkalinity;
    }
    public double getAlkalinity() {
        return alkalinity;
    }
}

