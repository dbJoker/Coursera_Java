package Class4;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ParsingBirthNameData {

    public static void runParsingBirthNameData (String[] args) {

        Class4.ParsingBirthNameData run = new Class4.ParsingBirthNameData();
        run.testTotalBirths();
        run.testGetRank();
        run.testGetName();
        run.testWhatIsNameInYear();
        run.testYearOfHighestRank();
        run.testGetAverageRank();
        run.testGetTotalBirthsRankedHigher();

    }

    public void totalBirths (FileResource fr) {
        int totalBirth = 0;
        int totalBirthBoy = 0;
        int totalBirthGirl = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirth += numBorn;
            if (rec.get(1).equals("M")) {
                totalBirthBoy += numBorn;
            } else {
                totalBirthGirl += numBorn;
            }
        }
        System.out.println("total births Boy = " + totalBirthBoy);
        System.out.println("total births Girl = " + totalBirthGirl);
        System.out.println("total births = " + totalBirth);
    }

    public int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource("Class4/Ressource/us_babynames_by_year/yob"+ year + ".csv");
        int currentRank = 0;
        int lastGirlRank = 0;
        int rank = -1;

        for (CSVRecord rec : fr.getCSVParser(false)) {

            currentRank += 1;
            if (rec.get(1).equals("F")) {
                lastGirlRank += 1;
            }

            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                if (gender.equals("M")) {
                    rank = currentRank - lastGirlRank;
                } else {
                    rank = currentRank;
                }
            }
        }
        return rank;
    }

    public String getName (int year, int rank, String gender) {

        FileResource fr = new FileResource("Class4/Ressource/us_babynames_by_year/yob"+ year + ".csv");
        int currentRank = 0;
        int lastGirlRank = 0;
        String name = "NO NAME";

        for (CSVRecord rec : fr.getCSVParser(false)) {
            currentRank += 1;

            if (rec.get(1).equals("F")) {
                lastGirlRank += 1;
            }

            if (gender.equals("F") && rank == currentRank && rank <= lastGirlRank) {
                return rec.get(0);
            } else if (gender.equals("M") && rank == (currentRank - lastGirlRank)) {
                return rec.get(0);
            }
        }

        return name;
    }

    public String whatIsNameInYear (String name, int year, int newYear, String gender) {

        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);

        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);

        return newName;
    }

    public int yearOfHighestRank (String name, String gender) {
        int highestRank = -1;
        int highestRankYear = -1;
        int rank = -1;

        DirectoryResource dr = new DirectoryResource();

        for (File file: dr.selectedFiles()) {
            int year = -1;
            int index1 = file.getName().indexOf("1");
            int index2 = file.getName().indexOf("2");
            int index = -1;

            if (index1 != -1 && (index1 < index2 || index2 == -1)) {
                index = index1;
            } else if (index2 != -1) {
                index = index2;
            }

            if (index != -1) {
                year = Integer.parseInt(file.getName().substring(index, index + 4));
            }
            rank = getRank(year, name, gender);

            if ((rank < highestRank && rank != -1) || (highestRank == -1 && rank != -1)) {
                highestRank = rank;
                highestRankYear = year;
            }

        }

        System.out.println("Year : " + highestRankYear + " , rank : " + highestRank);

        return highestRankYear;
    }

    public double getAverageRank (String name, String gender) {
        double averageRank = -1;
        int totalRank = 0;
        int nameRecurence = 0;
        int rank = -1;

        DirectoryResource dr = new DirectoryResource();

        for (File file: dr.selectedFiles()) {
            int year = -1;
            int index1 = file.getName().indexOf("1");
            int index2 = file.getName().indexOf("2");
            int index = -1;

            if (index1 != -1 && (index1 < index2 || index2 == -1)) {
                index = index1;
            } else if (index2 != -1) {
                index = index2;
            }

            if (index != -1) {
                year = Integer.parseInt(file.getName().substring(index, index + 4));
            }
            rank = getRank(year, name, gender);

            if (rank != -1) {
                totalRank += rank;
                nameRecurence ++;
            }
        }

        if (totalRank != 0 && nameRecurence != 0) {
            averageRank = (double) totalRank/nameRecurence;
        }

        return averageRank;
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        FileResource fr = new FileResource("Class4/Ressource/us_babynames_by_year/yob"+ year + ".csv");
        int totalBirthHigher = 0;

        boolean isNameFound = false;
        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                isNameFound = true;
            }

            if (rec.get(1).equals(gender) && !isNameFound) {
                totalBirthHigher += Integer.parseInt(rec.get(2));
            }
        }

        return totalBirthHigher;
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource("Class4/Ressource/us_babynames_by_year/yob2014.csv");
        totalBirths(fr);
    }

    public void testGetRank () {
        int rank = getRank(2012, "Mason", "M");
        System.out.println("Expected : 2, Result : " + rank);
        rank = getRank(2012, "Mason", "F");
        System.out.println("Expected : -1, Result : " + rank);
        rank = getRank(2014, "Isabella", "F");
        System.out.println("Expected : 4, Result : " + rank);
        rank = getRank(1971, "Frank", "M");
        System.out.println("Expected : 54, Result : " + rank);

    }

    public void testGetName () {
        String name = getName(2012, 2, "M");
        System.out.println("Expected : Mason, Result : " + name);
        name = getName(2012, 8, "F");
        System.out.println("Expected : NO NAME, Result : " + name);
        name = getName(2014, 3, "F");
        System.out.println("Expected : Sophia, Result : " + name);
        name = getName(1982, 450, "M");
        System.out.println("Expected : Forrest, Result : " + name);
    }

    public void testWhatIsNameInYear () {
        String name = whatIsNameInYear("Isabella", 2012, 2014, "F");
        System.out.println("Expected : Sophia, Result : " + name);
        name = whatIsNameInYear("Owen", 1974, 2014, "M");
        System.out.println("Expected : Leonel, Result : " + name);
    }

    public void testYearOfHighestRank () {
        int year = 0;
        year = yearOfHighestRank("Mason", "M");
        System.out.println("Expected : year 2012, Result : " + year);

        year = yearOfHighestRank("Ornito", "M");
        System.out.println("Expected : -1, Result : " + year);

        year = yearOfHighestRank("Mich", "M");
        System.out.println("Expected : year 1960, Result : " + year);
    }

    public void testGetAverageRank () {
        double averageRank = 0;
        averageRank = getAverageRank("Mason", "M");
        System.out.println("Expected : 3.0, Result : " + averageRank);

        averageRank = getAverageRank("Jacob", "M");
        System.out.println("Expected : 2.66, Result : " + averageRank);

        averageRank = getAverageRank("Ornito", "M");
        System.out.println("Expected : -1.0, Result : " + averageRank);

        averageRank = getAverageRank("Robert", "M");
        System.out.println("Expected : 10.76, Result : " + averageRank);
    }

    public void testGetTotalBirthsRankedHigher () {
        int totalBirth = 0;
        totalBirth = getTotalBirthsRankedHigher(2012, "Ethan", "M");
        System.out.println("Expected : 15, Result : " + totalBirth);

        totalBirth = getTotalBirthsRankedHigher(1990, "Drew", "M");
        System.out.println("Expected : 1498074, Result : " + totalBirth);
    }

}
