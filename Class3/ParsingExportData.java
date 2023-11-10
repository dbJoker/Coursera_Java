package Class3;

import edu.duke.FileResource;
import org.apache.commons.csv.*;

public class ParsingExportData {

    public static void runParsingExportData (String[] args) {
        Class3.ParsingExportData run = new Class3.ParsingExportData();
        run.tester();
    }

    public void tester () {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        System.out.println(countryInfo(parser,"Nauru"));
        listExportersTwoProducts(parser, "gold", "diamond");
        System.out.println("Number of country with gold : " + numberOfExporters(parser, "gold"));
        bigExporters(parser, "$999,999,999,999");

    }

    public String countryInfo (CSVParser parser, String country) {
        String countryInfo = "";

        for (CSVRecord record : parser) {
            String recordCountry = record.get("Country");
            if (recordCountry.equals(country)) {
                countryInfo = record.get("Country") + ": "
                        + record.get("Exports") + ": "
                        + record.get("Value (dollars)");
            }
        }

        if (countryInfo.isEmpty()) {
            countryInfo = "NOT FOUND";
        }

        return countryInfo;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {

        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {

                System.out.println(record.get("Country"));
            }
        }

    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int countryCounter = 0;

        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)){
                countryCounter++;
            }
        }

        return countryCounter;
    }

    public void bigExporters (CSVParser parser, String amount) {

        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }

    }

}
