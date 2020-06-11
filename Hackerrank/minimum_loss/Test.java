package minimum_loss;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public static ArrayList<String> readFile(String fileName) {
		ArrayList<String> readLines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = new String();
			while ((line = reader.readLine()) != null) {
				readLines.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readLines;
	}
	
	public static void test1() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//minimum_loss//input00.txt"));
		if (result == 2)
			System.out.println("OK");
		else 
			System.out.println("TEST 1 WRONG");
	}
	
	public static void test2() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//minimum_loss//input01.txt"));
		if (result == 1)
			System.out.println("OK");
		else 
			System.out.println("TEST 2 WRONG");
	}
	
	public static void test3() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//minimum_loss//input02.txt"));
		if (result == 2)
			System.out.println("OK");
		else 
			System.out.println("TEST 2 WRONG");
	}
	
	public static void main(String[] args) throws IOException {
		test1();
		test2();
		test3();
	}
}
