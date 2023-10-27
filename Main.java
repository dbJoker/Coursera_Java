import static Class1.HelloWorld.runHello;
import static Class1.PerimeterAssignmentRunner.runPerimeterAssignmentRunner;
import static Class2.Debugging.Debugging.runDebuffing;
import static Class2.StringsFirstAssignments.Part1.runStringFirstAssignmentPart1;
import static Class2.StringsFirstAssignments.Part2.runStringFirstAssignmentPart2;
import static Class2.StringsFirstAssignments.Part3.runStringFirstAssignmentPart3;
import static Class2.StringsFirstAssignments.Part4.runStringFirstAssignmentPart4;
import static Class2.StringsSecondAssignments.Part1.runStringSecondAssignmentPart1;
import static Class2.StringsSecondAssignments.Part2.runStringSecondAssignmentPart2;
import static Class2.StringsSecondAssignments.Part3.runStringSecondAssignmentPart3;
import static Class2.StringsThirdAssignments.Part1.runStringsThirdAssignmentsPart1;

public class Main {
    public static void main (String[] args) {
        try {

            //*****Class1*****
            //runHello(new String[0]);

            //runPerimeterAssignmentRunner(new String[0]);

            //*****Class2*****
            //runStringFirstAssignmentPart1(new String[0]);
            //runStringFirstAssignmentPart2(new String[0]);
            //runStringFirstAssignmentPart3(new String[0]);
            //runStringFirstAssignmentPart4(new String[0]);

            //runStringSecondAssignmentPart1(new String[0]);
            //runStringSecondAssignmentPart2(new String[0]);
            //runStringSecondAssignmentPart3(new String[0]);

            //runDebuffing(new String[0]);

            runStringsThirdAssignmentsPart1(new String[0]);


            //*****Class3*****

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
