package Class2.StringsFirstAssignments;


public class Part1 {

    public static void runStringFirstAssignmentPart1 (String[] args) {
        Part1 run = new Part1();
        run.testSimpleGene();
    }

    public String findSimpleGene(String dna) {
        String geneFound = "";

        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }
        int stopCodonIndex = dna.indexOf("TAA", startCodonIndex + 3);
        if (stopCodonIndex == -1) {
            return "";
        }
        if ((stopCodonIndex - startCodonIndex) % 3 != 0) {
            return "";
        }

        geneFound = dna.substring(startCodonIndex, stopCodonIndex + 3);

        return geneFound;
    }

    public void testSimpleGene() {

        String gene = "";
        String dnaWithNoStart = "GATTTGAATAAGAT";
        String dnaWithNoStop = "AGATGTGAATTAGAT";
        String dnaWithNoStartAndStop = "GATTTGAATTAGATTGGAT";
        String dnaWithGeneOf3 = "GATATGAATGAGATTGGATTAGGATAAGGGAT";
        String dnaWithNoGeneOf3 = "GATATGAATGAGATTGGATTAGTAAGGGAT";

        System.out.println("l'adn est : " + dnaWithNoStart);
        gene = findSimpleGene(dnaWithNoStart);
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoStop);
        gene = findSimpleGene(dnaWithNoStop);
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoStartAndStop);
        gene = findSimpleGene(dnaWithNoStartAndStop);
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithGeneOf3);
        gene = findSimpleGene(dnaWithGeneOf3);
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoGeneOf3);
        gene = findSimpleGene(dnaWithNoGeneOf3);
        System.out.println("Le Gene est : " + gene);

    }

}
