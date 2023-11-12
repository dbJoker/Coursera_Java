package Class3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.io.File;

public class ParsingWeatherData {

    public static void runParsingWeatherData (String[] args) {
        Class3.ParsingWeatherData run = new Class3.ParsingWeatherData();
        run.testColdestHourInFile();
        run.testFileWithColdestTemperature();
        run.testLowestHumidityInFile();
        run.testLowestHumidityInManyFiles();
        run.testAverageTemperatureInFile();
        run.testAverageTemperatureWithHighHumidityInFile();
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {

        CSVRecord minimumRecord = null;

        for (CSVRecord record: parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));

            if (currentTemp != -9999) {

                if (minimumRecord == null) {
                    minimumRecord = record;

                } else {

                    double minimumTemps = Double.parseDouble(minimumRecord.get("TemperatureF"));
                    if (currentTemp < minimumTemps) {
                        minimumRecord = record;
                    }
                }
            }
        }
        return minimumRecord;
    }

    public String fileWithColdestTemperature () {
        File minimumFile = null;
        CSVRecord minimumRecord = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file: dr.selectedFiles()) {
            FileResource fr = new FileResource(file);

            CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
            if (minimumRecord == null) {
                minimumRecord = currentRecord;
                minimumFile = file;

            } else {
                double minimumTemps = Double.parseDouble(minimumRecord.get("TemperatureF"));
                double currentTemps = Double.parseDouble(currentRecord.get("TemperatureF"));
                if (currentTemps < minimumTemps) {
                    minimumRecord = currentRecord;
                    minimumFile = file;
                }
            }

        }

        System.out.println("Coldest day was in file " + minimumFile.getName());
        FileResource fr = new FileResource(minimumFile.getAbsolutePath());
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");

        for (CSVRecord record: fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + " , " + record.get("TemperatureF"));
        }

        return minimumFile.getName();
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {

        CSVRecord minimumRecord = null;

        for (CSVRecord record: parser) {
            String currentHumidityString = record.get("Humidity");

            if (!currentHumidityString.equals("N/A")) {
                double currentHumidity = Double.parseDouble(currentHumidityString);

                if (minimumRecord == null) {
                    minimumRecord = record;

                } else {

                    double minimumHumidity = Double.parseDouble(minimumRecord.get("Humidity"));
                    if (currentHumidity < minimumHumidity) {
                        minimumRecord = record;
                    }
                }
            }
        }
        return minimumRecord;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord minimumRecord = null;
        DirectoryResource dr = new DirectoryResource();

        for (File file: dr.selectedFiles()) {
            FileResource fr = new FileResource(file);

            CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
            if (minimumRecord == null) {
                minimumRecord = currentRecord;

            } else {
                double minimumHumidity = Double.parseDouble(minimumRecord.get("Humidity"));
                double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
                if (currentHumidity < minimumHumidity) {
                    minimumRecord = currentRecord;
                }
            }
        }

        return minimumRecord;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double totalTemps = 0;
        int numberData = 0;

        for (CSVRecord record: parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));

            if (currentTemp != -9999) {

                totalTemps += currentTemp;
                numberData++;
            }
        }

        return totalTemps / numberData;
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value) {
        double totalTemps = 0;
        int numberData = 0;

        for (CSVRecord record: parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            double currentHumidity = Double.parseDouble(record.get("Humidity"));

            if (currentTemp != -9999 && currentHumidity >= value) {

                totalTemps += currentTemp;
                numberData++;
            }
        }

        if (numberData == 0) {
            return -9999;
        }

        return totalTemps / numberData;
    }

    public void testColdestHourInFile() {

        FileResource fr = new FileResource("Class3/Ressource/nc_weather/2014/weather-2014-05-01.csv");
        CSVRecord coldestRecord = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + coldestRecord.get("TemperatureF")
                + " at " + coldestRecord.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        String coldestFile = fileWithColdestTemperature();

    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);

        System.out.println("Lowest Humidity was " + csv.get("Humidity")
                + " at " + csv.get("DateUTC"));

    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();

        System.out.println("Lowest Humidity was " + lowestHumidityRecord.get("Humidity") + " at "
                + lowestHumidityRecord.get("DateUTC"));

    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureInFile(parser);

        System.out.println("Average temperature in file is " + averageTemp);

    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (averageTemp == -9999) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average temperature with Humidity 80 or more in file is " + averageTemp);
        }
    }



}
