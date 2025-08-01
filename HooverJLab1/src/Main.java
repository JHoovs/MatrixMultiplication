import java.io.*;
import java.util.*;

/**
 * Originally had matrices.get(0) and matrices.get(1) which was getting reset and
 * throwing an Index Out of Bounds error. It required I iterate over the list and increment
 * Matrix B by 1. 
 * 
 * I referenced Stack Overflow to learn how to monitor runtime in Java.
 * 
 * Reference:
 * https://fusion-reactor.com/blog/how-to-fix-an-array-index-out-of-bounds-exception-in-java/
 * https://www.geeksforgeeks.org/array-index-out-of-bounds-exception-in-java/
 * https://stackoverflow.com/questions/5204051/how-to-calculate-the-running-time-of-my-program
 */

public class Main {
	
	/**
	 * Compiles functions from FileHandler, MatrixMultiplication, and StrassenMatrixMultiplication
	 * to execute the program on an input file and produce results to an output file.
	 * 
	 * @param args is a string from command line for the inputFile and outputFile
	 */
	
	public static void main(String[] args) {
		
		//Command line argument check for correct format with 2 arguments
		if (args.length < 2) {
			System.out.println("Usage: java Main <inputFile> <outputFile>");
			return;
		}
		//Assign input and output
		String inputFile = args[0];
		String outputFile = args[1];
	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
			
			//Read matrix from list matrices
			List<int[][]> matrices = FileHandler.readFromFile(inputFile);
			
			//Get Matrix A and B for pair of 2D arrays in list
			for (int i = 0; i < matrices.size(); i += 2) {
				int[][] A = matrices.get(i);
				int[][] B = matrices.get(i + 1);
				int n = A.length;
				
				//Set comparison counter to 0
				MatrixMultiplication.resetCounter();
				
				//Start runtime
				long startNaive = System.nanoTime();
				
				//Run Naive Matrix Multiplication algorithm on Matrix A and B and create 2D array of result
				int [][] naiveResult = MatrixMultiplication.naiveMultiply(A, B);
				
				//End runtime
				long endNaive = System.nanoTime();
				
				//Calculate runtime
				long naiveTime = endNaive - startNaive;
				
				//Number of comparisons
				int naiveMultiplications = MatrixMultiplication.getCounter();
				
				//Set comparison counter to 0
				StrassenMatrixMultiplication.resetCounter();
				
				//Start runtime
				long startStrassen = System.nanoTime();
				
				//Run Strassen Algorithm on Matrix A and B and create 2D array for result
				int [][] strassenResult = StrassenMatrixMultiplication.multiply(A, B);
				
				//End runtime
				long endStrassen = System.nanoTime();
				
				//Calculate runtime
				long strassenTime = endStrassen - startStrassen;
				
				//Number of comparisons
				int strassenMultiplications = StrassenMatrixMultiplication.getCounter();
				
				//Write results of algorithms to output file
				FileHandler.writeResultsToFile(writer, n, naiveResult, naiveTime, naiveMultiplications, strassenResult, strassenTime, strassenMultiplications);
			}
			
			//Command line statement to show program finished
			System.out.println("Results written to " + outputFile);
		
		//Error handling
		} catch (IOException e) {
			System.err.println("I/O Error: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.err.println("Number format error: " + e.getMessage());
		}
	}	
}