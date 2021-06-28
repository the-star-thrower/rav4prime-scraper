package coffee.buttfirst.rav4prime;

import java.io.IOException;

public class main {

    public static void main(String args[]){
        final var scraper = new ApiScraper();
        try {
            scraper.getVehicle("JTMFB3FV1MD046086");
        } catch (IOException e) {
            System.out.println(String.format("Failed to obtain vehicle information due to %s", e.getMessage()));
        }
    }

}
