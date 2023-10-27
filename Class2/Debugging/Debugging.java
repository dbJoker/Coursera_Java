package Class2.Debugging;

public class Debugging {

    public static void runDebuffing (String[] args) {
        Class2.Debugging.Debugging run = new Class2.Debugging.Debugging();
        run.test();
    }

    public void findAbc(String input){
        int index = input.indexOf("abc");
        while (true){
            if (index == -1 || index >= input.length() - 3){
                break;
            }
            String found = input.substring(index+1, index+4);
            System.out.println("index " + index);
            System.out.println(found);
            index = input.indexOf("abc",index+3);
            System.out.println("index after updating " + index);
        }
    }

    public void test(){
        //findAbc("abcd");
        findAbc("abcabcabcabca");
    }

}
