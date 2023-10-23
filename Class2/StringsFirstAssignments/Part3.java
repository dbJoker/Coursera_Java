package Class2.StringsFirstAssignments;

public class Part3 {

    public static void runStringFirstAssignmentPart3 (String[] args) {
        Part3 run = new Part3();
        run.testingTwoOccurrences();
        run.testingLastPart();
    }

    public boolean twoOccurrences(String stringa, String stringb) {

        boolean isTwoOccurrences = false;
        int firstOccurenceIndex = stringb.indexOf(stringa);

        if (firstOccurenceIndex != -1) {
            int secondOccurenceIndex = stringb.indexOf(stringa, firstOccurenceIndex + stringa.length());
            if (secondOccurenceIndex != -1) {
                isTwoOccurrences = true;
            }
        }

        return isTwoOccurrences;
    }

    public String lastPart(String stringa, String stringb) {
        String returnString = stringb;

        int indexFound = stringb.indexOf(stringa);
        if (indexFound != -1) {
            returnString = stringb.substring(indexFound + stringa.length());
        }

        return  returnString;
    }

    public void testingTwoOccurrences() {
        boolean hasTwoOccurrences = false;
        String stringa = "", stringb = "";

        stringa = "by";
        stringb = "A story by Abby Long";
        hasTwoOccurrences = twoOccurrences(stringa,stringb);
        System.out.println("Is there atleast two occurences of \"" + stringa + "\" in \"" + stringb + "\" ? " + hasTwoOccurrences);

        stringa = "a";
        stringb = "banana";
        hasTwoOccurrences = twoOccurrences(stringa,stringb);
        System.out.println("Is there atleast two occurences of \"" + stringa + "\" in \"" + stringb + "\" ? " + hasTwoOccurrences);

        stringa = "atg";
        stringb = "ctgtatgta";
        hasTwoOccurrences = twoOccurrences(stringa,stringb);
        System.out.println("Is there atleast two occurences of \"" + stringa + "\" in \"" + stringb + "\" ? " + hasTwoOccurrences);
    }

    public void testingLastPart () {
        String lastPart = "";
        String stringa = "", stringb = "";

        stringa = "an";
        stringb = "banana";
        lastPart = lastPart(stringa,stringb);
        System.out.println("The part of the string after \"" + stringa + "\" in \"" + stringb + "\" is \"" + lastPart + "\"");

        stringa = "zoo";
        stringb = "forest";
        lastPart = lastPart(stringa,stringb);
        System.out.println("The part of the string after \"" + stringa + "\" in \"" + stringb + "\" is \"" + lastPart + "\"");

        stringa = "by";
        stringb = "A story by Abby Long";
        lastPart = lastPart(stringa,stringb);
        System.out.println("The part of the string after \"" + stringa + "\" in \"" + stringb + "\" is \"" + lastPart + "\"");

        stringa = "on";
        stringb = "coshon";
        lastPart = lastPart(stringa,stringb);
        System.out.println("The part of the string after \"" + stringa + "\" in \"" + stringb + "\" is \"" + lastPart + "\"");
    }

}
