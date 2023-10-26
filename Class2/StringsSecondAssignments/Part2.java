package Class2.StringsSecondAssignments;

public class Part2 {

    public static void runStringSecondAssignmentPart2 (String[] args) {
        Class2.StringsSecondAssignments.Part2 run = new Class2.StringsSecondAssignments.Part2();
        run.testHowMany();
    }

    public int howMany(String stringA, String stringB) {
        int stringARecurence = 0;
        int startIndex = 0;

        while (true) {

            int indexFound = stringB.indexOf(stringA, startIndex);

            if(indexFound == -1) {
                break;
            } else {

                stringARecurence++;
                startIndex = indexFound + stringA.length();

            }

        }

        return stringARecurence;
    }

    public void testHowMany() {
        System.out.println("Test : ");

        System.out.println("Expected : 3,  Result : " + howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println("Expected : 2,  Result : " + howMany("AA","ATAAAA"));
        System.out.println("Expected : 0,  Result : " + howMany("BC","ATGAACGAATTGAATC"));


    }

}
