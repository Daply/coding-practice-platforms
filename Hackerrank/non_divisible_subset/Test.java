package non_divisible_subset;

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
		int result = sol.solve(readFile("src//non_divisible_subset//input00.txt"));
		if (result == 3)
			System.out.println("OK");
		else 
			System.out.println("TEST 1 WRONG");
	}
	
	public static void test2() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//non_divisible_subset//input01.txt"));
		if (result == 3)
			System.out.println("OK");
		else 
			System.out.println("TEST 2 WRONG");
	}
	
	public static void test3() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//non_divisible_subset//input16.txt"));
		if (result == 11)
			System.out.println("OK");
		else 
			System.out.println("TEST 3 WRONG");
	}
	
	public static void test4() {
		Solution sol = new Solution();
		int result = sol.solve(readFile("src//non_divisible_subset//input02.txt"));
		if (result == 50)
			System.out.println("OK");
		else 
			System.out.println("TEST 4 WRONG");
	}
	
	public static void main(String[] args) throws IOException {
		test1();
		test2();
		test3();
		test4();
	}
}
