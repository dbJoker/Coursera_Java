package Class2.StringsFirstAssignments;

import edu.duke.*;

public class Part4 {


    public static void runStringFirstAssignmentPart4 (String[] args) {
        Part4 run = new Part4();
        run.findSimpleGene();
    }

    public void findSimpleGene() {

        URLResource webPage = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");


        System.out.println("Youtube links : ");
        for (String word : webPage.words()) {
            int startIndex = -1, endIndex = -1;
            //System.out.println(word);
            int indexFound = word.toLowerCase().indexOf("youtube.com");
            if (indexFound != -1) {
                startIndex = word.lastIndexOf("\"", indexFound);
                endIndex = word.indexOf("\"", indexFound);
            }
            if(startIndex != -1 && endIndex != -1) {
                String youtubeLink = word.substring(startIndex + 1, endIndex);
                System.out.println(youtubeLink);
            }
        }

    }

    public void testSimpleGene() {


    }

}
