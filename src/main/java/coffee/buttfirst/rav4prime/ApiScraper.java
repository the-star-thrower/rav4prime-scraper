package coffee.buttfirst.rav4prime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import static coffee.buttfirst.rav4prime.StaticsHelper.BLUEPRINT;
import static coffee.buttfirst.rav4prime.StaticsHelper.SUPERSONIC_RED;
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
            var result = inputStreamToJsonString(inputStream);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(result);
            final var exteriorColor = getExteriorColor(jsonNode);
            if (isDesiredColor(exteriorColor))
                System.out.println("oh shit waddup found the right color");;
        } finally {
            inputStream.close();
        }

        // TODO convert json to vehicle object
        return createDefaultVehicle();
    }

    private boolean isDesiredColor(String colorCode) {
        return colorCode.equals(BLUEPRINT) || colorCode.equals(SUPERSONIC_RED);
    }

    private String getExteriorColor(JsonNode jsonNode) {
        return jsonNode.get("extColor").get("colorCd").asText();
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