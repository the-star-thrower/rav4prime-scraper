package coffee.buttfirst.rav4prime;

public class Vehicle {
    protected static String DEFAULT_STRING_VAL = "UNKNOWN";
    protected static int DEFAULT_NUMERIC_VAL = -1;
    private final String vin;
    private final String make;
    private final String model;
    private final String trim;
    private final int year;
    private final String dealership;
    private final int dealerCode;
    private final String packages;
    private final double totalMsrp;
    private final String intColorCd;

    public Vehicle(String vin, String make, String model, String trim, int year,
                   String dealership, int dealerCode, String packages, double totalMsrp,
                   String intColorCd) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.year = year;
        this.dealership = dealership;
        this.dealerCode = dealerCode;
        this.packages = packages;
        this.totalMsrp = totalMsrp;
        this.intColorCd = intColorCd;
    }

    public String getIntColorCd() {
        return intColorCd;
    }

    public double getTotalMsrp() {
        return totalMsrp;
    }

    public String getPackages() {
        return packages;
    }

    public int getDealerCode() {
        return dealerCode;
    }

    public String getDealership() {
        return dealership;
    }

    public int getYear() {
        return year;
    }

    public String getTrim() {
        return trim;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public String getVin() {
        return vin;
    }

    public String toString(){
        return getMake()  + "\n" + getModel() + "\n" + getVin();
    }

    public static Vehicle createDefaultVehicle() {
        return new Vehicle(DEFAULT_STRING_VAL, DEFAULT_STRING_VAL, DEFAULT_STRING_VAL, DEFAULT_STRING_VAL,
                DEFAULT_NUMERIC_VAL, DEFAULT_STRING_VAL, DEFAULT_NUMERIC_VAL, DEFAULT_STRING_VAL,
                DEFAULT_NUMERIC_VAL, DEFAULT_STRING_VAL);
    }
}
