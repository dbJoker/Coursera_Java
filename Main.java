import static Class1.HelloWorld.runHello;
import static Class1.PerimeterAssignmentRunner.runPerimeterAssignmentRunner;
import static Class2.StringsFirstAssignments.Part1.runStringFirstAssignmentPart1;
import static Class2.StringsFirstAssignments.Part2.runStringFirstAssignmentPart2;
import static Class2.StringsFirstAssignments.Part3.runStringFirstAssignmentPart3;
import static Class2.StringsFirstAssignments.Part4.runStringFirstAssignmentPart4;

public class Main {
    public static void main (String[] args) {
        try {

            //runHello(new String[0]);

            //runPerimeterAssignmentRunner(new String[0]);

            //runStringFirstAssignmentPart1(new String[0]);
            //runStringFirstAssignmentPart2(new String[0]);
            //runStringFirstAssignmentPart3(new String[0]);
            runStringFirstAssignmentPart4(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
