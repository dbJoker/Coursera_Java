package Class2.StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1 {

    public static void runStringsThirdAssignmentsPart1 (String[] args) {
        Class2.StringsThirdAssignments.Part1 run = new Class2.StringsThirdAssignments.Part1();
        //run.testFindStopCodon();
        //run.testFindGene();
        run.printAllGenes("nonCodingDNAxxxMyGeneATGmyGenexTAAxxGeneATGTAACATGTAAATGCendTAATAAnonCodingDNAxTAGxTGA");
        run.testGetAllGenes("GATGTCACTGCTCCATTGATTAAGCAAGTCTGACACACATGTAGCTAAGCTGTGAGTTAATGTATGCAGCGATCTAACACCCACGCC");
        run.testProcessGenes();
    }

    public int findStopCodon (String dna, int startIndex, String stopCodon) {

        int stopCodonIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startIndex + 3);
        while ((stopCodonIndex != -1)) {

            if ((stopCodonIndex - startIndex) % 3 == 0) {
                return stopCodonIndex;
            }

            stopCodonIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), stopCodonIndex + 3);
        }

        return -1;
    }


    public String findGene(String dna, int startIndex) {

        int startCodonIndex = dna.toUpperCase().indexOf("ATG", startIndex);
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

    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true) {

            String gene = findGene(dna, startIndex);

            if (!gene.isEmpty()) {
                geneList.add(gene);
            } else {
                break;
            }

            startIndex = dna.indexOf(gene, startIndex) + gene.length();

        }
        return geneList;
    }

    public int countRecurenceofString(String stringToCount, String stringToFindIn) {

        int count = 0;
        int startIndex = 0;

        while (true) {

            int indexFound = stringToFindIn.toUpperCase().indexOf(stringToCount, startIndex);

            if (indexFound != -1) {
                count++;
                startIndex = indexFound + 1;
            } else {
                break;
            }

        }

        return count;
    }

    public double cgRatio(String dna) {

        double numberOfC = 0;
        double numberOfG = 0;

        numberOfC = countRecurenceofString("C", dna);
        numberOfG = countRecurenceofString("G", dna);

        return (numberOfC + numberOfG) / (double)dna.length();
    }

    public int countCTG(String dna) {

        return countRecurenceofString("CTG", dna);
    }

    public void processGenes(StorageResource sr) {

        int numGeneLongerThanNine = 0;
        int numGeneCGRatio = 0;
        int longestGene = 0;
        int numberGene = 0;

        System.out.println("Gene that are longer than 60 characters : ");
        for (String gene: sr.data()) {
            numberGene++;
            if (gene.length() > 60) {
                System.out.println(gene);
                numGeneLongerThanNine++;
            }

        }

        System.out.println("Number of gene longer than 60 characters : " + numGeneLongerThanNine);
        System.out.println("Gene that have a c-g ration higher than 0.35 : ");

        for (String gene: sr.data()) {
            if (cgRatio(gene) > 0.35) {
                System.out.println(gene);
                numGeneCGRatio++;
            }

            if (gene.length() > longestGene) {
                longestGene = gene.length();
            }

        }

        System.out.println("Number of gene that have a c-g ratio higher than 0.35 : " + numGeneCGRatio);

        System.out.println("Longest Gene = " + longestGene);
        System.out.println("Number of Gene : " + numberGene);


    }

    public void printAllGenes(String dna) {

        System.out.println("***********\n Printing Genes found in");
        System.out.println("dna : " + dna);
        System.out.println("Gene found : ");
        int startIndex = 0;
        while (true) {

            String gene = findGene(dna, startIndex);

            if (!gene.isEmpty()) {
                System.out.println(gene);
                System.out.println("Length : " + gene.length());
                startIndex = dna.indexOf(gene, startIndex) + gene.length();
            } else {
                break;
                /*startIndex = dna.indexOf("ATG", startIndex + 3);

                if(startIndex == -1) {
                    break;
                }
                */
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

        System.out.print("Expected : 17 ");
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
        System.out.println("Gene : " + findGene(dnaWithNoStartCodon, 0));

        System.out.println("DNA : " + dnaWithOneStopCodon);
        System.out.println("Gene : " + findGene(dnaWithOneStopCodon, 0));

        System.out.println("DNA : " + dnaWithMultipleStopCodon);
        System.out.println("Gene : " + findGene(dnaWithMultipleStopCodon, 0));

        System.out.println("DNA : " + dnaWithNoValidStopCodon);
        System.out.println("Gene : " + findGene(dnaWithNoValidStopCodon, 0));
    }

    public void testGetAllGenes(String dna) {

        System.out.println("***********\n Test getAllGenes : ");

        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println(gene);
        }

    }

    public  void testProcessGenes() {

        /*System.out.println("***********\n Test processGenes : ");

        String dna1 = "GATGTGGTATGGGTGATTGATGTGGTATGGGTGATTTGATGGGTGAT";
        String dna2 = "GATGTGGTGATTGATGTGATTT";
        String dna3 = "GATGTGCTATGGGTGATTGATGTTTTATATTTGATCTGATGCCTGAT";
        String dna4 = "GATGTTTTATATTTGATTTGATGTTTTATATTTGATTTGATGTTTTATATTTGA";
        String dna5 = "GATGTGTACCGATGAGATGGCGGTTTTATATTACGGTGATTGAACTCCGTTAATTATATGCTTGA";

        System.out.println("Execute process gene with dna : \n" + dna1);
        processGenes(getAllGenes(dna1));
        System.out.println("******** \n");

        System.out.println("Execute process gene with dna : \n" + dna2);
        processGenes(getAllGenes(dna2));
        System.out.println("******** \n");

        System.out.println("Execute process gene with dna : \n" + dna3);
        processGenes(getAllGenes(dna3));
        System.out.println("******** \n");

        System.out.println("Execute process gene with dna : \n" + dna4);
        processGenes(getAllGenes(dna4));
        System.out.println("******** \n");

        System.out.println("Execute process gene with dna : \n" + dna5);
        processGenes(getAllGenes(dna5));
        System.out.println("******** \n");*/



        System.out.println("\n*** TEST WITH REAL DNA ***\n**************************\n");
        FileResource fr = new FileResource("Class2/Ressource/brca1line.fa");
        String dna = fr.asString();
        printAllGenes(dna);
        processGenes(getAllGenes(dna));

        printAllGenes(dna);

    }

}
