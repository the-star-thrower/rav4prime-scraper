package coffee.buttfirst.rav4prime;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import static coffee.buttfirst.rav4prime.Vehicle.createDefaultVehicle;

public class ApiScraper {
    private static final String replacementText = "<VIN>";
    private static final String urlPattern = String.format("https://api.rti.toyota.com/marketplace-inventory/vehicles/%s?isVspec=true", replacementText);

    public ApiScraper() {
    }

    protected Vehicle getVehicle(String vin) throws IOException {
        final var url = urlPattern.replace(replacementText, vin);
        URL urlWithVin = new URL(url);
        System.out.println(String.format("Hitting url %s", url));

        InputStream inputStream = urlWithVin.openStream();
        try {
            Scanner scanner = new Scanner(inputStream);

            //Write all the JSON data into a string using a scanner
            String jsonResult = "";
            while (scanner.hasNext()) {
                jsonResult += scanner.nextLine();
            }

            ObjectMapper mapper = new ObjectMapper();
            Vehicle jsonText = mapper.readValue(jsonResult, Vehicle.class);
            System.out.println(jsonText.toString());
        } finally {
            inputStream.close();
        }

        // TODO convert json to vehicle object
        return createDefaultVehicle();
    }

    private Vehicle convertJsonResultToVehicle() {
        // TODO convert vehicle json data into a parsed result
        return createDefaultVehicle();
    }
}