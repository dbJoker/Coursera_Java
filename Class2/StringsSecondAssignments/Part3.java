package Class2.StringsSecondAssignments;

public class Part3 {

    public static void runStringSecondAssignmentPart3 (String[] args) {
        Class2.StringsSecondAssignments.Part3 run = new Class2.StringsSecondAssignments.Part3();
        run.testFindStopCodon();
        run.testFindGene();
        run.printAllGenes("AATGCTAACTAGCTGACTAAT");
        run.testCountGenes();
    }

    public int findStopCodon (String dna, int startIndex,String stopCodon) {

        int stopCodonIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex + 3);
        if (stopCodonIndex == -1) {
            return -1;
        }
        if ((stopCodonIndex - startIndex) % 3 != 0) {
            return -1;
        }

        return stopCodonIndex;
    }


    public String findGene(String dna) {


        int startCodonIndex = dna.toUpperCase().indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }

        int indexStopCondonTAA = findStopCodon(dna, startCodonIndex, "TAA");
        int indexStopCondonTAG = findStopCodon(dna, startCodonIndex, "TAG");
        int indexStopCondonTGA = findStopCodon(dna, startCodonIndex, "TGA");

        int indexStopMin = 0;

        if (indexStopCondonTAA == -1 || indexStopCondonTGA != -1 && indexStopCondonTGA < indexStopCondonTAA) {
            indexStopMin = indexStopCondonTGA;
        } else {
            indexStopMin = indexStopCondonTAA;
        }

        if (indexStopMin == -1 || indexStopCondonTAG != -1 && indexStopCondonTAG < indexStopMin) {
            indexStopMin = indexStopCondonTAG;
        }

        if(indexStopMin != -1) {
            return dna.substring(startCodonIndex, indexStopMin + 3);
        } else {
            return "";
        }

    }

    public int countGenes(String dna) {
        String currentDna = dna;
        int numGene = 0;

        while (true) {
            String gene = findGene(currentDna);
            if (!gene.isEmpty()) {
                numGene++;
                currentDna = currentDna.substring(currentDna.indexOf(gene) + gene.length());
            } else {
                break;
            }
        }
        return numGene;
    }

    public void printAllGenes(String dna) {

        System.out.println("***********\n Printing Genes found in");
        System.out.println("DNA : " + dna);
        System.out.println("Gene found : ");
        String currentDna = dna;
        while (true) {

            String gene = findGene(currentDna);

            if (!gene.isEmpty()) {
                System.out.println(gene);
                currentDna = currentDna.substring(currentDna.indexOf(gene) + gene.length());
            } else {
                break;
            }

        }
    }

    public void testFindStopCodon() {

        System.out.println("***********\n Test FindStopCodon : ");

        System.out.print("Expected : 9 ");
        System.out.println("Result : " +
                findStopCodon("ATGTGGTTGTAAGTGTAATG", 0, "TAA")
        );

        System.out.print("Expected : 12 ");
        System.out.println("Result : " +
                findStopCodon("TTGATGTGGTTGTGAGTGTAA", 0, "TGA")
        );

        System.out.print("Expected : -1 ");
        System.out.println("Result : " +
                findStopCodon("ATGTGGTTGTGAGTGTA", 0, "TAA")
        );


    }

    public void testFindGene() {

        System.out.println("***********\n Test FindGene : ");

        String dnaWithNoStartCodon = "TGGTGTGGTTGTAAGTGTAATG";
        String dnaWithOneStopCodon = "GATGTGGTATGGGTGAT";
        String dnaWithMultipleStopCodon = "GATGTGGTTGTAAGTGTAGTG";
        String dnaWithNoValidStopCodon = "GATGTGGTTTAAGTGTAGTG";

        System.out.println("DNA : " + dnaWithNoStartCodon);
        System.out.println("Gene : " + findGene(dnaWithNoStartCodon));

        System.out.println("DNA : " + dnaWithOneStopCodon);
        System.out.println("Gene : " + findGene(dnaWithOneStopCodon));

        System.out.println("DNA : " + dnaWithMultipleStopCodon);
        System.out.println("Gene : " + findGene(dnaWithMultipleStopCodon));

        System.out.println("DNA : " + dnaWithNoValidStopCodon);
        System.out.println("Gene : " + findGene(dnaWithNoValidStopCodon));
    }

    public void testCountGenes() {

        System.out.println("***********\n Test CountGenes : ");

        System.out.println("Expected : 2,  Result : " + countGenes("ATGTAAGATGCCCTAGT"));
        System.out.println("Expected : 3,  Result : " + countGenes("GATGTCACTGCTCCATTGATTAAGCAAGTCTGACACACATGTAGCTAAGCTGTGAGTTAATGTATGCAGCGATCTAACACCCACGCC"));
        System.out.println("Expected : 0,  Result : " + countGenes("ATGAACGAATTGGATC"));
        System.out.println("Expected : 0,  Result : " + countGenes("AGAACGAATTGGATC"));



    }

}
