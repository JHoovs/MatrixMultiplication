import java.io.*;
import java.util.*;

/**
 * Refernce on bitwise operations for checking multiple of 2 and general error handling syntax.
 * 
 * References:
 * https://www.geeksforgeeks.org/program-to-find-whether-a-given-number-is-power-of-2/
 * https://www.geeksforgeeks.org/exceptions-in-java/
 */

public class FileHandler {
	
	/**
	 * Passes in an input file and reads Matrix A and Matrix B from the file.
	 * Error checks formatting to ensure matrices are executable.
	 * Returns both matrices as a list.
	 * 
	 * @param filename
	 * @return List matrices containing Matrix A and Matrix B
	 * @throw FileNotFoundException if input file does not exist
	 * @throw IOException if the matrix size is incorrect, contains empty or missing values, or invalid characters.
	 */
	
	public static List<int[][]> readFromFile(String filename) throws IOException {
		List<int[][]> matrices = new ArrayList<>();
		
		File file = new File(filename);
		
		//Check if file exists
		if (!file.exists()) {
			throw new FileNotFoundException("Error: " + filename + "not found.");
		}
		
		//Initialize reader at line 0
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int lineCount = 0;
			
			//Read file until end of file
			while ((line = br.readLine()) != null) {
				line = line.trim();
				lineCount++;
				
				//Skip processing of empty line
				if (line.isEmpty()) continue;
				
				int n;
			
				//Parse line as integers 
				try {
					n = Integer.parseInt(line);
					
				//Check if matrix size is valid integer
				} catch (NumberFormatException e) {
					throw new IOException("Invalid matrix size format on line: " + lineCount + " (" + line + ") ");
				}
				
				//Check if matrix power of 2
				if ((n & (n-1)) != 0) {
					throw new IOException("Error: Matrix must be a power of 2.");
				}
				
				//Print to command line
				System.out.println("Reading Matrix A of size " + n + "x" + n);
				
				//Read matrix to 2D array
				int[][] matrixA = readMatrix(br, n, "Matrix A");
				
				//Print to command line
				System.out.println("Reading Matrix B of size " + n + "x" + n);
				
				//Read matrix to 2D array
				int[][] matrixB = readMatrix(br, n, "Matrix B");
		
				//Add 2D arrays to list
				matrices.add(matrixA);
				matrices.add(matrixB);
			}
		}
		return matrices;
	}
	
	/**
	 * Read matrix from BufferedReader into 2D array.
	 * Handle errors in matrix or file formatting.
	 * 
	 * @param BufferedReader reads matrix data from input file
	 * @param n is the size of the matrix
	 * @param matrixName is either Matrix A or Matrix B
	 * 
	 * @return 2D array of matrix
	 * @param IOException for missing lines, incorrect row lengths, or invalid characters.
	 */
	
	private static int[][] readMatrix(BufferedReader br, int n, String matrixName) throws IOException {
		
		//Store n x n matrix in 2D array
		int[][] matrix = new int[n][n];
		
		//Loop through rows of matrix
		for (int i = 0; i < n; i++) {
			String line;
			
			//Read lines and skip empty lines
			do {
				line = br.readLine();
			} while (line != null && line.trim().isEmpty());
			
			//Check if missing rows
			if (line == null || line.trim().isEmpty()) {
				throw new IOException(matrixName + " is incomplete or contains empty lines.");
			}
			
			//Split values in array
			String[] values = line.split("\\s+");
			
			//Ensure each row is correct length
			if (values.length != n) {
				throw new IOException(matrixName + " row " + (i + 1) + " is incorrect. Expect " + n + " elements, found: " + values.length);
			}
			
			//Loop to ensure valid integers in matrix
			for (int j = 0; j < n; j++) {
				try {
					matrix[i][j] = Integer.parseInt(values[j]);
				} catch (NumberFormatException e) {
					throw new IOException("Error: Invalid number format in " + matrixName + " at row " + (i + 1) + ", column " + (j+1) + " ('" + values[j] + "') .");
				}
			}
		}
		
		return matrix;
	}
	
	/**
	 * Writes matrix product results, runtime, and number of comparisons to an output file.
	 * 
	 * @param BufferedWriter writes to output file
	 * @param n is the size of the Matrix
	 * @param naiveResult is the matrix product of Naive Matrix Multiplication
	 * @param naiveTime is the runtime of Naive Matrix Multiplication
	 * @param naiveMultiplications is the number of comparisons made by Naive Matrix Multiplication
	 * @param strassenResult is the matrix product of Strassen Matrix Multiplication
	 * @param strassenTime is the runtime of Strassen Matrix Multiplication
	 * @param strassenMultiplications is the number of comparisons made by Strassen Matrix Multiplication
	 */
	
	public static void writeResultsToFile(BufferedWriter writer, int n, int[][] naiveResult, long naiveTime, long naiveMultiplications, int [][] strassenResult, long strassenTime, long strassenMultiplications) throws IOException {

		writer.write("--Matrix Size: " + n + " x " + n + "--\n\n");
		
		//Naive Matrix Multiplication Results
		writer.write("Naive Matrix Multiplication:\n");
		writer.write("Time: " + naiveTime + "ns\n");
		writer.write("Comparisons: " + naiveMultiplications + "\n");
		writer.write("Result:\n");
		writeMatrix(writer, naiveResult);
		
		//Strassen Matrix Multiplication Results
		writer.write("Strassen Matrix Multiplication:\n");
		writer.write("Time: " + strassenTime + "ns\n");
		writer.write("Comparisons: " + strassenMultiplications + "\n");
		writer.write("Result:\n");
		writeMatrix(writer, strassenResult);
		
		writer.write("\n\n");
	}
	
	/**
	 * Writes matrix product from 2D array into output file.
	 * 
	 * @param BufferedWriter writes matrix
	 * @param Matrix is the result of matrix multiplication (C)
	 */
	
	private static void writeMatrix(BufferedWriter writer, int[][] matrix) throws IOException {
		//Loop through rows of matrix
		for (int[] row : matrix) {
			
			//Loop through values of row
			for (int value : row) {
				
				//Write value with a space
				writer.write(value + " ");
			}
			writer.write("\n");
		}
	}
}