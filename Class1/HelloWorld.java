package Class1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelloWorld {
	public static void runHello (String args[]) throws FileNotFoundException {
		File file = new File("hello_unicode.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine())
			System.out.println(sc.nextLine());

		while (sc.hasNextLine())
			System.out.println(sc.nextLine());

	}
}
