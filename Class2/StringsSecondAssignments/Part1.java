package Class2.StringsSecondAssignments;

public class Part1 {

    public static void runStringSecondAssignmentPart1 (String[] args) {
        Class2.StringsSecondAssignments.Part1 run = new Class2.StringsSecondAssignments.Part1();
        run.testFindStopCodon();
        run.testFindGene();
        run.printAllGenes("GATGTCACTGCTCCATTGATTAAGCAAGTCTGACACACATGTAGCTAAGCTGTGAGTTAATGTATGCAGCGATCTAACACCCACGCC");
    }

    public int findStopCodon (String dna, int startIndex,String stopCodon) {

        int stopCodonIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex + 3);
        if (stopCodonIndex == -1) {
            return dna.length();
        }
        if ((stopCodonIndex - startIndex) % 3 != 0) {
            return dna.length();
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

        if (indexStopCondonTAA < indexStopCondonTAG) {
            indexStopMin = indexStopCondonTAA;
        } else {
            indexStopMin = indexStopCondonTAG;
        }

        if (indexStopCondonTGA < indexStopMin ) {
            indexStopMin = indexStopCondonTGA;
        }

        if(indexStopMin != dna.length()) {
            return dna.substring(startCodonIndex, indexStopMin + 3);
        } else {
            return "";
        }
    }

    public void printAllGenes(String dna) {

        System.out.println("dna : " + dna);
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

        System.out.println("Test Starting : ");

        System.out.print("Expected : 9 ");
        System.out.println("Result : " +
                findStopCodon("ATGTGGTTGTAAGTGTAATG", 0, "TAA")
            );

        System.out.print("Expected : 12 ");
        System.out.println("Result : " +
                findStopCodon("TTGATGTGGTTGTGAGTGTAA", 0, "TGA")
        );

        System.out.print("Expected : 17 ");
        System.out.println("Result : " +
                findStopCodon("ATGTGGTTGTGAGTGTA", 0, "TAA")
        );


    }

    public void testFindGene() {

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

}
