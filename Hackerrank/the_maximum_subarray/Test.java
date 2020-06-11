package the_maximum_subarray;

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
		String result = sol.solve(readFile("src//the_maximum_subarray//input00.txt")).trim();
		if (result.equals("10 10"))
			System.out.println("OK");
		else 
			System.out.println("TEST 1 WRONG");
	}
	
	public static void test2() {
		Solution sol = new Solution();
		String result = sol.solve(readFile("src//the_maximum_subarray//input01.txt")).trim();
		if (result.equals("10 11"))
			System.out.println("OK");
		else 
			System.out.println("TEST 2 WRONG");
	}
	
	public static void test3() {
		Solution sol = new Solution();
		String result = sol.solve(readFile("src//the_maximum_subarray//input02.txt")).trim();
		if (result.equals("-1 -1"))
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
