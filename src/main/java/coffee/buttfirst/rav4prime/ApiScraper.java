package coffee.buttfirst.rav4prime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import static coffee.buttfirst.rav4prime.Vehicle.createDefaultVehicle;

public class ApiScraper {
    private static final String replacementText = "<VIN>";
    private static final String urlPattern = String.format("https://api.rti.toyota.com/marketplace-inventory/vehicles/%s?isVspec=true", replacementText);
    private VehicleVerifier vehicleVerifier;

    public ApiScraper(VehicleVerifier vehicleVerifier) {
        this.vehicleVerifier = vehicleVerifier;
    }

    protected Vehicle getVehicle(String vin) throws IOException {
        final var url = urlPattern.replace(replacementText, vin);
        URL urlWithVin = new URL(url);
        System.out.println(String.format("Hitting url %s", url));

        InputStream inputStream = urlWithVin.openStream();
        try {
            var result = inputStreamToJsonString(inputStream);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(result);
            final var exteriorColor = getExteriorColor(jsonNode);
            if (vehicleVerifier.isDesiredExteriorColor(exteriorColor)) {
                System.out.println("oh shit waddup found the right exterior color");
            } else {
                System.out.println("OHNO: wrong exterior color");
            }
            final var interiorColor = getInteriorColor(jsonNode);
            if (vehicleVerifier.isDesiredInteriorColor(interiorColor)){
                System.out.println("aww yiss right interior color too");
            } else {
                System.out.println("OHNO: wrong interior color");
            }
        } finally {
            inputStream.close();
        }

        // TODO convert json to vehicle object
        return createDefaultVehicle();
    }

    private String getExteriorColor(JsonNode jsonNode) {
        return jsonNode.get("extColor").get("colorCd").asText();
    }

    private String getInteriorColor(JsonNode jsonNode) {
        return jsonNode.get("intColor").get("colorCd").asText();
    }

    //Write all the JSON data into a string using a scanner
    private String inputStreamToJsonString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);

        String stringResult = "";
        while (scanner.hasNext()) {
            stringResult += scanner.nextLine();
        }
        return stringResult;
    }

    private Vehicle convertJsonResultToVehicle() {
        // TODO convert vehicle json data into a parsed result
        return createDefaultVehicle();
    }
}