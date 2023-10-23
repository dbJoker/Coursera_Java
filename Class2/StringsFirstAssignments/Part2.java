package Class2.StringsFirstAssignments;

public class Part2 {

    public static void runStringFirstAssignmentPart2 (String[] args) {
        Part2 run = new Part2();
        run.testSimpleGene();
    }

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String geneFound = "";

        int startCodonIndex = dna.toUpperCase().indexOf(startCodon.toUpperCase());
        if (startCodonIndex == -1) {
            return "";
        }
        int stopCodonIndex = dna.toUpperCase().indexOf(stopCodon.toUpperCase(), startCodonIndex + 3);
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
        String dnaWithUpperCase = "ATGGGTTAAGTC";
        String dnaWithLowerCase = "gatgctataat";

        System.out.println("l'adn est : " + dnaWithNoStart);
        gene = findSimpleGene(dnaWithNoStart, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoStop);
        gene = findSimpleGene(dnaWithNoStop, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoStartAndStop);
        gene = findSimpleGene(dnaWithNoStartAndStop, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithGeneOf3);
        gene = findSimpleGene(dnaWithGeneOf3, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithNoGeneOf3);
        gene = findSimpleGene(dnaWithNoGeneOf3, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithUpperCase);
        gene = findSimpleGene(dnaWithUpperCase, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

        System.out.println("l'adn est : " + dnaWithLowerCase);
        gene = findSimpleGene(dnaWithLowerCase, "ATG", "TAA");
        System.out.println("Le Gene est : " + gene);

    }

}
