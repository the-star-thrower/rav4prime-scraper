package coffee.buttfirst.rav4prime;

import static coffee.buttfirst.rav4prime.StaticsHelper.*;

public class VehicleVerifier {

    public boolean isDesiredExteriorColor(String colorCode) {
        return colorCode.equals(BLUEPRINT) || colorCode.equals(SUPERSONIC_RED);
    }

    public boolean isDesiredInteriorColor(String colorCode) {
        return colorCode.equals(BLACK_RED_SOFTEX);
    }

}
